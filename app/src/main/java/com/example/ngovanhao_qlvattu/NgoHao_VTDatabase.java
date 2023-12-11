package com.example.ngovanhao_qlvattu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class NgoHao_VTDatabase extends SQLiteOpenHelper {

  public static final String DATABASE_NAME = "QLVT.db";

  private static final String TABLE_NAME = "NgoHaoVatTu";
  private static final String ID = "MaVatTu";
  private static final String NAME = "TenVatTu";
  private static final String NHASANXUAT = "NhaSanXuat";
  private static final String NAMSANXUAT = "NamSanXuat";
  private static final String SOLUONG = "SoLuong";
  private static final String DONGIA = "DonGia";
  private int version = 1;
  private Context context;

  public NgoHao_VTDatabase(Context context) {
    super(context, TABLE_NAME, null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String sql = "CREATE TABLE " + TABLE_NAME + " ( " +
        ID + " Varchar(250) primary key, " +
        NAME + " Varchar(250), " +
        NHASANXUAT + " Varchar(250), " +
        NAMSANXUAT + " Varchar(250), " +
        SOLUONG + " Varchar(250), " +
        DONGIA + " Varchar (250))";
    //tạo bảng
    sqLiteDatabase.execSQL(sql);
    String vt1 = "INSERT INTO " + TABLE_NAME + " VALUES ('VT01', 'Sắt', 'Hoà Phát', 2023, 10, 1000)";
    sqLiteDatabase.execSQL(vt1);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(sqLiteDatabase);
  }

  //Thêm mới 1 sv vào CSDL
  public void ThemVT(NgoHao_VatTu vt) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(ID, vt.getMaVatTu());
    values.put(NAME, vt.getTenVatTu());
    values.put(NHASANXUAT, vt.getNhaSanXuat());
    values.put(NAMSANXUAT, vt.getNamSanXuat());
    values.put(SOLUONG, vt.getSoLuong());
    values.put(DONGIA, vt.getDonGia());
    //đẩy values vào bảng CSDL
    db.insert(TABLE_NAME, null, values);
    db.close();
  }

  //Cap Nhat sv theo ma sv
  public int CapnhatVT(NgoHao_VatTu vt) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(NAME, vt.getTenVatTu());
    values.put(NHASANXUAT, vt.getNhaSanXuat());
    values.put(NAMSANXUAT, vt.getNamSanXuat());
    values.put(SOLUONG, vt.getSoLuong());
    values.put(DONGIA, vt.getDonGia());
    int a = db.update(TABLE_NAME, values,
        ID + "=?", new String[]{vt.getMaVatTu()});
    db.close();
    return a;
  }

  //Xoá sv trong CSDL
  public int XoaVT(NgoHao_VatTu vt) {
    SQLiteDatabase db = this.getWritableDatabase();
    int b = db.delete(TABLE_NAME, ID + "=?", new String[]{vt.getMaVatTu()});
    db.close();
    return b;
  }

  //Lấy thông tin sv trong CSDL
  public List<NgoHao_VatTu> ThongTinVT() {
    List<NgoHao_VatTu> listVt = new ArrayList<NgoHao_VatTu>();
    SQLiteDatabase db = this.getWritableDatabase();
    String sql2 = "SELECT * FROM " + TABLE_NAME;
    //CON TRỎ TRUY VẤN CURSOR
    Cursor cursor = db.rawQuery(sql2, null);
    //đưa con trỏ cursor về hàng đầu tiên
    cursor.moveToFirst();
    //kiểm tra đk
    while (!cursor.isAfterLast()) {
      String mavt = cursor.getString(0);
      String tenvt = cursor.getString(1);
      String nhasx = cursor.getString(2);
      String namsx = cursor.getString(3);
      String soluong = cursor.getString(4);
      String dongia = cursor.getString(5);
      NgoHao_VatTu vt = new NgoHao_VatTu(mavt, tenvt, nhasx, namsx, soluong, dongia);
      listVt.add(vt);
      cursor.moveToNext();
    }
    return listVt;
  }
}

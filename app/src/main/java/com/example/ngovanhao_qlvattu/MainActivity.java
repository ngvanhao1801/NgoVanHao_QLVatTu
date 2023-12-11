package com.example.ngovanhao_qlvattu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  private EditText edtMaVt, edtTenVt, edtNhaSx, edtNamSx, edtSoLuong, edtDonGia;

  private Button btnThem, btnSua, btnXoa, btnThoat;

  private ListView lvvt;

  private NgoHao_VTDatabase database;

  private NgoHao_VTAdapter adapter;

  private List<NgoHao_VatTu> listVt;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //Ánh xạ
    anhxa();
    //khởi tạo
    database = new NgoHao_VTDatabase(MainActivity.this);
    listVt = database.ThongTinVT();
    setAdapter();

    //bắt sự kiện cho các button
    btnThoat.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Xác nhận đóng chương trình");
        builder.setMessage("Bạn có muốn thoát khỏi chương trình không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            finish();
          }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
          }
        });
        //Hiển thị hội thoại
        AlertDialog dialog = builder.create();
        dialog.show();
      }
    });

    //bắt sự kiện thêm
    btnThem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String mavt = edtMaVt.getText().toString();
        String tenvt = edtTenVt.getText().toString();
        String nhasx = edtNhaSx.getText().toString();
        String namsx = edtNamSx.getText().toString();
        String soluong = edtSoLuong.getText().toString();
        String dongia = edtDonGia.getText().toString();
        NgoHao_VatTu vt = new NgoHao_VatTu(mavt, tenvt, nhasx, namsx, soluong, dongia);
        database.ThemVT(vt);
        listVt.clear();
        listVt.addAll(database.ThongTinVT());
        setAdapter();
      }
    });

    // bắt sự kiện cho việc chọn từng item trong listview
    lvvt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        NgoHao_VatTu sv = listVt.get(i);
        // đưa các thông tin của sv thứ i lên edittext
        edtMaVt.setText(sv.getMaVatTu());
        edtTenVt.setText(sv.getTenVatTu());
        edtNhaSx.setText(sv.getNhaSanXuat());
        edtNamSx.setText(sv.getNamSanXuat());
        edtSoLuong.setText(sv.getSoLuong());
        edtDonGia.setText(sv.getDonGia());
        // ẩn button Thêm
        btnThem.setEnabled(false);
        edtMaVt.setEnabled(false);
      }
    });

    // bắt sự kiện cho btnSua
    btnSua.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        NgoHao_VatTu vt = new NgoHao_VatTu();
        vt.setMaVatTu(String.valueOf(edtMaVt.getText()));
        vt.setTenVatTu(edtTenVt.getText() + "");
        vt.setNhaSanXuat(edtNhaSx.getText() + "");
        vt.setNamSanXuat(edtNamSx.getText() + "");
        vt.setSoLuong(edtSoLuong.getText() + "");
        vt.setDonGia(edtDonGia.getText() + "");
        int kq = database.CapnhatVT(vt);
        if (kq > 0) {
          Toast.makeText(MainActivity.this, "Cập nhật vật tư thành công",
              Toast.LENGTH_LONG).show();
          setAdapter();
          updateListSV();
        } else {
          Toast.makeText(MainActivity.this, "Cập nhật vật tư thất bại",
              Toast.LENGTH_LONG).show();
        }
        btnThem.setEnabled(true);
        edtMaVt.setEnabled(true);
      }
    });

    // bắt sự kiện cho btnXoa
    btnXoa.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        NgoHao_VatTu vt = new NgoHao_VatTu();
        vt.setMaVatTu(String.valueOf(edtMaVt.getText()));
        vt.setTenVatTu(edtTenVt.getText() + "");
        vt.setNhaSanXuat(edtNhaSx.getText() + "");
        vt.setNamSanXuat(edtNamSx.getText() + "");
        vt.setSoLuong(edtSoLuong.getText() + "");
        vt.setDonGia(edtDonGia.getText() + "");

        int kqxoa = database.XoaVT(vt);
        if (kqxoa > 0) {
          Toast.makeText(MainActivity.this, "Xóa vật tư thành công.",
              Toast.LENGTH_LONG).show();
          setAdapter();
          updateListSV();
        } else {
          Toast.makeText(MainActivity.this, "Xóa vật tư thất bại.",
              Toast.LENGTH_LONG).show();
        }
        btnThem.setEnabled(true);
      }
    });
  }


  private void updateListSV() {
    listVt.clear();
    listVt.addAll(database.ThongTinVT());
//        if(adapter!=null){
//            adapter.notifyDataSetChanged();
//        }
  }

  private void setAdapter() {
    if (adapter == null) {
      adapter = new NgoHao_VTAdapter(MainActivity.this, R.layout.item_listvt, listVt);
      lvvt.setAdapter(adapter);
    } else {
      // cập nhật lại DL
      adapter.notifyDataSetChanged();
    }
    edtMaVt.setText("");
    edtTenVt.setText("");
    edtNhaSx.setText("");
    edtNamSx.setText("");
    edtSoLuong.setText("");
    edtDonGia.setText("");
  }

  private void anhxa() {
    edtMaVt = findViewById(R.id.edt_mavt);
    edtTenVt = findViewById(R.id.edt_tenvt);
    edtNhaSx = findViewById(R.id.edt_nhasx);
    edtNamSx = findViewById(R.id.edt_namsx);
    edtSoLuong = findViewById(R.id.edt_soluong);
    edtDonGia = findViewById(R.id.edt_dongia);

    btnThem = findViewById(R.id.btn_add);
    btnSua = findViewById(R.id.btn_edit);
    btnXoa = findViewById(R.id.btn_delete);
    btnThoat = findViewById(R.id.btn_cancel);
    lvvt = findViewById(R.id.lv_vt);
  }

}
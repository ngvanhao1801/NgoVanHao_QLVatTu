package com.example.ngovanhao_qlvattu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NgoHao_VTAdapter extends ArrayAdapter<NgoHao_VatTu> {

  private Context context;

  private int resource;

  private List<NgoHao_VatTu> listVt;

  public NgoHao_VTAdapter(Context context, int resource, List<NgoHao_VatTu> objects) {
    super(context, resource, objects);
    this.context = context;
    this.resource = resource;
    this.listVt = objects;
  }

  class ViewHolder {
    private TextView tvmavt, tvtenvt, tvnhasx, tvnamsx, tvsoluong, tvdongia;

  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    ViewHolder viewHolder;
    //xét TH convertView rỗng
    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.item_listvt, parent, false);
      viewHolder = new ViewHolder();
      //ánh xạ
      viewHolder.tvmavt = convertView.findViewById(R.id.tv_mavt);
      viewHolder.tvtenvt = convertView.findViewById(R.id.tv_tenvt);
      viewHolder.tvnhasx = convertView.findViewById(R.id.tv_nhasx);
      viewHolder.tvnamsx = convertView.findViewById(R.id.tv_namsx);
      viewHolder.tvsoluong = convertView.findViewById(R.id.tv_soluong);
      viewHolder.tvdongia = convertView.findViewById(R.id.tv_dongia);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }
    //Thiết lập giá trị
    NgoHao_VatTu vt = listVt.get(position);
    viewHolder.tvmavt.setText(vt.getMaVatTu());
    viewHolder.tvtenvt.setText(vt.getTenVatTu());
    viewHolder.tvnhasx.setText(vt.getNhaSanXuat());
    viewHolder.tvnamsx.setText(vt.getNamSanXuat());
    viewHolder.tvsoluong.setText(vt.getSoLuong());
    viewHolder.tvdongia.setText(vt.getDonGia());

    return convertView;
  }

}

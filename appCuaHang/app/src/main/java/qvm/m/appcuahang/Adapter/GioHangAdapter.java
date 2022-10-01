package qvm.m.appcuahang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import qvm.m.appcuahang.Activity.GioHangActivity;
import qvm.m.appcuahang.Activity.MainActivity;
import qvm.m.appcuahang.Model.GioHang;
import qvm.m.appcuahang.R;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arrayGioHang;

    public GioHangAdapter(Context context, ArrayList<GioHang> arrayGioHang) {
        this.context = context;
        this.arrayGioHang = arrayGioHang;
    }

    @Override
    public int getCount() {
        return arrayGioHang.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayGioHang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txtTenSpGh, txtGiaSpGh;
        public ImageView imgAnhSpGh;
        public Button btnMinusGh, btnPlusGh, btnValuesGh;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_gio_hang,null);
                viewHolder.txtTenSpGh = view.findViewById(R.id.textViewTenSpGioHang);
                viewHolder.txtGiaSpGh = view.findViewById(R.id.textViewGiaSpGioHang);
                viewHolder.imgAnhSpGh = view.findViewById(R.id.imageViewAnhSpGioHang);
                viewHolder.btnMinusGh = view.findViewById(R.id.buttonMinusGh);
                viewHolder.btnPlusGh = view.findViewById(R.id.buttonPlusGh);
                viewHolder.btnValuesGh = view.findViewById(R.id.buttonValuesGh);
                view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        GioHang gioHang = (GioHang) getItem(i);
        viewHolder.txtTenSpGh.setText(gioHang.getTenSp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaSpGh.setText("Giá : " + decimalFormat.format(gioHang.getGiaSp()) + "USD");
        Glide.with(context).load(gioHang.getHinhSp())
                .placeholder(R.drawable.icon_noimage)
                .error(R.drawable.icon_error)
                .into(viewHolder.imgAnhSpGh);
        viewHolder.btnValuesGh.setText(gioHang.getSoLuongSp() + "");
        int sl = Integer.parseInt(viewHolder.btnValuesGh.getText().toString());
        if(sl >= 10){
            viewHolder.btnPlusGh.setVisibility(View.INVISIBLE);
            viewHolder.btnValuesGh.setVisibility(View.VISIBLE);
        }else if(sl <= 1){
            viewHolder.btnMinusGh.setVisibility(View.INVISIBLE);
        }else if(sl >= 1){
            viewHolder.btnMinusGh.setVisibility(View.VISIBLE);
            viewHolder.btnPlusGh.setVisibility(View.VISIBLE);
        }

        ViewHolder finalViewHolder = viewHolder;
        finalViewHolder.btnPlusGh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slMoiNhat = Integer.parseInt(finalViewHolder.btnValuesGh.getText().toString()) + 1;
                int slHienTai = MainActivity.gioHangArray.get(i).getSoLuongSp();
                long giahienTai = MainActivity.gioHangArray.get(i).getGiaSp();
                MainActivity.gioHangArray.get(i).setSoLuongSp(slMoiNhat);
                long giaMoiNhat = (giahienTai * slMoiNhat) / slHienTai;
                MainActivity.gioHangArray.get(i).setGiaSp(giaMoiNhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiaSpGh.setText("Giá : " + decimalFormat.format(giaMoiNhat) + "USD");
                GioHangActivity.EventUltil();
                if(slMoiNhat > 9){
                    finalViewHolder.btnPlusGh.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnMinusGh.setVisibility(View.VISIBLE);
                    finalViewHolder.btnValuesGh.setText(String.valueOf(slMoiNhat));
                }else {
                    finalViewHolder.btnPlusGh.setVisibility(View.VISIBLE);
                    finalViewHolder.btnMinusGh.setVisibility(View.VISIBLE);
                    finalViewHolder.btnValuesGh.setText(String.valueOf(slMoiNhat));
                }
            }
        });
        finalViewHolder.btnMinusGh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slMoiNhat = Integer.parseInt(finalViewHolder.btnValuesGh.getText().toString()) - 1;
                int slHienTai = MainActivity.gioHangArray.get(i).getSoLuongSp();
                long giahienTai = MainActivity.gioHangArray.get(i).getGiaSp();
                MainActivity.gioHangArray.get(i).setSoLuongSp(slMoiNhat);
                long giaMoiNhat = (giahienTai * slMoiNhat) / slHienTai;
                MainActivity.gioHangArray.get(i).setGiaSp(giaMoiNhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiaSpGh.setText("Giá : " + decimalFormat.format(giaMoiNhat) + "USD");
                GioHangActivity.EventUltil();
                if(slMoiNhat < 2){
                    finalViewHolder.btnPlusGh.setVisibility(View.VISIBLE);
                    finalViewHolder.btnMinusGh.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnValuesGh.setText(String.valueOf(slMoiNhat));
                }else {
                    finalViewHolder.btnPlusGh.setVisibility(View.VISIBLE);
                    finalViewHolder.btnMinusGh.setVisibility(View.VISIBLE);
                    finalViewHolder.btnValuesGh.setText(String.valueOf(slMoiNhat));
                }
            }
        });
        return view;
    }
}

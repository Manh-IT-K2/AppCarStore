package qvm.m.appcuahang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import qvm.m.appcuahang.Model.LoaiSp;
import qvm.m.appcuahang.R;

public class loaiSpAdapter extends BaseAdapter {
    ArrayList<LoaiSp> arrayListLoaiSp;
    Context context;

    public loaiSpAdapter(ArrayList<LoaiSp> arrayListLoaiSp, Context context) {
        this.arrayListLoaiSp = arrayListLoaiSp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLoaiSp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListLoaiSp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txt_tenLoaiSp;
        ImageView img_loaisp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater  inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.txt_tenLoaiSp = (TextView) view.findViewById(R.id.textViewLoaiSp);
            viewHolder.img_loaisp = (ImageView) view.findViewById(R.id.imageViewLoaiSp);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        LoaiSp loaiSp = (LoaiSp) getItem(i);
        viewHolder.txt_tenLoaiSp.setText(loaiSp.getTenLoaiSp());
        Glide.with(context).load(loaiSp.getHinhAnhSp())
                .placeholder(R.drawable.icon_noimage)
                .placeholder(R.drawable.icon_error)
                .into(viewHolder.img_loaisp);
        return view;
    }
}

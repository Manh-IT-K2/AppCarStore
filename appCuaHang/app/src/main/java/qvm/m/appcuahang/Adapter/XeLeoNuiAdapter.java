package qvm.m.appcuahang.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import qvm.m.appcuahang.Model.SanPham;
import qvm.m.appcuahang.R;

public class XeLeoNuiAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> arrayXeLeoNui;

    public XeLeoNuiAdapter(Context context, ArrayList<SanPham> arrayXeLeoNui) {
        this.context = context;
        this.arrayXeLeoNui = arrayXeLeoNui;
    }

    @Override
    public int getCount() {
        return arrayXeLeoNui.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayXeLeoNui.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder{
        public TextView textViewTenXLN, textViewGiaXLN, textViewMotaXLN;
        public ImageView imageViewXLn;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_xe_leo_nui,null);
            viewHolder.textViewTenXLN = (TextView) view.findViewById(R.id.textViewTenXeLeoNui);
            viewHolder.textViewGiaXLN= (TextView) view.findViewById(R.id.textViewGiaXeLeoNui);
            viewHolder.textViewMotaXLN = (TextView) view.findViewById(R.id.textViewMotaXeLeoNui);
            viewHolder.imageViewXLn = (ImageView) view.findViewById(R.id.imageViewXeLeoNui);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        SanPham sanPham = (SanPham) getItem(i);
        viewHolder.textViewTenXLN.setText(sanPham.getTenSp());
        DecimalFormat df = new DecimalFormat("###,###,###");
        viewHolder.textViewGiaXLN.setText("Giá : " + df.format(sanPham.getGiaSp()) + "USD");
        viewHolder.textViewMotaXLN.setMaxLines(2);
        viewHolder.textViewMotaXLN.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.textViewMotaXLN.setText(sanPham.getMoTaSp());
        Glide.with(context).load(sanPham.getHinhAnhSp())
                .placeholder(R.drawable.icon_noimage)
                .error(R.drawable.icon_error)
                .into(viewHolder.imageViewXLn);
        return view;
    }
}

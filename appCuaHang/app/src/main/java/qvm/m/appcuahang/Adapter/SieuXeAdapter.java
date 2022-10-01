package qvm.m.appcuahang.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import qvm.m.appcuahang.Model.SanPham;
import qvm.m.appcuahang.R;

public class SieuXeAdapter extends RecyclerView.Adapter<SieuXeAdapter.MyViewHolder> {

    Context context;
    List<SanPham> array;

    public SieuXeAdapter(Context context, List<SanPham> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sieu_xe,parent,false);
        Log.d("qvmanh",array.get(1) + "");
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPham sanPham = array.get(position);
        holder.txtTenSx.setText(sanPham.getTenSp() + "");
        Log.d("qmanh", sanPham.getIdSanPham() + "");
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.txtGiaSx.setText("Giá : " + sanPham.getGiaSp() + "USD");
        Log.d("qmanh", sanPham.getGiaSp()+ "");
        holder.txtMotaSx.setMaxLines(2);
        holder.txtMotaSx.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtMotaSx.setText(sanPham.getMoTaSp());
        Log.d("qmanh", sanPham.getMoTaSp() + "");
        Glide.with(context).load(sanPham.getHinhAnhSp()).into(holder.imgAnh);
        Log.d("qmanhh", sanPham.getHinhAnhSp() + "");
    }

    @Override
    public int getItemCount() {
        Log.d("manh", array.size() + "");
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTenSx, txtGiaSx, txtMotaSx;
        public ImageView imgAnh;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSx = (TextView) itemView.findViewById(R.id.txt_tenSX);
            txtGiaSx = (TextView) itemView.findViewById(R.id.txt_giaSX);
            txtMotaSx = (TextView) itemView.findViewById(R.id.txt_motaSX);
            imgAnh = (ImageView) itemView.findViewById(R.id.imageViewSieuXe);
        }
    }
//    Context context;
//    ArrayList<SanPham> arraySieuXe;
//
//    public SieuXeAdapter(Context context, ArrayList<SanPham> arraySieuXe) {
//        this.context = context;
//        this.arraySieuXe = arraySieuXe;
//    }
//
//    @Override
//    public int getCount() {
//        return arraySieuXe.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return arraySieuXe.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    class ViewHolder{
//        public TextView txt_tenSX, txt_giaSX, txt_motaSx;
//        public ImageView img_SX;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder = null;
//        if(view == null){
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.dong_sieu_xe,null);
//            viewHolder.txt_tenSX = (TextView) view.findViewById(R.id.txt_tenSp);
//            viewHolder.txt_giaSX = (TextView) view.findViewById(R.id.txt_giaSp);
//            viewHolder.txt_motaSx = (TextView) view.findViewById(R.id.txt_motaSX);
//            viewHolder.img_SX = (ImageView) view.findViewById(R.id.imageViewSieuXe);
//            view.setTag(viewHolder);
//        }else{
//            viewHolder = (ViewHolder) view.getTag();
//        }
//        SanPham sanPham = (SanPham) getItem(i);
//        viewHolder.txt_tenSX.setText(sanPham.getTenSp());
//        DecimalFormat df = new DecimalFormat("###,###,###");
//        viewHolder.txt_giaSX.setText("Giá : " + df.format(sanPham.getGiaSp()) + "USD");
//        viewHolder.txt_motaSx.setMaxLines(2);
//        viewHolder.txt_motaSx.setEllipsize(TextUtils.TruncateAt.END);
//        viewHolder.txt_motaSx.setText(sanPham.getMoTaSp());
//        Glide.with(context).load(sanPham.getHinhAnhSp())
//                .placeholder(R.drawable.icon_noimage)
//                .error(R.drawable.icon_error)
//                .into(viewHolder.img_SX);
//        return view;
//    }

}

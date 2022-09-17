package qvm.m.appcuahang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import qvm.m.appcuahang.Model.SanPham;
import qvm.m.appcuahang.R;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemHolder> {
    Context context;
    ArrayList<SanPham> arraySanPham;

    public SanPhamAdapter(Context context, ArrayList<SanPham> arraySanPham) {
        this.context = context;
        this.arraySanPham = arraySanPham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
    // get set thuộc tính
        SanPham sanPham = arraySanPham.get(position);
        holder.txtTenSp.setText(sanPham.getTenSp());
        DecimalFormat df =new DecimalFormat("###,###,###");
        holder.txtGiaSp.setText("Giá : " + df.format(sanPham.getGiaSp()) + "USD");
        Glide.with(context).load(sanPham.getHinhAnhSp())
                .placeholder(R.drawable.icon_noimage)
                .error(R.drawable.icon_error)
                .into(holder.imgHinhSp);

    }

    @Override
    public int getItemCount() {
        return arraySanPham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgHinhSp;
        public TextView txtTenSp,txtGiaSp;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhSp = (ImageView) itemView.findViewById(R.id.imageViewSanPham);
            txtTenSp = (TextView) itemView.findViewById(R.id.txt_tenSp);
            txtGiaSp = (TextView) itemView.findViewById(R.id.txt_giaSp);
        }
    }
}

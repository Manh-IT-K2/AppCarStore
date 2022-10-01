package qvm.m.appcuahang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

import qvm.m.appcuahang.Model.GioHang;
import qvm.m.appcuahang.Model.SanPham;
import qvm.m.appcuahang.R;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    Toolbar toolbarChiTiet;
    ImageView imgChiTiet;
    TextView txtTenChiTiet,txtGiaChiTiet,txtMotaChiTiet;
    Spinner spinner;
    ImageButton btnMua;

    int id = 0;
    String TenChiTiet = "";
    int GiaChiTiet = 0;
    String HinhAnhChiTiet = "";
    String MoTaChiTiet = "";
    int IdSanPham = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        unitView();
        ActionToolbar();
        GetInformation();
        CatchEventSpinner();
        EventButton();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giohang,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuGioHang:
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.gioHangArray.size() > 0){
                    int sl =  Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for(int i=0; i<MainActivity.gioHangArray.size(); i++){
                        if(MainActivity.gioHangArray.get(i).getIdSp() == id){
                            MainActivity.gioHangArray.get(i).setSoLuongSp(MainActivity.gioHangArray.get(i).getSoLuongSp() + sl);
                            if(MainActivity.gioHangArray.get(i).getSoLuongSp() >= 10){
                                MainActivity.gioHangArray.get(i).setSoLuongSp(10);
                            }
                            MainActivity.gioHangArray.get(i).setGiaSp(GiaChiTiet * MainActivity.gioHangArray.get(i).getSoLuongSp());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int soluong =  Integer.parseInt(spinner.getSelectedItem().toString());
                        long giaMoi = soluong * GiaChiTiet;
                        MainActivity.gioHangArray.add(new GioHang(id,TenChiTiet,giaMoi,HinhAnhChiTiet,soluong));
                    }
                }else{
                    int soluong =  Integer.parseInt(spinner.getSelectedItem().toString());
                    long giaMoi = soluong * GiaChiTiet;
                    MainActivity.gioHangArray.add(new GioHang(id,TenChiTiet,giaMoi,HinhAnhChiTiet,soluong));
                }
                Intent intent = new Intent(ChiTietSanPhamActivity.this,GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInformation() {
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanPham.getId();
        TenChiTiet = sanPham.getTenSp();
        GiaChiTiet = sanPham.getGiaSp();
        HinhAnhChiTiet = sanPham.getHinhAnhSp();
        MoTaChiTiet = sanPham.getMoTaSp();
        IdSanPham = sanPham.getIdSanPham();
        txtTenChiTiet.setText(TenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaChiTiet.setText("Giá : " + decimalFormat.format(GiaChiTiet) + "USD");
        txtMotaChiTiet.setText(MoTaChiTiet);
        Glide.with(getApplicationContext())
                .load(HinhAnhChiTiet)
                .placeholder(R.drawable.icon_noimage)
                .error(R.drawable.icon_error)
                .into(imgChiTiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void unitView() {
        toolbarChiTiet = (Toolbar) findViewById(R.id.toolbarChiTietSp);
        imgChiTiet = (ImageView) findViewById(R.id.imageViewChiTietSp);
        txtTenChiTiet = (TextView) findViewById(R.id.textView_TenChiTietSp);
        txtGiaChiTiet = (TextView) findViewById(R.id.textView_GiaChiTietSp);
        txtMotaChiTiet = (TextView) findViewById(R.id.textViewMoTaChiTietSp);
        spinner = (Spinner) findViewById(R.id.spinnerChiTietSp);
        btnMua = (ImageButton) findViewById(R.id.ButtonMuaChiTietSp);
    }
}
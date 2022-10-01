package qvm.m.appcuahang.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import qvm.m.appcuahang.Adapter.GioHangAdapter;
import qvm.m.appcuahang.R;

public class GioHangActivity extends AppCompatActivity {

    ListView listViewGioHang;
    static TextView txtTongTien;
    ImageView imgThongBaoGH;
    Toolbar toolbarGioHang;
    Button btnThanhToan, btnMuaTiep;
    GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
            unitView();
            ActionToolbar();
            CheckData();
            EventUltil();
            CatchOnItemListView();
    }

    private void CatchOnItemListView() {
        listViewGioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(GioHangActivity.this, "oke", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }


//    private void CatchOnItemListView() {
//        listViewGioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
//                builder.setTitle("Xác nhận xoá sản phẩm !");
//                builder.setMessage("Bạn có chắc muốn xoá sản phẩm này ?");
//
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        if(MainActivity.gioHangArray.size() <= 0){
//                            imgThongBaoGH.setVisibility(View.VISIBLE);
//                        }else{
//                            MainActivity.gioHangArray.remove(position);
//                            gioHangAdapter.notifyDataSetChanged();
//                            EventUltil();
//                            if(MainActivity.gioHangArray.size() <= 0){
//                                imgThongBaoGH.setVisibility(View.VISIBLE);
//                            }else{
//                                imgThongBaoGH.setVisibility(View.INVISIBLE);
//                                gioHangAdapter.notifyDataSetChanged();
//                                EventUltil();
//                            }
//                        }
//                    }
//                });
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        gioHangAdapter.notifyDataSetChanged();
//                        EventUltil();
//                    }
//                });
//                builder.show();
//                return true;
//            }
//        });
//    }

    public static void EventUltil() {
        long tongtien = 0;
        for (int i=0; i<MainActivity.gioHangArray.size(); i++){
            tongtien += MainActivity.gioHangArray.get(i).getGiaSp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongtien) + "USD");
    }

    private void CheckData() {
        if(MainActivity.gioHangArray.size() <= 0){
            gioHangAdapter.notifyDataSetChanged();
            imgThongBaoGH.setVisibility(View.VISIBLE);//hien
            listViewGioHang.setVisibility(View.INVISIBLE);// an
        }else{
            gioHangAdapter.notifyDataSetChanged();
            imgThongBaoGH.setVisibility(View.INVISIBLE);//an
            listViewGioHang.setVisibility(View.VISIBLE);// hien
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarGioHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void unitView() {
        listViewGioHang = (ListView) findViewById(R.id.listViewGioHang);
        txtTongTien = (TextView) findViewById(R.id.textViewTongTien);
        imgThongBaoGH = (ImageView) findViewById(R.id.img_thongbaoGioHang);
        toolbarGioHang = (Toolbar) findViewById(R.id.toolbarGioHang);
        btnThanhToan = (Button) findViewById(R.id.buttonThanhToan);
        btnMuaTiep = (Button) findViewById(R.id.buttonMuaTiep);
        gioHangAdapter = new GioHangAdapter(GioHangActivity.this,MainActivity.gioHangArray);
        listViewGioHang.setAdapter(gioHangAdapter);
    }
}
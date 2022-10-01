package qvm.m.appcuahang.Activity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import qvm.m.appcuahang.Adapter.SieuXeAdapter;
import qvm.m.appcuahang.Model.LoaiSpModel;
import qvm.m.appcuahang.Model.SanPham;
import qvm.m.appcuahang.R;
import qvm.m.appcuahang.Retrofit.ApiBanHang;
import qvm.m.appcuahang.Retrofit.RetrofitClient;
import qvm.m.appcuahang.ultil.CheckConnect;
import qvm.m.appcuahang.ultil.Server;

public class SieuXeActivity extends AppCompatActivity {

    Toolbar toolbarSx;
    RecyclerView recyclerViewSx;
    SieuXeAdapter sieuXeAdapter;
    List<SanPham> sanPhamList;
    int loai;
    int page = 1;

    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sieu_xe2);

        apiBanHang = RetrofitClient.getInstance(Server.BASE_URL).create(ApiBanHang.class);
        loai = getIntent().getIntExtra("loai",1);

        unitView();
//        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
//            GetIdLoaiSanPham();
        ActionToolbar();
        GetData();
//        }else {
//            CheckConnect.ShowToast_Short(getApplicationContext(),"Không có kết nối internet");
//            finish();
//        }
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
                break;
            case R.id.menuUser:
                Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void GetData() {
        compositeDisposable.add(apiBanHang.getSanPham(page,loai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if(loaiSpModel.isSuccsess()){
                                sanPhamList = loaiSpModel.getResult();
                                sieuXeAdapter = new SieuXeAdapter(getApplicationContext(),sanPhamList);
                                Log.d("list",sanPhamList.get(1).getTenSp() + "");
                                recyclerViewSx.setAdapter(sieuXeAdapter);
//                                sieuXeAdapter.notifyDataSetChanged();
                            }
                        },
                        throwable -> {
                            Toast.makeText(this, "Không có kết nối với server", Toast.LENGTH_SHORT).show();
                        }
                ));
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        String duongdansieuxe = Server.DuongDanSpSX+ String.valueOf(Page);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdansieuxe, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                int id = 0;
//                String tenSX = "";
//                int giaSx = 0;
//                String hinhAnhSX = "";
//                String mota = "";
//                int idSanPhamSX = 0;
//                if(response != null){
//                    try {
//                        JSONObject object = new JSONObject(response);
//                        JSONArray jsonArray = object.getJSONArray("");
//                        for(int i = 0; i < jsonArray.length(); i++){
//                            JSONObject jsonObject  = jsonArray.getJSONObject(i);
//                            id = jsonObject.getInt("id");
//                            tenSX = jsonObject.getString("tensp");
//                            giaSx = jsonObject.getInt("giasp");
//                            hinhAnhSX = jsonObject.getString("hinhanhsp");
//                            mota = jsonObject.getString("motasp");
//                            idSanPhamSX = jsonObject.getInt("idsanpham");
//                            arraySieuXe.add(new SanPham(id,tenSX,giaSx,hinhAnhSX,mota,idSanPhamSX));
//                            sieuXeAdapter.notifyDataSetChanged();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String> param = new HashMap<String,String>();
//                param.put("idSanPham",String.valueOf(idSX));
//                return super.getParams();
//            }
//        };
//        requestQueue.add(stringRequest);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarSx);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSx.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


//    private void GetIdLoaiSanPham() {
//        idSX = getIntent().getIntExtra("idLoaiSp",-1);
//        Log.d("giatrisanpham",idSX + "");
//    }

    private void unitView() {
        toolbarSx = (Toolbar) findViewById(R.id.toolbarSieuXe);
        recyclerViewSx = (RecyclerView) findViewById(R.id.recyclerViewSieuXe);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSx.setLayoutManager(layoutManager);
        recyclerViewSx.setHasFixedSize(true);

        sanPhamList = new ArrayList<>();
//        sieuXeAdapter = new SieuXeAdapter(getApplicationContext(),arraySieuXe);
//        recyclerViewSx.setAdapter(sieuXeAdapter);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}

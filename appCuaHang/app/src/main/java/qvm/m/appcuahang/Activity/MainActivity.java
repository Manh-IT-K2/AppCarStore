package qvm.m.appcuahang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import qvm.m.appcuahang.Adapter.SanPhamAdapter;
import qvm.m.appcuahang.Adapter.loaiSpAdapter;
import qvm.m.appcuahang.Model.GioHang;
import qvm.m.appcuahang.Model.LoaiSp;
import qvm.m.appcuahang.Model.SanPham;
import qvm.m.appcuahang.R;
import qvm.m.appcuahang.ultil.CheckConnect;
import qvm.m.appcuahang.ultil.Server;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;

    ArrayList<LoaiSp> mangloaisp;
    loaiSpAdapter loaiSpAdapter;

    int id = 0;
    String tenloaisp = "";
    String hinhanhsp = "";

    ArrayList<SanPham> mangSanPham;
    SanPhamAdapter sanPhamAdapter;

    public static ArrayList<GioHang> gioHangArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
            Actionbar();
            ACtionViewFlipper();
            GetDuLieuLoaiSp();
            GetDuLieuSpNew();
            CatchOnItemListView();
        }else{
            CheckConnect.ShowToast_Short(getApplicationContext(),"Không có internet !");
            finish();
        }
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


    private void CatchOnItemListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast_Short(getApplicationContext(),"Kiểm tra lại kết nối !");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,SieuXeActivity.class);
                            intent.putExtra("loai",1);
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast_Short(getApplicationContext(),"Kiểm tra lại kết nối !");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,XeLeoNuiActivity.class);
                            //intent.putExtra("idLoaiSp",mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast_Short(getApplicationContext(),"Kiểm tra lại kết nối !");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,LienHeActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast_Short(getApplicationContext(),"Kiểm tra lại kết nối !");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,ThongTinActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast_Short(getApplicationContext(),"Kiểm tra lại kết nối !");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetDuLieuSpNew() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongDanSpNew, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int id = 0;
                    String tenSanPham = "";
                    int giaSanPham = 0;
                    String hinhAnhSp = "";
                    String moTaSanPham = "";
                    int idSanPham = 0;
                    for (int i = 0; i < response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenSanPham = jsonObject.getString("tensp");
                            Log.d("mannn",tenSanPham);
                            giaSanPham = jsonObject.getInt("giasp");
                            hinhAnhSp = jsonObject.getString("hinhanhsp");
                            moTaSanPham = jsonObject.getString("motasp");
                            idSanPham = jsonObject.getInt("idsanpham");
                            mangSanPham.add(new SanPham(id,tenSanPham,giaSanPham,hinhAnhSp,moTaSanPham,idSanPham));
                            sanPhamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    private void GetDuLieuLoaiSp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongDanLoaiSp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for (int i=0 ;i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenloaisp = jsonObject.getString("tenloaisp");
                            hinhanhsp = jsonObject.getString("hinhanhsp");
                            mangloaisp.add(new LoaiSp(id,tenloaisp,hinhanhsp));
                            loaiSpAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add(3,new LoaiSp(0,"Liên Hệ","https://img.icons8.com/external-phatplus-lineal-color-phatplus/344/external-contacts-marketing-seo-phatplus-lineal-color-phatplus.png"));
                    mangloaisp.add(4,new LoaiSp(0,"Trợ Giúp","https://img.icons8.com/external-flaticons-lineal-color-flat-icons/344/external-help-dating-app-flaticons-lineal-color-flat-icons-4.png"));

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ACtionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://marketingreview.vn/wp-content/uploads/2020/07/L%C3%BD-do-Ferrari-hay-Lamborghini-kh%C3%B4ng-bao-gi%E1%BB%9D-qu%E1%BA%A3ng-c%C3%A1o-tr%C3%AAn-TV.jpg");
        mangquangcao.add("https://azco.vn/wp-content/uploads/2019/03/Chup-anh-quang-cao-thuong-hieu-xe-ferrari-7.jpg");
        mangquangcao.add("https://otohanquoc.vn/xe-oto-dep-nhat-the-gioi/imager_5513.jpg");
        for (int i=0 ; i<mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.icon_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolBarMain);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        listView = (ListView) findViewById(R.id.listView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mangloaisp = new ArrayList<>();
        mangloaisp.add(0,new LoaiSp(0,"Trang Chính","https://img.icons8.com/cute-clipart/344/home.png"));
        loaiSpAdapter = new loaiSpAdapter(mangloaisp,getApplicationContext());
        listView.setAdapter(loaiSpAdapter);

        mangSanPham = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(getApplicationContext(),mangSanPham);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(sanPhamAdapter);

        if(gioHangArray != null){

        }else{
            gioHangArray = new ArrayList<>();
        }

    }
}
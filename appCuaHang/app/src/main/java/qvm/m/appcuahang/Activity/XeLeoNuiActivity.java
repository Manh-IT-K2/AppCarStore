package qvm.m.appcuahang.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import qvm.m.appcuahang.Adapter.XeLeoNuiAdapter;
import qvm.m.appcuahang.Model.SanPham;
import qvm.m.appcuahang.R;
import qvm.m.appcuahang.ultil.CheckConnect;
import qvm.m.appcuahang.ultil.Server;

public class XeLeoNuiActivity extends AppCompatActivity {

    Toolbar toolbarXeLeoNui;
    ListView listViewXeLeoNui;
    XeLeoNuiAdapter xeLeoNuiAdapter;
    ArrayList<SanPham> arrayXln;
    int idxln = 0;
    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xe_leo_nui2);

        unitView();
        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
            //getIdLoaiSp();
            ActionToolbar();
            GetData();
        }else{
            CheckConnect.ShowToast_Short(getApplicationContext(),"Kiểm tra mạng !");
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

    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        String duongdansieuxe = Server.DuongDanSpSX + String.valueOf(ppage);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdansieuxe, new Response.Listener<String>() {
          JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongDanSpSX, new Response.Listener<JSONArray>() {
          @Override
            public void onResponse(JSONArray response) {
                int id = 0;
                String tenXeLeoNui = "";
                int giaXeLeoNui = 0;
                String hinhAnhXeLeoNui = "";
                String motaXeLeoNui = "";
                int idSanPhamXln = 0;
                if(response != null){
                    try {
                       //JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < response.length(); i++){
                            JSONObject jsonObject  = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenXeLeoNui = jsonObject.getString("tensp");
                            Log.d("manhne",tenXeLeoNui + "");
                            giaXeLeoNui = jsonObject.getInt("giasp");
                            hinhAnhXeLeoNui = jsonObject.getString("hinhanhsp");
                            motaXeLeoNui = jsonObject.getString("motasp");
                            idSanPhamXln = jsonObject.getInt("idsanpham");
                            arrayXln.add(new SanPham(id,tenXeLeoNui,giaXeLeoNui,hinhAnhXeLeoNui,motaXeLeoNui,idSanPhamXln));
                            Log.d("manhne",arrayXln.get(0) +"");
                            xeLeoNuiAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
 //         {
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String> param = new HashMap<String,String>();
//                param.put("idLoaiSp",String.valueOf(idxln));
//                return super.getParams();
//            }
//        };
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarXeLeoNui);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarXeLeoNui.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

//    private void getIdLoaiSp() {
//        idxln = getIntent().getIntExtra("idLoaiSp",-1);
//    }

    private void unitView() {
        toolbarXeLeoNui = (Toolbar) findViewById(R.id.toolbarXeLeoNui);
        listViewXeLeoNui = (ListView) findViewById(R.id.listViewXeLeoNui);
        arrayXln = new ArrayList<>();
        xeLeoNuiAdapter = new XeLeoNuiAdapter(getApplicationContext(),arrayXln);
        listViewXeLeoNui.setAdapter(xeLeoNuiAdapter);
    }

}
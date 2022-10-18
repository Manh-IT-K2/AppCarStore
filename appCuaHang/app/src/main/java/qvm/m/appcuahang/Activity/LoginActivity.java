package qvm.m.appcuahang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import qvm.m.appcuahang.R;
import qvm.m.appcuahang.Retrofit.ApiBanHang;
import qvm.m.appcuahang.Retrofit.RetrofitClient;
import qvm.m.appcuahang.ultil.Server;

public class LoginActivity extends AppCompatActivity {

    EditText edtUser, edtPassword;
    Button btnLogin;
    TextView txtRegister, txtForgot;
    ImageButton imgFaceBook, imgGoogle, imgInstagram, imgHome;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unitViews();
        EventBack();
        EventRegister();
        initControl();
    }

    private void initControl() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String str_username = edtUser.getText().toString().trim();
               String str_password = edtPassword.getText().toString().trim();
               if(TextUtils.isEmpty(str_username)){
                   Toast.makeText(LoginActivity.this, "Chưa nhập username !", Toast.LENGTH_SHORT).show();
               }else if(TextUtils.isEmpty(str_password)){
                   Toast.makeText(LoginActivity.this, "Chưa nhập password !", Toast.LENGTH_SHORT).show();
               }else{
                   compositeDisposable.add(apiBanHang.dangnhap(str_username,str_password)
                           .subscribeOn(Schedulers.io())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(
                                   userModel -> {
                                       if(userModel.isSuccess()){
                                           Server.user_current.setUsername(str_username);
                                           Server.user_current.setPassword(str_password);
                                           Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                           startActivity(intent);

                                       }else{
                                           Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại !", Toast.LENGTH_SHORT).show();
                                       }
                                   },
                                   throwable -> {
                                       Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                   }
                           ));
               }
            }
        });
    }

    private void EventRegister() {
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void EventBack() {
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void unitViews() {
        apiBanHang = RetrofitClient.getInstance(Server.BASE_URL).create(ApiBanHang.class);

        edtUser = (EditText) findViewById(R.id.editTextUserName);
        edtPassword = (EditText) findViewById(R.id.editTextPassWord);
        btnLogin = (Button) findViewById(R.id.btn_DN_Login);
        txtRegister = (TextView) findViewById(R.id.textViewRegister);
        txtForgot = (TextView) findViewById(R.id.textViewForgot);
        imgFaceBook = (ImageButton) findViewById(R.id.imageButtonFB);
        imgGoogle = (ImageButton) findViewById(R.id.imageButtonGG);
        imgInstagram = (ImageButton) findViewById(R.id.imageButtonIns);
        imgHome = (ImageButton) findViewById(R.id.imageButtonHome);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Server.user_current.getUsername() != null && Server.user_current.getPassword() != null){
            edtUser.setText(Server.user_current.getUsername());
            edtPassword.setText(Server.user_current.getPassword());
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
package qvm.m.appcuahang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.stream.JsonReader;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import qvm.m.appcuahang.R;
import qvm.m.appcuahang.Retrofit.ApiBanHang;
import qvm.m.appcuahang.Retrofit.RetrofitClient;
import qvm.m.appcuahang.ultil.Server;

public class RegisterActivity extends AppCompatActivity {

    EditText edtFullName, edtEmail, edtUserName, edtPassWord, edtRepassword;
    Button btnRegister;
    TextView txtLogin;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        unitView();
        EventLogin();
        unitControl();


    }

    private void unitControl() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangky();
            }
        });
    }
    private void dangky(){

        String str_fullname = edtFullName.getText().toString().trim();
        String str_password = edtPassWord.getText().toString().trim();
        String str_repassword = edtRepassword.getText().toString().trim();
        String str_username = edtUserName.getText().toString().trim();

        String str_email = edtEmail.getText().toString().trim();
        if(TextUtils.isEmpty(str_fullname)){
            Toast.makeText(this, "Bạn chưa nhập họ tên !", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_username)){
            Toast.makeText(this, "Bạn chưa nhập username !", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_password)){
            Toast.makeText(this, "Bạn chưa nhập password !", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_repassword)){
            Toast.makeText(this, "Bạn chưa nhập repassword !", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_email)){
            Toast.makeText(this, "Bạn chưa nhập email !", Toast.LENGTH_SHORT).show();
        }else {
            if(str_password.equals(str_repassword)){
                // post data
                compositeDisposable.add(apiBanHang.dangky(str_fullname,str_username,str_password,str_email)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if(userModel.isSuccess()){
                                        Server.user_current.setUsername(str_username);
                                        Server.user_current.setPassword(str_password);
                                        Intent intent = new Intent(this,LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(this, "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(this, "Tạo tài khoản thất bại !", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(this,throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));

            }else{
                Toast.makeText(this, "Password không khớp !", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void EventLogin() {
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void unitView() {
        apiBanHang = RetrofitClient.getInstance(Server.BASE_URL).create(ApiBanHang.class);

        edtFullName = (EditText) findViewById(R.id.edt_dk_FullName);
        edtEmail = (EditText) findViewById(R.id.edt_dk_TextEmail);
        edtUserName = (EditText) findViewById(R.id.edt_dk_Username);
        edtPassWord = (EditText) findViewById(R.id.edt_dk_PassWord);
        edtRepassword = (EditText) findViewById(R.id.edt_dk_RepassWord) ;
        btnRegister = (Button) findViewById(R.id.btn_DK_Register);
        txtLogin = (TextView) findViewById(R.id.textViewLogin);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
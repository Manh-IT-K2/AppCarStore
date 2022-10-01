package qvm.m.appcuahang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import qvm.m.appcuahang.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtUser, edtPassword;
    Button btnLogin;
    TextView txtRegister, txtForgot;
    ImageButton imgFaceBook, imgGoogle, imgInstagram, imgHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unitViews();
        EventBack();
        EventRegister();
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
        edtUser = (EditText) findViewById(R.id.editTextUserName);
        edtPassword = (EditText) findViewById(R.id.editTextPassWord);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        txtRegister = (TextView) findViewById(R.id.textViewRegister);
        txtForgot = (TextView) findViewById(R.id.textViewForgot);
        imgFaceBook = (ImageButton) findViewById(R.id.imageButtonFB);
        imgGoogle = (ImageButton) findViewById(R.id.imageButtonGG);
        imgInstagram = (ImageButton) findViewById(R.id.imageButtonIns);
        imgHome = (ImageButton) findViewById(R.id.imageButtonHome);
    }
}
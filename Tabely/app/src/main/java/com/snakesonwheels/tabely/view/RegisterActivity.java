package com.snakesonwheels.tabely.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.snakesonwheels.tabely.R;
import com.snakesonwheels.tabely.model.DBTools;

public class RegisterActivity extends AppCompatActivity {

    private TextView textUsername;
    private TextView textPassword;
    private TextView textConfirmPassword;
    private TextView textFullName;
    private TextView textEmail;
    private DBTools dbTools;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textUsername = findViewById(R.id.TextUsername);
        textPassword = findViewById(R.id.TextPassword);
        textConfirmPassword = findViewById(R.id.TextConfirmPassword);
        textFullName = findViewById(R.id.TextFullName);
        textEmail = findViewById(R.id.TextEmail);
        dbTools = new DBTools(this);
        buttonRegister = findViewById(R.id.ButtonRegister);
        buttonRegister.setEnabled(false);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to save userId
                Intent i = new Intent(RegisterActivity.this, HomeActivity.class);

               long success =  dbTools.insertCustomer(textFullName.getText().toString(),"","",textEmail.getText().toString(),"",textUsername.getText().toString().trim(),textPassword.getText().toString().trim());
               if(success == -1){
                   Toast.makeText(getApplicationContext(), "user already exists!",Toast.LENGTH_SHORT).show();
                   //buttonRegister.setText("User Already Exists");
               }else {
                   Toast.makeText(getApplicationContext(), "Welcome!",Toast.LENGTH_SHORT).show();
                   i.putExtra("userId", "" + success);
                   startActivity(i);
               }
            }
        });


        textUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });

        textPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });

        textConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });

        textEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });

        textFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });

    }

    private void changeButtonState() {
        if ((textUsername.getText().toString().length() > 0
                && textPassword.getText().toString().length() > 0
                && textFullName.getText().toString().length() > 0
                && textEmail.getText().toString().length() > 0
                && textConfirmPassword.getText().toString().length() > 0
                && textUsername.getText().toString().length() > 0))
            buttonRegister.setEnabled(true);
        else buttonRegister.setEnabled(false);
    }

}

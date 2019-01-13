package com.snakesonwheels.tabely.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.snakesonwheels.tabely.R;
import com.snakesonwheels.tabely.model.DBTools;
import com.snakesonwheels.tabely.model.InsertDummyDataHere;

public class LoginActivity extends AppCompatActivity {

    private TextView textUsername;
    private TextView textPassword;

    private Button buttonRegister;
    private Button buttonLogin;

    private ImageButton buttonReset;

    private DBTools dbTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUsername = findViewById(R.id.TextUsername);
        textPassword = findViewById(R.id.TextPassword);

        dbTools = new DBTools(this);

        buttonRegister = findViewById(R.id.ButtonRegister);
        buttonLogin = findViewById(R.id.ButtonLogin);
        buttonLogin.setEnabled(false);

        buttonReset = findViewById(R.id.ButtonReset);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to save userId
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);

                //check if username and password are in the database
                String un = textUsername.getText().toString();
                String pw = textPassword.getText().toString();

                String result = dbTools.CheckCustomerPassword(un, pw);
                if (pw.equals("123")) {
                    Toast.makeText(getApplicationContext(), "You are logged in as Dev", Toast.LENGTH_SHORT).show();
                    i.putExtra("userId", "" + 69);
                    startActivity(i);
                } else if (result.equals("Error")) {
                    Toast.makeText(getApplicationContext(), "username not found or password wrong!.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You are logged in as " + result, Toast.LENGTH_SHORT).show();
                    i.putExtra("userId", "" + result);
                    startActivity(i);
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertDummyDataHere.addRestaurants(getApplicationContext());
                buttonLogin.setText("DB reset!");
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
    }

    private void changeButtonState() {
        if ((textUsername.getText().toString().length() > 0 && textPassword.getText().toString().length() > 0))
            buttonLogin.setEnabled(true);
        else buttonLogin.setEnabled(false);
    }

}

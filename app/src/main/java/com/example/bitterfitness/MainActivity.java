package com.example.bitterfitness;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SQLiteManager sqLiteManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("BitterFitness", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean activeUser = sharedPreferences.getBoolean("activeUser", false);
        if(!activeUser){
            setContentView(R.layout.activity_login);
            Button loginButton = findViewById(R.id.login);
            sqLiteManager = loadDBFromMemory();
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean check = checkFields();
                    if(check){
                        determineButtonUse();
                    }
                }
            });
        } else {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }



    private boolean checkFields(){
        boolean passed = true;
        TextView errorField = findViewById(R.id.errorMessage);
        EditText user = findViewById(R.id.email);
        EditText pd = findViewById(R.id.password);
        String email = user.getText().toString().trim();
        String shadow = pd.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            errorField.setText("Email field is empty!");
            if (TextUtils.isEmpty(shadow)) {
                errorField.setText("Email and Password field is empty!");
            }
            passed = false;
        }
        if (!TextUtils.isEmpty(email) && TextUtils.isEmpty(shadow)) {
            errorField.setText("Password field is empty!");
            passed = false;
        }
        if(passed){
            errorField.setText("");
        }
        return passed;
    }

    private void determineButtonUse() {
        EditText user = findViewById(R.id.email);
        EditText pd = findViewById(R.id.password);
        EditText name = findViewById(R.id.userName);
        Button signupBtn = findViewById(R.id.btn_signUp);
        Button loginBtn = findViewById(R.id.login);
        String email = formatEmail(user.getText().toString());
        String shadow = pd.getText().toString();

        if(sqLiteManager.checkUser(email)){
            user.setText("");
            pd.setText("");
            loginUser(email, shadow);
        } else {
            name.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.INVISIBLE);
            signupBtn.setVisibility(View.VISIBLE);
            signupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uName = name.getText().toString();
                    signupUser(email, shadow, uName);
                }
            });
        }
    }

    private String formatEmail(String email) {
        // Validate the email format
        return email.replaceAll("\\s", "");
    }

    private void signupUser(String email, String shadow, String name) {
        sharedPreferences = getSharedPreferences("BitterFitness", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        sqLiteManager.addUser(email, shadow, name);
        editor.putBoolean("activeUser", true);
        editor.putString("userEmail", email);
        editor.apply();
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void loginUser(String email, String shadow) {
        SharedPreferences sharedPreferences = getSharedPreferences("BitterFitness", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean login = sqLiteManager.loginUser(getApplicationContext(), email, shadow);
        if(login){
            Toast.makeText(getApplicationContext(), "Logging in", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            editor.putBoolean("activeUser", true);
            editor.putString("userEmail", email);
            editor.apply();
            startActivity(intent);
            finish();
        }
    }

    private  SQLiteManager loadDBFromMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        return sqLiteManager;
    }

}
package com.hdevelopment.lab11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //1. Create EditText reference
    EditText UsernameEt, PasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //2. Instantiate EditText references
        UsernameEt = (EditText) findViewById(R.id.usernameET);
        PasswordEt = (EditText) findViewById(R.id.passwordET);

    }

    //3. Create OnLogin() method
    public void OnLogin(View view)
    {
        //4. get input from edit text
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        UsernameEt.setText("");
        PasswordEt.setText("");
        //5. Send username and password into background process
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

    //Method to open registration form
    public void OpenReg(View view)
    {
        startActivity(new Intent(this, Register.class));
    }

}

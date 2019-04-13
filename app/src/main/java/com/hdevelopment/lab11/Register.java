package com.hdevelopment.lab11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    //1. Declare EditText references
    EditText usernameET, passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //2. Instantiate EditText references
        usernameET = (EditText) findViewById(R.id.usernameET);
        passwordET = (EditText) findViewById(R.id.passwordET);

    }

    //3. Create OnReg() method
    public void OnReg(View view)
    {
        //4. Get input from EditText
        String str_username = usernameET.getText().toString();
        String str_password = passwordET.getText().toString();
        String type = "register";
        usernameET.setText("");
        passwordET.setText("");

        //5. Send user name and password in background process
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_username, str_password);
    }

}

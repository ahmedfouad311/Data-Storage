package com.example.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button loginBtn;
    EditText messageText;
    EditText passText;
    String tempTxt;
    public static final String NAME = "name";
    public static final String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageText = (EditText) findViewById(R.id.messageText);
        passText = (EditText) findViewById(R.id.passText);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((""+messageText.getText().toString()).isEmpty()){ // casting to string
                    Toast.makeText(MainActivity.this ,"Enter your name plz !!! ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if((""+passText.getText().toString()).isEmpty()){
                    Toast.makeText(MainActivity.this ,"Enter your password plz !!! ", Toast.LENGTH_SHORT).show();
                    return;
                }

                else{
                    Toast.makeText(MainActivity.this,"Welcome " + messageText.getText(),Toast.LENGTH_LONG).show();
                }

                Intent outIntent = new Intent(MainActivity.this,MainActivity2.class);
                tempTxt = messageText.getText().toString();
                outIntent.putExtra(NAME,tempTxt);
                tempTxt = passText.getText().toString();
                outIntent.putExtra(PASSWORD,tempTxt);
                startActivity(outIntent);

            }
        });

    }

    public void closeMethod(View view) {
        finish();
    }
}

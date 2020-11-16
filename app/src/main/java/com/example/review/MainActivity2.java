package com.example.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.review.Database.Database_Adapter;
import com.example.review.Database.Name_Pass_DB;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    TextView txtName;
    TextView txtPass;
    Button closeBtn2;

    Button btnWriteShared;
    Button btnReadShared;
    SharedPreferences preferences;

    Button btnWriteInternal;
    Button btnReadInternal;

    // preparing writing streams
    FileOutputStream fileOutputStream; // low level stream
    DataOutputStream dataOutputStream; // high level stream

    // preparing reading streams
    FileInputStream fileInputStream; // low level stream
    DataInputStream dataInputStream; // high level stream

    private static final String FILE_NAME = "NAME_PASS";

    Button btnWriteSQL;
    Button btnReadSQL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent incomingIntent = getIntent();
        txtName = (TextView) findViewById(R.id.txtName);
        txtPass = (TextView) findViewById(R.id.txtPass);

        txtName.setText(incomingIntent.getStringExtra(MainActivity.NAME));
        txtPass.setText(incomingIntent.getStringExtra(MainActivity.PASSWORD));

        btnWriteShared = findViewById(R.id.write);
        btnReadShared = findViewById(R.id.read);

        btnWriteShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences = getPreferences(Context.MODE_PRIVATE);
                // get prefrences 3l4an 2na ma3nde4 2ktar mn file fa kfaya get prefrences bas
                SharedPreferences.Editor editor = preferences.edit();
                // hna 3amalt object mn el editor 3l4an 3l4an 22dar 2kteb w 23ml edit fe el shared prefrences
                editor.putString(MainActivity.NAME,txtName.getText().toString());
                editor.putString(MainActivity.PASSWORD,txtPass.getText().toString());
                editor.commit();

                // delwa2ty 3ayz 2afadi el text views el 3andi ba3d ma22ra mnha el 7agat el goaha
                txtName.setText("");
                txtPass.setText("");
            }
        });

        btnReadShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences = getPreferences((Context.MODE_PRIVATE));
                txtName.setText(preferences.getString(MainActivity.NAME,"N/A"));
                txtPass.setText(preferences.getString(MainActivity.PASSWORD,"N/A"));
                // hna bta5od el key el mt5azen feha el info w default value 3l4an law mala2a4 7aga mt5azena yrga3 be el default value de


            }
        });

        btnWriteInternal = findViewById(R.id.write2);
        btnWriteInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fileOutputStream = openFileOutput(MainActivity2.FILE_NAME,Context.MODE_PRIVATE); // 3l4an 22dar 23ml store fe el internal be sora private
                    // 2na keda 22dar 2sta5dem el data direct bas hatb2a fe soret bytes and char ya3ny low level fa 3ayz 2a7awelha le
                    // high level zai string w intger w keda
                    dataOutputStream = new DataOutputStream(fileOutputStream);
                    dataOutputStream.writeUTF(txtName.getText().toString());
                    dataOutputStream.writeUTF(txtPass.getText().toString());

                    dataOutputStream.close();
                    fileOutputStream.close();

                    txtName.setText("");
                    txtPass.setText("");

                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnReadInternal = findViewById(R.id.read2);
        btnReadInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fileInputStream = openFileInput(MainActivity2.FILE_NAME);
                    dataInputStream = new DataInputStream(fileInputStream);
                    txtName.setText(dataInputStream.readUTF());
                    txtPass.setText(dataInputStream.readUTF());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnWriteSQL = findViewById(R.id.write3);
        btnWriteSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database_Adapter database_adapter = new Database_Adapter(MainActivity2.this);
                database_adapter.addEntry(new Name_Pass_DB(txtName.getText().toString(),txtPass.getText().toString()));
                txtName.setText("");
                txtPass.setText("");
            }
        });

        btnReadSQL = findViewById(R.id.read3);
        btnReadSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database_Adapter database_adapter = new Database_Adapter(MainActivity2.this);
                Name_Pass_DB result = database_adapter.getEntry();
                txtName.setText(result.getName());
                txtPass.setText(result.getPassword());

            }
        });

        closeBtn2 = (Button) findViewById(R.id.closeBtn2);
        closeBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

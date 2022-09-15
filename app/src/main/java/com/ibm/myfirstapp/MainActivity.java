package com.ibm.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{

    private EditText edPassword, edEmail;
    private Button button;
    private String msg = "";
    private String email, passwd;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPassword = findViewById(R.id.etPassword);
        edEmail = findViewById(R.id.etEmail);
        button = findViewById(R.id.blogin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String passwd = edPassword.getText().toString();

                SharedPreferences pref = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String emailShared = pref.getString("email", null);
                String passwdShared = pref.getString("passwd", null);

                if (email.isEmpty() || passwd.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),
                            "Please, fill in the fields to register",
                            Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("ATTECTION!")
                            .setMessage("Fill in all Fields")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else
                {
                    if (email.equals(emailShared) && passwd.equals(passwdShared) || email.equals("isa@email.com") && passwd.equals("222")) {
                        msg = "User or Password are Correct";

                        Intent intent = new Intent(MainActivity.this, ScreenActivity.class);
                        intent.putExtra("KeyEmail", email);
                        intent.putExtra("KeyPassword", passwd);
                        startActivity(intent);

                        send();
                    } else
                    {
                        dialog();
                    }
                }
            }
        });

    }

    private void send()
    {

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void dialog(){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("ATTECTION!")
                .setMessage("You do not have a registration, you want to register?")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                        intent.putExtra("KeyEmail", email);
                        intent.putExtra("KeyPassword", passwd);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}


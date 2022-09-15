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

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        EditText edEmail2 = findViewById(R.id.etEmail2);
        EditText edPassword = findViewById(R.id.etPassword2);
        EditText edPassword2 = findViewById(R.id.etPassword3);
        EditText edName = findViewById(R.id.etName);


        String email = getIntent().getStringExtra("KeyEmail");
        edEmail2.setText(email);
        String pswrd = getIntent().getStringExtra("KeyPassword");
        edPassword.setText(email);

        Button button = findViewById(R.id.bregister);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail2.getText().toString();
                String pswrd = edPassword.getText().toString();
                String pswrd2 = edPassword2.getText().toString();
                String name = edName.getText().toString();

                if (pswrd.equals(pswrd2)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    myEdit.putString("email", email);
                    myEdit.putString("pswrd", pswrd);
                    myEdit.putString("name", name);
                    myEdit.commit();

                    //tela surpresa
                    Intent intent = new Intent(AccountActivity.this, ScreenActivity.class);
                    intent.putExtra("KeyEmail", email);
                    startActivity(intent);
                } else {
                    //erro senhas divergentes
                    alert("Divergent Passwords");
                }
            }

            private void alert(String msg){
                new AlertDialog.Builder(AccountActivity.this)
                        .setTitle("ATTECTION!")
                        .setMessage(msg)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}
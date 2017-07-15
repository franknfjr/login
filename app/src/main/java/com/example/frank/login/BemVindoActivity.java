package com.example.frank.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BemVindoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);
        Bundle argBundle = getIntent().getExtras();
        String nome = argBundle.getString("nome");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Olá " + nome + ", seja bem-vindo");
    }
}

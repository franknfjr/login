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
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
      int id = item.getItemId();
      if (id == android.R.id.home) {
        finish();
        return true;
      }
      return super.onOptionsItemSelected(item);
    }
}

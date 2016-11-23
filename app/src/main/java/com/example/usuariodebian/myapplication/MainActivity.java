package com.example.usuariodebian.myapplication;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context ctx = getApplicationContext();

        Button btnLoadf = (Button)findViewById(R.id.id_btn_loadf);
        btnLoadf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogo = new DialogoOpenFile();
                dialogo.show(getFragmentManager(), "Abrir foto");
            }
        });

    }
}

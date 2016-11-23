package com.example.usuariodebian.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by usuariodebian on 19/11/16.
 */

public class DialogoOpenFile extends DialogFragment {

    private Bitmap mImagem;

    public void setBitmap(Bitmap imagem) {
        this.mImagem = imagem;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


        View v = inflater.inflate(R.layout.dialogo_conteudo, null);

        builder.setView(v)
                .setPositiveButton(R.string.btn_escolher, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getApplicationContext(), "Carregar", Toast.LENGTH_SHORT).show();
            }
        })
        .setNegativeButton(R.string.btn_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getApplicationContext(), "Canccelando", Toast.LENGTH_SHORT).show();
                DialogoOpenFile.this.getDialog().cancel();
            }
        });

        ImageView iv = (ImageView)v.findViewById(R.id.imagemItem);

        iv.setImageBitmap(this.mImagem);

        return builder.create();
    }
}

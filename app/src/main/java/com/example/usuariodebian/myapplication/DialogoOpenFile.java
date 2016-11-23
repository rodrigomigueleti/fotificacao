package com.example.usuariodebian.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by usuariodebian on 19/11/16.
 */

public class DialogoOpenFile extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Cursor cur = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

        if (cur != null) {
            for (int i = 0; i < cur.getCount(); i++) {
                cur.moveToPosition(i);
                Log.v("DIALOGOGALERIA", Uri.parse(cur.getString(1)) + " - " + cur.getString(3));
            }
        }

        builder.setView(inflater.inflate(R.layout.dialogo_conteudo, null))
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
        return builder.create();
    }
}

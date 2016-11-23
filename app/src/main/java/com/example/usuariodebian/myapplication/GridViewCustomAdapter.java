package com.example.usuariodebian.myapplication;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by usuariodebian on 19/11/16.
 */

public class GridViewCustomAdapter extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        GridView gv = (GridView)findViewById(R.id.gridview);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });

        MetaFotosAdapter adapter = new MetaFotosAdapter(this);
        gv.setAdapter(adapter);
    }

    public static class MetaFotosAdapter extends BaseAdapter {

        private static final String TAG = "ManateeAdapter";
        private static int convertViewCounter = 0;
        private Context mContext;
        private LayoutInflater mInflater;

        static class ViewHolder {
            ImageView image;
        }

        private String[] metaFotosPath;

        private Bitmap[] metaFotosImages;

        public MetaFotosAdapter(Context context) {
            Log.v(TAG, "Construindo MetaFotosAdapter");
            this.mContext = context;
            mInflater = LayoutInflater.from(context);
            loadGalleryPath();
        }

        @Override
        public int getCount() {
            return metaFotosPath.length;
        }

        public int getViewTypeCount() {
            return 1;
        }

        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.gridimage, null);
                convertViewCounter++;
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.gridImageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Bitmap imageResource = BitmapFactory.decodeFile(metaFotosPath[position]);
            holder.image.setImageBitmap(this.getImageThumb(imageResource));

            return convertView;
        }

        @Override
        public Object getItem(int position) {
            Bitmap imageResource = BitmapFactory.decodeFile(metaFotosPath[position]);
            return imageResource;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private Bitmap getImageThumb(Bitmap imageResource) {
            Bitmap fotoThumb = Bitmap.createScaledBitmap(imageResource, 100, 100, false);
            return fotoThumb;
        }


        private void loadGalleryPath() {
            Cursor cur = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
            if (cur != null) {
                metaFotosPath = new String[cur.getCount()];
                for (int i = 0; i < cur.getCount(); i++) {
                    cur.moveToPosition(i);
                    //metaFotosImages[i] = BitmapFactory.decodeFile(cur.getString(1));
                    metaFotosPath[i] = cur.getString(1);
                }
                cur.close();
            }
        }
    }
}


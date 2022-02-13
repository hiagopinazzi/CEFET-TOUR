package com.example.cefet_tour;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.InputStream;
import java.net.URL;

public class CustomListView extends ArrayAdapter<String> {
    private String[] id;
    private String[] viagem;
    private Activity context;

    public CustomListView(Activity context,String[] id,String[] viagem) {
        super(context,R.layout.layout,id);
        this.context=context;
        this.id=id;
        this.viagem=viagem;
    }
    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent){
        View r=convertView;
        ViewHolder viewHolder ;
        if(r==null)
        {
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.layout, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)r.getTag();
        }
        viewHolder.tvw1.setText(id[position]);
        viewHolder.tvw2.setText(viagem[position]);
        return r;
    }

    class ViewHolder{
        TextView tvw1;
        TextView tvw2;

        ViewHolder(View v){
            tvw1=(TextView)v.findViewById(R.id.tvprofilename);
            tvw2=(TextView)v.findViewById(R.id.tvemail);
        }
    }
}

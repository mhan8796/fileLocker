package com.example.guohuan.filelocker;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Guohuan on 11/13/2015.
 */
public class loadImageFileAs extends AsyncTask<String,Void,Bitmap> {

    Context context;
    ImageView im;
    File f;
    Bitmap[] pic;
    int position;
    public loadImageFileAs(Context context , ImageView im,File f,Bitmap[] pic,int position)
    {
        this.context = context;
        this.im=im;
        this.f=f;
        this.pic=pic;
        this.position=position;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return method.decodeFile(f);
    }

    protected void onPostExecute(Bitmap result){
        result=method.rotaingImageView(method.readPictureDegree(f.getAbsolutePath()),result);
        pic[position]=result;
        im.setImageBitmap(result);

    }

}

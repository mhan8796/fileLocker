package com.example.guohuan.filelocker;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Guohuan on 11/15/2015.
 */
public class scanAs extends AsyncTask<String,Void,String> {
    ListView scanFileListView;
    ArrayList<HashMap<String,Object>> fileList;//file and name
    String type;
    Activity context;
    ProgressDialog progressDialog;
    Bitmap[] pic;
    public scanAs(Activity context,ListView scanFileListView,String type)
    {
        this.scanFileListView=scanFileListView;
        this.type=type;
        this.context=context;
    }
    @Override
    protected void onPreExecute()
    {
        progressDialog=new ProgressDialog(context,0);
        progressDialog.setTitle("Scanning for "+type+" file");
        progressDialog.show();
        fileList=new ArrayList<HashMap<String,Object>>();
    }
    @Override
    protected String doInBackground(String... params) {
        List sdlist=method.getExtSDCardPaths();
        File[] sd=new File[sdlist.size()];
        for(int i=0;i<sdlist.size();i++)
        {
            sd[i]=new File(sdlist.get(i).toString());
        }
        if(type.equalsIgnoreCase("pdf")) {
            scan(sd,"pdf");
        }

        if(type.equals("image"))
        {
            scan(sd,"png");
            scan(sd,"jpg");
            scan(sd,"tif");
            scan(sd,"bpm");

        }
        return null;
    }

    @Override
    protected void onPostExecute(String result)
    {
        progressDialog.dismiss();
        pic=new Bitmap[fileList.size()];
        setupAdapter();
    }

    private void setupAdapter() {
        String[] from=new String[1];
        int[] to=new int[1];
        from[0]="name";
        to[0]= R.id.fileSytemItemTextView;
        SimpleAdapter fileAdapter =new SimpleAdapter(context,
                fileList,R.layout.filesystemlistviewitem,from,to)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.filesystemlistviewitem, null);
                String typeGetView=fileType.getFileType(((File) fileList.get(position).get("file")).getAbsolutePath());
                ImageView im=(ImageView) convertView.findViewById(R.id.fileSytemItemImageView);
                TextView name=(TextView) convertView.findViewById(R.id.fileSytemItemTextView);
                name.setText(((File) fileList.get(position).get("file")).getName());
                if(typeGetView.equalsIgnoreCase("PDF"))
                {
                    //im.setBackground(getResources().getDrawable(R.drawable.pdf));
                    im.setImageDrawable(context.getResources().getDrawable(R.drawable.pdf));
                }
                else if(typeGetView.equalsIgnoreCase("folder"))
                {

                }
                else if(type.equalsIgnoreCase("image")&&
                        (typeGetView.equalsIgnoreCase("png")
                        ||typeGetView.equalsIgnoreCase("jpg")
                        ||typeGetView.equalsIgnoreCase("tif")
                        ||typeGetView.equalsIgnoreCase("bpm")))
                {

                    // Drawable d = Drawable.createFromPath(((File) fileList.get(position).get("file")).getAbsolutePath());
                    //im.setImageBitmap(method.decodeFile(((File) fileList.get(position).get("file"))));
                    //im.setImageDrawable(d);

                    if(pic[position]==null)
                    {
                        im.setImageDrawable(context.getResources().getDrawable(R.drawable.picture));
                        loadImageFileAs l=new loadImageFileAs(context,im,(File) fileList.get(position).get("file"),pic,position);
                        l.execute();
                    }
                    else
                    {
                        im.setImageBitmap(pic[position]);
                    }
                }
                else
                {
                    im.setImageDrawable(context.getResources().getDrawable(R.drawable.other));
                }

                return convertView;
            }
        };
        scanFileListView.setAdapter(fileAdapter);
    }

    private void scan(File[] fileL,String typeS) {

        for(int i=0;i< fileL.length;i++)
        {
            if(fileL[i]!=null&&fileL[i].exists()) {
                if ((fileType.getFileType(fileL[i].getAbsolutePath()).equalsIgnoreCase(typeS))) {
                    HashMap<String, Object> item = new HashMap<String, Object>();
                    item.put("file", fileL[i]);
                    item.put("name", fileL[i].getName());
                    fileList.add(item);
                } else if ((fileType.getFileType(fileL[i].getAbsolutePath()).equalsIgnoreCase("folder")
                        &&(fileL[i]
                        .listFiles()!=null)
                        && (fileL[i].listFiles().length > 0))) {
                    scan(fileL[i].listFiles(), typeS);
                }
            }
        }
    }
}

package com.example.guohuan.filelocker;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class fileSystem extends AppCompatActivity {

    ListView fileSystemListView;
    List<Map<String, Object>> fileList;
    SimpleAdapter fileAdapter;
    TextView pathTextView;
    Stack oldPath;
    Bitmap[] pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_system);

        oldPath=new Stack();
        getSupportActionBar().setTitle("");
        pathTextView= (TextView) findViewById(R.id.pathTextView);
        pathTextView.setText("/storage");
        fileList=new ArrayList<Map<String, Object>>();
        fileSystemListView=(ListView) findViewById(R.id.fileSystemListView);
        List sdCardList=method.getExtSDCardPaths();
        //Toast.makeText(fileSystem.this,""+sdCardList.size(),Toast.LENGTH_LONG).show();
        populateList(sdCardList);
        setAdapter();
        setListener();


    }

    private void setListener() {
        fileSystemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (fileType.getFileType(((File) fileList.get(position).get("file")).getAbsolutePath()).equalsIgnoreCase("folder")) {

                    if (((File) fileList.get(position).get("file")).exists()&&
                            fileType.getFileType(((File) fileList.get(position).get("file")).getAbsolutePath()).equalsIgnoreCase("folder"))
                    //&&((File)fileList.get(position).get("file")).listFiles().length>0 )
                    {
                        oldPath.add(((File) fileList.get(position).get("file")));
                        pathTextView.setText(((File) fileList.get(position).get("file")).getAbsolutePath());
                        ArrayList temp = new ArrayList();
                        File[] fileArray = ((File) fileList.get(position).get("file")).listFiles();
                        if (fileArray != null) {
                            for (File f : fileArray) {
                                temp.add(f);
                            }
                        }
                        populateList(temp);
                        //fileAdapter.notifyDataSetChanged();
                        setAdapter();
                        //Toast.makeText(fileSystem.this,fileType.getFileHeader(((File) fileList.get(position).get("file")).getAbsolutePath()),Toast.LENGTH_LONG).show();
                      }
            }
                       // Toast.makeText(fileSystem.this,fileType.getFileType(((File) fileList.get(position).get("file")).getAbsolutePath()),Toast.LENGTH_LONG).show();
}
        });
    }

    private void populateList(List fileL) {
        //fileList=new ArrayList();
        fileList.clear();
        for(int i=0;i< fileL.size();i++)
        {
            HashMap item=new HashMap();
            item.put("text_content",new File(fileL.get(i).toString()).getName());
            item.put("file",new File(fileL.get(i).toString()));
            fileList.add(item);
        }
        pic=new Bitmap[fileList.size()];
    }


    private void setAdapter() {
        String[] from=new String[1];
        int[] to=new int[1];
        from[0]="text_content";
        to[0]= R.id.fileSytemItemTextView;
        fileAdapter =new SimpleAdapter(fileSystem.this,
                fileList,R.layout.filesystemlistviewitem,from,to)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                LayoutInflater inflater = (LayoutInflater) fileSystem.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.filesystemlistviewitem, null);
                String type=fileType.getFileType(((File) fileList.get(position).get("file")).getAbsolutePath());
                ImageView im=(ImageView) convertView.findViewById(R.id.fileSytemItemImageView);
                TextView name=(TextView) convertView.findViewById(R.id.fileSytemItemTextView);
                name.setText(((File) fileList.get(position).get("file")).getName());
                if(type.equalsIgnoreCase("PDF"))
                {
                    //im.setBackground(getResources().getDrawable(R.drawable.pdf));
                    im.setImageDrawable(getResources().getDrawable(R.drawable.pdf));
                }
                else if(type.equalsIgnoreCase("folder"))
                {

                }
                else if(type.equalsIgnoreCase("png")
                        ||type.equalsIgnoreCase("jpg")
                        ||type.equalsIgnoreCase("tif")
                        ||type.equalsIgnoreCase("bpm"))
                {

                   // Drawable d = Drawable.createFromPath(((File) fileList.get(position).get("file")).getAbsolutePath());
                    //im.setImageBitmap(method.decodeFile(((File) fileList.get(position).get("file"))));
                    //im.setImageDrawable(d);

                    if(pic[position]==null)
                    {
                        im.setImageDrawable(getResources().getDrawable(R.drawable.picture));
                        loadImageFileAs l=new loadImageFileAs(fileSystem.this,im,(File) fileList.get(position).get("file"),pic,position);
                        l.execute();
                    }
                    else
                    {
                        im.setImageBitmap(pic[position]);
                    }
                }
                else
                {
                    im.setImageDrawable(getResources().getDrawable(R.drawable.other));
                }

                return convertView;
            }
        };
        //Toast.makeText(fileSystem.this,"  "+(fileAdapter==null),Toast.LENGTH_LONG).show();
        fileSystemListView.setAdapter(fileAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file_system, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            oldPath.clear();
            pathTextView.setText("/storage");
            fileList=new ArrayList<Map<String, Object>>();
            fileSystemListView=(ListView) findViewById(R.id.fileSystemListView);
            List sdCardList=method.getExtSDCardPaths();
            //Toast.makeText(fileSystem.this,""+sdCardList.size(),Toast.LENGTH_LONG).show();
            populateList(sdCardList);
            setAdapter();
            //setListener();
            return true;
        }
        else
        if (id == R.id.action_back) {
            //Toast.makeText(fileSystem.this,((File)oldPath.remove()).getAbsolutePath()+"",Toast.LENGTH_LONG).show();


            if(oldPath.size()>1) {
                oldPath.pop();
                File fi = (File) oldPath.pop();
                //Toast.makeText(fileSystem.this,oldPath.toString(),Toast.LENGTH_LONG).show();
                pathTextView.setText(fi.getAbsolutePath());
                oldPath.add(fi);
                ArrayList temp = new ArrayList();
                File[] fileArray = fi.listFiles();
                if (fileArray != null) {
                    for (File f : fileArray) {
                        temp.add(f);
                    }
                }
                populateList(temp);
                //fileAdapter.notifyDataSetChanged();
                setAdapter();
            }
            else
            {
                if(oldPath.size()==0)
                {
                    overridePendingTransition(0,0);
                    finish();
                }
                else {
                    oldPath.clear();
                    pathTextView.setText("/storage");
                    fileList = new ArrayList<Map<String, Object>>();
                    fileSystemListView = (ListView) findViewById(R.id.fileSystemListView);
                    List sdCardList = method.getExtSDCardPaths();
                    //Toast.makeText(fileSystem.this,""+sdCardList.size(),Toast.LENGTH_LONG).show();
                    populateList(sdCardList);
                    fileAdapter.notifyDataSetChanged();
                    setAdapter();
                    //  setListener();
                }
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}

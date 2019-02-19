package com.example.guohuan.filelocker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class scanfileActivity extends AppCompatActivity {
    ListView scanFileListView;
    String typeToScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanfile);
        typeToScan=getIntent().getStringExtra(menuActivity.type);
        getSupportActionBar().setTitle(typeToScan);
        scanFileListView= (ListView) findViewById(R.id.scanFileListView);
        scanAs s=new scanAs(scanfileActivity.this,scanFileListView,typeToScan);
        s.execute();
    }


}

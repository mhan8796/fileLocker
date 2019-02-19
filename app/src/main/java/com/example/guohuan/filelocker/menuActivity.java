package com.example.guohuan.filelocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class menuActivity extends AppCompatActivity {
    ListView MenulistView;
    public static String type="type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenulistView= (ListView) findViewById(R.id.MenulistView);
        setUpAdapter();
        setUpListener();
    }

    private void setUpListener() {
        MenulistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch( position)
                {
                    case 0:
                        Intent intent=new Intent(menuActivity.this,fileSystem.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    case 1:
                        intent=new Intent(menuActivity.this,scanfileActivity.class);
                        intent.putExtra(type,"image");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    case 2:
                        intent=new Intent(menuActivity.this,scanfileActivity.class);
                        intent.putExtra(type,"pdf");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;

                }


            }
        });
    }

    private void setUpAdapter() {
        String[] menu={"All files","image","pdf"};
        MenulistView.setAdapter(new ArrayAdapter<>(menuActivity.this,android.R.layout.simple_expandable_list_item_1 ,menu));
    }
}

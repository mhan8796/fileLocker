package com.example.guohuan.filelocker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDbHelper myDbHelper;
    SQLiteDatabase mDb;
    Cursor dbCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDbHelper = new MyDbHelper(MainActivity.this);
        mDb = myDbHelper.getWritableDatabase();
        dbCursor = mDb.rawQuery("SELECT * FROM " + MyDbHelper.LOGIN_TABLE_NAME, null);
        if (!dbCursor.moveToFirst()) {
            setContentView(R.layout.activity_main_nopass);

            final EditText password1 = (EditText) findViewById(R.id.newPass_editText);
            final EditText password2 = (EditText) findViewById(R.id.newPassC_editText);
            Button signUp = (Button) findViewById(R.id.signUp_button);

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String p1 = password1.getText().toString();
                    String p2 = password2.getText().toString();

                    if(p1.length()!=0&&p2.length()!=0){
                        if(p1.matches(p2)){
                            ContentValues newValues = new ContentValues();
                            newValues.put(MyDbHelper.LOGIN_COL_PASSWORD, p1);
                            mDb.insert(
                                    MyDbHelper.LOGIN_TABLE_NAME,
                                    null,
                                    newValues
                            );
                            Toast.makeText(MainActivity.this,"password created",Toast.LENGTH_SHORT).show();
                            Intent newIntent = new Intent(MainActivity.this,menuActivity.class);
                            startActivity(newIntent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"password doesn't match",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Please enter both passwords",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            setContentView(R.layout.activity_main_pass);
            final EditText password = (EditText) findViewById(R.id.pass_editText);
            Button login = (Button) findViewById(R.id.login_button);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String p = password.getText().toString();
                    String[] allColumns = new String[]{"_id", MyDbHelper.LOGIN_COL_PASSWORD};
                    dbCursor = mDb.query(true,
                            MyDbHelper.LOGIN_TABLE_NAME, allColumns,
                            null, null, MyDbHelper.LOGIN_COL_PASSWORD, null, null,null);
                    if (dbCursor != null)
                        dbCursor.moveToFirst();

                    if (dbCursor.moveToFirst()){
                        String data = dbCursor.getString(dbCursor.getColumnIndex(MyDbHelper.LOGIN_COL_PASSWORD));
                        if(data.matches(p)){
                            Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                            Intent newIntent = new Intent(MainActivity.this,menuActivity.class);
                            startActivity(newIntent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Password incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

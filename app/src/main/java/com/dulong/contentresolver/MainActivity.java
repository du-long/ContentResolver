package com.dulong.contentresolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String INSERT_URI = "content://com.dulong.contentprovider.PersonContentProvider/insert";
    public static final String DELECT_URI = "content://com.dulong.contentprovider.PersonContentProvider/delect";
    public static final String UPDATE_URI = "content://com.dulong.contentprovider.PersonContentProvider/update";
    public static final String QUERY_URI = "content://com.dulong.contentprovider.PersonContentProvider/query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContentResolver contentResolver = getContentResolver();
        ContentValues contentValues;
        switch (v.getId()) {
            case R.id.btn_insert:
                contentValues = new ContentValues();
                contentValues.put("name", "杜龙");
                contentValues.put("sex", "男");
                contentValues.put("age", 20);
                contentResolver.insert(Uri.parse(INSERT_URI), contentValues);
                break;
            case R.id.btn_delete:
                contentResolver.delete(Uri.parse(DELECT_URI), "name=?", new String[]{"杜龙"});
                break;
            case R.id.btn_update:
                contentValues = new ContentValues();
                contentValues.put("name", "杜龙");
                contentValues.put("sex", "男");
                contentValues.put("age", 27);
                contentResolver.update(Uri.parse(UPDATE_URI), contentValues, "name=?", new String[]{"杜龙"});
                break;
            case R.id.btn_query:
                Cursor cursor = contentResolver.query(Uri.parse(QUERY_URI), null, "name=?", new String[]{"杜龙"}, "id desc");
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String sex = cursor.getString(cursor.getColumnIndex("sex"));
                    int age = cursor.getInt(cursor.getColumnIndex("age"));
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    Log.i("dl--", name + "-------------" + sex + "-------------" + age + "-------------" + id);
                }
                break;
        }
    }
}

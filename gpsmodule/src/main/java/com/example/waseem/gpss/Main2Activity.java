package com.example.waseem.gpss;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends Activity {

    ImageView deleteitem;
    private ArrayList countries =  new ArrayList<>();
    EditText ed;
    ImageView btn,clearbtn;
    DtabaeHelper mydb;
    Cursor cursor;
    Uri gmmIntentUri;
    Intent mapIntent_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ed = (EditText) findViewById(R.id.fav);
        btn = (ImageView) findViewById(R.id.add);
        mydb = new DtabaeHelper(this);



        cursor = mydb.checkitems();
        if(cursor.getCount()==0){

            Toast.makeText(getApplicationContext(),"Database is Empty",Toast.LENGTH_SHORT).show();
        }
        else {
            StringBuffer data = new StringBuffer();
            while (cursor.moveToNext()){
                countries.add(cursor.getString(1));
            }
        }

        initViews();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fav_data = ed.getText().toString();
                if(fav_data.equals("")){
                 Toast.makeText(getApplicationContext(),"Please Enter Your Fav Location",Toast.LENGTH_SHORT).show();
                }
                else {
                    countries.add(fav_data);
                    ed.setText("");
                    mydb.insertitem(fav_data);
                    initViews();
                }
            }
        });

        clearbtn = (ImageView)findViewById(R.id.clear);
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countries.clear();
                initViews();
                mydb.deleteAll();

            }
        });
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new DataAdapter(countries);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    String location = (String) countries.get(position);

                    Main2Activity.this.gmmIntentUri = Uri
                            .parse("geo:0,0?q="+location);
                    Main2Activity.this.mapIntent_new = new Intent(
                            "android.intent.action.VIEW",
                            Main2Activity.this.gmmIntentUri);


                    Main2Activity.this.mapIntent_new
                            .setPackage("com.google.android.apps.maps");
                    Main2Activity.this
                            .startActivity(Main2Activity.this.mapIntent_new);

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
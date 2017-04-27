package com.cclz.myapp_170427_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        Intent it=getIntent();
        String msg=it.getStringExtra("msg");
        if(msg != null){
            TextView tv=(TextView) findViewById(R.id.textView);
            tv.setText(msg);
        }
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView tv2=(TextView) findViewById(R.id.textView2);
                String value=dataSnapshot.getValue(String.class);
                tv2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void clickSave(View V){

        EditText ed=(EditText) findViewById(R.id.editText);
        myRef.setValue(ed.getText().toString());
    }
}

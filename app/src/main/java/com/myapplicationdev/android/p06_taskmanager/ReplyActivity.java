package com.myapplicationdev.android.p06_taskmanager;

import android.content.Intent;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReplyActivity extends AppCompatActivity {
    DBHelper db;
    ArrayList<Task> tasks;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        CharSequence reply = null;
        Intent intent = getIntent();
        tv = (TextView)findViewById(R.id.tv);
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null){
            reply = remoteInput.getCharSequence("status");
        }
        DBHelper dbh = new DBHelper(this);
        if(reply != null){
            Toast.makeText(ReplyActivity.this, "You have indicated: " + reply,
                    Toast.LENGTH_SHORT).show();
            if(reply.toString().equalsIgnoreCase("Completed")){

                Task task = (Task)intent.getSerializableExtra("task");
                dbh.deleteTask(task.getId());
                tv.setText(task.getId()+"");
                startActivityForResult();
            }else{

            }
        }
    }
}

package com.neo.tasks;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button add;
    private RecyclerView listView;
    private List<String> list = new ArrayList<String>();
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//Hide title bar
        initView();
        initAction();
    }

    private void initView() {//initiallize all
        add = findViewById(R.id.add);//correlate button
        listView = findViewById(R.id.listView);//relate listview
        adapter = new ListAdapter(list, this);//initialize adaptar
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);//adaptor works
    }

    private void initAction() {// make components work
        //onclick listener for button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText inputText = new EditText(MainActivity.this);//new an input bar for users to enter task
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Enter your task")//the title of the input bar
                        .setView(inputText)//put the message in
                        .setNegativeButton("cancel", null);//cancel button does not need to have a manipulation
                builder.setPositiveButton("add", new DialogInterface.OnClickListener() {//添加按钮 点击后进入点击事件

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String data = inputText.getText().toString();//get the text user enter
                        if (!data.equals("")) {//if the user writes something
                            list.add(data);//put the text into the list
                            adapter.notifyDataSetChanged();//refresh the listview with adaptor
                        } else {//if no message recieved, hint the user
                            Toast.makeText(MainActivity.this, "Are you kidding me?", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
            }
        });
    }
}
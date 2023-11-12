package com.example.myapplication; // Replace 'yourpackage' with your actual package name

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ListView listView;
    private ArrayList<String> itemList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize your views
        editText = findViewById(R.id.e1);
        listView = findViewById(R.id.lv1);
        Button addButton = findViewById(R.id.b1);

        // Initialize your data
        itemList = new ArrayList<>();

        // Initialize your adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        // Set click listener for the "ADD ITEM" button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    // Method to handle "ADD ITEM" button click
    public void add() {
        String newItem = editText.getText().toString();
        if (!newItem.isEmpty()) {
            itemList.add(newItem);
            adapter.notifyDataSetChanged();
            editText.setText(""); // Clear the EditText after adding an item
        }
    }
}

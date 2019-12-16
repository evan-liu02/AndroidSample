package com.example.android.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.android.R;
import com.example.android.adapter.TestAdapter;
import com.example.android.adapter.TestMultiLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView rv = findViewById(R.id.rv);
        List<String> dataList = new ArrayList<String>();
        dataList.add("Item1");
        dataList.add("Item2");
        dataList.add("Item3");
//        TestAdapter adapter = new TestAdapter(this, dataList, R.layout.layout_recycler_one);
        TestMultiLayoutAdapter adapter = new TestMultiLayoutAdapter(this, dataList, new int[]{R.layout.layout_item_header, R.layout.layout_recycler_one});
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
}

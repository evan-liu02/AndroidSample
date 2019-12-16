package com.example.android.adapter;

import android.content.Context;

import com.example.android.R;

import java.util.List;

public class TestAdapter extends BaseAdapter<String> {

    public TestAdapter(Context context, List<String> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void onBindData(BaseHolder holder, String data, int position) {
        holder.setText(R.id.text, "Item" + position);
    }
}

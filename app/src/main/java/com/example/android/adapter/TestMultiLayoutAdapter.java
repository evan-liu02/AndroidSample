package com.example.android.adapter;

import android.content.Context;
import android.util.Log;

import com.example.android.R;

import java.util.List;

public class TestMultiLayoutAdapter extends MultiLayoutBaseAdapter<String> {

    public TestMultiLayoutAdapter(Context context, List<String> dataList, int[] layoutIds) {
        super(context, dataList, layoutIds);
    }

    @Override
    public int getItemType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public void onBindData(BaseHolder holder, String data, int position, int itemViewType) {
        Log.e("Test", "onBindData: " + position + ", " + itemViewType);
        if (itemViewType == 1) {
            holder.setText(R.id.text, data);
        }
    }
}

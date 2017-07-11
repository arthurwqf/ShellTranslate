package com.xingyun.jiujiugk.main.technology.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingchen.pulltorefresh.WrapRecyclerView;
import com.xingyun.jiujiugk.R;
import com.xingyun.jiujiugk.common.BaseAutoLoadMoreAdapter;

import java.util.ArrayList;

/**
 * Created by wangqf on 2017/2/27 0027.
 */

public class FragmentTechnologyCenter extends Fragment {

    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.refresh_recycler_view, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mContext = getContext();

        WrapRecyclerView recyclerView = (WrapRecyclerView) view.findViewById(R.id.wrv);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        ArrayList<String> mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("这里是item " + i);
        }
        final TechRecyclerViewAdapter adapter = new TechRecyclerViewAdapter(mContext, recyclerView, mData);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadmoreListener(new BaseAutoLoadMoreAdapter.OnLoadmoreListener() {
            @Override
            public void onLoadmore() {
                adapter.onNothing();
            }
        });
        adapter.setOnItemClickListener(new BaseAutoLoadMoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private class TechRecyclerViewAdapter extends BaseAutoLoadMoreAdapter<String> {

        public TechRecyclerViewAdapter(Context context, WrapRecyclerView recyclerView, ArrayList<String> data) {
            super(context, recyclerView, data);
        }

        @Override
        public BaseViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
            return new MyBaseViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.recyclerview_list_item, parent,
                    false));

        }

        @Override
        public int getItemHeight(RecyclerView.ViewHolder holder) {
            holder.itemView.measure(0, 0);
            return holder.itemView.getMeasuredHeight();
        }

        @Override
        public void onBindBaseViewHolder(BaseViewHolder holder, int position) {
            MyBaseViewHolder myHolder = (MyBaseViewHolder) holder;
            myHolder.tv_name.setText(mData.get(position));
        }

        class MyBaseViewHolder extends BaseViewHolder {
            TextView tv_name;

            public MyBaseViewHolder(View v) {
                super(v);
                tv_name = (TextView) v.findViewById(R.id.tv_name);
            }
        }
    }
}

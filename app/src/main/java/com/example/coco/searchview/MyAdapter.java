package com.example.coco.searchview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by coco on 2017/11/6.
 * 适配器  显示多布局
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final String TAG = "MyAdapter";
    private final int NORMAL_TYPE = 0;
    private final int FOOT_TYPE = 11111;
    private List<String> list;//数据
    private int mMaxCount = 15;//最大显示数
    private int mDataCount = 0;
    private IListener mIListener;

    public MyAdapter(List<String> list, IListener mIListener) {
        this.list = list;
        this.mIListener = mIListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View normal_views = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.rv_item, parent, false);
        View foot_view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.clear_item, parent, false);
        if (viewType == FOOT_TYPE) {
            return new ViewHolder(foot_view, FOOT_TYPE);
        } else if (viewType == NORMAL_TYPE) {
            return new ViewHolder(normal_views, NORMAL_TYPE);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) != FOOT_TYPE) {
            holder.mTv.setText(list.get(position));
        } else {
            Log.e(TAG, "onBindViewHolder: 填充底部容器");
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() < mMaxCount) {
            mDataCount = list.size();
            return list.size();
        }
        mDataCount = mMaxCount;
        return mMaxCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mDataCount - 1) {
            return FOOT_TYPE;
        }
        return NORMAL_TYPE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTv;
        public RelativeLayout mFoot;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == NORMAL_TYPE) {
                mTv = itemView.findViewById(R.id.mTv);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mIListener.normalItemClick(list.get(ViewHolder.this.getAdapterPosition()));
                    }
                });
            } else if (viewType == FOOT_TYPE) {
                mFoot = (RelativeLayout) itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mIListener.clearClick();
                    }
                });
            }
        }
    }

    public interface IListener {
        void normalItemClick(String s);

        void clearClick();
    }
}

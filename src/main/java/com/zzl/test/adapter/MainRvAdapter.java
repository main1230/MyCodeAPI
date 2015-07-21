package com.zzl.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzl.test.R;

/**
 * Description:
 * Created by zhangzl
 * Date: 15/7/1
 */
public class MainRvAdapter extends RecyclerView.Adapter {
    private String[] data_;

    public void setData(String[] data) {
        this.data_ = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_cell, viewGroup, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder vh = (MyViewHolder) viewHolder;

        vh.getTv().setText(data_[i]);

        final int pos = i;
        if (onItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(view, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data_.length;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int postion);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    private OnItemClickListener onItemClickListener;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private View root;
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.root = itemView;
            this.tv = (TextView) root.findViewById(R.id.tv);
        }

        public TextView getTv() {
            return tv;
        }
    }
}

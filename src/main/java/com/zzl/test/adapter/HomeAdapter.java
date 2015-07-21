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
 * Date: 15/7/21
 */
public class HomeAdapter extends RecyclerView.Adapter {
    /**
     * 数据
     */
    private String[] data_;

    /**
     * 设置数据
     * @param data
     */
    public void setData(String[] data) {
        this.data_ = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_home, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        // 设置文本内容
        myViewHolder.tv.setText(data_[position]);

        // 设置点击监听事件
        if (onClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClickListener(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data_.length;
    }

    public interface OnClickListener {
        void onClickListener(View view, int position);
    }

    private OnClickListener onClickListener;
    public void setOnClickListener(OnClickListener clickListener) {
        this.onClickListener = clickListener;
    }

    /**
     * ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}

package com.fanxiaoyudemo.magicalwardrobe.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fanxiaoyudemo.magicalwardrobe.R;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.LinearViewHolder>
{

    private Context myContext;
    private OnItemClickListener myListener;

    public GridAdapter(Context context, OnItemClickListener listener)
    {
        this.myContext = context;
        this.myListener = listener;
    }

    @NonNull
    @Override
    public LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        return new LinearViewHolder(LayoutInflater.from(myContext).inflate(R.layout.layout_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearViewHolder viewHolder, final int i)
    {
        viewHolder.TV_TITLE.setText("卫衣时尚穿搭(*╹▽╹*)");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                myListener.onClick(i);
                Toast.makeText(myContext, "click:" + i, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return 50;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView IV_ADVICE;
        private TextView TV_TITLE;

        public LinearViewHolder(@NonNull View itemView)
        {
            super(itemView);
            IV_ADVICE = (ImageView)itemView.findViewById(R.id.iv_advice);
            TV_TITLE = (TextView) itemView.findViewById(R.id.tv_title);

        }
    }

    public interface OnItemClickListener
    {
        void onClick(int pos);
    }
}

package com.fanxiaoyudemo.magicalwardrobe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanxiaoyudemo.magicalwardrobe.Advertisement.AdevertisementActivity;
import com.fanxiaoyudemo.magicalwardrobe.R;

public class LinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private Context myContext;
    private OnItemClickListener myListener;

    public LinearAdapter(Context context, OnItemClickListener listener)
    {
        this.myContext = context;
        this.myListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
            return new LinearViewHolder(LayoutInflater.from(myContext).inflate(R.layout.layout_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i)
    {
//        ((LinearViewHolder)viewHolder).IV_ADVICE.setImageResource(R.drawable.cake);
          ((LinearViewHolder)viewHolder).TV_TITLE.setText("卫衣时尚穿搭(*^▽^*)");
         viewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
//             myListener.onClick(i);
//             Toast.makeText(myContext, "Click" + i, Toast.LENGTH_SHORT).show();

                  Intent intent = new Intent(myContext, AdevertisementActivity.class);
                  myContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return 2;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder
    {

        private TextView TV_TITLE;
        private ImageView IV_ADVICE;

        public LinearViewHolder(@NonNull View itemView)
        {
            super(itemView);
            TV_TITLE = (TextView) itemView.findViewById(R.id.tv_title);
            IV_ADVICE = (ImageView) itemView.findViewById(R.id.iv_advice);
        }
    }

    public interface OnItemClickListener
    {
        void onClick(int pos);
    }
}


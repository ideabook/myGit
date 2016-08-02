package com.example.qiao.mygit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 一 on 2016/7/21.
 */
public class MyRecyclerAdapter2 extends RecyclerView.Adapter<MyRecyclerAdapter2.MyViewHolder> {
    private List<String> mDatas,mDatas2;
    private Context mContext;
    private LayoutInflater inflater;
    private List<Integer> mHeights;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }


    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    public MyRecyclerAdapter2(Context context, List<String> datas ) {
        this.mContext = context;
        this.mDatas = datas;
        mHeights=new ArrayList<Integer>();
        for (int i=0;i<mDatas.size();i++)
        {
            mHeights.add((int)(200+Math.random()*500));
        }
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout. item_home,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;

    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
//        holder.btn.setText(mDatas2.get(position));
        RecyclerView.LayoutParams lp= (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        lp.height=mHeights.get(position);
        holder.itemView.setLayoutParams(lp);
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.tv_item);
        }
    }
}

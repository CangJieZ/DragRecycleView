package sean.com.drag;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

import sean.com.drag.drag.DragHolderCallBack;
import sean.com.drag.drag.RecycleCallBack;


/**
 * Author: zhuwt
 * Date: 2016/6/21 18:07
 * Description: 说明
 * PackageName: com.ancun.autopack.ProjectAdapter
 * Copyright: 杭州存网络科技有限公司
 **/
public class DragAdapter extends RecyclerView.Adapter<DragAdapter.DragHolder> {

    private List<String> list;

    private RecycleCallBack mRecycleClick;
    public SparseArray<Integer> show = new SparseArray<>();

    public DragAdapter(RecycleCallBack click, List<String> data) {
        this.list = data;
        this.mRecycleClick = click;
    }

    public void setData(List<String> data) {
        this.list = data;
    }

    @Override
    public DragHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        return new DragHolder(view, mRecycleClick);
    }

    @Override
    public void onBindViewHolder(final DragHolder holder, final int position) {
        holder.text.setText(list.get(position));
        if (null == show.get(position))
            holder.del.setVisibility(View.INVISIBLE);
        else
            holder.del.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class DragHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener, DragHolderCallBack {

        public TextView text;
        public ImageView del;
        public RelativeLayout item;
        private RecycleCallBack mClick;

        public DragHolder(View itemView, RecycleCallBack click) {
            super(itemView);
            mClick = click;
            item = (RelativeLayout) itemView.findViewById(R.id.item);
            text = (TextView) itemView.findViewById(R.id.text);
            del = (ImageView) itemView.findViewById(R.id.del);
            item.setOnClickListener(this);
            del.setOnClickListener(this);
        }

        @Override
        public void onSelect() {
            show.clear();
            show.put(getAdapterPosition(), getAdapterPosition());
            itemView.setBackgroundColor(Color.LTGRAY);
            del.setVisibility(View.VISIBLE);
        }

        @Override
        public void onClear() {
            itemView.setBackgroundResource(R.drawable.right_bottom_view);
            notifyDataSetChanged();
        }

        @Override
        public void onClick(View v) {
            if (null != mClick) {
                show.clear();
                mClick.itemOnClick(getAdapterPosition(), v);
            }
        }
    }
}

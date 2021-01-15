package com.siren.liu.adapter.recycler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.siren.liu.adapter.recycler.delegate.ItemViewDelegate;
import com.siren.liu.adapter.recycler.delegate.ItemViewDelegateManager;
import com.siren.liu.adapter.recycler.holder.RVBaseViewHolder;

import java.util.List;

/**
 * Created by LiuG on 2018/12/6.
 */

public class RVBaseAdapter<T> extends RecyclerView.Adapter<RVBaseViewHolder> {
    private Context mContext;
    private List<T> mData;
    private ItemViewDelegateManager mItemViewDelegateManager;
    private OnItemClickListener mItemClickListener;
    private RVBaseViewHolder mHolder;

    public RVBaseAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public int getItemViewType(int position) {
        if (hasMultiItemViewDelegate()) {
            return mItemViewDelegateManager.getItemViewType(mData, position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//viewType就是getItemViewType方法的返回值
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int mLayoutId = itemViewDelegate.getItemViewLayoutId();
        mHolder = RVBaseViewHolder.createRVBaseViewHolder(mContext, parent, mLayoutId);
        onViewHolderCreated(mHolder, mHolder.getRoot());
        addClickListener(mHolder, viewType);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(final RVBaseViewHolder holder, final int position) {
        convert(holder, position);
    }

    protected void onViewHolderCreated(RVBaseViewHolder holder, View itemView) {

    }

    public RVBaseViewHolder getHolder() {
        return mHolder;
    }

    protected boolean isEnabled(int viewType) {//点击事件的扩展
        return true;
    }

    final private void convert(RVBaseViewHolder holder, int position) {
        mItemViewDelegateManager.convert(holder, mData, position);
    }

    protected void addClickListener(final RVBaseViewHolder holder, final int viewType) {
        if (!isEnabled(viewType)) {
            return;
        }
        holder.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, holder, holder.getAdapterPosition());
                }
            }
        });

        holder.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemClickListener != null) {
                    return mItemClickListener.onItemLongClick(v, holder, holder.getAdapterPosition());
                }
                return false;
            }
        });
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        mData = data;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    private boolean hasMultiItemViewDelegate() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public RVBaseAdapter addItemViewDelegate(ItemViewDelegate delegate) {
        mItemViewDelegateManager.addItemViewDelegate(delegate);
        return this;
    }

    public RVBaseAdapter addItemViewDelegate(int viewType, ItemViewDelegate delegate) {
        mItemViewDelegateManager.addItemViewDelegate(viewType, delegate);
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, RVBaseViewHolder holder, int position);

        boolean onItemLongClick(View itemView, RVBaseViewHolder holder, int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}

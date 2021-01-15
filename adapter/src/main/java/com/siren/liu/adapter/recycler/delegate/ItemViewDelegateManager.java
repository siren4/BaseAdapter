package com.siren.liu.adapter.recycler.delegate;

import androidx.collection.SparseArrayCompat;

import com.siren.liu.adapter.recycler.holder.RVBaseViewHolder;

import java.util.List;

/**
 * Created by LiuG on 2019/1/2.
 */

public class ItemViewDelegateManager<T> {

    private SparseArrayCompat<ItemViewDelegate> itemViewDelegates;

    public ItemViewDelegateManager() {
        itemViewDelegates = new SparseArrayCompat<>();
    }

    public ItemViewDelegate getItemViewDelegate(int viewType) {
        return itemViewDelegates.get(viewType);
    }

    public void addItemViewDelegate(ItemViewDelegate delegate) {
        if (delegate == null) {
            return;
        }
        int viewType = itemViewDelegates.size();
        itemViewDelegates.put(viewType, delegate);
    }

    public void addItemViewDelegate(int viewType, ItemViewDelegate delegate) {
        if (itemViewDelegates.get(viewType) != null) {
            throw new IllegalArgumentException("An ItemViewDelegate is already registered for the viewType = "
                    + viewType
                    + ". Already registered ItemViewDelegate is "
                    + itemViewDelegates.get(viewType));
        }
        itemViewDelegates.put(viewType, delegate);
    }

    public int getItemViewDelegateCount() {
        return itemViewDelegates.size();
    }

    public int getItemViewType(List<T> datas, int position) {
        int delegateCount = itemViewDelegates.size();
        for (int i = delegateCount - 1; i >= 0; i--) {
            ItemViewDelegate<T> delegate = itemViewDelegates.valueAt(i);
            if (delegate.isForViewType(datas, position)) {
                return itemViewDelegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source"
        );
    }

    public void convert(RVBaseViewHolder holder, List<T> datas, int position) {
        for (int i = 0; i < itemViewDelegates.size(); i++) {
            ItemViewDelegate delegate = itemViewDelegates.valueAt(i);
            if (delegate.isForViewType(datas, position)) {
                delegate.convert(holder, datas, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }
}

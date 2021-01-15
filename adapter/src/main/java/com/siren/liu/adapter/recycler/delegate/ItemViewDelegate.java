package com.siren.liu.adapter.recycler.delegate;

import com.siren.liu.adapter.recycler.holder.RVBaseViewHolder;

import java.util.List;

/**
 * Created by LiuG on 2019/1/2.
 */

public interface ItemViewDelegate<T> {

    /**
     * itemView的layoutId
     *
     * @return
     */
    int getItemViewLayoutId();

    /**
     * 多样式布局实现的关键
     *
     * @param datas
     * @param position
     * @return 返回true的时候，就用getItemViewLayoutId()进行布局
     */
    boolean isForViewType(List<T> datas, int position);

    /**
     * 更新数据
     *
     * @param holder
     * @param datas
     * @param position
     */
    void convert(RVBaseViewHolder holder, List<T> datas, int position);
}

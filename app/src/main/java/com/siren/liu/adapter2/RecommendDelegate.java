package com.siren.liu.adapter2;

import com.siren.liu.adapter.recycler.delegate.ItemViewDelegate;
import com.siren.liu.adapter.recycler.holder.RVBaseViewHolder;

import java.util.List;

/**
 * Created by LiuG on 2019/1/11.
 */

public final class RecommendDelegate implements ItemViewDelegate<RecommendData> {

    @Override
    public int getItemViewLayoutId() {
        return 0;
    }

    @Override
    public boolean isForViewType(List<RecommendData> datas, int position) { //显示RecommendDelegate样式的规则，如列表每隔4个显示。
        return position % 4 == 0;
    }

    @Override
    public void convert(RVBaseViewHolder holder, List<RecommendData> datas, int position) {

    }
}

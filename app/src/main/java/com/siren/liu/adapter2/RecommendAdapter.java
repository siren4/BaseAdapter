package com.siren.liu.adapter2;

import android.content.Context;

import com.siren.liu.adapter.recycler.RVBaseAdapter;

import java.util.List;

/**
 * Created by LiuG on 2019/1/11.
 */

public final class RecommendAdapter<RecommendData> extends RVBaseAdapter {

    public RecommendAdapter(Context context, List<RecommendData> mData) {
        super(context, mData);//子类必须调用父类的构造函数

        //如果方法是protected修饰的,RecommendAdapter能够直接调用但方法返回的RVBaseAdapter对象却不能链式调用addItemViewDelegate。
        //因为RecommendAdapter调用是子类调用父类的protected方法，但RVBaseAdapter调用相当于在其他包内调用protected方法。
        addItemViewDelegate(new RecommendDelegate())
                .addItemViewDelegate(new RecommendDelegate())
                .addItemViewDelegate(new RecommendDelegate())
                .addItemViewDelegate(new RecommendDelegate());
    }

}

package com.siren.liu.adapter.recycler.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.siren.liu.adapter.Orientation;

/**
 * Created by LiuG on 2019/3/22.
 */
public class StaggerDecoration extends RecyclerView.ItemDecoration {

    private int rowSpace;
    private int columnSpace;
    private Orientation orientation;
    private int spanCount;

    public StaggerDecoration(int rowSpace, int columnSpace, int spanCount, Orientation orientation) {
        this.rowSpace = rowSpace;
        this.columnSpace = columnSpace;
        this.spanCount = spanCount;
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (orientation == Orientation.VERTICAL) {
            if (position % spanCount == 0) {
                outRect.left = 0;
            } else {
                outRect.left = columnSpace;
            }
            if (position < spanCount) {
                outRect.top = 0;
            } else {
                outRect.top = rowSpace;
            }
        } else {
            if (position % spanCount == 0) {
                outRect.top = 0;
            } else {
                outRect.top = rowSpace;
            }
            if (position < spanCount) {
                outRect.left = 0;
            } else {
                outRect.left = columnSpace;
            }
        }
    }
}

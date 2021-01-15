package com.siren.liu.adapter.recycler.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.siren.liu.adapter.Orientation;

/**
 * Created by LiuG on 2019/3/22.
 */
public class LinearDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private Orientation orientation;

    public LinearDecoration(int space, Orientation orientation) {
        this.space = space;
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (orientation == Orientation.VERTICAL) {
            if (position == 0) {
                outRect.top = 0;
            } else {
                outRect.top = space;
            }
        } else {
            if (position == 0) {
                outRect.left = 0;
            } else {
                outRect.left = space;
            }
        }
    }
}

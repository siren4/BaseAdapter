package com.siren.liu.adapter.recycler.holder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by LiuG on 2019/1/2.
 */

public class RVBaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArrayCompat<View> views;
    private View root;
    private Context mContext;

    private RVBaseViewHolder(Context context, View itemView) {
        super(itemView);
        views = new SparseArrayCompat<>();
        mContext = context;
        root = itemView;
    }

    public static RVBaseViewHolder createRVBaseViewHolder(Context context, ViewGroup parent, @LayoutRes int mLayoutId) {
        View itemView = LayoutInflater.from(context).inflate(mLayoutId, parent, false);
        RVBaseViewHolder viewHolder = new RVBaseViewHolder(context, itemView);
        return viewHolder;
    }

    public static RVBaseViewHolder createRVBaseViewHolder(Context context, View itemView) {
        RVBaseViewHolder viewHolder = new RVBaseViewHolder(context, itemView);
        return viewHolder;
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = root.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public Context getContext() {
        return mContext;
    }

    public View getRoot() {
        return root;
    }

    public TextView getTextView(@IdRes int viewId) {
        return getView(viewId);
    }

    public Button getButton(@IdRes int viewId) {
        return getView(viewId);
    }

    public ImageView getImageView(@IdRes int viewId) {
        return getView(viewId);
    }

    public EditText getEditText(@IdRes int viewId) {
        return getView(viewId);
    }

    public ProgressBar getProgressBar(@IdRes int viewId) {
        return getView(viewId);
    }

    public RatingBar getRatingBar(@IdRes int viewId) {
        return getView(viewId);
    }

    public RVBaseViewHolder setText(@IdRes int viewId, String text) {
        getTextView(viewId).setText(text);
        return this;
    }

    public RVBaseViewHolder setText(@IdRes int viewId, @StringRes int strId) {
        getTextView(viewId).setText(strId);
        return this;
    }

    public RVBaseViewHolder setImageResource(@IdRes int viewId, @DrawableRes int resId) {
        getImageView(viewId).setImageResource(resId);
        return this;
    }

    public RVBaseViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        getImageView(viewId).setImageBitmap(bitmap);
        return this;
    }

    public RVBaseViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
        getImageView(viewId).setImageDrawable(drawable);
        return this;
    }

    public RVBaseViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        getView(viewId).setBackgroundColor(color);
        return this;
    }

    public RVBaseViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        getView(viewId).setBackgroundResource(backgroundRes);
        return this;
    }

    public RVBaseViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        getTextView(viewId).setTextColor(textColor);
        return this;
    }

    public RVBaseViewHolder setTextColorRes(@IdRes int viewId, @ColorRes int textColorRes) {
        getTextView(viewId).setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    public RVBaseViewHolder setVisible(@IdRes int viewId, boolean visible) {
        getView(viewId).setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public boolean getVisible(@IdRes int viewId) {
        return getView(viewId).getVisibility() == View.VISIBLE;
    }


    public RVBaseViewHolder setProgress(@IdRes int viewId, int progress) {
        getProgressBar(viewId).setProgress(progress);
        return this;
    }

    public RVBaseViewHolder setMax(@IdRes int viewId, int max) {
        getProgressBar(viewId).setMax(max);
        return this;
    }

    public RVBaseViewHolder setProgress(@IdRes int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * 星星进度条
     *
     * @param viewId
     * @param rating
     * @return
     */
    public RVBaseViewHolder setRating(@IdRes int viewId, float rating) {
        getRatingBar(viewId).setRating(rating);
        return this;
    }

    public RVBaseViewHolder setRating(@IdRes int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    @SuppressLint("NewApi")
    public RVBaseViewHolder setAlpha(@IdRes int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * 设置超链接<web、phone、email、map>
     *
     * @param viewId
     * @return
     */
    public RVBaseViewHolder addLinkify(@IdRes int viewId) {
        TextView textView = getView(viewId);
        Linkify.addLinks(textView, Linkify.ALL);
        return this;
    }

    /**
     * 设置字体
     *
     * @param typeface 1.字体库一般放在assets目录，即时加载性能损耗很大，建议应用启动时就加载.
     *                 2.Typeface.createFromAsset(mContext.getAssets(), "fonts/regular.otf").
     * @param viewIds
     * @return
     */
    public RVBaseViewHolder setTypeface(Typeface typeface, @IdRes int... viewIds) {
        for (int viewId : viewIds) {
            TextView textView = getView(viewId);
            textView.setTypeface(typeface);
            textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public RVBaseViewHolder setTag(@IdRes int viewId, Object tag) {
        getView(viewId).setTag(tag);
        return this;
    }

    /**
     * 保存key-value
     *
     * @param viewId
     * @param key    key的长度必须大于24位，一般是自定义id.
     * @param tag
     * @return
     */
    public RVBaseViewHolder setTag(@IdRes int viewId, @IdRes int key, Object tag) {
        getView(viewId).setTag(key, tag);
        return this;
    }

    /**
     * check状态
     *
     * @param viewId
     * @param checked
     * @return
     */
    public RVBaseViewHolder setChecked(@IdRes int viewId, boolean checked) {
        Checkable view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    public RVBaseViewHolder setOnClickListener(View.OnClickListener onClickListener, @IdRes int... viewIds) {
        for (int viewId : viewIds) {
            getView(viewId).setOnClickListener(onClickListener);
        }
        return this;
    }

    public RVBaseViewHolder setOnTouchListener(View.OnTouchListener onTouchListener, @IdRes int... viewIds) {
        for (int viewId : viewIds) {
            getView(viewId).setOnTouchListener(onTouchListener);
        }
        return this;
    }

    public RVBaseViewHolder setOnLongClickListener(View.OnLongClickListener onLongClickListener, @IdRes int... viewIds) {
        for (int viewId : viewIds) {
            getView(viewId).setOnLongClickListener(onLongClickListener);
        }
        return this;
    }

}

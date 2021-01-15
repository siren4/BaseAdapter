package com.siren.liu.adapter.pager;


import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by LiuG on 2019/1/21.
 */
public class BaseFragmentAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> fragments;
    private SparseArray<String> tabs;

    public BaseFragmentAdapter(FragmentManager fm, SparseArray<Fragment> fragments) {
        this(fm, fragments, null);
    }

    public BaseFragmentAdapter(FragmentManager fm, SparseArray<Fragment> fragments, SparseArray<String> tabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position >= 0 && position < getCount()) {
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        fragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (tabs != null && tabs.size() > position) {
            return tabs.get(position);
        }
        return null;
    }

    public void setData(SparseArray<Fragment> fragments, SparseArray<String> tabs) {
        this.fragments = fragments;
        this.tabs = tabs;
        notifyDataSetChanged();
    }

    public Fragment getFragment(int position) {
        if (position >= 0 && position < getCount()) {
            return fragments.get(position);
        }
        return null;
    }
}

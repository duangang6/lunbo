package com.example.lunbo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ContentPagerAdapter extends FragmentStateAdapter {
    private List<ContentFragment> datas;

    public ContentPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<ContentFragment> datas) {
        //构造方法参数为页面和datas
        super(fragmentActivity);
        this.datas = datas;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
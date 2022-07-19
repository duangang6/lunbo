package com.example.lunbo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {
    //私有变量（图片id）
    private int content;


    public ContentFragment(int content) {
        //构造函数参数为图片id
        this.content = content;
    }

    //私有变量（图片）
    private ImageView tv_content;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建界面
        View view = inflater.inflate(R.layout.view, container, false);
        //获得view里面的图片模板控件
        tv_content = view.findViewById(R.id.imageView);
        //将图片放在模板控件里面
        tv_content.setImageResource(content);
        return view;
    }

    public void setContent(int content) {
        this.content = content;
        tv_content.setImageResource(content);
    }
}

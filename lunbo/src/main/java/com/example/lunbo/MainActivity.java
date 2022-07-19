package com.example.lunbo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewpager;
    List<ContentFragment> datas;
    ImageView imageViews[];//指示器图片数组
    ImageView imageView;//指示器的控件
    ViewGroup group;
    boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewpager = findViewById(R.id.viewpager);
        //获取线性布局
        group = findViewById(R.id.viewGroup);

        //创建ContentFragment对象（返回值为view）
        new ContentFragment(R.drawable.test1);
        //创建动态数组
        datas = new ArrayList<>();
        //添加元素（页面）
        datas.add(new ContentFragment(R.drawable.test1));
        datas.add(new ContentFragment(R.drawable.test2));
        datas.add(new ContentFragment(R.drawable.test3));

        //适配器
        ContentPagerAdapter adapter = new ContentPagerAdapter(this, datas);
        //添加
        viewpager.setAdapter(adapter);

        //创建页面滑动监听
        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                for (int i = 0; i < imageViews.length; i++) {
                    imageViews[position].setBackgroundResource(R.mipmap.zhishiqi1);
                    if (position != i) {
                        imageViews[i].setBackgroundResource(R.mipmap.zhishiqi0);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning = true;
                while(isRunning){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int cur_item=viewpager.getCurrentItem();
                            cur_item=(cur_item+1)%imageViews.length;
                            viewpager.setCurrentItem(cur_item);
                        }
                    });
                }
            }
        }).start();
    }

    public void initPointer() {
        imageViews = new ImageView[3];
        for (int i = 0; i < imageViews.length; i++) {
            imageView = new ImageView(this);
            //设置控件的宽高
            imageView.setLayoutParams(new LinearLayoutCompat.LayoutParams(85, 85));
            //设置控件的padding属性（间隔）
            imageView.setPadding(20, 0, 20, 0);
            imageViews[i] = imageView;
            //初始化第一个page页面的图片的原点为选中状态
            if (i == 0) {
                //表示当前图片
                imageViews[i].setBackgroundResource(R.mipmap.zhishiqi1);
                /**
                 * 在java代码中动态生成ImageView的时候
                 * 要设置其BackgroundResource属性才有效
                 * 设置ImageResource属性无效
                 */

            } else {
                imageViews[i].setBackgroundResource(R.mipmap.zhishiqi0);
            }
            group.addView(imageViews[i]);
        }
    }

}


package com.example.user.semiprojectteam2.tab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.user.semiprojectteam2.MemoListActivity;
import com.example.user.semiprojectteam2.R;
import com.example.user.semiprojectteam2.adapter.MemoAdapter;
import com.example.user.semiprojectteam2.data.MemoBean;
import com.example.user.semiprojectteam2.data.SaveBean;
import com.example.user.semiprojectteam2.data.Util;
import com.example.user.semiprojectteam2.tab1.MemoWriteActivity;
import com.google.gson.Gson;

import java.util.List;

public class MemoDetailActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private List<MemoBean> mList;
    private ViewPager mPager;
    private MemoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 액티비티의 최상단 status bar를 숨긴다. (setContentView보다 먼저 나온다.)
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 액티비티의 타이틀을 숨긴다. (setContentView보다 먼저 나온다.)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_detail_memo);

        mTabLayout = findViewById(R.id.tabLayout);
        mPager = findViewById(R.id.pager);

        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDel = findViewById(R.id.btnDel);


        mTabLayout.addTab(mTabLayout.newTab().setText("글쓰기"));
        mTabLayout.addTab(mTabLayout.newTab().setText("사진찍기"));
        mTabLayout.addTab(mTabLayout.newTab().setText("위치정보"));

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);//탭의 가로 전체 사이즈 지정
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        //프래그먼트를 관리할 PagerAdapter를 생성한다.
        PagerAdatper2 pagerAdatper = new PagerAdatper2(getSupportFragmentManager(),mTabLayout.getTabCount());
        mPager.setAdapter(pagerAdatper);

        //Tab 레이아웃과 ViewPager를 이벤트로 서로 연결시켜준다.
        //ViewPager가 움직였을 때, 탭이 바뀌게끔 한다.
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        //탭레이아웃이 바뀌면 ViewPager의 Fragment도 바뀌는 작업을 연결한다.
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //현재 사용자가 바꾼 탭의 이벤트가 넘어온다.
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MemoBean memoBean = (MemoBean)getIntent().getSerializableExtra(MemoBean.class.getName());

                Intent i = new Intent(MemoDetailActivity.this, MemoWriteActivity.class);
                i.putExtra(MemoBean.class.getName(), memoBean);
                startActivity(i);

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MemoBean memoBean = (MemoBean)getIntent().getSerializableExtra(MemoBean.class.getName());
                Integer selIdx = (Integer)memoBean.getSelIdx();

                Gson gson = new Gson();
                String jsonStr = Util.openFile(MemoDetailActivity.this, SaveBean.class.getName());
                SaveBean saveBean = gson.fromJson(jsonStr, SaveBean.class);
                saveBean.getMemoBeanList().remove( selIdx.intValue() );

                //저장
                String jsonStr2 = gson.toJson(saveBean);
                Util.saveFile(MemoDetailActivity.this, SaveBean.class.getName(), jsonStr2);

                Intent i = new Intent(MemoDetailActivity.this, MemoListActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });


    }
}

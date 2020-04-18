package com.example.user.semiprojectteam2.tab1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.semiprojectteam2.tab2.Tab1Fragment;
import com.example.user.semiprojectteam2.tab2.Tab2Fragment;
import com.example.user.semiprojectteam2.tab2.Tab3Fragment;

/**
 * 뷰 페이져 어댑터 이다.
 * @since 2018.07.13
 * @author 조수연
 */

public class PagerAdatper1 extends FragmentStatePagerAdapter {
    //탭의 갯수를 저장하고 있는 멤버변수
    private int mNumOfTabs;
    public PagerAdatper1(FragmentManager fm, int numOfTabs) {
        super(fm);
        mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        //BaseAdapter에서 getView()메서드에 해당하는 메서드로써,
        //position 값이 곧! 현재 선택된 Tab의 Index번호를 나타낸다.

        switch (position){
            case 0:
                Fragment1Activity tab1 = new Fragment1Activity();
                return tab1;
            case 1:
                Fragment2Activity tab2 = new Fragment2Activity();
                return tab2;
            case 2:
                Fragment3Activity tab3 = new Fragment3Activity();
                return tab3;

        }
        return null;
    }

    @Override
    public int getCount() {
        //전체 탭의 갯수를 넘긴다.
        return mNumOfTabs;
    }
}

package com.example.user.semiprojectteam2.tab2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * 뷰 페이져 어댑터 이다.
 * @since 2018.07.13
 * @author 조수연
 */

public class PagerAdatper2 extends FragmentStatePagerAdapter {
    //탭의 갯수를 저장하고 있는 멤버변수
    private int mNumOfTabs;
    public PagerAdatper2(FragmentManager fm, int numOfTabs) {
        super(fm);
        mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        //BaseAdapter에서 getView()메서드에 해당하는 메서드로써,
        //position 값이 곧! 현재 선택된 Tab의 Index번호를 나타낸다.

        switch (position){
            case 0:
                Tab1Fragment tab1 = new Tab1Fragment();
                return tab1;
            case 1:
                Tab2Fragment tab2 = new Tab2Fragment();
                return tab2;
            case 2:
                Tab3Fragment tab3 = new Tab3Fragment();
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

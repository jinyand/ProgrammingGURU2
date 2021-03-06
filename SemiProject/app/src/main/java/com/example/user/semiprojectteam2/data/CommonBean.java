package com.example.user.semiprojectteam2.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 모든 Bean 클래스의 최상위 클래스
 * 모든 Bean 클래스의 공통되는 부분을 기술한다.
 * Created by user on 2018-07-17.
 */

public class CommonBean implements Serializable {

    /* 등록 날짜 */
    private String regDate;

    public String getRegDate() {

        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}

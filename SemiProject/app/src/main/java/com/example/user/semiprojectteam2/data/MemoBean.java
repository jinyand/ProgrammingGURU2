package com.example.user.semiprojectteam2.data;

/**
 * 메모 정보 Bean
 * Created by user on 2018-07-17.
 */

public class MemoBean extends CommonBean {

    // List에서 현재 나의 Row의 Index를 저장하는 변수
    private int selIdx;

    private String imgPath;
    private String memo;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getSelIdx() {
        return selIdx;
    }

    public void setSelIdx(int selIdx) {
        this.selIdx = selIdx;
    }
}

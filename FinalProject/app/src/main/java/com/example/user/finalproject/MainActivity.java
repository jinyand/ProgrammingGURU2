package com.example.user.finalproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.user.finalproject.tab.TabMainActivity;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent i = new Intent(getApplicationContext(), TabMainActivity.class );
            startActivity(i);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //액티비티의 타이틀을 숨긴다.
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //다시 화면에 들어왔을때 예약 걸어주기
        handler.postDelayed(r, 2000); //2초 뒤에 Runnable객체 수행
    }

    @Override
    protected void onPause() {
        super.onPause();
        //화면을 벗어나면, handler에 예약해놓은 작업을 취소하자
        handler.removeCallbacks(r);
    }
}

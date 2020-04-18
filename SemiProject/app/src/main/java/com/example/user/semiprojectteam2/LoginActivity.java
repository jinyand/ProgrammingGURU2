package com.example.user.semiprojectteam2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.semiprojectteam2.data.MemberBean;
import com.example.user.semiprojectteam2.data.MemoBean;
import com.example.user.semiprojectteam2.data.SaveBean;
import com.example.user.semiprojectteam2.tab1.Fragment2Activity;
import com.example.user.semiprojectteam2.tab1.MemoWriteActivity;
import com.example.user.semiprojectteam2.tab1.PagerAdatper1;
import com.google.gson.Gson;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextView mTxtLoginId;
    private Button mBtnUpdate, mBtnLogout;
    private SaveBean mSaveBean;
    private ImageView mImgCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //액티비티의 최상단 status bar 를 숨긴다.(setContentView보다 먼저 나온다.)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //액티비티의 타이틀을 숨긴다. (setContentView보다 먼저 나온다.)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);

        String jsonStr = Util.openFile(LoginActivity.this, SaveBean.class.getName());
        Gson gson = new Gson();
        mSaveBean = gson.fromJson(jsonStr, SaveBean.class);

        final MemberBean bean = mSaveBean.getMemberBean();

        mImgCamera = findViewById(R.id.imgCamera);
        mTxtLoginId = findViewById(R.id.txtLoginId);
        mBtnLogout = findViewById(R.id.btnLogout);
        mBtnUpdate = findViewById(R.id.btnUpdate);

        mTxtLoginId.setText(bean.getMemberId());

        final EditText edtId = findViewById(R.id.edtId);
        final EditText edtPw = findViewById(R.id.edtPw);
        final EditText edtPwCheck = findViewById(R.id.edtPwCheck);

        edtId.setText(bean.getMemberId());
        edtPw.setText(bean.getMemberPw());

        if(bean != null && bean.getImgPath() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile( bean.getImgPath() );
            mImgCamera.setImageBitmap(bitmap);
        }

        mTxtLoginId.setText("회원ID: " + mSaveBean.getMemberBean().getMemberId());

        mBtnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //MemberBean updBean = new MemberBean();
                String jsonStr = Util.openFile(LoginActivity.this, SaveBean.class.getName());
                Gson gson = new Gson();
                SaveBean saveBean = gson.fromJson(jsonStr, SaveBean.class);

                MemberBean updBean = saveBean.getMemberBean();

                //saVEbEAN안에 있는 멤버빈을 넣어준다.
                updBean.setMemberName(edtId.getText().toString());
                updBean.setMemberPw(edtPw.getText().toString());
                updBean.setMemberPw(edtPwCheck.getText().toString());

                //세이브빈을 저장
                saveBean.setMemberBean(updBean);
                String jsonStr2 = gson.toJson(saveBean);
                Util.saveFile(LoginActivity.this, SaveBean.class.getName(), jsonStr2);

                if (!edtPw.getText().toString().equals(edtPwCheck.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "비밀번호가 일치하는지 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT).show();
                } else if (edtPw.getText().toString().equals(edtPwCheck.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "회원 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();

                    // 수정된 데이터의 Bean 생성
                    updBean.setMemberId(edtId.getText().toString());
                    updBean.setMemberPw(edtPw.getText().toString());

                    //저장을 파일로 해야지
                    //SaveBean saveBean = new SaveBean();
                    //saveBean.setMemberBean( updBean );

                    //Gson gson = new Gson();
                    //String jsonStr = gson.toJson(saveBean);
                    //Util.saveFile(LoginActivity.this, SaveBean.class.getName(), jsonStr );

                    finish();
                }

            }
        });

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
    }

}//end onCreate()


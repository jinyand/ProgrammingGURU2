package com.example.user.semiprojectteam2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.semiprojectteam2.data.MemberBean;
import com.example.user.semiprojectteam2.data.SaveBean;
import com.example.user.semiprojectteam2.tab1.MemoWriteActivity;
import com.google.gson.Gson;

public class UpdateActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final MemberBean bean = (MemberBean)getIntent().getSerializableExtra(MemberBean.class.getName());

        final EditText edtId = findViewById(R.id.edtId);
        final EditText edtPw = findViewById(R.id.edtPw);
        final EditText edtPwCheck = findViewById(R.id.edtPwCheck);
        final ImageView imgCamera = findViewById(R.id.imgCamera);

        edtId.setText(bean.getMemberName());
        edtPw.setText(bean.getMemberPw());
        edtPwCheck.setText(bean.getMemberPw());

        if(bean != null && bean.getImgPath() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile( bean.getImgPath() );
            imgCamera.setImageBitmap(bitmap);
        }

        Button btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonStr= Util.openFile(UpdateActivity.this, SaveBean.class.getName());
                Gson gson = new Gson();
                SaveBean saveBean = gson.fromJson(jsonStr, SaveBean.class);

                MemberBean updBean = saveBean.getMemberBean();

                //saVEbEAN안에 있는 멤버빈을 넣어준다.
                updBean.setMemberName(edtId.getText().toString());
                updBean.setMemberPw(edtPw.getText().toString());
                updBean.setMemberPw(edtPwCheck.getText().toString());

                //세이브빈을 저장
                saveBean.setMemberBean( updBean );
                String jsonStr2 = gson.toJson(saveBean);
                Util.saveFile(UpdateActivity.this, SaveBean.class.getName(),jsonStr2 );



                finish();
            }
        });
    }
}

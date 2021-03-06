package com.example.user.finalproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.finalproject.PostViewActivity;
import com.example.user.finalproject.R;
import com.example.user.finalproject.bean.FCBean;
import com.example.user.finalproject.bean.FoundBean;
import com.example.user.finalproject.bean.SaveBean;
import com.example.user.finalproject.bean.Util;
import com.example.user.finalproject.tab.TabMainActivity;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by user on 2018-07-31.
 */

public class ChatAdapter extends BaseAdapter {

    private Context mContext;
    private List<FCBean> mList;
    private int mFoundBeanIdx;
    private LayoutInflater mInflater;


    public ChatAdapter(Context context, int foundBeanIdx, List<FCBean> list) {
        mContext = context;
        mList = list;
        mFoundBeanIdx = foundBeanIdx;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(R.layout.chat_view, null);

        final FCBean bean = mList.get(position);

        TextView txtWrite = convertView.findViewById(R.id.txtWrite);
        txtWrite.setText(bean.getComment());
        TextView txtDate = convertView.findViewById(R.id.txtDate);
        txtDate.setText(bean.getRegDate());

        Button btnDel = convertView.findViewById(R.id.btnDel);
        btnDel.setTag(position);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();
                String jsonStr = Util.openFile(mContext, SaveBean.class.getName());
                SaveBean saveBean = gson.fromJson(jsonStr, SaveBean.class);

                // 선택된 삭제 버튼의 메모된 인덱스 번호를 취득
                Integer selIdx = (Integer) v.getTag();

                if (selIdx != null) {
                    //화면상 삭제
                    mList.remove(selIdx.intValue()); // 해당 인덱스의 데이터 삭제

                    //저장
                    saveBean.getFoundBeanList().get(mFoundBeanIdx).getFcBeanList().remove(selIdx.intValue());
                    String jsonStr2 = gson.toJson(saveBean);
                    Util.saveFile(mContext,SaveBean.class.getName(),jsonStr2);

                    ChatAdapter.this.notifyDataSetInvalidated(); // 어댑터 리프레쉬
                }
            }
        });

        return convertView;
    }
}

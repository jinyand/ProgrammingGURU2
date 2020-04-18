package com.example.user.semiprojectteam2.tab2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.semiprojectteam2.LoginActivity;
import com.example.user.semiprojectteam2.MainActivity;
import com.example.user.semiprojectteam2.R;
import com.example.user.semiprojectteam2.Util;
import com.example.user.semiprojectteam2.data.MemberBean;
import com.example.user.semiprojectteam2.data.MemoBean;
import com.example.user.semiprojectteam2.data.SaveBean;
import com.google.gson.Gson;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {


    public Tab1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        //TODO 여기에 View를 찾고 이벤트를 등록하고 등등의 처리를 할 수 있다.

        MemoBean memoBean = (MemoBean)getActivity().getIntent().getSerializableExtra(MemoBean.class.getName());

        TextView txtMemoView = view.findViewById(R.id.txtMemoView);


        if(memoBean != null) {
            txtMemoView.setText(memoBean.getMemo());
        }

        return view;
    }

}
package com.example.user.semiprojectteam2.tab2;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.semiprojectteam2.R;
import com.example.user.semiprojectteam2.data.MemberBean;
import com.example.user.semiprojectteam2.data.MemoBean;
import com.example.user.semiprojectteam2.data.SaveBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2Fragment extends Fragment {
    private SaveBean mSaveBean;
    private ImageView mImgCamera;
    private String mPhotoPath;


    public Tab2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        // Inflate the layout for this fragment
        MemoBean bean = (MemoBean)getActivity().getIntent().getSerializableExtra(MemoBean.class.getName());

        // bean.setImgPath( mPhotoPath );
        mImgCamera = view.findViewById(R.id.imgCamera);

        if(bean != null && bean.getImgPath() != null ) {
            Bitmap bitmap = BitmapFactory.decodeFile( bean.getImgPath() );
            mImgCamera.setImageBitmap(bitmap);
        }

        return view;
    }

}

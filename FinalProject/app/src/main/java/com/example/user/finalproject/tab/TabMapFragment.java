package com.example.user.finalproject.tab;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.finalproject.R;
import com.example.user.finalproject.bean.FoundBean;
import com.example.user.finalproject.bean.LostBean;
import com.example.user.finalproject.bean.SaveBean;
import com.example.user.finalproject.bean.Util;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by user on 2018-07-27.
 */

public class TabMapFragment extends Fragment {

    private FoundBean mFoundBean;
    private LostBean mLostBean;
    private LocationManager mLocManager;
    private LatLng mCurPosLatLng = new LatLng(37.6281894, 127.0897268); // 현재 위치의 위도, 경도,
    private static SupportMapFragment mMapFragment;
    private SaveBean mSaveBean;
    public static final String MEMBER_FILE_NAME = "memberFileName";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.map_view, container, false);

        if(mMapFragment == null) {
            mMapFragment = SupportMapFragment.newInstance();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.map_container, mMapFragment).commit();
        } else {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.map_container, mMapFragment).commit();
        }

        mMapFragment.getMapAsync(onMapReadyCallback);

        // GPS가 켜져 있는지 확인한다.
        mLocManager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
        if(!mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(i);
        }

        return view;
    }; //end onCreateView()


    public OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(final GoogleMap googleMap) {

            googleMap.getUiSettings().setZoomControlsEnabled(true);

            String jsonStr = Util.openFile(getContext(), SaveBean.class.getName());

            if(jsonStr != null) {
                Gson gson = new Gson();
                mSaveBean = gson.fromJson(jsonStr, SaveBean.class);
            }

            if(mSaveBean != null && mSaveBean.getFoundBeanList() != null) {
                List<FoundBean> list = mSaveBean.getFoundBeanList();
                List<LostBean> list2 = mSaveBean.getLostBeanList();
                float hue = 270;
                for(int i=0; i<list.size(); i++) {
                    FoundBean foundBean = list.get(i);
                    if(foundBean.getLatitude() == 0) {
                        continue;
                    }
                    MarkerOptions markerOptions = new MarkerOptions();
                    mCurPosLatLng = new LatLng(foundBean.getLatitude(), foundBean.getLongitude());
                    markerOptions.position(mCurPosLatLng).icon(BitmapDescriptorFactory.defaultMarker(hue));
                    markerOptions.title("Found");
                    markerOptions.snippet("습득물");
                    googleMap.addMarker(markerOptions).showInfoWindow();
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(mCurPosLatLng));
                }

                for(int i=0; i<list2.size(); i++) {
                    LostBean lostBean = list2.get(i);
                    if(lostBean.getLatitude() == 0) {
                        continue;
                    }
                    MarkerOptions markerOptions = new MarkerOptions();
                    mCurPosLatLng = new LatLng(lostBean.getLatitude(), lostBean.getLongitude());
                    markerOptions.position(mCurPosLatLng);
                    markerOptions.title("Lost");
                    markerOptions.snippet("분실물");
                    googleMap.addMarker(markerOptions).showInfoWindow();

                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(mCurPosLatLng));
                }
            }

            if(mCurPosLatLng != null) {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mCurPosLatLng, 17));
                mCurPosLatLng = null;
            }
        }
    };



}

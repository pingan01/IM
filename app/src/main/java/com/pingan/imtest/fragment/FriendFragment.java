package com.pingan.imtest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pingan.imtest.R;

public class FriendFragment extends Fragment {

    public static FriendFragment instance = null;   // 单例模式

    public static FriendFragment newInstance() {

        if (instance == null) {
            instance = new FriendFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend, container, false);
    }
}

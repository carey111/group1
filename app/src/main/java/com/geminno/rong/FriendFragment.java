package com.geminno.rong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2016/10/21.
 */
public class FriendFragment extends Fragment {
    public static FriendFragment instance = null;//单例模式

    public static FriendFragment getInstance() {
        if (instance == null) {
            instance = new FriendFragment();
        }
        return instance;
    }

    private View mView;
    private Button mButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.friend_fragment,null);
        mButton = (Button) mView.findViewById(R.id.friend);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RongIM.getInstance()!=null){
                    RongIM.getInstance().startPrivateChat(getActivity(),"1","私人聊天");
                }
            }
        });


//        TextView tv = new TextView(getActivity());
//        tv.setText("第三页");
//        return tv;
        return mView;

    }

}
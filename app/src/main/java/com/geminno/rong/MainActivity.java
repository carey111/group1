package com.geminno.rong;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.geminno.rong.pojo.Friend;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String token1 = "yVOHN+v9p4pN6VSxxBqSy+Ti1mHV1bzsBudRVcrx0Llb0nHAujsAFCyRPOfT2PLZep1h9Svlnij4kXgHRGUiXQ==";
    private static final String token2 = "SmNhI5AaV4YtkWNoUQKiR+Ti1mHV1bzsBudRVcrx0Llb0nHAujsAFG1poWk5k27tA/ljRHbDgZL4kXgHRGUiXQ==";
    private List<Friend> userIdList;
    private static final String TAG = "MainActivity";

    private Button mUser1, mUser2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUser1 = (Button) findViewById(R.id.connect_1);
        mUser2 = (Button) findViewById(R.id.connect_2);
        mUser1.setOnClickListener(this);
        mUser2.setOnClickListener(this);

        initUserInfo();

    }

    private void connectRongServer(String token) {

        RongIM.connect(token, new RongIMClient.ConnectCallback() {


            @Override
            public void onSuccess(String userId) {

                if (userId.equals("1")) {
                    mUser1.setText("用户1连接服务器成功");
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    Toast.makeText(MainActivity.this, "connect server success 1", Toast.LENGTH_SHORT).show();

                } else {

                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    Toast.makeText(MainActivity.this, "connect server success 2", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                // Log.e("onError", "onError userid:" + errorCode.getValue());//获取错误的错误码
                Log.e(TAG, "connect failure errorCode is : " + errorCode.getValue());
            }


            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "token is error ,please check token and appkey");
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.connect_1) {
            connectRongServer(token1);
        } else if (v.getId() == R.id.connect_2) {
            connectRongServer(token2);
        }
    }

    private void initUserInfo() {
        userIdList = new ArrayList<Friend>();
        userIdList.add(new Friend(1, "liu", "http://10.40.5.25:8080/web/imgs/photo.png"));
        userIdList.add(new Friend(2, "li", "http://10.40.5.25:8080/web/imgs/aa.png"));
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {
                for (Friend i : userIdList) {
                    if ((i.getUserId()+"").equals(s)) {
                        Log.e(TAG, i.getPortraitUri());
                        return new UserInfo(i.getUserId()+"", i.getName(), Uri.parse(i.getPortraitUri()));
                    }
                }
                Log.e("MainActivity", "UserId is : " + s);
                return null;
            }
        }, true);
    }

}

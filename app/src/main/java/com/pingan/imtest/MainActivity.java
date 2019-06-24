package com.pingan.imtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pingan.imtest.app.APP;
import com.pingan.imtest.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends AppCompatActivity implements RongIM.UserInfoProvider {

    private ArrayList<UserInfo> userIdList = new ArrayList<UserInfo>();

    private String token1 = "ZNAziyntBtrp52aCGzpVXRHiydkzJWhq/Yc5mTrDu4qI90CXj6bYrUROAj9nFaF70WnqHdRG4bAuLAViEtye+OdfjD45jtXj";//huanhuan

    private String token2 = "JihsyhNcBMj8/nJqOi4SkSGmnRXxT+QJQdfE9tPQ5WBZ/JZqV1XV+OB6VCEgDukdL7g3C+Ab/oh9Q7NeWrietA==";//shishi

    private String token3 = "YkWTkWC3NDkFgAICibueHhHiydkzJWhq/Yc5mTrDu4qI90CXj6bYreHDoF4jN6WmruEzogx1Ff3CzUT73wm55w==";//anan

    private String token4 = "q+FiGKRJrH/vpRvBkIysmCGmnRXxT+QJQdfE9tPQ5WCtseYcrYB+0/pBPxUUrefrbbeXoBLTlpCEcKywqFKbCQ==";//qiqi

    private TextView mTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mTxt = findViewById(R.id.txtDiscussionID);

        initUserInfo();

        connect(token1);

        initData();

    }

    private void initData() {

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btn_conversationlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //启动会话列表
                //startActivity(new Intent(MainActivity.this, HomeActivity.class));
                startActivity(new Intent(MainActivity.this, ConversationListActivity.class));

            }
        });

        findViewById(R.id.discussionID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                creatDiscussion();
            }
        });

        findViewById(R.id.btn_Discussion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RongIM.getInstance().startDiscussionChat(MainActivity.this, mTxt.getText().toString().trim(), "讨论组");
            }
        });
    }

    /**
     * 初始化用户信息
     */
    private void initUserInfo() {

        userIdList.add(new UserInfo("huanhuan", "欢欢", "http://pic1.win4000.com/pic/e/f1/4fb01408746.jpg"));
        userIdList.add(new UserInfo("shishi", "诗诗", "http://pic1.win4000.com/pic/f/63/088a1410587.jpg"));
        userIdList.add(new UserInfo("anan", "安安", "http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));

        RongIM.setUserInfoProvider(this, true);

    }

    private void creatDiscussion() {

        List<String> list = new ArrayList<>();
        list.add("shishi");
        list.add("anan");
        RongIM.getInstance().createDiscussion("123", list, new RongIMClient.CreateDiscussionCallback() {
            @Override
            public void onSuccess(String s) {

                Toast.makeText(MainActivity.this, "讨论组ID:" + s, Toast.LENGTH_SHORT).show();

                mTxt.setText(s);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(APP.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误。可以从下面两点检查
                 * 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {

                    Toast.makeText(MainActivity.this, "token出错", Toast.LENGTH_SHORT).show();
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {

                    Toast.makeText(MainActivity.this, "用户id:" + userid, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                    Toast.makeText(MainActivity.this, errorCode.getValue() + "", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public io.rong.imlib.model.UserInfo getUserInfo(String s) {

        for (UserInfo userInfo : userIdList) {
            if (userInfo.getUserId().equals(s)) {
                Log.e("TAG", userInfo.getPortraitUri());
                return new io.rong.imlib.model.UserInfo(userInfo.getUserId(), userInfo.getName(), Uri.parse(userInfo.getPortraitUri()));
            }
        }
        Log.e("TAG", "UserId is : " + s);

        return null;
    }

    @Override
    protected void onDestroy() {
        RongIM.getInstance().disconnect();
        super.onDestroy();
    }
}

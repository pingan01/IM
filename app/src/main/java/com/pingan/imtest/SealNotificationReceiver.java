package com.pingan.imtest;

import android.content.Context;
import android.util.Log;

import io.rong.push.PushType;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;


public class SealNotificationReceiver extends PushMessageReceiver {

    @Override
    public boolean onNotificationMessageArrived(Context context, PushType pushType, PushNotificationMessage pushNotificationMessage) {

        Log.e("TAG","接受到消息------》onNotificationMessageArrived"+pushNotificationMessage.getTargetId()+"--"+pushNotificationMessage.getPushData());


        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushType pushType, PushNotificationMessage pushNotificationMessage) {
        return false;
    }
}

package com.busysnail.sunshinemvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;


public class WelcomeActivity extends AppCompatActivity {

    private DelayHandler mHandler = new DelayHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler.sendEmptyMessageDelayed(1, 1000);
    }

    //通过静态内部类+弱引用解决了由非静态内部类引起的内存泄漏。
    // 不过这样虽然避免了Activity泄漏，Looper线程的消息队列中还是可能会有待处理的消息，
    // 所以我们在Activity的Destroy时或者Stop时应该移除消息队列中的消息。
    //在这个例子里没必要，但这是一个良好的习惯
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    private static class DelayHandler extends Handler {
        private WeakReference<WelcomeActivity> mActivity;

        DelayHandler(WelcomeActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(mActivity.get(), MainActivity.class);
            mActivity.get().startActivity(intent);
            mActivity.get().finish();
        }
    }
}

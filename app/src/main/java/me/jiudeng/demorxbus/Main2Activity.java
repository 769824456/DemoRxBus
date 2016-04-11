package me.jiudeng.demorxbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import rx.Subscription;
import rx.functions.Action1;

//http://www.jianshu.com/p/ca090f6e2fe2
public class Main2Activity extends AppCompatActivity {

    private Subscription rxSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    public void doSend(View view) {
        RxBus.getDefault().post(new UserEvent(1, "yoyo"));
    }

}

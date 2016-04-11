package me.jiudeng.demorxbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import rx.Subscription;
import rx.functions.Action1;

//http://www.jianshu.com/p/ca090f6e2fe2
public class MainActivity extends AppCompatActivity {

    private Subscription rxSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // rxSubscription是一个Subscription的全局变量，这段代码可以在onCreate/onStart等生命周期内
        rxSubscription = RxBus.getDefault().toObserverable(UserEvent.class)
                .subscribe(new Action1<UserEvent>() {
                               @Override
                               public void call(UserEvent userEvent) {
                                   long   id   = userEvent.getId();
                                   String name = userEvent.getName();
                                   Toast.makeText(MainActivity.this, "id=" + id + ",name=" + name, Toast.LENGTH_LONG).show();
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                // TODO: 处理异常
                            }
                        });
    }

    public void doNext(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
    }
}

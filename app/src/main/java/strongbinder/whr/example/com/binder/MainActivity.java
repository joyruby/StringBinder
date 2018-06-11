package strongbinder.whr.example.com.binder;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import strongbinder.whr.example.com.binder.aidl.IcallbackSub;
import strongbinder.whr.example.com.binder.aidl.Ilog;
import strongbinder.whr.example.com.binder.aidl.LogSub;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Ilog mLogProxy;

    private String TAG = MainActivity.class.getSimpleName();

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    String ss = msg.getData().getString("send");
                    Toast.makeText(MainActivity.this,ss,Toast.LENGTH_LONG).show();
                    break;
            }
            return false;
        }
    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        Intent intent = new Intent(this, MyService.class);

        Log.d(TAG,Thread.currentThread().getName());
        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {


                mLogProxy = LogSub.asInterface(service);

                try {
                    mLogProxy.attachBinder(new IcallbackSub() {
                        @Override
                        public void sendMessage(String ss) {
                            Log.d(TAG,Thread.currentThread().getName());
                            Message msg = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putString("send",ss);
                            msg.setData(bundle);
                            msg.what = 1;
                            mHandler.sendMessage(msg);

                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        bindService(intent,connection,BIND_AUTO_CREATE);
    }


    @Override
    public void onClick(View v) {
        try {
            mLogProxy.log("aidl success");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

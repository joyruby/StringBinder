package strongbinder.whr.example.com.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import strongbinder.whr.example.com.binder.aidl.IcallBack;
import strongbinder.whr.example.com.binder.aidl.LogSub;

public class MyService extends Service {
    private String TAG = MyService.class.getSimpleName();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  new LogTest();
    }

    class LogTest extends LogSub{

        private IcallBack mIcallBack;

        @Override
        public void log(String ss) throws RemoteException {
            Log.d(TAG,ss);
            if(mIcallBack != null){
                mIcallBack.sendMessage("success");
            }
        }

        @Override
        public void attachBinder(IcallBack callback) throws RemoteException {
            mIcallBack = callback;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}

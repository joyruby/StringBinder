package strongbinder.whr.example.com.binder.aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/**
 * Created by whrwhr446 on 2018/6/11.
 */

public interface IcallBack extends IInterface {

    String descriptor = "whr.example.com.strongbinder.aidl.IcallBack";

    void sendMessage(String ss) throws RemoteException;


    int INTERFACE_SEND_MESSAGE = IBinder.INTERFACE_TRANSACTION+1;
}

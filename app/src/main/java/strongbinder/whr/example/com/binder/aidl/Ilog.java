package strongbinder.whr.example.com.binder.aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/**
 * Created by whrwhr446 on 2018/6/11.
 */

public interface Ilog  extends IInterface{
    String descriptor = "whr.example.com.strongbinder";
    void log(String ss) throws RemoteException;
    void attachBinder(IcallBack callback)throws RemoteException;


    int INTERFACE_ss = IBinder.INTERFACE_TRANSACTION+1;
    int INTERFACE_ATTACH_BINDER = IBinder.INTERFACE_TRANSACTION+2;
}

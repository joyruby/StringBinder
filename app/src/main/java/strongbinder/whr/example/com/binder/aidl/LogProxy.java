package strongbinder.whr.example.com.binder.aidl;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by whrwhr446 on 2018/6/11.
 */

public class LogProxy implements Ilog {

    private IBinder mRemote;

    public LogProxy(IBinder remote){
        mRemote = remote;
    }
    @Override
    public void log(String ss) throws RemoteException {

        Parcel data = Parcel.obtain();
        data.writeInterfaceToken(descriptor);
        data.writeString(ss);
        mRemote.transact(INTERFACE_ss,data,null,IBinder.FLAG_ONEWAY);
        data.recycle();

    }

    @Override
    public void attachBinder(IcallBack callback) throws RemoteException {
        Parcel data = Parcel.obtain();

        data.writeInterfaceToken(descriptor);

        data.writeStrongBinder(callback.asBinder());

        mRemote.transact(INTERFACE_ATTACH_BINDER,data,null,IBinder.FLAG_ONEWAY);
        data.recycle();
    }


    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}

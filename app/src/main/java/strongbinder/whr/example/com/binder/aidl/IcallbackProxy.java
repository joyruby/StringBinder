package strongbinder.whr.example.com.binder.aidl;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by whrwhr446 on 2018/6/11.
 */

public class IcallbackProxy implements IcallBack {

    IBinder mRemote;

    public IcallbackProxy(IBinder remote){
        mRemote = remote;
    }
    @Override
    public void sendMessage(String ss) throws RemoteException {
        Parcel data = Parcel.obtain();

        data.writeInterfaceToken(descriptor);

        data.writeString(ss);

        mRemote.transact(INTERFACE_SEND_MESSAGE,data,null,IBinder.FLAG_ONEWAY);

        data.recycle();
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}

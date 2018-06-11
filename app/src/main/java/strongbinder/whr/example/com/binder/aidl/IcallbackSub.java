package strongbinder.whr.example.com.binder.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by whrwhr446 on 2018/6/11.
 */

public abstract class IcallbackSub extends Binder implements IcallBack {

    static IcallBack asInterface(IBinder obj){
        if(obj == null){
            return  null;
        }

        IcallBack in = (IcallBack) obj.queryLocalInterface(descriptor);

        if(in != null){
            return in;
        }

        return new IcallbackProxy(obj);
    }


    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code){
            case INTERFACE_SEND_MESSAGE:
                data.enforceInterface(descriptor);
                String ss = data.readString();
                sendMessage(ss);
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }
}

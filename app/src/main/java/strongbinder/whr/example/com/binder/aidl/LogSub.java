package strongbinder.whr.example.com.binder.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by whrwhr446 on 2018/6/11.
 */

public abstract class LogSub extends Binder implements Ilog {

    public static Ilog asInterface(IBinder obj){
        if(obj == null){
            return  null;
        }

        Ilog ilog = (Ilog) obj.queryLocalInterface(descriptor);
        if(ilog != null){
            return ilog;
        }
        return new LogProxy(obj);
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code){
            case INTERFACE_TRANSACTION:
                reply.writeString(descriptor);
                return true;
            case INTERFACE_ss:
                data.enforceInterface(descriptor);
                String ss = data.readString();
                log(ss);
                return true;
            case INTERFACE_ATTACH_BINDER:
                data.enforceInterface(descriptor);
                IBinder binder = data.readStrongBinder();

                attachBinder(IcallbackSub.asInterface(binder));

                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }
}

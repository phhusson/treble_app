package android.hidl.manager.V1_0;

public interface IServiceManager extends android.hidl.base.V1_0.IBase {
    public static final String kInterfaceName = "android.hidl.manager@1.0::IServiceManager";

    /* package private */ static IServiceManager asInterface(android.os.IHwBinder binder) {
        if (binder == null) {
            return null;
        }

        android.os.IHwInterface iface =
                binder.queryLocalInterface(kInterfaceName);

        if ((iface != null) && (iface instanceof IServiceManager)) {
            return (IServiceManager)iface;
        }

        IServiceManager proxy = new IServiceManager.Proxy(binder);

        try {
            for (String descriptor : proxy.interfaceChain()) {
                if (descriptor.equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (android.os.RemoteException e) {
        }

        return null;
    }

    public static IServiceManager castFrom(android.os.IHwInterface iface) {
        return (iface == null) ? null : IServiceManager.asInterface(iface.asBinder());
    }

    @Override
    public android.os.IHwBinder asBinder();

    public static IServiceManager getService(String serviceName) throws android.os.RemoteException {
        return IServiceManager.asInterface(android.os.HwBinder.getService("android.hidl.manager@1.0::IServiceManager",serviceName));
    }

    public static IServiceManager getService() throws android.os.RemoteException {
        return IServiceManager.asInterface(android.os.HwBinder.getService("android.hidl.manager@1.0::IServiceManager","default"));
    }

    public static final class Transport {
        public static final byte EMPTY = 0;
        public static final byte HWBINDER = 1;
        public static final byte PASSTHROUGH = 2;
        public static final String toString(byte o) {
            if (o == EMPTY) {
                return "EMPTY";
            }
            if (o == HWBINDER) {
                return "HWBINDER";
            }
            if (o == PASSTHROUGH) {
                return "PASSTHROUGH";
            }
            return "0x" + Integer.toHexString(Byte.toUnsignedInt((byte)(o)));
        }

        public static final String dumpBitfield(byte o) {
            java.util.ArrayList<String> list = new java.util.ArrayList<>();
            byte flipped = 0;
            list.add("EMPTY"); // EMPTY == 0
            if ((o & HWBINDER) == HWBINDER) {
                list.add("HWBINDER");
                flipped |= HWBINDER;
            }
            if ((o & PASSTHROUGH) == PASSTHROUGH) {
                list.add("PASSTHROUGH");
                flipped |= PASSTHROUGH;
            }
            if (o != flipped) {
                list.add("0x" + Integer.toHexString(Byte.toUnsignedInt((byte)(o & (~flipped)))));
            }
            return String.join(" | ", list);
        }

    };

    public static final class PidConstant {
        public static final int NO_PID = -1; // (-1)
        public static final String toString(int o) {
            if (o == NO_PID) {
                return "NO_PID";
            }
            return "0x" + Integer.toHexString(o);
        }

        public static final String dumpBitfield(int o) {
            java.util.ArrayList<String> list = new java.util.ArrayList<>();
            int flipped = 0;
            if ((o & NO_PID) == NO_PID) {
                list.add("NO_PID");
                flipped |= NO_PID;
            }
            if (o != flipped) {
                list.add("0x" + Integer.toHexString(o & (~flipped)));
            }
            return String.join(" | ", list);
        }

    };

    public final static class InstanceDebugInfo {
        public String interfaceName = new String();
        public String instanceName = new String();
        public int pid;
        public final java.util.ArrayList<Integer> clientPids = new java.util.ArrayList<Integer>();
        public int arch;

        @Override
        public final boolean equals(Object otherObject) {
            if (this == otherObject) {
                return true;
            }
            if (otherObject == null) {
                return false;
            }
            if (otherObject.getClass() != IServiceManager.InstanceDebugInfo.class) {
                return false;
            }
            IServiceManager.InstanceDebugInfo other = (IServiceManager.InstanceDebugInfo)otherObject;
            if (!android.os.HidlSupport.deepEquals(this.interfaceName, other.interfaceName)) {
                return false;
            }
            if (!android.os.HidlSupport.deepEquals(this.instanceName, other.instanceName)) {
                return false;
            }
            if (this.pid != other.pid) {
                return false;
            }
            if (!android.os.HidlSupport.deepEquals(this.clientPids, other.clientPids)) {
                return false;
            }
            if (this.arch != other.arch) {
                return false;
            }
            return true;
        }

        @Override
        public final int hashCode() {
            return java.util.Objects.hash(
                    android.os.HidlSupport.deepHashCode(this.interfaceName), 
                    android.os.HidlSupport.deepHashCode(this.instanceName), 
                    android.os.HidlSupport.deepHashCode(this.pid), 
                    android.os.HidlSupport.deepHashCode(this.clientPids), 
                    android.os.HidlSupport.deepHashCode(this.arch));
        }

        @Override
        public final String toString() {
            java.lang.StringBuilder builder = new java.lang.StringBuilder();
            builder.append("{");
            builder.append(".interfaceName = ");
            builder.append(this.interfaceName);
            builder.append(", .instanceName = ");
            builder.append(this.instanceName);
            builder.append(", .pid = ");
            builder.append(this.pid);
            builder.append(", .clientPids = ");
            builder.append(this.clientPids);
            builder.append(", .arch = ");
            builder.append(android.hidl.base.V1_0.DebugInfo.Architecture.toString(this.arch));
            builder.append("}");
            return builder.toString();
        }

        public final void readFromParcel(android.os.HwParcel parcel) {
            android.os.HwBlob blob = parcel.readBuffer(64/* size */);
            readEmbeddedFromParcel(parcel, blob, 0 /* parentOffset */);
        }

        public static final java.util.ArrayList<InstanceDebugInfo> readVectorFromParcel(android.os.HwParcel parcel) {
            java.util.ArrayList<InstanceDebugInfo> _hidl_vec = new java.util.ArrayList();
            android.os.HwBlob _hidl_blob = parcel.readBuffer(16 /* sizeof hidl_vec<T> */);

            {
                int _hidl_vec_size = _hidl_blob.getInt32(0 + 8 /* offsetof(hidl_vec<T>, mSize) */);
                android.os.HwBlob childBlob = parcel.readEmbeddedBuffer(
                        _hidl_vec_size * 64,_hidl_blob.handle(),
                        0 + 0 /* offsetof(hidl_vec<T>, mBuffer) */,true /* nullable */);

                _hidl_vec.clear();
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; ++_hidl_index_0) {
                    final IServiceManager.InstanceDebugInfo _hidl_vec_element = new IServiceManager.InstanceDebugInfo();
                    _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 64);
                    _hidl_vec.add(_hidl_vec_element);
                }
            }

            return _hidl_vec;
        }

        public final void readEmbeddedFromParcel(
                android.os.HwParcel parcel, android.os.HwBlob _hidl_blob, long _hidl_offset) {
            interfaceName = _hidl_blob.getString(_hidl_offset + 0);

            parcel.readEmbeddedBuffer(
                    interfaceName.getBytes().length + 1,
                    _hidl_blob.handle(),
                    _hidl_offset + 0 + 0 /* offsetof(hidl_string, mBuffer) */,false /* nullable */);

            instanceName = _hidl_blob.getString(_hidl_offset + 16);

            parcel.readEmbeddedBuffer(
                    instanceName.getBytes().length + 1,
                    _hidl_blob.handle(),
                    _hidl_offset + 16 + 0 /* offsetof(hidl_string, mBuffer) */,false /* nullable */);

            pid = _hidl_blob.getInt32(_hidl_offset + 32);
            {
                int _hidl_vec_size = _hidl_blob.getInt32(_hidl_offset + 40 + 8 /* offsetof(hidl_vec<T>, mSize) */);
                android.os.HwBlob childBlob = parcel.readEmbeddedBuffer(
                        _hidl_vec_size * 4,_hidl_blob.handle(),
                        _hidl_offset + 40 + 0 /* offsetof(hidl_vec<T>, mBuffer) */,true /* nullable */);

                clientPids.clear();
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; ++_hidl_index_0) {
                    int _hidl_vec_element;
                    _hidl_vec_element = childBlob.getInt32(_hidl_index_0 * 4);
                    clientPids.add(_hidl_vec_element);
                }
            }
            arch = _hidl_blob.getInt32(_hidl_offset + 56);
        }

        public final void writeToParcel(android.os.HwParcel parcel) {
            android.os.HwBlob _hidl_blob = new android.os.HwBlob(64 /* size */);
            writeEmbeddedToBlob(_hidl_blob, 0 /* parentOffset */);
            parcel.writeBuffer(_hidl_blob);
        }

        public static final void writeVectorToParcel(
                android.os.HwParcel parcel, java.util.ArrayList<InstanceDebugInfo> _hidl_vec) {
            android.os.HwBlob _hidl_blob = new android.os.HwBlob(16 /* sizeof(hidl_vec<T>) */);
            {
                int _hidl_vec_size = _hidl_vec.size();
                _hidl_blob.putInt32(0 + 8 /* offsetof(hidl_vec<T>, mSize) */, _hidl_vec_size);
                _hidl_blob.putBool(0 + 12 /* offsetof(hidl_vec<T>, mOwnsBuffer) */, false);
                android.os.HwBlob childBlob = new android.os.HwBlob((int)(_hidl_vec_size * 64));
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; ++_hidl_index_0) {
                    _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 64);
                }
                _hidl_blob.putBlob(0 + 0 /* offsetof(hidl_vec<T>, mBuffer) */, childBlob);
            }

            parcel.writeBuffer(_hidl_blob);
        }

        public final void writeEmbeddedToBlob(
                android.os.HwBlob _hidl_blob, long _hidl_offset) {
            _hidl_blob.putString(_hidl_offset + 0, interfaceName);
            _hidl_blob.putString(_hidl_offset + 16, instanceName);
            _hidl_blob.putInt32(_hidl_offset + 32, pid);
            {
                int _hidl_vec_size = clientPids.size();
                _hidl_blob.putInt32(_hidl_offset + 40 + 8 /* offsetof(hidl_vec<T>, mSize) */, _hidl_vec_size);
                _hidl_blob.putBool(_hidl_offset + 40 + 12 /* offsetof(hidl_vec<T>, mOwnsBuffer) */, false);
                android.os.HwBlob childBlob = new android.os.HwBlob((int)(_hidl_vec_size * 4));
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; ++_hidl_index_0) {
                    childBlob.putInt32(_hidl_index_0 * 4, clientPids.get(_hidl_index_0));
                }
                _hidl_blob.putBlob(_hidl_offset + 40 + 0 /* offsetof(hidl_vec<T>, mBuffer) */, childBlob);
            }
            _hidl_blob.putInt32(_hidl_offset + 56, arch);
        }
    };

    android.hidl.base.V1_0.IBase get(String fqName, String name)
        throws android.os.RemoteException;
    boolean add(String name, android.hidl.base.V1_0.IBase service)
        throws android.os.RemoteException;
    byte getTransport(String fqName, String name)
        throws android.os.RemoteException;
    java.util.ArrayList<String> list()
        throws android.os.RemoteException;
    java.util.ArrayList<String> listByInterface(String fqName)
        throws android.os.RemoteException;
    boolean registerForNotifications(String fqName, String name, IServiceNotification callback)
        throws android.os.RemoteException;
    java.util.ArrayList<IServiceManager.InstanceDebugInfo> debugDump()
        throws android.os.RemoteException;
    void registerPassthroughClient(String fqName, String name)
        throws android.os.RemoteException;
    java.util.ArrayList<String> interfaceChain()
        throws android.os.RemoteException;
    String interfaceDescriptor()
        throws android.os.RemoteException;
    java.util.ArrayList<byte[/* 32 */]> getHashChain()
        throws android.os.RemoteException;
    void setHALInstrumentation()
        throws android.os.RemoteException;
    boolean linkToDeath(android.os.IHwBinder.DeathRecipient recipient, long cookie)
        throws android.os.RemoteException;
    void ping()
        throws android.os.RemoteException;
    android.hidl.base.V1_0.DebugInfo getDebugInfo()
        throws android.os.RemoteException;
    void notifySyspropsChanged()
        throws android.os.RemoteException;
    boolean unlinkToDeath(android.os.IHwBinder.DeathRecipient recipient)
        throws android.os.RemoteException;

    public static final class Proxy implements IServiceManager {
        private android.os.IHwBinder mRemote;

        public Proxy(android.os.IHwBinder remote) {
            mRemote = java.util.Objects.requireNonNull(remote);
        }

        @Override
        public android.os.IHwBinder asBinder() {
            return mRemote;
        }

        @Override
        public String toString() {
            try {
                return this.interfaceDescriptor() + "@Proxy";
            } catch (android.os.RemoteException ex) {
                /* ignored; handled below. */
            }
            return "[class or subclass of " + IServiceManager.kInterfaceName + "]@Proxy";
        }

        // Methods from ::android::hidl::manager::V1_0::IServiceManager follow.
        @Override
        public android.hidl.base.V1_0.IBase get(String fqName, String name)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IServiceManager.kInterfaceName);
            _hidl_request.writeString(fqName);
            _hidl_request.writeString(name);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(1 /* get */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                android.hidl.base.V1_0.IBase _hidl_out_service = android.hidl.base.V1_0.IBase.asInterface(_hidl_reply.readStrongBinder());
                return _hidl_out_service;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean add(String name, android.hidl.base.V1_0.IBase service)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IServiceManager.kInterfaceName);
            _hidl_request.writeString(name);
            _hidl_request.writeStrongBinder(service == null ? null : service.asBinder());

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(2 /* add */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public byte getTransport(String fqName, String name)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IServiceManager.kInterfaceName);
            _hidl_request.writeString(fqName);
            _hidl_request.writeString(name);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(3 /* getTransport */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                byte _hidl_out_transport = _hidl_reply.readInt8();
                return _hidl_out_transport;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public java.util.ArrayList<String> list()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IServiceManager.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(4 /* list */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                java.util.ArrayList<String> _hidl_out_fqInstanceNames = _hidl_reply.readStringVector();
                return _hidl_out_fqInstanceNames;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public java.util.ArrayList<String> listByInterface(String fqName)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IServiceManager.kInterfaceName);
            _hidl_request.writeString(fqName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(5 /* listByInterface */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                java.util.ArrayList<String> _hidl_out_instanceNames = _hidl_reply.readStringVector();
                return _hidl_out_instanceNames;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean registerForNotifications(String fqName, String name, IServiceNotification callback)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IServiceManager.kInterfaceName);
            _hidl_request.writeString(fqName);
            _hidl_request.writeString(name);
            _hidl_request.writeStrongBinder(callback == null ? null : callback.asBinder());

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(6 /* registerForNotifications */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public java.util.ArrayList<IServiceManager.InstanceDebugInfo> debugDump()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IServiceManager.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(7 /* debugDump */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                java.util.ArrayList<IServiceManager.InstanceDebugInfo> _hidl_out_info = IServiceManager.InstanceDebugInfo.readVectorFromParcel(_hidl_reply);
                return _hidl_out_info;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void registerPassthroughClient(String fqName, String name)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IServiceManager.kInterfaceName);
            _hidl_request.writeString(fqName);
            _hidl_request.writeString(name);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(8 /* registerPassthroughClient */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        // Methods from ::android::hidl::base::V1_0::IBase follow.
        @Override
        public java.util.ArrayList<String> interfaceChain()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(android.hidl.base.V1_0.IBase.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(256067662 /* interfaceChain */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                java.util.ArrayList<String> _hidl_out_descriptors = _hidl_reply.readStringVector();
                return _hidl_out_descriptors;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public String interfaceDescriptor()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(android.hidl.base.V1_0.IBase.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(256136003 /* interfaceDescriptor */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                String _hidl_out_descriptor = _hidl_reply.readString();
                return _hidl_out_descriptor;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public java.util.ArrayList<byte[/* 32 */]> getHashChain()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(android.hidl.base.V1_0.IBase.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(256398152 /* getHashChain */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                java.util.ArrayList<byte[/* 32 */]> _hidl_out_hashchain =  new java.util.ArrayList<byte[/* 32 */]>();
                {
                    android.os.HwBlob _hidl_blob = _hidl_reply.readBuffer(16 /* size */);
                    {
                        int _hidl_vec_size = _hidl_blob.getInt32(0 /* offset */ + 8 /* offsetof(hidl_vec<T>, mSize) */);
                        android.os.HwBlob childBlob = _hidl_reply.readEmbeddedBuffer(
                                _hidl_vec_size * 32,_hidl_blob.handle(),
                                0 /* offset */ + 0 /* offsetof(hidl_vec<T>, mBuffer) */,true /* nullable */);

                        _hidl_out_hashchain.clear();
                        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; ++_hidl_index_0) {
                            final byte[/* 32 */] _hidl_vec_element = new byte[32];
                            {
                                long _hidl_array_offset_1 = _hidl_index_0 * 32;
                                for (int _hidl_index_1_0 = 0; _hidl_index_1_0 < 32; ++_hidl_index_1_0) {
                                    _hidl_vec_element[_hidl_index_1_0] = childBlob.getInt8(_hidl_array_offset_1);
                                    _hidl_array_offset_1 += 1;
                                }
                            }
                            _hidl_out_hashchain.add(_hidl_vec_element);
                        }
                    }
                }
                return _hidl_out_hashchain;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void setHALInstrumentation()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(android.hidl.base.V1_0.IBase.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(256462420 /* setHALInstrumentation */, _hidl_request, _hidl_reply, android.os.IHwBinder.FLAG_ONEWAY);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean linkToDeath(android.os.IHwBinder.DeathRecipient recipient, long cookie)
                throws android.os.RemoteException {
            return mRemote.linkToDeath(recipient, cookie);
        }
        @Override
        public void ping()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(android.hidl.base.V1_0.IBase.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(256921159 /* ping */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public android.hidl.base.V1_0.DebugInfo getDebugInfo()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(android.hidl.base.V1_0.IBase.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(257049926 /* getDebugInfo */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                android.hidl.base.V1_0.DebugInfo _hidl_out_info = new android.hidl.base.V1_0.DebugInfo();
                _hidl_out_info.readFromParcel(_hidl_reply);
                return _hidl_out_info;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void notifySyspropsChanged()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(android.hidl.base.V1_0.IBase.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(257120595 /* notifySyspropsChanged */, _hidl_request, _hidl_reply, android.os.IHwBinder.FLAG_ONEWAY);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean unlinkToDeath(android.os.IHwBinder.DeathRecipient recipient)
                throws android.os.RemoteException {
            return mRemote.unlinkToDeath(recipient);
        }
    }

    public static abstract class Stub extends android.os.HwBinder implements IServiceManager {
        @Override
        public android.os.IHwBinder asBinder() {
            return this;
        }

        @Override
        public final java.util.ArrayList<String> interfaceChain() {
            return new java.util.ArrayList<String>(java.util.Arrays.asList(
                    IServiceManager.kInterfaceName,
                    android.hidl.base.V1_0.IBase.kInterfaceName));
        }

        @Override
        public final String interfaceDescriptor() {
            return IServiceManager.kInterfaceName;

        }

        @Override
        public final java.util.ArrayList<byte[/* 32 */]> getHashChain() {
            return new java.util.ArrayList<byte[/* 32 */]>(java.util.Arrays.asList(
                    new byte[/* 32 */]{77,4,106,89,-114,-123,-15,-62,-45,-125,-61,-87,9,108,60,5,120,-23,116,88,7,46,-25,-26,127,112,78,-103,-43,-5,13,63} /* 4d046a598e85f1c2d383c3a9096c3c0578e97458072ee7e67f704e99d5fb0d3f */,
                    new byte[/* 32 */]{-67,-38,-74,24,77,122,52,109,-90,-96,125,-64,-126,-116,-15,-102,105,111,76,-86,54,17,-59,31,46,20,86,90,20,-76,15,-39} /* bddab6184d7a346da6a07dc0828cf19a696f4caa3611c51f2e14565a14b40fd9 */));

        }

        @Override
        public final void setHALInstrumentation() {

        }

        @Override
        public final boolean linkToDeath(android.os.IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override
        public final void ping() {
            return;

        }

        @Override
        public final android.hidl.base.V1_0.DebugInfo getDebugInfo() {
            android.hidl.base.V1_0.DebugInfo info = new android.hidl.base.V1_0.DebugInfo();
            info.pid = -1;
            info.ptr = 0;
            info.arch = android.hidl.base.V1_0.DebugInfo.Architecture.UNKNOWN;return info;
        }

        @Override
        public final void notifySyspropsChanged() {
            android.os.SystemProperties.reportSyspropChanged();
        }

        @Override
        public final boolean unlinkToDeath(android.os.IHwBinder.DeathRecipient recipient) {
            return true;

        }

        @Override
        public android.os.IHwInterface queryLocalInterface(String descriptor) {
            if (kInterfaceName.equals(descriptor)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String serviceName) throws android.os.RemoteException {
            registerService(serviceName);
        }

        @Override
        public String toString() {
            return this.interfaceDescriptor() + "@Stub";
        }

        @Override
        public void onTransact(int _hidl_code, android.os.HwParcel _hidl_request, final android.os.HwParcel _hidl_reply, int _hidl_flags)
                throws android.os.RemoteException {
            switch (_hidl_code) {
                case 1 /* get */:
                {
                    _hidl_request.enforceInterface(IServiceManager.kInterfaceName);

                    String fqName = _hidl_request.readString();
                    String name = _hidl_request.readString();
                    android.hidl.base.V1_0.IBase _hidl_out_service = get(fqName, name);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeStrongBinder(_hidl_out_service == null ? null : _hidl_out_service.asBinder());
                    _hidl_reply.send();
                    break;
                }

                case 2 /* add */:
                {
                    _hidl_request.enforceInterface(IServiceManager.kInterfaceName);

                    String name = _hidl_request.readString();
                    android.hidl.base.V1_0.IBase service = android.hidl.base.V1_0.IBase.asInterface(_hidl_request.readStrongBinder());
                    boolean _hidl_out_success = add(name, service);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_success);
                    _hidl_reply.send();
                    break;
                }

                case 3 /* getTransport */:
                {
                    _hidl_request.enforceInterface(IServiceManager.kInterfaceName);

                    String fqName = _hidl_request.readString();
                    String name = _hidl_request.readString();
                    byte _hidl_out_transport = getTransport(fqName, name);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt8(_hidl_out_transport);
                    _hidl_reply.send();
                    break;
                }

                case 4 /* list */:
                {
                    _hidl_request.enforceInterface(IServiceManager.kInterfaceName);

                    java.util.ArrayList<String> _hidl_out_fqInstanceNames = list();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeStringVector(_hidl_out_fqInstanceNames);
                    _hidl_reply.send();
                    break;
                }

                case 5 /* listByInterface */:
                {
                    _hidl_request.enforceInterface(IServiceManager.kInterfaceName);

                    String fqName = _hidl_request.readString();
                    java.util.ArrayList<String> _hidl_out_instanceNames = listByInterface(fqName);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeStringVector(_hidl_out_instanceNames);
                    _hidl_reply.send();
                    break;
                }

                case 6 /* registerForNotifications */:
                {
                    _hidl_request.enforceInterface(IServiceManager.kInterfaceName);

                    String fqName = _hidl_request.readString();
                    String name = _hidl_request.readString();
                    IServiceNotification callback = IServiceNotification.asInterface(_hidl_request.readStrongBinder());
                    boolean _hidl_out_success = registerForNotifications(fqName, name, callback);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_success);
                    _hidl_reply.send();
                    break;
                }

                case 7 /* debugDump */:
                {
                    _hidl_request.enforceInterface(IServiceManager.kInterfaceName);

                    java.util.ArrayList<IServiceManager.InstanceDebugInfo> _hidl_out_info = debugDump();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    IServiceManager.InstanceDebugInfo.writeVectorToParcel(_hidl_reply, _hidl_out_info);
                    _hidl_reply.send();
                    break;
                }

                case 8 /* registerPassthroughClient */:
                {
                    _hidl_request.enforceInterface(IServiceManager.kInterfaceName);

                    String fqName = _hidl_request.readString();
                    String name = _hidl_request.readString();
                    registerPassthroughClient(fqName, name);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.send();
                    break;
                }

                case 256067662 /* interfaceChain */:
                {
                    _hidl_request.enforceInterface(android.hidl.base.V1_0.IBase.kInterfaceName);

                    java.util.ArrayList<String> _hidl_out_descriptors = interfaceChain();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeStringVector(_hidl_out_descriptors);
                    _hidl_reply.send();
                    break;
                }

                case 256131655 /* debug */:
                {
                    _hidl_request.enforceInterface(android.hidl.base.V1_0.IBase.kInterfaceName);

                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.send();
                    break;
                }

                case 256136003 /* interfaceDescriptor */:
                {
                    _hidl_request.enforceInterface(android.hidl.base.V1_0.IBase.kInterfaceName);

                    String _hidl_out_descriptor = interfaceDescriptor();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeString(_hidl_out_descriptor);
                    _hidl_reply.send();
                    break;
                }

                case 256398152 /* getHashChain */:
                {
                    _hidl_request.enforceInterface(android.hidl.base.V1_0.IBase.kInterfaceName);

                    java.util.ArrayList<byte[/* 32 */]> _hidl_out_hashchain = getHashChain();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    {
                        android.os.HwBlob _hidl_blob = new android.os.HwBlob(16 /* size */);
                        {
                            int _hidl_vec_size = _hidl_out_hashchain.size();
                            _hidl_blob.putInt32(0 /* offset */ + 8 /* offsetof(hidl_vec<T>, mSize) */, _hidl_vec_size);
                            _hidl_blob.putBool(0 /* offset */ + 12 /* offsetof(hidl_vec<T>, mOwnsBuffer) */, false);
                            android.os.HwBlob childBlob = new android.os.HwBlob((int)(_hidl_vec_size * 32));
                            for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; ++_hidl_index_0) {
                                {
                                    long _hidl_array_offset_1 = _hidl_index_0 * 32;
                                    for (int _hidl_index_1_0 = 0; _hidl_index_1_0 < 32; ++_hidl_index_1_0) {
                                        childBlob.putInt8(_hidl_array_offset_1, _hidl_out_hashchain.get(_hidl_index_0)[_hidl_index_1_0]);
                                        _hidl_array_offset_1 += 1;
                                    }
                                }
                            }
                            _hidl_blob.putBlob(0 /* offset */ + 0 /* offsetof(hidl_vec<T>, mBuffer) */, childBlob);
                        }
                        _hidl_reply.writeBuffer(_hidl_blob);
                    }
                    _hidl_reply.send();
                    break;
                }

                case 256462420 /* setHALInstrumentation */:
                {
                    _hidl_request.enforceInterface(android.hidl.base.V1_0.IBase.kInterfaceName);

                    setHALInstrumentation();
                    break;
                }

                case 256660548 /* linkToDeath */:
                {
                break;
                }

                case 256921159 /* ping */:
                {
                break;
                }

                case 257049926 /* getDebugInfo */:
                {
                    _hidl_request.enforceInterface(android.hidl.base.V1_0.IBase.kInterfaceName);

                    android.hidl.base.V1_0.DebugInfo _hidl_out_info = getDebugInfo();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_out_info.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    break;
                }

                case 257120595 /* notifySyspropsChanged */:
                {
                    _hidl_request.enforceInterface(android.hidl.base.V1_0.IBase.kInterfaceName);

                    notifySyspropsChanged();
                    break;
                }

                case 257250372 /* unlinkToDeath */:
                {
                break;
                }

            }
        }
    }
}

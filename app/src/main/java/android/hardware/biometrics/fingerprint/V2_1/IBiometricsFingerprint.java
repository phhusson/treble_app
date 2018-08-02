package android.hardware.biometrics.fingerprint.V2_1;

public interface IBiometricsFingerprint extends android.hidl.base.V1_0.IBase {
    public static final String kInterfaceName = "android.hardware.biometrics.fingerprint@2.1::IBiometricsFingerprint";

    /* package private */ static IBiometricsFingerprint asInterface(android.os.IHwBinder binder) {
        if (binder == null) {
            return null;
        }

        android.os.IHwInterface iface =
                binder.queryLocalInterface(kInterfaceName);

        if ((iface != null) && (iface instanceof IBiometricsFingerprint)) {
            return (IBiometricsFingerprint)iface;
        }

        IBiometricsFingerprint proxy = new IBiometricsFingerprint.Proxy(binder);

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

    public static IBiometricsFingerprint castFrom(android.os.IHwInterface iface) {
        return (iface == null) ? null : IBiometricsFingerprint.asInterface(iface.asBinder());
    }

    @Override
    public android.os.IHwBinder asBinder();

    public static IBiometricsFingerprint getService(String serviceName) throws android.os.RemoteException {
        return IBiometricsFingerprint.asInterface(android.os.HwBinder.getService("android.hardware.biometrics.fingerprint@2.1::IBiometricsFingerprint",serviceName));
    }

    public static IBiometricsFingerprint getService() throws android.os.RemoteException {
        return IBiometricsFingerprint.asInterface(android.os.HwBinder.getService("android.hardware.biometrics.fingerprint@2.1::IBiometricsFingerprint","default"));
    }

    long setNotify(IBiometricsFingerprintClientCallback clientCallback)
        throws android.os.RemoteException;
    long preEnroll()
        throws android.os.RemoteException;
    int enroll(byte[/* 69 */] hat, int gid, int timeoutSec)
        throws android.os.RemoteException;
    int postEnroll()
        throws android.os.RemoteException;
    long getAuthenticatorId()
        throws android.os.RemoteException;
    int cancel()
        throws android.os.RemoteException;
    int enumerate()
        throws android.os.RemoteException;
    int remove(int gid, int fid)
        throws android.os.RemoteException;
    int setActiveGroup(int gid, String storePath)
        throws android.os.RemoteException;
    int authenticate(long operationId, int gid)
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

    public static final class Proxy implements IBiometricsFingerprint {
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
            return "[class or subclass of " + IBiometricsFingerprint.kInterfaceName + "]@Proxy";
        }

        // Methods from ::android::hardware::biometrics::fingerprint::V2_1::IBiometricsFingerprint follow.
        @Override
        public long setNotify(IBiometricsFingerprintClientCallback clientCallback)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IBiometricsFingerprint.kInterfaceName);
            _hidl_request.writeStrongBinder(clientCallback == null ? null : clientCallback.asBinder());

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(1 /* setNotify */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                long _hidl_out_deviceId = _hidl_reply.readInt64();
                return _hidl_out_deviceId;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public long preEnroll()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IBiometricsFingerprint.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(2 /* preEnroll */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                long _hidl_out_authChallenge = _hidl_reply.readInt64();
                return _hidl_out_authChallenge;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int enroll(byte[/* 69 */] hat, int gid, int timeoutSec)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IBiometricsFingerprint.kInterfaceName);
            {
                android.os.HwBlob _hidl_blob = new android.os.HwBlob(69 /* size */);
                {
                    long _hidl_array_offset_0 = 0 /* offset */;
                    for (int _hidl_index_0_0 = 0; _hidl_index_0_0 < 69; ++_hidl_index_0_0) {
                        _hidl_blob.putInt8(_hidl_array_offset_0, hat[_hidl_index_0_0]);
                        _hidl_array_offset_0 += 1;
                    }
                }
                _hidl_request.writeBuffer(_hidl_blob);
            }
            _hidl_request.writeInt32(gid);
            _hidl_request.writeInt32(timeoutSec);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(3 /* enroll */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_debugErrno = _hidl_reply.readInt32();
                return _hidl_out_debugErrno;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int postEnroll()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IBiometricsFingerprint.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(4 /* postEnroll */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_debugErrno = _hidl_reply.readInt32();
                return _hidl_out_debugErrno;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public long getAuthenticatorId()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IBiometricsFingerprint.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(5 /* getAuthenticatorId */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                long _hidl_out_AuthenticatorId = _hidl_reply.readInt64();
                return _hidl_out_AuthenticatorId;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int cancel()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IBiometricsFingerprint.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(6 /* cancel */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_debugErrno = _hidl_reply.readInt32();
                return _hidl_out_debugErrno;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int enumerate()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IBiometricsFingerprint.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(7 /* enumerate */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_debugErrno = _hidl_reply.readInt32();
                return _hidl_out_debugErrno;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int remove(int gid, int fid)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IBiometricsFingerprint.kInterfaceName);
            _hidl_request.writeInt32(gid);
            _hidl_request.writeInt32(fid);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(8 /* remove */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_debugErrno = _hidl_reply.readInt32();
                return _hidl_out_debugErrno;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int setActiveGroup(int gid, String storePath)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IBiometricsFingerprint.kInterfaceName);
            _hidl_request.writeInt32(gid);
            _hidl_request.writeString(storePath);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(9 /* setActiveGroup */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_debugErrno = _hidl_reply.readInt32();
                return _hidl_out_debugErrno;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int authenticate(long operationId, int gid)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(IBiometricsFingerprint.kInterfaceName);
            _hidl_request.writeInt64(operationId);
            _hidl_request.writeInt32(gid);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(10 /* authenticate */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_debugErrno = _hidl_reply.readInt32();
                return _hidl_out_debugErrno;
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

    public static abstract class Stub extends android.os.HwBinder implements IBiometricsFingerprint {
        @Override
        public android.os.IHwBinder asBinder() {
            return this;
        }

        @Override
        public final java.util.ArrayList<String> interfaceChain() {
            return new java.util.ArrayList<String>(java.util.Arrays.asList(
                    IBiometricsFingerprint.kInterfaceName,
                    android.hidl.base.V1_0.IBase.kInterfaceName));
        }

        @Override
        public final String interfaceDescriptor() {
            return IBiometricsFingerprint.kInterfaceName;

        }

        @Override
        public final java.util.ArrayList<byte[/* 32 */]> getHashChain() {
            return new java.util.ArrayList<byte[/* 32 */]>(java.util.Arrays.asList(
                    new byte[/* 32 */]{31,-67,-63,-8,82,-8,-67,46,74,108,92,-77,10,-62,-73,-122,104,-55,-115,-50,17,-118,97,118,45,64,52,-82,-123,-97,67,-40} /* 1fbdc1f852f8bd2e4a6c5cb30ac2b78668c98dce118a61762d4034ae859f43d8 */,
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
                case 1 /* setNotify */:
                {
                    _hidl_request.enforceInterface(IBiometricsFingerprint.kInterfaceName);

                    IBiometricsFingerprintClientCallback clientCallback = IBiometricsFingerprintClientCallback.asInterface(_hidl_request.readStrongBinder());
                    long _hidl_out_deviceId = setNotify(clientCallback);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt64(_hidl_out_deviceId);
                    _hidl_reply.send();
                    break;
                }

                case 2 /* preEnroll */:
                {
                    _hidl_request.enforceInterface(IBiometricsFingerprint.kInterfaceName);

                    long _hidl_out_authChallenge = preEnroll();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt64(_hidl_out_authChallenge);
                    _hidl_reply.send();
                    break;
                }

                case 3 /* enroll */:
                {
                    _hidl_request.enforceInterface(IBiometricsFingerprint.kInterfaceName);

                    byte[/* 69 */] hat = new byte[69];
                    {
                        android.os.HwBlob _hidl_blob = _hidl_request.readBuffer(69 /* size */);
                        {
                            long _hidl_array_offset_0 = 0 /* offset */;
                            for (int _hidl_index_0_0 = 0; _hidl_index_0_0 < 69; ++_hidl_index_0_0) {
                                hat[_hidl_index_0_0] = _hidl_blob.getInt8(_hidl_array_offset_0);
                                _hidl_array_offset_0 += 1;
                            }
                        }
                    }
                    int gid = _hidl_request.readInt32();
                    int timeoutSec = _hidl_request.readInt32();
                    int _hidl_out_debugErrno = enroll(hat, gid, timeoutSec);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_debugErrno);
                    _hidl_reply.send();
                    break;
                }

                case 4 /* postEnroll */:
                {
                    _hidl_request.enforceInterface(IBiometricsFingerprint.kInterfaceName);

                    int _hidl_out_debugErrno = postEnroll();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_debugErrno);
                    _hidl_reply.send();
                    break;
                }

                case 5 /* getAuthenticatorId */:
                {
                    _hidl_request.enforceInterface(IBiometricsFingerprint.kInterfaceName);

                    long _hidl_out_AuthenticatorId = getAuthenticatorId();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt64(_hidl_out_AuthenticatorId);
                    _hidl_reply.send();
                    break;
                }

                case 6 /* cancel */:
                {
                    _hidl_request.enforceInterface(IBiometricsFingerprint.kInterfaceName);

                    int _hidl_out_debugErrno = cancel();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_debugErrno);
                    _hidl_reply.send();
                    break;
                }

                case 7 /* enumerate */:
                {
                    _hidl_request.enforceInterface(IBiometricsFingerprint.kInterfaceName);

                    int _hidl_out_debugErrno = enumerate();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_debugErrno);
                    _hidl_reply.send();
                    break;
                }

                case 8 /* remove */:
                {
                    _hidl_request.enforceInterface(IBiometricsFingerprint.kInterfaceName);

                    int gid = _hidl_request.readInt32();
                    int fid = _hidl_request.readInt32();
                    int _hidl_out_debugErrno = remove(gid, fid);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_debugErrno);
                    _hidl_reply.send();
                    break;
                }

                case 9 /* setActiveGroup */:
                {
                    _hidl_request.enforceInterface(IBiometricsFingerprint.kInterfaceName);

                    int gid = _hidl_request.readInt32();
                    String storePath = _hidl_request.readString();
                    int _hidl_out_debugErrno = setActiveGroup(gid, storePath);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_debugErrno);
                    _hidl_reply.send();
                    break;
                }

                case 10 /* authenticate */:
                {
                    _hidl_request.enforceInterface(IBiometricsFingerprint.kInterfaceName);

                    long operationId = _hidl_request.readInt64();
                    int gid = _hidl_request.readInt32();
                    int _hidl_out_debugErrno = authenticate(operationId, gid);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_debugErrno);
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

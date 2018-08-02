package vendor.huawei.hardware.tp.V1_0;

public interface ITouchscreen extends android.hidl.base.V1_0.IBase {
    public static final String kInterfaceName = "vendor.huawei.hardware.tp@1.0::ITouchscreen";

    /* package private */ static ITouchscreen asInterface(android.os.IHwBinder binder) {
        if (binder == null) {
            return null;
        }

        android.os.IHwInterface iface =
                binder.queryLocalInterface(kInterfaceName);

        if ((iface != null) && (iface instanceof ITouchscreen)) {
            return (ITouchscreen)iface;
        }

        ITouchscreen proxy = new ITouchscreen.Proxy(binder);

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

    public static ITouchscreen castFrom(android.os.IHwInterface iface) {
        return (iface == null) ? null : ITouchscreen.asInterface(iface.asBinder());
    }

    @Override
    public android.os.IHwBinder asBinder();

    public static ITouchscreen getService(String serviceName) throws android.os.RemoteException {
        return ITouchscreen.asInterface(android.os.HwBinder.getService("vendor.huawei.hardware.tp@1.0::ITouchscreen",serviceName));
    }

    public static ITouchscreen getService() throws android.os.RemoteException {
        return ITouchscreen.asInterface(android.os.HwBinder.getService("vendor.huawei.hardware.tp@1.0::ITouchscreen","default"));
    }

    boolean hwTsSetGloveMode(boolean status)
        throws android.os.RemoteException;
    boolean hwTsSetCoverMode(boolean status)
        throws android.os.RemoteException;
    boolean hwTsSetCoverWindowSize(boolean status, int x0, int y0, int x1, int y1)
        throws android.os.RemoteException;
    boolean hwTsSetRoiEnable(boolean status)
        throws android.os.RemoteException;

    @java.lang.FunctionalInterface
    public interface hwTsGetRoiDataCallback {
        public void onValues(boolean ret, java.util.ArrayList<Integer> arg);
    }

    void hwTsGetRoiData(hwTsGetRoiDataCallback _hidl_cb)
        throws android.os.RemoteException;

    @java.lang.FunctionalInterface
    public interface hwTsGetChipInfoCallback {
        public void onValues(boolean ret, String chip_info);
    }

    void hwTsGetChipInfo(hwTsGetChipInfoCallback _hidl_cb)
        throws android.os.RemoteException;
    boolean hwTsSetEasyWeakupGesture(int gesture)
        throws android.os.RemoteException;
    boolean hwTsSetEasyWeakupGestureReportEnable(boolean status)
        throws android.os.RemoteException;

    @java.lang.FunctionalInterface
    public interface hwTsGetEasyWeakupGuestureDataCallback {
        public void onValues(boolean ret, java.util.ArrayList<Integer> gusture_data);
    }

    void hwTsGetEasyWeakupGuestureData(hwTsGetEasyWeakupGuestureDataCallback _hidl_cb)
        throws android.os.RemoteException;
    void hwTsSetDozeMode(int optype, int status, int delaytime)
        throws android.os.RemoteException;

    @java.lang.FunctionalInterface
    public interface hwTsCapacitanceMmiTestCallback {
        public void onValues(int ret, String fail_reason);
    }

    void hwTsCapacitanceMmiTest(hwTsCapacitanceMmiTestCallback _hidl_cb)
        throws android.os.RemoteException;

    @java.lang.FunctionalInterface
    public interface hwTsCapacitanceRunningTestCallback {
        public void onValues(int ret, String fail_reason);
    }

    void hwTsCapacitanceRunningTest(int runningTestStatus, hwTsCapacitanceRunningTestCallback _hidl_cb)
        throws android.os.RemoteException;

    @java.lang.FunctionalInterface
    public interface hwTsCalibrationTestCallback {
        public void onValues(int ret, String fail_reason);
    }

    void hwTsCalibrationTest(int testMode, hwTsCalibrationTestCallback _hidl_cb)
        throws android.os.RemoteException;
    int hwTsSnrTest()
        throws android.os.RemoteException;
    boolean hwTsPressCalSetEndTimeOfAgeing()
        throws android.os.RemoteException;
    long hwTsPressCalGetLeftTimeOfStartCalibration()
        throws android.os.RemoteException;
    boolean hwTsPressCalIsSupportCalibration()
        throws android.os.RemoteException;
    boolean hwTsPressCalSetTypeOfCalibration(int type)
        throws android.os.RemoteException;
    boolean hwTsPressCalSet_range_of_spec(int range)
        throws android.os.RemoteException;
    boolean hwTsPressCalGetStateOfHandle()
        throws android.os.RemoteException;
    boolean hwTsPressCalSetCountOfCalibration(int count)
        throws android.os.RemoteException;
    boolean hwTsPressCalGetCountOfCalibration()
        throws android.os.RemoteException;
    boolean hwTsPressCalSetSizeOfVerifyPoint(int size)
        throws android.os.RemoteException;
    int hwTsPressCalGetSizeOfVerifyPoint()
        throws android.os.RemoteException;
    int hwTsPressCalGetResultOfVerifyPoint(int point)
        throws android.os.RemoteException;
    boolean hwTsPressCalOpenCalibrationModule()
        throws android.os.RemoteException;
    boolean hwTsPressCalCloseCalibrationModule()
        throws android.os.RemoteException;
    boolean hwTsPressCalStartCalibration(int number)
        throws android.os.RemoteException;
    boolean hwTsPressCalStopCalibration(int number)
        throws android.os.RemoteException;
    boolean hwTsPressCalStartVerify(int number)
        throws android.os.RemoteException;
    boolean hwTsPressCalStopVerify(int number)
        throws android.os.RemoteException;
    int hwTsPressCalGetVersionInformation(int type)
        throws android.os.RemoteException;
    int hwTsPressCalZcalPosChecking(int p, int x, int y)
        throws android.os.RemoteException;
    int hwTsSetAftAlgoState(int enable)
        throws android.os.RemoteException;
    int hwTsSetAftAlgoOrientation(int orientation)
        throws android.os.RemoteException;
    int hwTsSetAftConfig(String config)
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

    public static final class Proxy implements ITouchscreen {
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
            return "[class or subclass of " + ITouchscreen.kInterfaceName + "]@Proxy";
        }

        // Methods from ::vendor::huawei::hardware::tp::V1_0::ITouchscreen follow.
        @Override
        public boolean hwTsSetGloveMode(boolean status)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeBool(status);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(1 /* hwTsSetGloveMode */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsSetCoverMode(boolean status)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeBool(status);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(2 /* hwTsSetCoverMode */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsSetCoverWindowSize(boolean status, int x0, int y0, int x1, int y1)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeBool(status);
            _hidl_request.writeInt32(x0);
            _hidl_request.writeInt32(y0);
            _hidl_request.writeInt32(x1);
            _hidl_request.writeInt32(y1);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(3 /* hwTsSetCoverWindowSize */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsSetRoiEnable(boolean status)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeBool(status);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(4 /* hwTsSetRoiEnable */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void hwTsGetRoiData(hwTsGetRoiDataCallback _hidl_cb)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(5 /* hwTsGetRoiData */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                java.util.ArrayList<Integer> _hidl_out_arg = _hidl_reply.readInt32Vector();
                _hidl_cb.onValues(_hidl_out_ret, _hidl_out_arg);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void hwTsGetChipInfo(hwTsGetChipInfoCallback _hidl_cb)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(6 /* hwTsGetChipInfo */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                String _hidl_out_chip_info = _hidl_reply.readString();
                _hidl_cb.onValues(_hidl_out_ret, _hidl_out_chip_info);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsSetEasyWeakupGesture(int gesture)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(gesture);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(7 /* hwTsSetEasyWeakupGesture */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsSetEasyWeakupGestureReportEnable(boolean status)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeBool(status);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(8 /* hwTsSetEasyWeakupGestureReportEnable */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void hwTsGetEasyWeakupGuestureData(hwTsGetEasyWeakupGuestureDataCallback _hidl_cb)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(9 /* hwTsGetEasyWeakupGuestureData */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                java.util.ArrayList<Integer> _hidl_out_gusture_data = _hidl_reply.readInt32Vector();
                _hidl_cb.onValues(_hidl_out_ret, _hidl_out_gusture_data);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void hwTsSetDozeMode(int optype, int status, int delaytime)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(optype);
            _hidl_request.writeInt32(status);
            _hidl_request.writeInt32(delaytime);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(10 /* hwTsSetDozeMode */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void hwTsCapacitanceMmiTest(hwTsCapacitanceMmiTestCallback _hidl_cb)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(11 /* hwTsCapacitanceMmiTest */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_ret = _hidl_reply.readInt32();
                String _hidl_out_fail_reason = _hidl_reply.readString();
                _hidl_cb.onValues(_hidl_out_ret, _hidl_out_fail_reason);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void hwTsCapacitanceRunningTest(int runningTestStatus, hwTsCapacitanceRunningTestCallback _hidl_cb)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(runningTestStatus);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(12 /* hwTsCapacitanceRunningTest */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_ret = _hidl_reply.readInt32();
                String _hidl_out_fail_reason = _hidl_reply.readString();
                _hidl_cb.onValues(_hidl_out_ret, _hidl_out_fail_reason);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void hwTsCalibrationTest(int testMode, hwTsCalibrationTestCallback _hidl_cb)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(testMode);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(13 /* hwTsCalibrationTest */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_ret = _hidl_reply.readInt32();
                String _hidl_out_fail_reason = _hidl_reply.readString();
                _hidl_cb.onValues(_hidl_out_ret, _hidl_out_fail_reason);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int hwTsSnrTest()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(14 /* hwTsSnrTest */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_ret = _hidl_reply.readInt32();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalSetEndTimeOfAgeing()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(15 /* hwTsPressCalSetEndTimeOfAgeing */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public long hwTsPressCalGetLeftTimeOfStartCalibration()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(16 /* hwTsPressCalGetLeftTimeOfStartCalibration */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                long _hidl_out_left_time = _hidl_reply.readInt64();
                return _hidl_out_left_time;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalIsSupportCalibration()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(17 /* hwTsPressCalIsSupportCalibration */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalSetTypeOfCalibration(int type)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(type);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(18 /* hwTsPressCalSetTypeOfCalibration */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalSet_range_of_spec(int range)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(range);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(19 /* hwTsPressCalSet_range_of_spec */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalGetStateOfHandle()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(20 /* hwTsPressCalGetStateOfHandle */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalSetCountOfCalibration(int count)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(count);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(21 /* hwTsPressCalSetCountOfCalibration */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalGetCountOfCalibration()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(22 /* hwTsPressCalGetCountOfCalibration */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalSetSizeOfVerifyPoint(int size)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(size);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(23 /* hwTsPressCalSetSizeOfVerifyPoint */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int hwTsPressCalGetSizeOfVerifyPoint()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(24 /* hwTsPressCalGetSizeOfVerifyPoint */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_count = _hidl_reply.readInt32();
                return _hidl_out_count;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int hwTsPressCalGetResultOfVerifyPoint(int point)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(point);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(25 /* hwTsPressCalGetResultOfVerifyPoint */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalOpenCalibrationModule()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(26 /* hwTsPressCalOpenCalibrationModule */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalCloseCalibrationModule()
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(27 /* hwTsPressCalCloseCalibrationModule */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalStartCalibration(int number)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(number);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(28 /* hwTsPressCalStartCalibration */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalStopCalibration(int number)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(number);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(29 /* hwTsPressCalStopCalibration */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalStartVerify(int number)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(number);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(30 /* hwTsPressCalStartVerify */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean hwTsPressCalStopVerify(int number)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(number);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(31 /* hwTsPressCalStopVerify */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                boolean _hidl_out_ret = _hidl_reply.readBool();
                return _hidl_out_ret;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int hwTsPressCalGetVersionInformation(int type)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(type);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(32 /* hwTsPressCalGetVersionInformation */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_version = _hidl_reply.readInt32();
                return _hidl_out_version;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int hwTsPressCalZcalPosChecking(int p, int x, int y)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(p);
            _hidl_request.writeInt32(x);
            _hidl_request.writeInt32(y);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(33 /* hwTsPressCalZcalPosChecking */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_code = _hidl_reply.readInt32();
                return _hidl_out_code;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int hwTsSetAftAlgoState(int enable)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(enable);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(34 /* hwTsSetAftAlgoState */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_code = _hidl_reply.readInt32();
                return _hidl_out_code;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int hwTsSetAftAlgoOrientation(int orientation)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeInt32(orientation);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(35 /* hwTsSetAftAlgoOrientation */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_code = _hidl_reply.readInt32();
                return _hidl_out_code;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public int hwTsSetAftConfig(String config)
                throws android.os.RemoteException {
            android.os.HwParcel _hidl_request = new android.os.HwParcel();
            _hidl_request.writeInterfaceToken(ITouchscreen.kInterfaceName);
            _hidl_request.writeString(config);

            android.os.HwParcel _hidl_reply = new android.os.HwParcel();
            try {
                mRemote.transact(36 /* hwTsSetAftConfig */, _hidl_request, _hidl_reply, 0 /* flags */);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();

                int _hidl_out_code = _hidl_reply.readInt32();
                return _hidl_out_code;
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

    public static abstract class Stub extends android.os.HwBinder implements ITouchscreen {
        @Override
        public android.os.IHwBinder asBinder() {
            return this;
        }

        @Override
        public final java.util.ArrayList<String> interfaceChain() {
            return new java.util.ArrayList<String>(java.util.Arrays.asList(
                    ITouchscreen.kInterfaceName,
                    android.hidl.base.V1_0.IBase.kInterfaceName));
        }

        @Override
        public final String interfaceDescriptor() {
            return ITouchscreen.kInterfaceName;

        }

        @Override
        public final java.util.ArrayList<byte[/* 32 */]> getHashChain() {
            return new java.util.ArrayList<byte[/* 32 */]>(java.util.Arrays.asList(
                    new byte[/* 32 */]{118,-40,10,-39,-2,-92,115,23,53,82,-75,28,-48,-11,-14,-73,-16,9,56,94,-35,-67,-60,106,84,34,127,21,-34,-34,85,-38} /* 76d80ad9fea473173552b51cd0f5f2b7f009385eddbdc46a54227f15dede55da */,
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
                case 1 /* hwTsSetGloveMode */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    boolean status = _hidl_request.readBool();
                    boolean _hidl_out_ret = hwTsSetGloveMode(status);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 2 /* hwTsSetCoverMode */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    boolean status = _hidl_request.readBool();
                    boolean _hidl_out_ret = hwTsSetCoverMode(status);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 3 /* hwTsSetCoverWindowSize */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    boolean status = _hidl_request.readBool();
                    int x0 = _hidl_request.readInt32();
                    int y0 = _hidl_request.readInt32();
                    int x1 = _hidl_request.readInt32();
                    int y1 = _hidl_request.readInt32();
                    boolean _hidl_out_ret = hwTsSetCoverWindowSize(status, x0, y0, x1, y1);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 4 /* hwTsSetRoiEnable */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    boolean status = _hidl_request.readBool();
                    boolean _hidl_out_ret = hwTsSetRoiEnable(status);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 5 /* hwTsGetRoiData */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    hwTsGetRoiData(new hwTsGetRoiDataCallback() {
                        @Override
                        public void onValues(boolean ret, java.util.ArrayList<Integer> arg) {
                            _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                            _hidl_reply.writeBool(ret);
                            _hidl_reply.writeInt32Vector(arg);
                            _hidl_reply.send();
                            }});
                    break;
                }

                case 6 /* hwTsGetChipInfo */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    hwTsGetChipInfo(new hwTsGetChipInfoCallback() {
                        @Override
                        public void onValues(boolean ret, String chip_info) {
                            _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                            _hidl_reply.writeBool(ret);
                            _hidl_reply.writeString(chip_info);
                            _hidl_reply.send();
                            }});
                    break;
                }

                case 7 /* hwTsSetEasyWeakupGesture */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int gesture = _hidl_request.readInt32();
                    boolean _hidl_out_ret = hwTsSetEasyWeakupGesture(gesture);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 8 /* hwTsSetEasyWeakupGestureReportEnable */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    boolean status = _hidl_request.readBool();
                    boolean _hidl_out_ret = hwTsSetEasyWeakupGestureReportEnable(status);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 9 /* hwTsGetEasyWeakupGuestureData */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    hwTsGetEasyWeakupGuestureData(new hwTsGetEasyWeakupGuestureDataCallback() {
                        @Override
                        public void onValues(boolean ret, java.util.ArrayList<Integer> gusture_data) {
                            _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                            _hidl_reply.writeBool(ret);
                            _hidl_reply.writeInt32Vector(gusture_data);
                            _hidl_reply.send();
                            }});
                    break;
                }

                case 10 /* hwTsSetDozeMode */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int optype = _hidl_request.readInt32();
                    int status = _hidl_request.readInt32();
                    int delaytime = _hidl_request.readInt32();
                    hwTsSetDozeMode(optype, status, delaytime);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.send();
                    break;
                }

                case 11 /* hwTsCapacitanceMmiTest */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    hwTsCapacitanceMmiTest(new hwTsCapacitanceMmiTestCallback() {
                        @Override
                        public void onValues(int ret, String fail_reason) {
                            _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                            _hidl_reply.writeInt32(ret);
                            _hidl_reply.writeString(fail_reason);
                            _hidl_reply.send();
                            }});
                    break;
                }

                case 12 /* hwTsCapacitanceRunningTest */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int runningTestStatus = _hidl_request.readInt32();
                    hwTsCapacitanceRunningTest(runningTestStatus, new hwTsCapacitanceRunningTestCallback() {
                        @Override
                        public void onValues(int ret, String fail_reason) {
                            _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                            _hidl_reply.writeInt32(ret);
                            _hidl_reply.writeString(fail_reason);
                            _hidl_reply.send();
                            }});
                    break;
                }

                case 13 /* hwTsCalibrationTest */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int testMode = _hidl_request.readInt32();
                    hwTsCalibrationTest(testMode, new hwTsCalibrationTestCallback() {
                        @Override
                        public void onValues(int ret, String fail_reason) {
                            _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                            _hidl_reply.writeInt32(ret);
                            _hidl_reply.writeString(fail_reason);
                            _hidl_reply.send();
                            }});
                    break;
                }

                case 14 /* hwTsSnrTest */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int _hidl_out_ret = hwTsSnrTest();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 15 /* hwTsPressCalSetEndTimeOfAgeing */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    boolean _hidl_out_ret = hwTsPressCalSetEndTimeOfAgeing();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 16 /* hwTsPressCalGetLeftTimeOfStartCalibration */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    long _hidl_out_left_time = hwTsPressCalGetLeftTimeOfStartCalibration();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt64(_hidl_out_left_time);
                    _hidl_reply.send();
                    break;
                }

                case 17 /* hwTsPressCalIsSupportCalibration */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    boolean _hidl_out_ret = hwTsPressCalIsSupportCalibration();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 18 /* hwTsPressCalSetTypeOfCalibration */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int type = _hidl_request.readInt32();
                    boolean _hidl_out_ret = hwTsPressCalSetTypeOfCalibration(type);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 19 /* hwTsPressCalSet_range_of_spec */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int range = _hidl_request.readInt32();
                    boolean _hidl_out_ret = hwTsPressCalSet_range_of_spec(range);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 20 /* hwTsPressCalGetStateOfHandle */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    boolean _hidl_out_ret = hwTsPressCalGetStateOfHandle();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 21 /* hwTsPressCalSetCountOfCalibration */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int count = _hidl_request.readInt32();
                    boolean _hidl_out_ret = hwTsPressCalSetCountOfCalibration(count);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 22 /* hwTsPressCalGetCountOfCalibration */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    boolean _hidl_out_ret = hwTsPressCalGetCountOfCalibration();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 23 /* hwTsPressCalSetSizeOfVerifyPoint */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int size = _hidl_request.readInt32();
                    boolean _hidl_out_ret = hwTsPressCalSetSizeOfVerifyPoint(size);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 24 /* hwTsPressCalGetSizeOfVerifyPoint */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int _hidl_out_count = hwTsPressCalGetSizeOfVerifyPoint();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_count);
                    _hidl_reply.send();
                    break;
                }

                case 25 /* hwTsPressCalGetResultOfVerifyPoint */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int point = _hidl_request.readInt32();
                    int _hidl_out_result = hwTsPressCalGetResultOfVerifyPoint(point);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_result);
                    _hidl_reply.send();
                    break;
                }

                case 26 /* hwTsPressCalOpenCalibrationModule */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    boolean _hidl_out_ret = hwTsPressCalOpenCalibrationModule();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 27 /* hwTsPressCalCloseCalibrationModule */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    boolean _hidl_out_ret = hwTsPressCalCloseCalibrationModule();
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 28 /* hwTsPressCalStartCalibration */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int number = _hidl_request.readInt32();
                    boolean _hidl_out_ret = hwTsPressCalStartCalibration(number);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 29 /* hwTsPressCalStopCalibration */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int number = _hidl_request.readInt32();
                    boolean _hidl_out_ret = hwTsPressCalStopCalibration(number);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 30 /* hwTsPressCalStartVerify */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int number = _hidl_request.readInt32();
                    boolean _hidl_out_ret = hwTsPressCalStartVerify(number);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 31 /* hwTsPressCalStopVerify */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int number = _hidl_request.readInt32();
                    boolean _hidl_out_ret = hwTsPressCalStopVerify(number);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeBool(_hidl_out_ret);
                    _hidl_reply.send();
                    break;
                }

                case 32 /* hwTsPressCalGetVersionInformation */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int type = _hidl_request.readInt32();
                    int _hidl_out_version = hwTsPressCalGetVersionInformation(type);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_version);
                    _hidl_reply.send();
                    break;
                }

                case 33 /* hwTsPressCalZcalPosChecking */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int p = _hidl_request.readInt32();
                    int x = _hidl_request.readInt32();
                    int y = _hidl_request.readInt32();
                    int _hidl_out_code = hwTsPressCalZcalPosChecking(p, x, y);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_code);
                    _hidl_reply.send();
                    break;
                }

                case 34 /* hwTsSetAftAlgoState */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int enable = _hidl_request.readInt32();
                    int _hidl_out_code = hwTsSetAftAlgoState(enable);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_code);
                    _hidl_reply.send();
                    break;
                }

                case 35 /* hwTsSetAftAlgoOrientation */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    int orientation = _hidl_request.readInt32();
                    int _hidl_out_code = hwTsSetAftAlgoOrientation(orientation);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_code);
                    _hidl_reply.send();
                    break;
                }

                case 36 /* hwTsSetAftConfig */:
                {
                    _hidl_request.enforceInterface(ITouchscreen.kInterfaceName);

                    String config = _hidl_request.readString();
                    int _hidl_out_code = hwTsSetAftConfig(config);
                    _hidl_reply.writeStatus(android.os.HwParcel.STATUS_SUCCESS);
                    _hidl_reply.writeInt32(_hidl_out_code);
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

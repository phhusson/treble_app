package android.hardware.biometrics.fingerprint.V2_1;


public final class FingerprintEnroll {
    public final android.hardware.biometrics.fingerprint.V2_1.FingerprintFingerId finger = new android.hardware.biometrics.fingerprint.V2_1.FingerprintFingerId();
    public int samplesRemaining;
    public long msg;

    @Override
    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (otherObject.getClass() != android.hardware.biometrics.fingerprint.V2_1.FingerprintEnroll.class) {
            return false;
        }
        android.hardware.biometrics.fingerprint.V2_1.FingerprintEnroll other = (android.hardware.biometrics.fingerprint.V2_1.FingerprintEnroll)otherObject;
        if (!android.os.HidlSupport.deepEquals(this.finger, other.finger)) {
            return false;
        }
        if (this.samplesRemaining != other.samplesRemaining) {
            return false;
        }
        if (this.msg != other.msg) {
            return false;
        }
        return true;
    }

    @Override
    public final int hashCode() {
        return java.util.Objects.hash(
                android.os.HidlSupport.deepHashCode(this.finger), 
                android.os.HidlSupport.deepHashCode(this.samplesRemaining), 
                android.os.HidlSupport.deepHashCode(this.msg));
    }

    @Override
    public final String toString() {
        java.lang.StringBuilder builder = new java.lang.StringBuilder();
        builder.append("{");
        builder.append(".finger = ");
        builder.append(this.finger);
        builder.append(", .samplesRemaining = ");
        builder.append(this.samplesRemaining);
        builder.append(", .msg = ");
        builder.append(this.msg);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(android.os.HwParcel parcel) {
        android.os.HwBlob blob = parcel.readBuffer(24/* size */);
        readEmbeddedFromParcel(parcel, blob, 0 /* parentOffset */);
    }

    public static final java.util.ArrayList<FingerprintEnroll> readVectorFromParcel(android.os.HwParcel parcel) {
        java.util.ArrayList<FingerprintEnroll> _hidl_vec = new java.util.ArrayList();
        android.os.HwBlob _hidl_blob = parcel.readBuffer(16 /* sizeof hidl_vec<T> */);

        {
            int _hidl_vec_size = _hidl_blob.getInt32(0 + 8 /* offsetof(hidl_vec<T>, mSize) */);
            android.os.HwBlob childBlob = parcel.readEmbeddedBuffer(
                    _hidl_vec_size * 24,_hidl_blob.handle(),
                    0 + 0 /* offsetof(hidl_vec<T>, mBuffer) */,true /* nullable */);

            _hidl_vec.clear();
            for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; ++_hidl_index_0) {
                final android.hardware.biometrics.fingerprint.V2_1.FingerprintEnroll _hidl_vec_element = new android.hardware.biometrics.fingerprint.V2_1.FingerprintEnroll();
                _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 24);
                _hidl_vec.add(_hidl_vec_element);
            }
        }

        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(
            android.os.HwParcel parcel, android.os.HwBlob _hidl_blob, long _hidl_offset) {
        finger.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 0);
        samplesRemaining = _hidl_blob.getInt32(_hidl_offset + 8);
        msg = _hidl_blob.getInt64(_hidl_offset + 16);
    }

    public final void writeToParcel(android.os.HwParcel parcel) {
        android.os.HwBlob _hidl_blob = new android.os.HwBlob(24 /* size */);
        writeEmbeddedToBlob(_hidl_blob, 0 /* parentOffset */);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(
            android.os.HwParcel parcel, java.util.ArrayList<FingerprintEnroll> _hidl_vec) {
        android.os.HwBlob _hidl_blob = new android.os.HwBlob(16 /* sizeof(hidl_vec<T>) */);
        {
            int _hidl_vec_size = _hidl_vec.size();
            _hidl_blob.putInt32(0 + 8 /* offsetof(hidl_vec<T>, mSize) */, _hidl_vec_size);
            _hidl_blob.putBool(0 + 12 /* offsetof(hidl_vec<T>, mOwnsBuffer) */, false);
            android.os.HwBlob childBlob = new android.os.HwBlob((int)(_hidl_vec_size * 24));
            for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; ++_hidl_index_0) {
                _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 24);
            }
            _hidl_blob.putBlob(0 + 0 /* offsetof(hidl_vec<T>, mBuffer) */, childBlob);
        }

        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(
            android.os.HwBlob _hidl_blob, long _hidl_offset) {
        finger.writeEmbeddedToBlob(_hidl_blob, _hidl_offset + 0);
        _hidl_blob.putInt32(_hidl_offset + 8, samplesRemaining);
        _hidl_blob.putInt64(_hidl_offset + 16, msg);
    }
};


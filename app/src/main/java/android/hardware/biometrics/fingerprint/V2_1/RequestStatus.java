package android.hardware.biometrics.fingerprint.V2_1;


public final class RequestStatus {
    public static final int SYS_UNKNOWN = 1;
    public static final int SYS_OK = 0;
    public static final int SYS_ENOENT = -2; // (-2)
    public static final int SYS_EINTR = -4; // (-4)
    public static final int SYS_EIO = -5; // (-5)
    public static final int SYS_EAGAIN = -11; // (-11)
    public static final int SYS_ENOMEM = -12; // (-12)
    public static final int SYS_EACCES = -13; // (-13)
    public static final int SYS_EFAULT = -14; // (-14)
    public static final int SYS_EBUSY = -16; // (-16)
    public static final int SYS_EINVAL = -22; // (-22)
    public static final int SYS_ENOSPC = -28; // (-28)
    public static final int SYS_ETIMEDOUT = -110; // (-110)
    public static final String toString(int o) {
        if (o == SYS_UNKNOWN) {
            return "SYS_UNKNOWN";
        }
        if (o == SYS_OK) {
            return "SYS_OK";
        }
        if (o == SYS_ENOENT) {
            return "SYS_ENOENT";
        }
        if (o == SYS_EINTR) {
            return "SYS_EINTR";
        }
        if (o == SYS_EIO) {
            return "SYS_EIO";
        }
        if (o == SYS_EAGAIN) {
            return "SYS_EAGAIN";
        }
        if (o == SYS_ENOMEM) {
            return "SYS_ENOMEM";
        }
        if (o == SYS_EACCES) {
            return "SYS_EACCES";
        }
        if (o == SYS_EFAULT) {
            return "SYS_EFAULT";
        }
        if (o == SYS_EBUSY) {
            return "SYS_EBUSY";
        }
        if (o == SYS_EINVAL) {
            return "SYS_EINVAL";
        }
        if (o == SYS_ENOSPC) {
            return "SYS_ENOSPC";
        }
        if (o == SYS_ETIMEDOUT) {
            return "SYS_ETIMEDOUT";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        int flipped = 0;
        if ((o & SYS_UNKNOWN) == SYS_UNKNOWN) {
            list.add("SYS_UNKNOWN");
            flipped |= SYS_UNKNOWN;
        }
        list.add("SYS_OK"); // SYS_OK == 0
        if ((o & SYS_ENOENT) == SYS_ENOENT) {
            list.add("SYS_ENOENT");
            flipped |= SYS_ENOENT;
        }
        if ((o & SYS_EINTR) == SYS_EINTR) {
            list.add("SYS_EINTR");
            flipped |= SYS_EINTR;
        }
        if ((o & SYS_EIO) == SYS_EIO) {
            list.add("SYS_EIO");
            flipped |= SYS_EIO;
        }
        if ((o & SYS_EAGAIN) == SYS_EAGAIN) {
            list.add("SYS_EAGAIN");
            flipped |= SYS_EAGAIN;
        }
        if ((o & SYS_ENOMEM) == SYS_ENOMEM) {
            list.add("SYS_ENOMEM");
            flipped |= SYS_ENOMEM;
        }
        if ((o & SYS_EACCES) == SYS_EACCES) {
            list.add("SYS_EACCES");
            flipped |= SYS_EACCES;
        }
        if ((o & SYS_EFAULT) == SYS_EFAULT) {
            list.add("SYS_EFAULT");
            flipped |= SYS_EFAULT;
        }
        if ((o & SYS_EBUSY) == SYS_EBUSY) {
            list.add("SYS_EBUSY");
            flipped |= SYS_EBUSY;
        }
        if ((o & SYS_EINVAL) == SYS_EINVAL) {
            list.add("SYS_EINVAL");
            flipped |= SYS_EINVAL;
        }
        if ((o & SYS_ENOSPC) == SYS_ENOSPC) {
            list.add("SYS_ENOSPC");
            flipped |= SYS_ENOSPC;
        }
        if ((o & SYS_ETIMEDOUT) == SYS_ETIMEDOUT) {
            list.add("SYS_ETIMEDOUT");
            flipped |= SYS_ETIMEDOUT;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString(o & (~flipped)));
        }
        return String.join(" | ", list);
    }

};


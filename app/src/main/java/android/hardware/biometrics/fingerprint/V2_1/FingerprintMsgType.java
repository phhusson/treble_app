package android.hardware.biometrics.fingerprint.V2_1;


public final class FingerprintMsgType {
    public static final int ERROR = -1; // (-1)
    public static final int ACQUIRED = 1;
    public static final int TEMPLATE_ENROLLING = 3;
    public static final int TEMPLATE_REMOVED = 4;
    public static final int AUTHENTICATED = 5;
    public static final int TEMPLATE_ENUMERATING = 6;
    public static final String toString(int o) {
        if (o == ERROR) {
            return "ERROR";
        }
        if (o == ACQUIRED) {
            return "ACQUIRED";
        }
        if (o == TEMPLATE_ENROLLING) {
            return "TEMPLATE_ENROLLING";
        }
        if (o == TEMPLATE_REMOVED) {
            return "TEMPLATE_REMOVED";
        }
        if (o == AUTHENTICATED) {
            return "AUTHENTICATED";
        }
        if (o == TEMPLATE_ENUMERATING) {
            return "TEMPLATE_ENUMERATING";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        int flipped = 0;
        if ((o & ERROR) == ERROR) {
            list.add("ERROR");
            flipped |= ERROR;
        }
        if ((o & ACQUIRED) == ACQUIRED) {
            list.add("ACQUIRED");
            flipped |= ACQUIRED;
        }
        if ((o & TEMPLATE_ENROLLING) == TEMPLATE_ENROLLING) {
            list.add("TEMPLATE_ENROLLING");
            flipped |= TEMPLATE_ENROLLING;
        }
        if ((o & TEMPLATE_REMOVED) == TEMPLATE_REMOVED) {
            list.add("TEMPLATE_REMOVED");
            flipped |= TEMPLATE_REMOVED;
        }
        if ((o & AUTHENTICATED) == AUTHENTICATED) {
            list.add("AUTHENTICATED");
            flipped |= AUTHENTICATED;
        }
        if ((o & TEMPLATE_ENUMERATING) == TEMPLATE_ENUMERATING) {
            list.add("TEMPLATE_ENUMERATING");
            flipped |= TEMPLATE_ENUMERATING;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString(o & (~flipped)));
        }
        return String.join(" | ", list);
    }

};


package net.darkstudios.rdslib.util;

import java.sql.Array;
import java.util.ArrayList;

public class RDSUtils {
    public static int SECOND = 20;
    public static int FIFTEEN_SECONDS = 300;
    public static int THIRTY_SECONDS = 600;
    public static int MINUTE = 1200;
    public static int FIFTEEN_MINUTES = 18000;
    public static int THIRTY_MINUTES = 36000;
    public static int HOUR = 72000;

    public static <T> T[] removeNulls(T[] pItems) {
        ArrayList<T> outList = new ArrayList<>();

        for (T item: pItems) {
            if (item != null) {
                outList.add(item);
            }
        }

        return (T[]) outList.toArray(new Array[outList.size()]);
    }

    public static int rgbToInt(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
}

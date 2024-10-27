package net._doc.createworkers.utils;

import com.google.common.util.concurrent.AtomicDouble;

public class DegreeUtils {
    
    public static boolean addToAngle(AtomicDouble angle, float add) {
        float a = angle.floatValue();
        float x = a + add;
        boolean flipped = false;
        if (a + add >= 180) {
            x = -180 + (a + add - 180);
            flipped = true;
        } else if (a + add <= -180) {
            x += 360;
            flipped = true;
        }
        angle.set(x);
        return flipped;
    }
    
    public static float distanceBetween(float degreeA, float degreeB) {
        float nDeg = Math.abs(degreeA) - Math.abs(degreeB);
        if (nDeg < -180 && nDeg > -360)
            nDeg += 180;
        
        return nDeg;
    }
}

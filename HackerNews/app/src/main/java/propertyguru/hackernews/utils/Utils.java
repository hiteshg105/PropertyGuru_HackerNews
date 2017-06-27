package propertyguru.hackernews.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by hitesh on 6/22/17.
 */

public class Utils {

    public static final String TAG="TAG";

    public static float convertPixelsToDp(float px, Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }

}

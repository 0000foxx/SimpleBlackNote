
package com.simpleblacklight.utility;

import android.util.Log;

public class TraceLogger
{

    public static final String LOG_IDENTIFY = "Trace Log";

    public static void print(String msg)
    {
        Log.v(LOG_IDENTIFY, msg);
    }
}

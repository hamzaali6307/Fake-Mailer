package org.xbmc.fakemailer.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Em Waqas on 6/17/2017.
 */

public class SessionManager {
    private static final String PREF_NAME = "hamyDevv" ;
    private static String TAG = SessionManager.class.getSimpleName();
    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    private static final String KEY_Value = "value";
    private static final String KEY_LOGIN = "login";



    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    public void setLogin(boolean login) {
        editor.putBoolean (KEY_LOGIN, login);
        // commit changes
        editor.commit();
        Log.d( TAG, "item modified!");
    }
    public boolean getLogin() {
        return pref.getBoolean (KEY_LOGIN, false);
    }


}

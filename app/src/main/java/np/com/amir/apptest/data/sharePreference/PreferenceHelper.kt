package np.com.amir.apptest.data.sharePreference

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper: IPreferenceHelper {

    lateinit var prefs: SharedPreferences
    private val PREF_NAME = "prefrenceManager"


    companion object PrefManagerConstants{

    }

    fun initSharedPreference(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }


    private fun setKeyValues(key: String, value: String?) {
        prefs.edit().putString(key, value).apply()
    }

    private fun setKeyValues(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    private fun setKeyValues(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }

    private fun setKeyValues(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    private fun getStringValues(key: String): String {
        return prefs.getString(key, "")
    }

    private fun getIntValues(key: String): Int {
        val nullValue = 0
        return prefs.getInt(key, nullValue)
    }

    private fun getLongValues(key: String): Long {
        val nullValue: Long = 0
        return prefs.getLong(key, nullValue)
    }

    private fun getBoolValues(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }


}
package np.com.amir.apptest.data.sharePreference

import android.content.Context
import android.content.SharedPreferences
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.preference.PreferenceManager
import np.com.amir.apptest.data.sharePreference.PreferenceHelper
import np.com.amir.apptest.data.sharePreference.PreferenceHelper.Consts.API_KEY

class PreferenceHelper constructor(
        context: Context): IPreferenceHelper {

    fun defaultPrefs(context: Context): SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context)
    val prefs = defaultPrefs(context)


    companion object Consts{

        private const val API_KEY = "Api_Key"



    }

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     */
    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit({ it.putString(key, value) })
            is Int -> edit({ it.putInt(key, value) })
            is Boolean -> edit({ it.putBoolean(key, value) })
            is Float -> edit({ it.putFloat(key, value) })
            is Long -> edit({ it.putLong(key, value) })
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**
     * finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
     */
    inline fun <reified T : Any> get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> prefs.getString(key, defaultValue as? String) as T?
            Int::class -> prefs.getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> prefs.getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> prefs.getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> prefs.getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}
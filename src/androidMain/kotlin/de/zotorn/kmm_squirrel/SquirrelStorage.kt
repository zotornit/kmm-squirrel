package de.zotorn.kmm_squirrel

import android.content.Context

actual class SquirrelStorage(context: Context, name: String) : KeyValueStorage {


    private val sharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    actual override fun getString(key: String, default: String?): String? {
        return sharedPreferences.getString(key, default)
    }

    actual override fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    actual override fun getInt(key: String, default: Int): Int {
        return sharedPreferences.getInt(key, default)
    }

    actual override fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    actual override fun getLong(key: String, default: Long): Long {
        return sharedPreferences.getLong(key, default)
    }

    actual override fun putLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    actual override fun getFloat(key: String, default: Float): Float {
        return sharedPreferences.getFloat(key, default)
    }

    actual override fun putFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    actual override fun getBoolean(key: String, default: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, default)
    }

    actual override fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    actual override fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    actual override fun removeAll() {
        sharedPreferences.edit().clear().apply()
    }

}
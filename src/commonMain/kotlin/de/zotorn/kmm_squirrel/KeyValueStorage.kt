package de.zotorn.kmm_squirrel//package de.zotorn.kmm_squirrel

interface KeyValueStorage {
    fun getString(key: String, default: String?): String?
    fun putString(key: String, value: String)

    fun getInt(key: String, default: Int): Int
    fun putInt(key: String, value: Int)

    fun getLong(key: String, default: Long): Long
    fun putLong(key: String, value: Long)

    fun getFloat(key: String, default: Float): Float
    fun putFloat(key: String, value: Float)

    fun getBoolean(key: String, default: Boolean): Boolean
    fun putBoolean(key: String, value: Boolean)

    fun remove(key: String)
    fun removeAll()
}
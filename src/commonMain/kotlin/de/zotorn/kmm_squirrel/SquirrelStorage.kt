package de.zotorn.kmm_squirrel

expect class SquirrelStorage : KeyValueStorage{
    override fun getString(key: String, default: String?): String?
    override fun putString(key: String, value: String)

    override fun getInt(key: String, default: Int): Int
    override fun putInt(key: String, value: Int)

    override fun getLong(key: String, default: Long): Long
    override fun putLong(key: String, value: Long)

    override fun getFloat(key: String, default: Float): Float
    override fun putFloat(key: String, value: Float)

    override fun getBoolean(key: String, default: Boolean): Boolean
    override fun putBoolean(key: String, value: Boolean)

    override fun remove(key: String)
    override fun removeAll()
}
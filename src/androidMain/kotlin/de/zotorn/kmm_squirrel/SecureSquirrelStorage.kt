package de.zotorn.kmm_squirrel

actual class SecureSquirrelStorage : KeyValueStorage {
    actual override fun getString(key: String, default: String?): String? {
        TODO("Not yet implemented")
    }

    actual override fun putString(key: String, value: String) {
    }

    actual override fun getInt(key: String, default: Int): Int {
        TODO("Not yet implemented")
    }

    actual override fun putInt(key: String, value: Int) {
    }

    actual override fun getLong(key: String, default: Long): Long {
        TODO("Not yet implemented")
    }

    actual override fun putLong(key: String, value: Long) {
    }

    actual override fun getFloat(key: String, default: Float): Float {
        TODO("Not yet implemented")
    }

    actual override fun putFloat(key: String, value: Float) {
    }

    actual override fun getBoolean(key: String, default: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    actual override fun putBoolean(key: String, value: Boolean) {
    }

    actual override fun remove(key: String) {
    }

    actual override fun removeAll() {
    }

}
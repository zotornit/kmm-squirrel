package de.zotorn.kmm_squirrel

import platform.Foundation.NSUserDefaults

actual class SquirrelStorage(private val name: String) : KeyValueStorage {

    private fun get(key: String, default: String?) : String? {
        NSUserDefaults.standardUserDefaults.dictionaryForKey(name)?.let {
            if (it.keys.contains(key)) {
                return it[key] as String
            }
        }
        return default
    }

    private fun put(key: String, value: String) {
        val targetMap: MutableMap<String, Any?> = NSUserDefaults.standardUserDefaults.dictionaryForKey(name)?.toMutableMap() as? MutableMap<String, Any?>
            ?: mutableMapOf()
        targetMap.put(key, value)
        NSUserDefaults.standardUserDefaults.setObject(targetMap, name)
    }

    actual override fun getString(key: String, default: String?): String? {
        return get(key, default) ?: default
    }

    actual override fun putString(key: String, value: String) {
        put(key, value)
    }

    actual override fun getInt(key: String, default: Int): Int {
        return getString(key, default.toString())?.toIntOrNull() ?: default
    }

    actual override fun putInt(key: String, value: Int) {
        put(key, value.toString())
    }

    actual override fun getLong(key: String, default: Long): Long {
        return getString(key, default.toString())?.toLongOrNull() ?: default
    }

    actual override fun putLong(key: String, value: Long) {
        put(key, value.toString())
    }

    actual override fun getFloat(key: String, default: Float): Float {
        return getString(key, default.toString())?.toFloatOrNull() ?: default
    }

    actual override fun putFloat(key: String, value: Float) {
        put(key, value.toString())
    }

    actual override fun getBoolean(key: String, default: Boolean): Boolean {
        return getString(key, default.toString())?.toBooleanStrictOrNull() ?: default
    }

    actual override fun putBoolean(key: String, value: Boolean) {
        putString(key, value.toString())
    }

    actual override fun remove(key: String) {
        NSUserDefaults.standardUserDefaults.dictionaryForKey(name)?.let {
            if (it.keys.contains(key)) {
                val editMap = it.toMutableMap()
                editMap.remove(key)
                NSUserDefaults.standardUserDefaults.setObject(editMap, name)
            }
        }
    }

    actual override fun removeAll() {
        NSUserDefaults.standardUserDefaults.dictionaryForKey(name)?.let {
            val editMap = it.toMutableMap()
            editMap.clear()
            NSUserDefaults.standardUserDefaults.setObject(editMap, name)
        }
    }

}
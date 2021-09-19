package de.zotorn.kmm_squirrel

import kotlinx.cinterop.*
import platform.CoreFoundation.*
import platform.Foundation.*
import platform.Security.*

/**
 * This class used the iOS KeyChain in order to store key:value pairs of data.
 * Any primitive will be converted to a String before storing
 *
 * Inspiration:
 * https://www.raywenderlich.com/11496196-how-to-secure-ios-user-data-keychain-services-and-biometrics-with-swiftui
 * https://github.com/JetBrains/kotlin-native/issues/3013
 */

actual class SecureSquirrelStorage(private val name: String) : KeyValueStorage {

    private fun put(key: String, value: String) {
        remove(key)
        val storeQuery = CFDictionaryCreateMutable(null, 4, null, null)
        CFDictionaryAddValue(storeQuery, kSecClass, kSecClassGenericPassword)
        CFDictionaryAddValue(storeQuery, kSecAttrAccount, CFBridgingRetain(name))
        CFDictionaryAddValue(storeQuery, kSecAttrService, CFBridgingRetain(key))
        @Suppress("CAST_NEVER_SUCCEEDS")
        val nsString = (value as NSString).dataUsingEncoding(NSUTF8StringEncoding)
        CFDictionaryAddValue(storeQuery, kSecValueData, CFBridgingRetain(nsString))
        SecItemAdd(storeQuery, null)
    }

    private fun get(key: String): String? {
        val searchQuery = CFDictionaryCreateMutable(null, 6, null, null)
        CFDictionaryAddValue(searchQuery, kSecClass, kSecClassGenericPassword)
        CFDictionaryAddValue(searchQuery, kSecAttrAccount, CFBridgingRetain(name))
        CFDictionaryAddValue(searchQuery, kSecAttrService, CFBridgingRetain(key))
        CFDictionaryAddValue(searchQuery, kSecMatchLimit, kSecMatchLimitOne)
        CFDictionaryAddValue(searchQuery, kSecReturnAttributes, kCFBooleanTrue)
        CFDictionaryAddValue(searchQuery, kSecReturnData, kCFBooleanTrue)

        memScoped {
            val pointed = alloc<CFTypeRefVar>() // must be final val, otherwise will crash
            val resultStatus = SecItemCopyMatching(searchQuery, pointed.ptr)

            if(resultStatus != errSecSuccess) {
                return null
            }

            val dict = CFBridgingRelease(pointed.value) as NSDictionary
            val dataObject = dict.objectForKey(CFBridgingRelease(kSecValueData))
            val nsData = dataObject as NSData
            return NSString.create(nsData, NSUTF8StringEncoding) as String?
        }
    }

    actual override fun remove(key: String) {
        val deleteQuery = CFDictionaryCreateMutable(null, 3, null, null)
        CFDictionaryAddValue(deleteQuery, kSecClass, kSecClassGenericPassword)
        CFDictionaryAddValue(deleteQuery, kSecAttrAccount, CFBridgingRetain(name))
        CFDictionaryAddValue(deleteQuery, kSecAttrService, CFBridgingRetain(key))
        SecItemDelete(deleteQuery)
    }

    actual override fun removeAll() {
        val deleteQuery = CFDictionaryCreateMutable(null, 2, null, null)
        CFDictionaryAddValue(deleteQuery, kSecClass, kSecClassGenericPassword)
        CFDictionaryAddValue(deleteQuery, kSecAttrAccount, CFBridgingRetain(name))
        SecItemDelete(deleteQuery)
    }


    actual override fun getString(key: String, default: String?): String? = get(key) ?: default

    actual override fun putString(key: String, value: String) = put(key, value)

    actual override fun getInt(key: String, default: Int): Int = get(key)?.toIntOrNull() ?: default

    actual override fun putInt(key: String, value: Int) = put(key, value.toString())

    actual override fun getBoolean(key: String, default: Boolean): Boolean = get(key)?.toBooleanStrictOrNull() ?: default

    actual override fun putBoolean(key: String, value: Boolean) = put(key, value.toString())

    actual override fun getLong(key: String, default: Long): Long = get(key)?.toLongOrNull() ?: default

    actual override fun putLong(key: String, value: Long) = put(key, value.toString())

    actual override fun getFloat(key: String, default: Float): Float = get(key)?.toFloatOrNull() ?: default

    actual override fun putFloat(key: String, value: Float) = put(key, value.toString())

}
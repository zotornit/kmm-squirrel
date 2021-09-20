# kmm-squirrel

## Kotlin KMM Android/iOS library for simple key-value data storage

The library supports unencrypted and encrypted storage of small data. 
Under the hood the library used 
[`SharedPreferences`](https://developer.android.com/reference/android/content/SharedPreferences) 
and [`EncryptedSharedPreferences`](https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences) on Android.

On iOS for unencrypted storage `NSUserDefaults` will be used. For encrypted data `iOS KeyChain`.


## Installation

Soon... waiting to be able to publish to maven central

## Usage within a [Kotlin KMM project](https://kotlinlang.org/lp/mobile/)

### Android `androidApp`

```
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val storage = SquirrelStorage(this, "my_storage")
}
```

### iOS `iosApp`

create a class in shared/src/iosMain
```
class SquirrelStorageFactory {
    fun build(name: String) = SquirrelStorage(name)
}
```

from a SwiftUI View
```
struct ContentView: View {
    ...
    private let squirrelStorage = SquirrelStorageFactory().build(name: "my_storage_name")
    ...
}
```

There is also a sample project which makes use of the library: [kmm-squirrel-sample](https://github.com/zotornit/kmm-squirrel-sample) TBD
package dev.alfin.airmaps.core.helpers

import android.os.Handler
import android.os.Looper


fun mainThread(perform: () -> Unit) {
    Handler(Looper.getMainLooper()).post {
        perform()
    }
}
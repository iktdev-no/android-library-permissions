package no.iktdev.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

abstract class Pem(val context: Context) {
    fun isPermitted(pem: String): Boolean =
        ContextCompat.checkSelfPermission(context, pem) == PackageManager.PERMISSION_GRANTED

}
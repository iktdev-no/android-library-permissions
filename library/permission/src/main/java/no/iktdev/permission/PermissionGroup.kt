package no.iktdev.permission

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

abstract class PermissionGroup(context: Context): Pem(context) {

    abstract fun permissions(): List<String>
    open var isRequired: Boolean = false

    fun isAllPermitted(): Boolean {
        val permitted = permissions().map { super.isPermitted(it) }
        return permitted.none { !it }
    }

    private var requester: ActivityResultLauncher<Array<String>>? = null

    /**
     * Must be called in fragment onCreate
     */
    fun registerPermissionListener(fragment: Fragment, listener: PermissionResult?) {
        requester = fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it.values.all { k -> k }) {
                listener?.approved()
            } else {
                val failed = it.filter { r -> !r.value }.keys.firstOrNull()
                listener?.rejected(failed)
            }
        }
    }

    /**
     * Must be called in activity onCreate
     */
    fun registerPermissionListener(activity: FragmentActivity, listener: PermissionResult?) {
        requester = activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it.values.all { k -> k }) {
                listener?.approved()
            } else {
                val failed = it.filter { r -> !r.value }.keys.firstOrNull()
                listener?.rejected(failed)
            }
        }
    }

    fun requestPermissions() {
        requester?.launch(permissions().toTypedArray())
    }
}
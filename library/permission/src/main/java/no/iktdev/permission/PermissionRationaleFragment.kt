package no.iktdev.permission

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class PermissionRationaleFragment(val group: PermissionGroup): Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        group.registerPermissionListener(this, permissionResultListener)

    }
    abstract val permissionResultListener: PermissionResult


}
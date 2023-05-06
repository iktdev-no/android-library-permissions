package no.iktdev.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

abstract class Permission(context: Context, val permission: String): Pem(context) {


    fun requestPermission(activity: FragmentActivity, listener: PermissionResult?) {
        val requester = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            when {
                it -> listener?.approved()
                !it -> listener?.rejected(permission)
            }
        }
        requester.launch(permission)
    }

    fun requestPermission(fragment: Fragment, listener: PermissionResult?) {
        val requester = fragment.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            when {
                it -> listener?.approved()
                !it -> listener?.rejected(permission)
            }
        }
        requester.launch(permission)
    }


}


/*

    @NonNull
    @Override
    public final <I, O> ActivityResultLauncher<I> registerForActivityResult(
            @NonNull ActivityResultContract<I, O> contract,
            @NonNull ActivityResultCallback<O> callback) {
        return registerForActivityResult(contract, mActivityResultRegistry, callback);
    }

    /**
     * Get the {@link ActivityResultRegistry} associated with this activity.
     *
     * @return the {@link ActivityResultRegistry}
     */
    @NonNull
    @Override
    public final ActivityResultRegistry getActivityResultRegistry() {
        return mActivityResultRegistry;
    }
*/
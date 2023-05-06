package no.iktdev.permission

interface PermissionResult {
    fun approved()
    fun rejected(setting: String?)
}

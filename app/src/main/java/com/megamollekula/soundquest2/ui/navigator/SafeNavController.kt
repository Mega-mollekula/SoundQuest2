package com.megamollekula.soundquest2.ui.navigator

import android.os.Handler
import android.os.Looper
import androidx.navigation.NavHostController

class SafeNavController(
    private val navController: NavHostController,
    private val lockDelay: Long = 800L
) {

    private var locked = false

    fun navigate(route: String) {
        if (locked) return
        locked = true

        navController.navigate(route)

        Handler(Looper.getMainLooper()).postDelayed({
            locked = false
        }, lockDelay)
    }

    fun safePop() {
        if (locked) return
        locked = true

        navController.popBackStack()

        Handler(Looper.getMainLooper()).postDelayed({
            locked = false
        }, lockDelay)
    }

    fun safePopToStart() {
        if (locked) return
        locked = true

        navController.navigate("start") {
            popUpTo("start") { inclusive = true }
            launchSingleTop = true
        }

        Handler(Looper.getMainLooper()).postDelayed({
            locked = false
        }, lockDelay)
    }
}
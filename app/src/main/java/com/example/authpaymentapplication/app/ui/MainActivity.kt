package com.example.authpaymentapplication.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.authpaymentapplication.R
import com.example.authpaymentapplication.app.Singletons

class MainActivity : AppCompatActivity() {
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Singletons.init(applicationContext)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHost

        navController = navHost.navController

        val appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.paymentFragment,
                    R.id.loginFragment
                )
            )
        setupActionBarWithNavController(navController!!, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: super.onSupportNavigateUp()
    }
}
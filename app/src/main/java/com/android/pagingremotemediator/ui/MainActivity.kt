package com.android.pagingremotemediator.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.android.pagingremotemediator.R
import com.android.pagingremotemediator.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

   private lateinit var navController: NavController

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      val navHost =
         supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
      navController = navHost.navController
   }

   override fun onNavigateUp(): Boolean {
      return navController.navigateUp() || super.onNavigateUp()
   }
}
package com.example.m5individual13

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.m5individual13.navigation.NavManager
import com.example.m5individual13.ui.theme.M5Individual13Theme
import com.example.m5individual13.view.HomeView
import com.example.m5individual13.viewmodel.IMCViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: IMCViewModel by viewModels()
        setContent {
            M5Individual13Theme {
               NavManager(viewModel = viewModel, context = this)
            }
        }
    }
}


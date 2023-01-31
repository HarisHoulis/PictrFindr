package com.houlis.haris.pictrfindr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.houlis.haris.pictrfindr.ui.theme.PictrFindrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PictrFindrTheme {

            }
        }
    }
}

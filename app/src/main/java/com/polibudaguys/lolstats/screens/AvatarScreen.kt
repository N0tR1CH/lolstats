package com.polibudaguys.lolstats.screens

import android.content.Context
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.viewinterop.AndroidView
import com.polibudaguys.lolstats.data.UserDto

@Composable
fun AvatarScreen(userViewModel: UserDto) {
    val userName: String = userViewModel.user.collectAsState().value.name
    val url = "https://www.op.gg/summoners/eune/$userName"
    AndroidView(factory = { context: Context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = { webView: WebView ->
        webView.loadUrl(url)
    })
}
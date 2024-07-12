package com.artimanton.searchtracker.ui.components

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun ClickableTextComponent(url: String) {
    val openUrlLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {}

    val annotatedString = buildAnnotatedString {
        append(url)
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            start = 0,
            end = length
        )
        addStringAnnotation("URL", url, 0, length)
    }

    ClickableText(
        text = annotatedString,
        onClick = {
            try {
                val fullUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    "http://$url"
                } else {
                    url
                }
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl))
                openUrlLauncher.launch(intent)
            } catch (e: ActivityNotFoundException) {
                Log.d("ClickableTextComponent", "ActivityNotFoundException: ${e.message}")
            }
        }
    )
}
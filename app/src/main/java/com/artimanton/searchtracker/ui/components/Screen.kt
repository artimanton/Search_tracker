package com.artimanton.searchtracker.ui.components

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artimanton.searchtracker.data.db.RequestEntity
import com.artimanton.searchtracker.viewmodel.RequestViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueryListScreen(viewModel: RequestViewModel = hiltViewModel()) {
    val allRequests by viewModel.allRequests.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Browser Queries") })
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                items(allRequests) { entity ->
                    QueryItem(entity = entity, onDelete = { viewModel.deleteQuery(entity) })
                }
            }
        }
    )
}

@Composable
fun QueryItem(entity: RequestEntity, onDelete: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ClickableTextComponent(entity.queryText)
            //Text(text = entity.queryText, style = MaterialTheme.typography.bodySmall)
            Text(text = entity.timestamp.toString(), style = MaterialTheme.typography.bodyMedium)
            entity.url?.let {
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = { onDelete(entity.id) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

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

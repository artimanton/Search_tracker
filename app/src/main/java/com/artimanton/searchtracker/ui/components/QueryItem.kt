package com.artimanton.searchtracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artimanton.searchtracker.data.db.RequestEntity

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
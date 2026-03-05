package com.singhj.liberary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainContent() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray) // Placeholder for header background
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Company Logo Placeholder
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Company Logo",
                modifier = Modifier.size(40.dp)
            )
            
            // Company Name
            Text("Coaching & Library Hub", style = MaterialTheme.typography.titleMedium)
            
            // Profile Icon
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Icon",
                modifier = Modifier.size(40.dp)
            )
        }

        // Main content area
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .safeContentPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Welcome to the Main Screen!")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* TODO: Implement navigation or action */ }) {
                Text("View Dashboard")
            }
        }
    }
}
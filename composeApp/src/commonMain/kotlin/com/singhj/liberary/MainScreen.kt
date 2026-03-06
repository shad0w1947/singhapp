package com.singhj.liberary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainContent() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Company Logo Placeholder
            Icon(
                imageVector = Icons.Default.AccountCircle, // Replace with your logo
                contentDescription = "Company Logo",
                modifier = Modifier.size(40.dp)
            )

            Text("Coaching/Library Logo", style = MaterialTheme.typography.titleMedium)

            // Profile Icon
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Icon",
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Announcements
        AnnouncementsSection()

        Spacer(modifier = Modifier.height(24.dp))

        // Performance Summary
        PerformanceSummary()

        Spacer(modifier = Modifier.height(24.dp))

        // Fees and Library Subscription
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            FeesStatusCard(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            LibrarySubscriptionCard(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun AnnouncementsSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Announcements", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        // Placeholder for a tab view or horizontal pager
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Upcoming Test Schedule / New Library Books", color = Color.Black)
        }
    }
}

@Composable
fun PerformanceSummary() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Performance Summary", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Placeholder for chart
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Chart")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Recent Test Score", style = MaterialTheme.typography.bodyLarge)
                    Text("75%", fontSize = 36.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun FeesStatusCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Fees Status", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Status: Paid", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* TODO */ }) {
                Text("Pay Now")
            }
        }
    }
}

@Composable
fun LibrarySubscriptionCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Library Subscription", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Status: Active (12 days)", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* TODO */ }) {
                Text("Renew/Manage")
            }
        }
    }
}
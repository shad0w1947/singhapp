package com.singhj.liberary

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .systemBarsPadding() // Add padding for status bar
            .padding(horizontal = 16.dp), // Keep horizontal padding
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
                modifier = Modifier
                    .size(40.dp)
                    .clickable { /* TODO: Handle profile click */ }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Announcements
        AnnouncementsSection { /* TODO: Handle announcement click */ }

        Spacer(modifier = Modifier.height(24.dp))

        // Performance Summary
        PerformanceSummary { /* TODO: Handle performance summary click */ }

        Spacer(modifier = Modifier.height(24.dp))

        // Fees and Library Subscription
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            FeesStatusCard(modifier = Modifier.weight(1f)) { /* TODO: Handle fees card click */ }
            Spacer(modifier = Modifier.width(16.dp))
            LibrarySubscriptionCard(modifier = Modifier.weight(1f)) { /* TODO: Handle library card click */ }
        }
    }
}

@Composable
fun AnnouncementsSection(onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)) {
        Text("Announcements", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        // Placeholder with aspect ratio for responsiveness
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f) // Responsive height
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Upcoming Test Schedule / New Library Books", color = Color.Black)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerformanceSummary(onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Performance Summary", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Responsive placeholder for chart
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f) // Takes 40% of the card width
                        .aspectRatio(1f) // Square shape
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Chart")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Recent Test Score", style = MaterialTheme.typography.bodyLarge)
                    Text("75%", fontSize = 36.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeesStatusCard(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier.aspectRatio(1f), // Make card square
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Fees Status", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Status: Paid", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Button click can be different from card click */ }) {
                Text("Pay Now")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibrarySubscriptionCard(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier.aspectRatio(1f), // Make card square
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Library Subscription", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Status: Active", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Button click can be different from card click */ }) {
                Text("Renew/Manage")
            }
        }
    }
}

@Composable
expect fun SetSystemBarsTransparent()
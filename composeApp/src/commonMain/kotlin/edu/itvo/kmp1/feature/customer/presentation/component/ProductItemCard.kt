package edu.itvo.kmp1.feature.customer.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.itvo.kmp1.feature.customer.domain.model.Product

@Composable
fun ProductItemCard(
    product: Product,
    onDeleteClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = product.code,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = product.category,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = product.price.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = product.stock.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = product.taxable.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )

            }

            TextButton(
                onClick = onDeleteClick
            ) {
                Text("Delete")
            }
        }
    }
}
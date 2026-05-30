package edu.itvo.kmp1.feature.customer.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductFormCard(
    code: String,
    description: String,
    category: String,
    price: Double,
    stock: Int,
    taxable: Boolean,
    onCodeChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onPriceChange: (Double) -> Unit,
    onStockChange: (Int) -> Unit,
    onTaxableChange: (Boolean) -> Unit,
    onSaveClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "Customer Registration",
                style = MaterialTheme.typography.titleLarge
            )

            OutlinedTextField(
                value = code,
                onValueChange = onCodeChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Code")
                },
                singleLine = true
            )

            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Description")
                },
                singleLine = true
            )

            OutlinedTextField(
                value = category,
                onValueChange = onCategoryChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("category")
                },
                singleLine = true
            )

            OutlinedTextField(
                value = price.toString(),
                onValueChange = {
                    val newPrice = it.toDoubleOrNull() ?: price
                    onPriceChange(newPrice)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Price")
                },
                singleLine = true
            )
            OutlinedTextField(
                value = stock.toString(),
                onValueChange = {
                    val newStock = it.toIntOrNull() ?: stock
                    onStockChange(newStock)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Stock")
                },
                singleLine = true
            )

            OutlinedTextField(
                value = taxable.toString(),
                onValueChange = {
                    val newTaxable = it.toBoolean()
                    onTaxableChange(newTaxable)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Taxable")
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(4.dp))

            Button(
                onClick = onSaveClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Product")
            }
        }
    }
}
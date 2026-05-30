package edu.itvo.kmp1.feature.customer.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.itvo.kmp1.feature.customer.domain.model.Customer
import edu.itvo.kmp1.feature.customer.domain.model.Product
import edu.itvo.kmp1.feature.customer.presentation.component.CustomerFormCard
import edu.itvo.kmp1.feature.customer.presentation.component.ProductFormCard
import edu.itvo.kmp1.feature.customer.presentation.event.CustomerEvent
import edu.itvo.kmp1.feature.customer.presentation.event.ProductEvent
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(
    viewModel: ProductViewModel,
    onBack: () -> Unit
) {

    var code by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0.0) }
    var stock by remember { mutableStateOf(0) }
    var taxable by remember { mutableStateOf(true) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Product Form")
                },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Back")
                    }
                }
            )
        }
    )  { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            ProductFormCard(
                code = code,
                description = description,
                category = category,
                price = price,
                stock = stock,
                taxable = taxable,
                onCodeChange = { code = it },
                onDescriptionChange = { description = it },
                onCategoryChange = { category = it },
                onPriceChange = { price = it },
                onStockChange = { stock = it },
                onTaxableChange = { taxable = it },

                onSaveClick = {
                viewModel.onEvent(
                    ProductEvent.SaveProduct(
                        Product(
                            code = code,
                            description = description,
                            category = category,
                            price = price,
                            stock = stock,
                            taxable = taxable
                        )
                    )
                )

                code=""
                    description=""
                    category=""
                    price=0.0
                    stock=0
                    taxable=true

                onBack()
            }
        )
        }
    }
}
package edu.itvo.kmp1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import edu.itvo.kmp1.feature.customer.presentation.screen.CustomerFormScreen
import edu.itvo.kmp1.feature.customer.presentation.screen.CustomerListScreen
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel

@Composable
fun CustomerRouter(viewModel: CustomerViewModel) {

    var showForm by remember { mutableStateOf(false) }

    if (showForm) {
        CustomerFormScreen(
            onBack = { showForm = false },
            viewModel = viewModel
        )
    } else {
        CustomerListScreen(
            onAddClick = { showForm = true },
            viewModel = viewModel
        )

    }
}
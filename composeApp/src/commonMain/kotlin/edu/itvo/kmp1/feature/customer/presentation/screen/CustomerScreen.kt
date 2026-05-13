package edu.itvo.kmp1.feature.customer.presentation.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel
@Composable
fun CustomerScreen(
    viewModel: CustomerViewModel
) {

    var showForm by remember { mutableStateOf(false) }

    if (showForm) {

        CustomerFormScreen(
            viewModel = viewModel,
            onBack = { showForm = false }
        )

    } else {

        CustomerListScreen(
            viewModel = viewModel,
            onAddClick = { showForm = true }
        )
    }
}
package edu.itvo.kmp1.feature.customer.presentation.state

sealed class ProductUiState {

    data object List : ProductUiState()

    data object Form : ProductUiState()
}
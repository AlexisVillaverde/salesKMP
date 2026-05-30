package edu.itvo.kmp1.feature.customer.presentation.event

import edu.itvo.kmp1.feature.customer.domain.model.Product

sealed interface ProductEvent {

    data class SaveProduct(
        val product: Product
    ) : ProductEvent

    data class DeleteProduct(
        val id: String
    ) : ProductEvent
}
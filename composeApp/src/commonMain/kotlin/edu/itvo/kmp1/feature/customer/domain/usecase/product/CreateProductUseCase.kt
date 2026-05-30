package edu.itvo.kmp1.feature.customer.domain.usecase.product

import edu.itvo.kmp1.feature.customer.domain.model.Product
import edu.itvo.kmp1.feature.customer.domain.repository.ProductRepository
import me.tatarka.inject.annotations.Inject

class CreateProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(
        product: Product
    ) {

        repository.save(product)
    }
}
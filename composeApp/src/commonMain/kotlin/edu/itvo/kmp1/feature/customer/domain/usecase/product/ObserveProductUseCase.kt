package edu.itvo.kmp1.feature.customer.domain.usecase.product


import edu.itvo.kmp1.feature.customer.domain.repository.ProductRepository
import me.tatarka.inject.annotations.Inject

class ObserveProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke() =
        repository.observeAll()
}
package edu.itvo.kmp1.feature.customer.data.repository

import edu.itvo.kmp1.feature.customer.core.repository.BaseInMemoryRepository
import edu.itvo.kmp1.feature.customer.domain.model.Product
import edu.itvo.kmp1.feature.customer.domain.repository.ProductRepository
import me.tatarka.inject.annotations.Inject

class ProductRepositoryLocalImpl @Inject constructor():
    BaseInMemoryRepository<Product, String>(),
    ProductRepository {

    override fun getId(item: Product): String {

        return item.code
    }
}
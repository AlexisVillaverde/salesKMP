package edu.itvo.kmp1.feature.customer.data.datasource.remote

import edu.itvo.kmp1.feature.customer.data.dto.ProductDto
import edu.itvo.kmp1.feature.customer.data.remote.ProductApi


class ProductRemoteDataSource (
        private val api: ProductApi
    ) {

        suspend fun getProducts(): List<ProductDto> {

            return api.getProducts().data
        }

        suspend fun saveProduct(
            product: ProductDto
        ) {

            api.saveProduct(product)
        }

        suspend fun deleteProduct(
            id: String
        ) {

            api.deleteProduct(id)
        }
}

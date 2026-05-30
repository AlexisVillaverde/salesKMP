package edu.itvo.kmp1.feature.customer.data.repository

import edu.itvo.kmp1.feature.customer.data.datasource.remote.CustomerRemoteDataSource
import edu.itvo.kmp1.feature.customer.data.datasource.remote.ProductRemoteDataSource
import edu.itvo.kmp1.feature.customer.data.mapper.toDomain
import edu.itvo.kmp1.feature.customer.data.mapper.toDto
import edu.itvo.kmp1.feature.customer.domain.model.Customer
import edu.itvo.kmp1.feature.customer.domain.model.Product
import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository
import edu.itvo.kmp1.feature.customer.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl (
        private val remote: ProductRemoteDataSource
    ) : ProductRepository {

        override fun observeAll(): Flow<List<Product>> = flow {

            val products =
                remote.getProducts()

            emit(
                products.map {
                    it.toDomain()
                }
            )
        }

        override suspend fun findById(
            id: String
        ): Product? {

            return observeAll()
                .let { flow ->

                    var result: Product? = null

                    flow.collect { products ->

                        result = products.find {
                            it.code == id
                        }
                    }

                    result
                }
        }

        override suspend fun save(
            item: Product
        ) {

            remote.saveProduct(
                item.toDto()
            )
        }

        override suspend fun deleteById(
            id: String
        ) {

            remote.deleteProduct(id)
        }
    }
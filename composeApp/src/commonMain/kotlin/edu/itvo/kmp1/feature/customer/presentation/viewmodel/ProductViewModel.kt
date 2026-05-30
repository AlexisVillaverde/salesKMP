package edu.itvo.kmp1.feature.customer.presentation.viewmodel

import edu.itvo.kmp1.feature.customer.domain.model.Customer
import edu.itvo.kmp1.feature.customer.domain.model.Product
import edu.itvo.kmp1.feature.customer.domain.usecase.customer.DeleteCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.customer.ObserveCustomersUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.customer.CreateCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.product.CreateProductUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.product.DeleteProductUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.product.ObserveProductsUseCase
import edu.itvo.kmp1.feature.customer.presentation.event.CustomerEvent
import edu.itvo.kmp1.feature.customer.presentation.event.ProductEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

class ProductViewModel @Inject constructor(
    private val observeProductUseCase: ObserveProductsUseCase,
    private val saveProductUseCase: CreateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
){

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _products =
        MutableStateFlow<List<Product>>(emptyList())

    val products: StateFlow<List<Product>> = _products

    init {
        observeProducts()
    }

    private var observeJob: Job? = null
    private fun observeProducts() {
        observeJob?.cancel()
        observeJob= scope.launch {
            observeProductUseCase()
                .collect { list ->
                    _products.value = list
                }
        }
    }

    fun onEvent(event: ProductEvent) {
        when (event) {

            is ProductEvent.SaveProduct -> {
                scope.launch {
                    saveProductUseCase(event.product)
                    observeProducts()
                }
            }

            is ProductEvent.DeleteProduct -> {
                scope.launch {
                    deleteProductUseCase(event.id)
                    observeProducts()
                }
            }
        }
    }
}
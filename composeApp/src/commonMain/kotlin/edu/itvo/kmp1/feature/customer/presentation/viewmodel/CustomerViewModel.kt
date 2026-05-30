package edu.itvo.kmp1.feature.customer.presentation.viewmodel

import edu.itvo.kmp1.feature.customer.domain.model.Customer
import edu.itvo.kmp1.feature.customer.domain.usecase.customer.DeleteCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.customer.ObserveCustomersUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.customer.CreateCustomerUseCase
import edu.itvo.kmp1.feature.customer.presentation.event.CustomerEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

class CustomerViewModel @Inject constructor(
    private val observeCustomersUseCase: ObserveCustomersUseCase,
    private val saveCustomerUseCase: CreateCustomerUseCase,
    private val deleteCustomerUseCase: DeleteCustomerUseCase
){

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _customers =
        MutableStateFlow<List<Customer>>(emptyList())

    val customers: StateFlow<List<Customer>> = _customers

    init {
        observeCustomers()
    }

    private var observeJob: Job? = null
    private fun observeCustomers() {
        observeJob?.cancel()
        observeJob= scope.launch {
            observeCustomersUseCase()
                .collect { list ->
                    _customers.value = list
                }
        }
    }

    fun onEvent(event: CustomerEvent) {
        when (event) {

            is CustomerEvent.SaveCustomer -> {
                scope.launch {
                    saveCustomerUseCase(event.customer)
                    observeCustomers()
                }
            }

            is CustomerEvent.DeleteCustomer -> {
                scope.launch {
                    deleteCustomerUseCase(event.id)
                    observeCustomers()
                }
            }
        }
    }
}
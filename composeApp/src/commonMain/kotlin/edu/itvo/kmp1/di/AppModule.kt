package edu.itvo.kmp1.di

import edu.itvo.kmp1.feature.customer.data.repository.CustomerRepositoryImpl
import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository
import edu.itvo.kmp1.feature.customer.domain.usecase.DeleteCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.ObserveCustomersUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.SaveCustomerUseCase
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel

object AppModule {

    private val repository: CustomerRepository by lazy {
        CustomerRepositoryImpl()
    }

    val customerViewModel: CustomerViewModel by lazy {
        CustomerViewModel(
            observeCustomersUseCase = ObserveCustomersUseCase(repository),
            saveCustomerUseCase = SaveCustomerUseCase(repository),
            deleteCustomerUseCase = DeleteCustomerUseCase(repository)
        )
    }
}
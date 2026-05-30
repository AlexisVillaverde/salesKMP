package edu.itvo.kmp1.feature.customer.domain.usecase.customer

import edu.itvo.kmp1.feature.customer.domain.model.Customer
import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository
import me.tatarka.inject.annotations.Inject

class CreateCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {

    suspend operator fun invoke(
        customer: Customer
    ) {

        repository.save(customer)
    }
}
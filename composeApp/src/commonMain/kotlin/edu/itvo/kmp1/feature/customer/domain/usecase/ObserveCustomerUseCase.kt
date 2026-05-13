package edu.itvo.kmp1.feature.customer.domain.usecase


import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository

class ObserveCustomersUseCase(
    private val repository: CustomerRepository
) {

    operator fun invoke() =
        repository.observeAll()
}
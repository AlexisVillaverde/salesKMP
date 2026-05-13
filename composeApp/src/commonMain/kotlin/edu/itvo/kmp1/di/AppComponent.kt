package edu.itvo.kmp1.di


import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import edu.itvo.kmp1.feature.customer.data.repository.CustomerRepositoryImpl
import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel


@Component
abstract class AppComponent {

    @Provides
    fun provideCustomerRepository(): CustomerRepository {
        return CustomerRepositoryImpl()
    }

    abstract val customerViewModel: CustomerViewModel
}

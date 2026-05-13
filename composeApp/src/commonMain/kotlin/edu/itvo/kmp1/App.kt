package edu.itvo.kmp1


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import edu.itvo.kmp1.feature.customer.data.repository.CustomerRepositoryImpl
import edu.itvo.kmp1.feature.customer.domain.usecase.DeleteCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.ObserveCustomersUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.SaveCustomerUseCase
import edu.itvo.kmp1.feature.customer.presentation.screen.CustomerScreen
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel


@Composable
fun App() {

    val repository = remember {
        CustomerRepositoryImpl()
    }

    val viewModel = remember {

        CustomerViewModel(
            observeCustomersUseCase = ObserveCustomersUseCase(repository),
            saveCustomerUseCase = SaveCustomerUseCase(repository),
            deleteCustomerUseCase = DeleteCustomerUseCase(repository)
        )
    }

    MaterialTheme {

        CustomerScreen(
            viewModel = viewModel
        )
    }
}
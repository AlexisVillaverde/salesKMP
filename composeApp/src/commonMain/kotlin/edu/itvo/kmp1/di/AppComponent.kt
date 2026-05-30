package edu.itvo.kmp1.di
import edu.itvo.kmp1.core.network.createHttpClient
import edu.itvo.kmp1.feature.customer.data.datasource.remote.CustomerRemoteDataSource
import edu.itvo.kmp1.feature.customer.data.datasource.remote.ProductRemoteDataSource
import edu.itvo.kmp1.feature.customer.data.remote.CustomerApi
import edu.itvo.kmp1.feature.customer.data.remote.ProductApi
import edu.itvo.kmp1.feature.customer.data.repository.CustomerRepositoryImpl
import edu.itvo.kmp1.feature.customer.data.repository.ProductRepositoryImpl
import edu.itvo.kmp1.feature.customer.domain.usecase.customer.DeleteCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.customer.ObserveCustomersUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.customer.CreateCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.product.CreateProductUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.product.DeleteProductUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.product.ObserveProductsUseCase
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.ProductViewModel

class AppComponent {

    private val httpClient =
        createHttpClient()

    private val api =
        CustomerApi(
            client = httpClient,
            baseUrl = "http://10.1.6.93:3000"
        )

    private val remote =
        CustomerRemoteDataSource(api)

    private val repository =
        CustomerRepositoryImpl(remote)

    private val observeCustomersUseCase =
        ObserveCustomersUseCase(repository)

    private val saveCustomerUseCase =
        CreateCustomerUseCase(repository)

    private val deleteCustomerUseCase =
        DeleteCustomerUseCase(repository)

    val customerViewModel =
        CustomerViewModel(
            observeCustomersUseCase,
            saveCustomerUseCase,
            deleteCustomerUseCase
        )

    // --- Product ---
    private val productApi =
        ProductApi(
            client = httpClient,
            baseUrl = "http://10.1.6.93:3000"
        )

    private val productRemote =
        ProductRemoteDataSource(productApi)

    private val productRepository =
        ProductRepositoryImpl(productRemote)

    private val observeProductsUseCase =
        ObserveProductsUseCase(productRepository)

    private val saveProductUseCase =
        CreateProductUseCase(productRepository)

    private val deleteProductUseCase =
        DeleteProductUseCase(productRepository)

    val productViewModel =
        ProductViewModel(
            observeProductsUseCase,
            saveProductUseCase,
            deleteProductUseCase
        )
}
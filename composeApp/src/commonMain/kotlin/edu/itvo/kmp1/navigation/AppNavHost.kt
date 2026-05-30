package edu.itvo.kmp1.navigation

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.itvo.kmp1.feature.customer.presentation.screen.CustomerFormScreen
import edu.itvo.kmp1.feature.customer.presentation.screen.CustomerListScreen
import edu.itvo.kmp1.feature.customer.presentation.screen.ProductFormScreen
import edu.itvo.kmp1.feature.customer.presentation.screen.ProductListScreen
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.ProductViewModel


@Composable
fun AppNavHost(
    customerViewModel: CustomerViewModel,
    productViewModel: ProductViewModel
) {

    val navController = rememberNavController()
    val currentRoute by navController.currentBackStackEntryAsState()

    Scaffold(
        floatingActionButton = {
            when (currentRoute?.destination?.route) {
                CustomerRoutes.List.route -> FloatingActionButton(
                    onClick = { navController.navigate(CustomerRoutes.Form.route) }
                ) { Text("➕") }

                ProductRoutes.List.route -> FloatingActionButton(
                    onClick = { navController.navigate(ProductRoutes.Form.route) }
                ) { Text("➕") }
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute?.destination?.route == CustomerRoutes.List.route,
                    onClick = { navController.navigate(CustomerRoutes.List.route) },
                    icon = { Text("👤Customers") },
                    label = { Text("Customers") }
                )
                NavigationBarItem(
                    selected = currentRoute?.destination?.route == ProductRoutes.List.route,
                    onClick = { navController.navigate(ProductRoutes.List.route) },
                    icon = { Text("🗃️ Products") },
                    label = { Text("Products") }
                )
            }
        }
    ) { innerPadding ->


        NavHost(
            navController = navController,
            startDestination = CustomerRoutes.List.route
        ) {

            composable(CustomerRoutes.List.route) {

                CustomerListScreen(
                    viewModel = customerViewModel,
                    onAddClick = {
                        navController.navigate(CustomerRoutes.Form.route)
                    }
                )
            }

            composable(CustomerRoutes.Form.route) {

                CustomerFormScreen(
                    viewModel = customerViewModel,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(ProductRoutes.List.route) {
                ProductListScreen(
                    viewModel = productViewModel,
                    onAddClick = {
                        navController.navigate(ProductRoutes.Form.route)
                    }
                )
            }

            composable(ProductRoutes.Form.route) {
                ProductFormScreen(
                    viewModel = productViewModel,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

        }
    }
}
package online.onenut.cointracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import online.onenut.cointracker.data.CoinTrackerDB
import online.onenut.cointracker.data.repository.impl.ExpenseRepositiryImpl
import online.onenut.cointracker.data.repository.impl.IncomeRepositoryImpl
import online.onenut.cointracker.strings.NavRoutes
import online.onenut.cointracker.ui.HomeViewModel
import online.onenut.cointracker.ui.expense.ExpenseViewModel
import online.onenut.cointracker.ui.expense.composables.ExpensesListScreen
import online.onenut.cointracker.ui.expense.state.rememberExpenseListState
import online.onenut.cointracker.ui.home.HomeScreenScaffold
import online.onenut.cointracker.ui.home.state.rememberHomeState
import online.onenut.cointracker.ui.income.IncomeViewModel
import online.onenut.cointracker.ui.income.composables.IncomeListScreen
import online.onenut.cointracker.ui.income.states.rememberIncomeListState
import online.onenut.cointracker.ui.theme.CoinTrackerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Red.copy(alpha = 0.1f)
                ) {

                    val context = LocalContext.current
                    val coinTrackerDB = CoinTrackerDB.getInstance(context)
                    val navController = rememberNavController()
                   // val navController = rememberNavController(navigators = )

                    val expenseRepository = ExpenseRepositiryImpl(coinTrackerDB.ExpenseDao())
                    val incomeRepository = IncomeRepositoryImpl(coinTrackerDB.IncomeDao())
                    val homeState = rememberHomeState(HomeViewModel(expenseRepositiry = expenseRepository, incomeRepositoryImpl = incomeRepository), navController)

                    val navigation = NavHost(
                        navController = navController,
                        startDestination = NavRoutes.homeScreen
                    ) {
                        composable(
                            route = NavRoutes.homeScreen,
                            content = {
                                HomeScreenScaffold(homeState = homeState)
                            }
                        )
                        composable(
                            route = NavRoutes.expensesListScreen,
                            content = {
                                ExpensesListScreen(
                                    expensesState = rememberExpenseListState(
                                        viewModel = ExpenseViewModel(expenseRepository)
                                    )
                                )
                            }
                        )
                        composable(
                            route = NavRoutes.incomesListScreen,
                            content = {
                                IncomeListScreen(
                                    state = rememberIncomeListState(
                                        viewModel = IncomeViewModel(
                                            incomeRepository
                                        )
                                    )
                                )
                            }
                        )

                    }

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    Box(Modifier.size(110.dp)) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
                .size(110.dp)

        ) {

        }

        Card(
            shape = CircleShape,
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(20.dp)
                .offset(x = (85.dp), y = (80.dp)),
            border = BorderStroke(2.dp, Color.Green)
        ) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Edit, "",
                    Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileTest() {
    ProfileScreen()
}
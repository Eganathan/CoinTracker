package online.onenut.cointracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import online.onenut.cointracker.data.CoinTrackerDB
import online.onenut.cointracker.data.repository.impl.ExpenseRepositiryImpl
import online.onenut.cointracker.data.repository.impl.IncomeRepositoryImpl
import online.onenut.cointracker.ui.BaseViewModel
import online.onenut.cointracker.ui.home.state.HomeState
import online.onenut.cointracker.ui.income.IncomeViewModel
import online.onenut.cointracker.ui.income.composables.IncomeListScreen
import online.onenut.cointracker.ui.income.states.IncomeListState
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

                    val expenseRepository = ExpenseRepositiryImpl(coinTrackerDB.ExpenseDao())
                    val incomeRepository = IncomeRepositoryImpl(coinTrackerDB.IncomeDao())
                    val scope = rememberCoroutineScope()
                    val modelBottomSheetState = rememberModalBottomSheetState(
                        initialValue = ModalBottomSheetValue.Hidden,
                        confirmStateChange = { ModalBottomSheetValue.Expanded != it })
                    val test = remember { HomeState(BaseViewModel(expenseRepositiry = ExpenseRepositiryImpl(expenseDao = coinTrackerDB.ExpenseDao()))) }
                    // HomeScreenScaffold(test)
                    // ExpensesListScreen(expensesState = ExpensesState(ExpenseViewModel(expenseRepository)))
                    IncomeListScreen(state = rememberIncomeListState(viewModel = IncomeViewModel(incomeRepository)))
                }
            }
        }
    }
}
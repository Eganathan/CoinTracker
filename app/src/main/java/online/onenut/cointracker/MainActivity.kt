package online.onenut.cointracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import online.onenut.cointracker.data.ExpenseRepositiry
import online.onenut.cointracker.data.model.CoinTrackerDB
import online.onenut.cointracker.ui.BaseViewModel
import online.onenut.cointracker.ui.expense.ExpensesVM
import online.onenut.cointracker.ui.home.HomeScreenScaffold
import online.onenut.cointracker.ui.home.state.HomeState
import online.onenut.cointracker.ui.theme.CoinTrackerTheme

class MainActivity : ComponentActivity() {
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
                    val test = remember { HomeState(BaseViewModel(expenseRepositiry = ExpenseRepositiry(expenseDao = CoinTrackerDB.getInstance(context).ExpenseDao()))) }
                    HomeScreenScaffold(test)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoinTrackerTheme {
        Greeting("Android")
    }
}
package com.active.transactiontest.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.active.transactiontest.R
import com.active.transactiontest.presentation.State.TransactionState
import com.active.transactiontest.presentation.viewmodel.TransactionListViewModel
import com.active.transactiontest.ui.theme.TransactionTestTheme

@Composable
fun TransactionListScreen(
    viewModel: TransactionListViewModel,
    addTransactionClick: () -> Unit,
    calculateClick: () -> Unit,
) {

    val countryList = viewModel.list.collectAsState()
    val totalAmount = viewModel.total.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(0.8f)
        ) {
            items(countryList.value) { item ->
                TransactionItem(item)
            }
        }

        // Buttons row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { addTransactionClick() },
                colors =  ButtonDefaults.buttonColors(colorResource(id = R.color.purple_700)),
                modifier = Modifier,
            ) {
                Text("Agregar Transacción")
            }
            Button(
                onClick = { calculateClick() },
                colors =  ButtonDefaults.buttonColors(colorResource(id = R.color.purple_700)),
                modifier = Modifier,
            ) {
                Text("Calcular Saldo")
            }
        }

        Text(
            text = "Your total amount is "+totalAmount.value.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(vertical = 8.dp),
            style = TextStyle(
                fontSize = 20.sp,
            ),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun TransactionItem(transaction: TransactionState) {

    Card(
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = transaction.concept,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .height(24.dp)
                    .fillMaxWidth(),
                style = TextStyle(
                    fontSize = 16.sp,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            val simbol = if(transaction.withdraw) "- " else "+ "
            Text(
                text = simbol + transaction.value.toString(),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .height(20.dp)
                    .fillMaxWidth(),
                style = TextStyle(
                    fontSize = 16.sp,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ItemList() {
    TransactionTestTheme {
        TransactionItem(TransactionState(1000, "test", true))
    }
}

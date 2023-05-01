package com.example.tidya.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tidya.database.DrugViewModel
import com.example.tidya.database.GetDrug
import com.example.tidya.model.User
import com.example.tidya.outfit

@Composable
fun HistoryScreen(user: User,drugViewModel: DrugViewModel = hiltViewModel()){
    val drugs = drugViewModel.drugs.collectAsState(initial = emptyList())
    val name = remember {
        mutableStateOf("")
    }
    val date = remember {
        mutableStateOf("")
    }

    val time = remember {
        mutableStateOf("")
    }
    //val selectedDate = remember { mutableStateOf("") }

    Scaffold {innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Text(
                text = "History", modifier = Modifier.padding(top = 20.dp, start = 20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = outfit
            )
            Text(text = "Hi ${user.displayName}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h4,
                fontSize = 30.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = user.email)
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(onClick = { drugViewModel.deleteAll(
            GetDrug(name = name.value, date = date.value , time = time.value)
        )
        },Modifier.size(50.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff16C2D5)),
            contentPadding = PaddingValues(0.dp)
        ){
            Icon(Icons.Default.Add ,contentDescription = null, tint= Color.Black)
        }
    }

}
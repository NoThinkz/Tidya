package com.example.tidya.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tidya.R
import com.example.tidya.bottomnav.BottomBarScreen
import com.example.tidya.database.DrugViewModel
import com.example.tidya.database.GetDrug
import com.example.tidya.model.User
import com.example.tidya.outfit
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(user: User, navController: NavController,drugViewModel: DrugViewModel = hiltViewModel()) {  //HomeScreen(user: User)

    val drugs = drugViewModel.drugs.collectAsState(initial = emptyList())
    val name = remember {
        mutableStateOf("")
    }


    /*var textcolor = when (status){
        true -> Color(0xff7D7D7D)
        false -> Color(0xff000000)*/

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Home",
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = outfit
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .height(150.dp)
                    .background(
                        Color(0xff16C2D5),
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_face_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                    )
                    Text(
                        text = "${user.displayName}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h4,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 20.dp, top = 25.dp)
                    )
                }
                Text(
                    text = "${user.email}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h4,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 80.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = "Today activityes", modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp), fontWeight = FontWeight.Bold
                )

                IconButton(modifier = Modifier.padding(start = 180.dp), onClick = {
                    navController.navigate(
                        BottomBarScreen.Add.route
                    )
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }

            //Text(text = "\"Today's date is $currentDate\"") //currentDate วันทีปัจจุบัน

            /*Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Button(onClick = { drugViewModel.deleteAll(
                    GetDrug(name = name.value)
                )
                },Modifier.size(50.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff16C2D5)),
                    contentPadding = PaddingValues(0.dp)
                ){
                    Icon(Icons.Default.Add ,contentDescription = null, tint=Color.Black)
                }
            }*/


            /*for (i in 1..3){ //(Data ทั้งหมดที่จะแสดง Select มาแล้ว)
                //ตัวแสดงยาจาฐานข้อมูล
            }*/
            LazyColumn(modifier = Modifier.padding(bottom = 60.dp)) {
                items(drugs.value) { drugs ->
                    Drug(drugs.id,drugs.name, drugs.time, drugs.Status)

//                    Card(
//                        modifier = Modifier
//
//                            .padding(start = 20.dp, end = 20.dp, top = 25.dp)
//                            .background(
//                                color = Color(0xffEBEBEB), shape = RoundedCornerShape(20.dp)
//                            )
//                            .height(100.dp)
//                            .fillMaxWidth(),
//
//                        elevation = 20.dp,
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .background(
//                                    color = Color(0xffEBEBEB),
//                                    shape = RoundedCornerShape(20.dp)
//                                )
//                                .padding(10.dp),
//
//                            verticalArrangement = Arrangement.Center
//                        ) {
//                            Text(
//                                text = "Name: " + drugs.name,
//                                Modifier.padding(start = 20.dp),
//                                fontWeight = FontWeight.Bold,
//                                fontSize = 15.sp
//                            )
//                            Text(
//                                text = "Date: " + drugs.date,
//                                Modifier.padding(start = 20.dp),
//                                fontWeight = FontWeight.Bold,
//                                fontSize = 15.sp
//                            )
//
//                        }
//                        Column(
//                            modifier = Modifier
//
//                                .fillMaxHeight()
//                                .fillMaxWidth(), verticalArrangement = Arrangement.Center
//                        ) {
//                            IconButton(
//                                modifier = Modifier
//                                    .align(Alignment.End)
//                                    .padding(end = 20.dp)
//                                    .background(
//                                        color = Color(0xffD9D9D9),
//                                        shape = RoundedCornerShape(5.dp)
//                                    )
//                                    .height(24.dp)
//                                    .width(24.dp),
//                                onClick = { drugs.Status
//                                }) { //เขียน Update Database ตรงนี้ !!!!!!
//                                Icon(
//                                    painter = painterResource(id = R.drawable.baseline_check_24),
//                                    contentDescription = null,
//                                    tint = when (drugs.Status) {
//                                        true -> Color.Unspecified
//                                        false -> Color.Transparent
//                                    }
//                                )
//                            }
//                        }
//                    }
                }
            }
            /*Column(

                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 60.dp)
            ) {
               //println(drugs.value)

                Drug("Name : " + drugs.value, "12:00", true)
                Drug("Drug2", "12:00", false)
                for(i in 1..6){

                }
            }*/
        }
    }
}


package com.example.mvvmandroid.ui.theme.screens.home


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.inputmethodservice.Keyboard.Row
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mvvmandroid.navigation.ADD_ACCOUNT
import com.example.mvvmandroid.navigation.ADD_TASK
import com.example.mvvmandroid.navigation.VIEW_ACCOUNT
import com.example.mvvmandroid.navigation.VIEW_TASKS
import kotlinx.coroutines.launch
import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavController){

    val context= LocalContext.current

    //Navigation Drawer

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    val menuList = listOf(
        Icons.Filled.Home to "Home" ,
        Icons.Filled.Favorite to "Contacts" ,
        Icons.Filled.Info to "History",
        Icons.Filled.Settings to "Settings",
        Icons.Filled.AccountCircle to "Profile"
    )

    var selectedItem by remember { mutableStateOf(-1) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                menuList.forEachIndexed { index, pair ->
                    NavigationDrawerItem(
                        label = {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Icon(imageVector = pair.first, contentDescription = pair.second)
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(text = pair.second)
                            }
                        },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index })
                }
            }
        }) {
        Scaffold(
            topBar = {
                
                TopAppBar(title = { Text(text = "Homepage") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = "")
                        }
                    })

            }
        ) {


                //Main Content of the screen

                Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp)){


                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text = "This is current page", fontSize = 20.sp)

                        Text(
                            text = "EstatePlus payment platform",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))



                        Text(
                            text = "Manage your properties with ease!",
                            fontSize = 18.sp
                        )
                    }



                    Spacer(modifier = Modifier.height(10.dp))



                    Button(onClick = {navController.navigate(ADD_TASK)},
                        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Black)) {
                        Text(text = "Add task")
                    }



                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = {navController.navigate(VIEW_TASKS)},
                        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Black)) {
                        Text(text = "View task")
                    }


                    Spacer(modifier = Modifier.height(10.dp))




                    Button(onClick = {navController.navigate(ADD_ACCOUNT)},
                        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Black)) {
                        Text(text = "Add Account")
                    }



                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = {navController.navigate(VIEW_ACCOUNT)},
                        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Black)) {
                        Text(text = "View Account")
                    }


                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)){

                        val mUrl = "https://www.google.com/maps/@-1.2610626,36.7962211,14z?entry=ttu&g_ep=EgoyMDI0MDgyMC4xIKXMDSoASAFQAw%3D%3D"

                        AndroidView(factory = {
                            WebView(it).apply {
                                settings.apply {
                                    javaScriptEnabled=true
                                }


                                layoutParams = ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT
                                )
                                webViewClient = WebViewClient()
                                loadUrl(mUrl)
                            }

                        }, update = { it.loadUrl(mUrl) })



                    }



                }













            //End of main contents





            }


    }


}





@Composable
private fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Other")
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){

    HomeScreen(navController = rememberNavController())

}
package com.example.instagram_clone

import android.provider.ContactsContract
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProfileScreen(){
    Column(
       modifier = Modifier.fillMaxSize()
    ){
        TopBar(name = "Chacne_Official")
        Spacer(modifier = Modifier.height(4.dp))
    }
}

// backIcon, bellIcon, detailIcon ...
@Composable
fun TopBar(
    name : String,
    modifier : Modifier = Modifier
){
    Row(
        //suzik
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
    ){
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = name,
            // if user name to long that ...
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "Bell",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_dotmenu),
            contentDescription = "Menu",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}

// profilePicture, selfContent ...
@Composable
fun ProfileSection(
    modifier : Modifier = Modifier
){
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp) // side margine
        ){
            RoundImage(image = painterResource(id = R.drawable.profileImage), modifier = Modifier
                .size(100.dp)
                .weight(3f) // 30% of all images are photos
            )
        }
    }
}

// profilePicture (rounded or other decoration)
@Composable
fun RoundImage(
    image : Painter,
    modifier : Modifier = Modifier
){
    Image(
        painter = image,
        contentDescription = "picture deco",
        modifier = Modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            // this widget is width is automatically assigned according to the height.
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}
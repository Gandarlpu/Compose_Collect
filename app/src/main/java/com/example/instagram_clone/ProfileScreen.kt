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
        ProfileSection()
    }
}

// backIcon, bellIcon, detailIcon ...
@Composable
fun TopBar(
    name : String,
    modifier : Modifier = Modifier
){
    Row(
        //수직
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
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

// 프로필 사진, 구독자 수 ...
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
            RoundImage(
                image = painterResource(id = R.drawable.profileimage),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f) // 30% of all images are photos
            )
            Spacer(modifier = Modifier.width(16.dp))
            StateSection(modifier = Modifier.weight(7f))
        }
    }
}

// profilePicture (rounded or other decoration)
// 15:07
@Composable
fun RoundImage(
    image : Painter,
    modifier : Modifier = Modifier
){
    Image(
        painter = image,
        contentDescription = "picture deco",
        modifier = modifier
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

// 게시글, 팔로워, 팔로윙
@Composable
fun StateSection(
    modifier : Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ProfileStat(numberText = "601", text = "Posts")
        ProfileStat(numberText = "100K", text = "Followers")
        ProfileStat(numberText = "71", text = "Followings")
    }
}

// 숫자 뒤에 K를 붙이는 기능
@Composable
fun ProfileStat(
    numberText : String,
    text : String,
    modifier : Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(4.dp)) // 두개의 Text사이의 간격 조절
        Text(text = text)
    }
}
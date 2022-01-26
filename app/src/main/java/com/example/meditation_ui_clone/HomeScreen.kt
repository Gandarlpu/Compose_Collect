package com.example.meditation_ui_clone

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditation_ui_clone.ui.theme.Meditation_UI_CloneTheme
import com.plcoding.meditationuiyoutube.ui.theme.*

@Composable
fun HomeScreen(){
    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()
    ){
        Column{
            GreetingSection()
            ChipSection(chips = listOf("Sweet sleep" , "Insomnia" , "Depression"))
            CurrentMeditation()
        }

    }
}

@Composable
fun GreetingSection(
    name : String = "Chance"
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, $name",
                style = MaterialTheme.typography.h1
            )
            Text(
                text = "We wish you hava a good day!",
                style = MaterialTheme.typography.body1
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White, // 이 아이콘에 흰색을 지정
            modifier = Modifier.size(24.dp)
        )
    }
}


@Composable
fun ChipSection(
    chips : List<String>
){
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow{
        items(chips.size){
            Box(
                contentAlignment = Alignment.Center, //가운데 정렬
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp) // 외부
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp) // 내부
            ){
                Text(text = chips[it] , color = TextWhite)
            }
        }
    }
}

@Preview
@Composable
fun testPreview(){
    Meditation_UI_CloneTheme{
        ChipSection(listOf("Sweet sleep" , "Insomnia" , "Depression"))
    }
}

@Composable
fun CurrentMeditation(
    color : Color = LightRed
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp) // Row바깥의 여백
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp) // 내부아이템들과 도형간의 여백
            .fillMaxWidth()
    ){
        Column {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.h1
            )
            Text(
                text = "Meditation * 3-10 min",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }

    }

}



@ExperimentalFoundationApi
@Composable
fun FeatureSection(features : List<Feature>){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(
            text = "Features",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2), // 행당 원하는 항목 수 전달
            contentPadding = PaddingValues(start = 7.5.dp , end = 7.5.dp , bottom = 100.dp),
            // 바텀네비가 있으므로 bottom에 여백을 많이 주는것
            modifier = Modifier.fillMaxHeight()
        ){
            items(features.size){

            }
        }


    }
}

// 박스는 3가지 색깔로 나뉘어져 있으므로 그 아이템들 색상 설정정
@Composable
fun FeatureItem(
    feature : Feature
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)//종횡비 그리드에서 구성가능한 너비를 제공하기 때문
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f , height * 0.3f)
        // 물결무늬의 처음 시작을 0f으로 줬지만 height를 30%만큼 줘서 30%만큼 밑에서 시작
        val mediumColoredPoint2 = Offset(width * 0.1f , height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f , height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f , height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f,  -height.toFloat()) // 급격한 경사주기

        val mediumColoredPath = Path().apply{
            moveTo(mediumColoredPoint1.x , mediumColoredPoint1.y)
            standardQuardFromTo(mediumColoredPoint1 , mediumColoredPoint2)
            standardQuardFromTo(mediumColoredPoint2 , mediumColoredPoint3)
            standardQuardFromTo(mediumColoredPoint3 , mediumColoredPoint4)
            standardQuardFromTo(mediumColoredPoint4 , mediumColoredPoint5)
            lineTo(width.toFloat() + 100f , height.toFloat() + 100f)
            lineTo(-100f , height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f , height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f , height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f , height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f , height.toFloat())
        val lightPoint5 = Offset(width * 1.4f , -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x , lightPoint1.y)
            standardQuardFromTo(lightPoint1 , lightPoint2)
            standardQuardFromTo(lightPoint2 , lightPoint3)
            standardQuardFromTo(lightPoint3 , lightPoint4)
            standardQuardFromTo(lightPoint4 , lightPoint5)
            lineTo(width.toFloat() + 100f , height.toFloat() + 100f)
            lineTo(-100f , height.toFloat() + 100f)
            close()

        }


    }
}






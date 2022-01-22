package com.example.instagram_clone

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalFoundationApi
@Composable
fun ProfileScreen(){
    var selectedTabIndex by remember{
        mutableStateOf(0)
    }

    Column(
       modifier = Modifier.fillMaxSize()
    ){
        TopBar(name = "Chacne_Official")
        Spacer(modifier = Modifier.height(4.dp))
        ProfileSection()
        Spacer(modifier = Modifier.height(25.dp))
        ButtonSection(modifier = Modifier.fillMaxWidth()) // fillMaxWidth안하면 왼쪽벽에 붙어버림 따라서,
        Spacer(modifier = Modifier.height(25.dp))
        HighlightSection(
            highlights = listOf(
                ImageWithText(
                    image = painterResource(id = R.drawable.youtube),
                    text = "YouTube"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.qa),
                    text = "Q&A"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.discord),
                    text = "Discord"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.telegram),
                    text = "Telegram"
                ),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        PostTabView(
            imageWithTexts = listOf(
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_grid),
                    text = "Posts"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_reels),
                    text = "Reels"
               ),
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_igtv),
                    text = "IGTV"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_profile),
                    text = "Profile"
                )
            )
        ){
          selectedTabIndex = it
        }

        when(selectedTabIndex){
            0 -> PostSection(
                posts = listOf(
                    painterResource(id = R.drawable.num_1),
                    painterResource(id = R.drawable.num_2),
                    painterResource(id = R.drawable.num_3),
                    painterResource(id = R.drawable.num_4),
                    painterResource(id = R.drawable.num_5),
                    painterResource(id = R.drawable.num_6),

                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
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
        // 프로필 내용 컴포즈
        ProfileDescription(
            displayName = "Programming Mentor",
            description = "10 years of coding experience\n" +
                        "Want me to make your app? Send me an email!\n" +
                        "Subscribe to my YouTube channel!",
            url = "https://youtube.com/c/PhilippLackner",
            followedBy = listOf("codinginflow" , "miakhlifa"),
            otherCount = 17
        )
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


// 프로필 설명란
@Composable
fun ProfileDescription(
    displayName : String,
    description : String,
    url : String,
    followedBy : List<String>,
    otherCount : Int
){

    val letterSpacing = 0.5.sp // 모든 텍스트에 적용할 여백
    val lineHeight = 20.sp // 줄 높이
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ){
        // displayName
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        // description
        Text(
            text = description,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        // url
        Text(
            text = url,
            color = Color(0xFF3D3D91),
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )

        if(followedBy.isNotEmpty()){
            Text(
                text = buildAnnotatedString { // boldStyle을 지정해서 해당 스타일이 필요한 text의 경우 적용시키기 위해 만듦
                    val boldStyle = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    append("Followed by ")
                    followedBy.forEachIndexed{index , name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop() // ,가 boldStyle되지 않도록 스타일을 빼놓음.
                        if(index < followedBy.size-1) {
                            append(", ")
                        }
                    }
                    if(otherCount > 2){ // 2인 이유는 앞에 forEach에서 2명의 사람을 추가했기 때문에 그외 otherCount만큼 더 있다라는걸 알려주기위한듯
                        append(" and ")
                        pushStyle(boldStyle)
                        append("$otherCount others")
                    }
                },
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }
    }
}

@Composable
fun ButtonSection(
    modifier : Modifier = Modifier
){
    val minWidth = 95.dp
    val height = 30.dp
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ){
        // 액션 버튼 넣기 (클릭하면 밑으로 메뉴가 나오는 버튼)
        ActionButton(
            text = "Following",
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            text = "Message",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            text = "Email",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .height(height)
        )
    }
}


@Composable
fun ActionButton(
    modifier : Modifier = Modifier,
    text : String? = null,
    icon : ImageVector? = null
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(6.dp)
    ){
        if(text != null){
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )
        }
        if(icon != null){
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

// 이미지와 텍스트를 사용하여 root 패키지에 스토리 하이라이트라는 데이터 클래스를 생성한다.
@Composable
fun HighlightSection(
    modifier: Modifier = Modifier,
    highlights: List<ImageWithText>
){
    // 이미지와 텍스트를 같이 사용하기 때문에 메모리 사용량이 높아질 것으로 예상된다.
    // 따라서, 일반 Row 말고 LazyRow을 사용하는 듯
    LazyRow(modifier = modifier){
        items(highlights.size){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(end = 15.dp)
            ){
                RoundImage(
                    image = highlights[it].image,
                    modifier = Modifier.size(70.dp)
                )
                Text(
                    text = highlights[it].text,
                    overflow = TextOverflow.Ellipsis, // 넘기는 글 처리를 ...으로
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


// 탭 레이아웃
@Composable
fun PostTabView(
    modifier : Modifier = Modifier,
    imageWithTexts: List<ImageWithText>,
    onTabSelected : (selectedIndex : Int) -> Unit
    // 탭 선택할 때마다 이 함수가 트리거되어 루트 구성가능에서 선택가능한 인덱스 가져와 실제로 펴히라수잇다.
){
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val inactiveColor = Color(0xFF777777)
    // 탭이 선택되지 않았을 때 색상
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent, // 강조 색상
        contentColor = Color.Black, //내용 색상
        modifier = modifier

    ){
        imageWithTexts.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                // 탭이 선택되었는지 어캐앐잇나
                // selectedTabIndex가 0일때 처음 탭인덱스는 0부터 시작이니까
                selectedContentColor = Color.Black, // 선택된 탭의 색상
                unselectedContentColor = inactiveColor, // 선택되지 않은 탭의 색상
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }
            ) {
                Icon(
                    painter = item.image,
                    contentDescription = item.text,
                    tint = if(selectedTabIndex == index) Color.Black else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun PostSection(
    posts : List<Painter>,
    modifier : Modifier = Modifier
){
    LazyVerticalGrid(
        cells = GridCells.Fixed(3), // 몇개의 고정값
        modifier = modifier
            .scale(1.01f)
    ){
        items(posts.size){
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop, //컨텐츠 배율 자르기
                modifier = Modifier
                    .aspectRatio(1f) // 가로 세로 배율이 1f
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}









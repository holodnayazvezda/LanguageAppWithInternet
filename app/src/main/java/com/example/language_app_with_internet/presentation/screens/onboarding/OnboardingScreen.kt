package com.example.language_app_with_internet.presentation.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.language_app_with_internet.common.Constants
import com.example.language_app_with_internet.presentation.components.AppButton
import com.example.languageapp.R
import com.example.language_app_with_internet.presentation.components.BaseScreenBackground
import com.example.language_app_with_internet.presentation.theme.blue
import com.example.language_app_with_internet.presentation.theme.dark
import com.example.language_app_with_internet.presentation.theme.greyDark
import com.example.language_app_with_internet.presentation.theme.lightGrey
import com.example.language_app_with_internet.presentation.theme.orange
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.startKoin

@Composable
fun OnboardingScreen(
    viewModel: OnboardingScreenViewModel = koinViewModel(),
    navigateToLanguageSelect: () -> Unit
) {
    val onbScreenNumber = viewModel.onbScreenNumber.collectAsState().value
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {3}, initialPage = onbScreenNumber)
    BaseScreenBackground {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState
            ) { page ->
                val item = Constants.onboardingItems[page]
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(item.image),
                        contentDescription = null,
                        modifier = Modifier.width(if (page == 0) 240.dp else 215.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(Modifier.height(if (page == 0) 131.dp else if (page == 2) 120.dp else 95.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        repeat(3) { i ->
                            Box(
                                modifier = Modifier.size(8.dp).clip(CircleShape).background(
                                    if (i == page) orange
                                    else lightGrey
                                ).padding(8.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(40.dp))
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 18.sp,
                        color = greyDark.copy(alpha = 0.89f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(50.dp))
                    AppButton(
                        text = item.buttonText,
                        onClick = {
                            scope.launch {
                                if (page != 2) {
                                    pagerState.animateScrollToPage(page + 1)
                                } else {
                                    navigateToLanguageSelect()
                                }
                                viewModel.next()
                            }
                        },
                        background = blue
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Skp onboarding",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 15.sp,
                        color = dark,
                        modifier = Modifier.clickable {
                            viewModel.skip()
                            navigateToLanguageSelect()
                        }
                    )
                }
            }
        }
    }
}
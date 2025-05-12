package com.example.features.newsDetails

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import com.example.R
import com.example.data.model.ArticleDTO
import com.example.ui.theme.widget.TextTitle
import com.example.util.Dimens

@Composable
fun NewsDetailItem(
    article: ArticleDTO
) {
    val cardPadding = dimensionResource(id = R.dimen.sixteen_dp)
    val smallPadding = dimensionResource(id = R.dimen.eight_dp)
    val tinyPadding = dimensionResource(id = R.dimen.four_dp)

    Scaffold { paddingValues ->
        Column(
            Modifier
                .fillMaxSize(Dimens.FONT_SIZE_1F)
                .padding(paddingValues),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(smallPadding)
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.sixteen_dp))),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = dimensionResource(
                        id = R.dimen.ten_dp
                    )
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(cardPadding)
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.CenterVertically),
                    ) {
                        TextTitle(
                            name = article.title,
                            modifier = Modifier,
                            color = MaterialTheme.colorScheme.surface,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Spacer(modifier = Modifier.height(tinyPadding))
                    }
                }
            }

            MyWebView(url = article.url)
        }
    }
}

@Composable
fun MyWebView(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    })
}

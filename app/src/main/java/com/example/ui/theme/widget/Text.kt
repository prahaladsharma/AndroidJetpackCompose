package com.example.ui.theme.widget


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.util.Dimens.bottomPadding
import com.example.util.Dimens.endPadding
import com.example.util.Dimens.startPadding
import com.example.util.Dimens.topPadding

@Composable
fun TextTitle(
    name: String,
    modifier: Modifier,
    color: Color,
    fontWeight: FontWeight,
    style: TextStyle,
) {
    Text(
        text = name,
        modifier = modifier.padding(startPadding, topPadding, endPadding, bottomPadding),
        fontWeight = fontWeight,
        style = style,
        color = color,
    )
}

@Composable
fun TextMedium(
    name: String,
    modifier: Modifier,
    color: Color,
    style: TextStyle,
){
    Text(
        text = name,
        modifier = modifier.padding(startPadding, topPadding, endPadding, bottomPadding),
        style = style,
        color = color,
    )
}
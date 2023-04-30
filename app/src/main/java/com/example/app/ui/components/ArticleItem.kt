package com.example.app.ui.components

/**
 * Class:ArticleItem
 * @author: tangyu
 * Description:
 * @Date:  2022/11/28
 *

 */
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.model.entity.ArticleEntity

@Composable
fun ArticleItem(article: ArticleEntity, isLast: Boolean,modifier: Modifier = Modifier) {

    Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
        Text(

            text = article.title,
            color = Color(0xff333333),
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.Start) {
            Text(
                "来源：${article.source}",
                color = Color(0xff999999),
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                article.timestamp,
                color = Color(0xff999999),
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
    if (!isLast)
        Divider()
}

@Preview
@Composable
fun ArticleItemPreview() {
}


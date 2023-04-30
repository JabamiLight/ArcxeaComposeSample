package com.example.app.ui.components

/**
 * Class:VideoItem
 * @author: tangyu
 * Description:
 * @Date:  2022/11/29
 *

 */
import android.widget.ImageView.ScaleType
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.app.model.entity.VideoEntity
import com.example.app.ui.theme.APPTheme
import com.example.app.viewmodel.VideoViewModel
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun VideoItem(videoEntity: VideoEntity, b: Boolean) {
    ConstraintLayout(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {

        val (image, title, type, duration, divider) = createRefs()

        AsyncImage(
            model = videoEntity.imageUrl,
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    width = Dimension.value(115.dp)
                }
                .aspectRatio(6 / 3.4f)

        )
        Text(
            text = videoEntity.title,
            fontSize = 16.sp,
            color = Color(0xff666666),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(image.top)
                start.linkTo(image.end, margin = 8.dp)
                end.linkTo(parent.end)

                width = Dimension.preferredWrapContent

            }
        )
        Text(
            text = videoEntity.type,
            fontSize = 12.sp,
            color = Color(0xff999999),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(type) {
                start.linkTo(title.start)
                bottom.linkTo(image.bottom)

            }
        )
        Text(
            text = "时长${videoEntity.duration}",
            fontSize = 12.sp,
            color = Color(0xff999999),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(duration) {
                baseline.linkTo(type.baseline)
                start.linkTo(type.end, margin = 8.dp)
            }
        )
        Divider(modifier = Modifier.constrainAs(divider) {
            top.linkTo(image.bottom, margin = 16.dp)
        })

    }


}


//@Preview()
//@Composable
//
//fun VideoItemPreview(@PreviewParameter(VideoViewModel::class) dbBean: VideoViewModel,) {
//}


package com.jiahaoliuliu.coilimagecaching

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.imageLoader
import com.jiahaoliuliu.coilimagecaching.ui.theme.CoilImageCachingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val imageUrl = "https://www.livehome3d.com/assets/img/articles/design-house/how-to-design-a-house@2x.jpg"
        setContent {
            CoilImageCachingTheme {
                Scaffold { contentPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1400f/800f)
                        )
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = {
                                imageLoader.diskCache?.clear()
                                imageLoader.memoryCache?.clear()
                            }
                        ) {
                            Text("Clear cache")
                        }
                    }
                }
            }
        }
    }
}
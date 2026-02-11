package com.jiahaoliuliu.coilimagecaching

import android.R.attr.contentDescription
import android.os.Bundle
import android.os.Debug
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
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
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data(imageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1400f/800f),
                            placeholder = painterResource(R.drawable.coillogo),
                            error = painterResource(R.drawable.coillogo)
                        )
                        Spacer(Modifier.height(16.dp))
                        AsyncImage(
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data(imageUrl)
                                .crossfade(true)
                                .placeholder(R.drawable.coillogo)
                                .error(R.drawable.coillogo)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1400f/800f),
                        )
                        Spacer(Modifier.height(16.dp))
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1400f/800f),
                            placeholder = painterResource(R.drawable.coillogo),
                            error = painterResource(R.drawable.coillogo)
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                        ) {
                            Button(
                                onClick = {
                                    imageLoader.diskCache?.clear()
                                    imageLoader.memoryCache?.clear()
//                                imageLoader.diskCache?.remove("MyImageKey")
//                                imageLoader.memoryCache?.remove(MemoryCache.Key("MyImageKey"))
                                }
                            ) {
                                Text("Clear cache")
                            }
                            Button(
                                onClick = {
                                    printOutMemory()
                                }
                            ) {
                                Text("Print out memory")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun printOutMemory() {
        val memoryInfo = Debug.MemoryInfo().also(Debug::getMemoryInfo)
        val javaHeap = memoryInfo.getMemoryStat("summary.java-heap")
        println("Summary. Java Heap: $javaHeap")
        val nativeHeap = memoryInfo.getMemoryStat("summary.native-heap")
        println("Summary. Native Heap: $nativeHeap")
        val code = memoryInfo.getMemoryStat("summary.code")
        println("Summary. Code: $code")
        val stack = memoryInfo.getMemoryStat("summary.stack")
        println("Summary. Stack: $stack")
        val graphics = memoryInfo.getMemoryStat("summary.graphics")
        println("Summary. Graphics: $graphics")
        val privateOther = memoryInfo.getMemoryStat("summary.private-other")
        println("Summary. Private Other: $privateOther")
        val system = memoryInfo.getMemoryStat("summary.system")
        println("Summary. System: $system")
    }
}
package com.jiahaoliuliu.coilimagecaching

import android.R.attr.bitmap
import android.os.Bundle
import android.os.Debug
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.jiahaoliuliu.coilimagecaching.ui.theme.CoilImageCachingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoilImageCachingTheme {
                Scaffold { contentPadding ->
                    Column(
                        modifier = Modifier
                            .padding(contentPadding)
                            .padding(16.dp)
                            .fillMaxSize()
                    ) {
                        ItemRow(
                            imageUrl = "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/main/images/Coffee.jpg"
                        )
                        ItemRow(
                            imageUrl = "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/main/images/Cookies.jpg"
                        )
                        ItemRow(
                            imageUrl = "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/main/images/Pastel%20de%20Belen.jpg"
                        )
                        ItemRow(
                            imageUrl = "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/main/images/Sagrada%20familia.jpg"
                        )
                        ItemRow(
                            imageUrl = "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/main/images/Snow.jpg"
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
        println("MemoryInfo -----------")
        println(memoryInfo.memoryStats)
        println("Each fields -----------")
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
        val totalPss = memoryInfo.getMemoryStat("summary.total-pss")
        println("Summary. Total PSS: $totalPss")
        val totalSwap = memoryInfo.getMemoryStat("summary.total-swap")
        println("Summary. Total Swap: $totalSwap")
        println("------------------------")
        println("Total PSS: ${memoryInfo.totalPss}")
        println("Total Private Clean: ${memoryInfo.totalPrivateClean}")
        println("Total Private Dirty: ${memoryInfo.totalPrivateDirty}")
        println("Total Shared Clean: ${memoryInfo.totalSharedClean}")
        println("Total Shared Dirty: ${memoryInfo.totalSharedDirty}")
        println("Total Swapped PSS: ${memoryInfo.totalSwappablePss}")
        println("------------------------")
        println("DalvikPSS: ${memoryInfo.dalvikPss}")
        println("DalvikPrivateDirty ${memoryInfo.dalvikPrivateDirty}")
        println("DalvikSharedDirty ${memoryInfo.dalvikSharedDirty}")
        println("NativePSS: ${memoryInfo.nativePss}")
        println("NativePrivateDirty: ${memoryInfo.nativePrivateDirty}")
        println("NativeSharedDirty: ${memoryInfo.nativeSharedDirty}")
        println("OtherPSS: ${memoryInfo.otherPss}")
        println("OtherPrivateDirty: ${memoryInfo.otherPrivateDirty}")
        println("OtherSharedDirty: ${memoryInfo.otherSharedDirty}")
        println("------------------------")
    }
}

//import android.graphics.Bitmap
//import android.os.Build
//
//fun getBitmapAllocationSize(bitmap: Bitmap): Long {
////    return if (Build.VERSION.SDK_INT >= Build.VERSION_MAX_ALLOWED) {
//        // getAllocationByteCount() returns the size of the memory
//        // allocated to hold this bitmap's pixels.
//        return bitmap.allocationByteCount.toLong()
////    } else {
////        // Fallback for older APIs (Pre-KitKat)
////        bitmap.byteCount.toLong()
////    }
//}
//
@Composable
private fun ItemRow(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .height(128.dp)
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            placeholder = painterResource(R.drawable.coillogo),
            error = painterResource(R.drawable.coillogo),
            contentScale = ContentScale.Crop,
        )
    }
}

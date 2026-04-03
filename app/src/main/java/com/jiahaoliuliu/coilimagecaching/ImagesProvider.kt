package com.jiahaoliuliu.coilimagecaching

object ImagesProvider {
    fun provideOriginalImages() =
        listOf(
            "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/main/images/Coffee.jpg",
            "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/main/images/Cookies.jpg",
            "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/main/images/Pastel%20de%20Belen.jpg",
            "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/main/images/Sagrada%20familia.jpg",
            "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/main/images/Snow.jpg",
        )

    fun provideCroppedImages() =
        listOf(
            "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/memoryOptimization/imagesCropped/Coffee.jpg",
            "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/memoryOptimization/imagesCropped/Cookies.jpg",
            "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/memoryOptimization/imagesCropped/Pastel%20de%20Belen.jpg",
            "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/memoryOptimization/imagesCropped/Sagrada%20familiaResized2.jpg",
            "https://raw.githubusercontent.com/jiahaoliuliu/CoilImageCaching/refs/heads/memoryOptimization/imagesCropped/Snow.jpg",
        )
}
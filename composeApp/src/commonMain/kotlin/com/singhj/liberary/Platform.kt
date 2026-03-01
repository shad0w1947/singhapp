package com.singhj.liberary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
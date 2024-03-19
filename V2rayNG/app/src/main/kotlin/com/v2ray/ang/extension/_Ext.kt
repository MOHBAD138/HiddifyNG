package com.v2ray.ang.extension

import android.content.Context
import android.os.Build
import android.widget.Toast
import com.v2ray.ang.AngApplication
import me.drakeet.support.toast.ToastCompat
import org.json.JSONObject
import java.net.URI
import java.net.URLConnection

val Context.v2RayApplication: AngApplication
    get() = applicationContext as AngApplication

//fun Context.toast(message: Int):  Toast = ToastCompat
//        .makeText(this, message, Toast.LENGTH_SHORT)
//        .apply {
//            show()
//        }
//
//fun Context.toast(message: CharSequence): Toast = ToastCompat
//        .makeText(this, message, Toast.LENGTH_SHORT)
//        .apply {
//            show()
//        }

fun JSONObject.putOpt(pair: Pair<String, Any>) = putOpt(pair.first, pair.second)
fun JSONObject.putOpt(pairs: Map<String, Any>) = pairs.forEach { putOpt(it.key to it.value) }

const val threshold = 1000
const val divisor = 1024F

fun Long.toSpeedString() = toTrafficString() + "/s"

fun Long.toTrafficString(): String {
    if (this == 0L)
        return "\t\t\t0\t  B"

    if (this < threshold)
        return "${this.toFloat().toShortString()}\t  B"

    val kib = this / divisor
    if (kib < threshold)
        return "${kib.toShortString()}\t KB"

    val mib = kib / divisor
    if (mib < threshold)
        return "${mib.toShortString()}\t MB"

    val gib = mib / divisor
    if (gib < threshold)
        return "${gib.toShortString()}\t GB"

    val tib = gib / divisor
    if (tib < threshold)
        return "${tib.toShortString()}\t TB"

    val pib = tib / divisor
    if (pib < threshold)
        return "${pib.toShortString()}\t PB"

    return "∞"
}

fun Context.toast(message: CharSequence) {
    ToastCompat.makeText(this, message, Toast.LENGTH_SHORT).apply { show() }
}

fun JSONObject.putOpt(pair: Pair<String, Any?>) {
    put(pair.first, pair.second)
}

fun JSONObject.putOpt(pairs: Map<String, Any?>) {
    pairs.forEach { put(it.key, it.value) }
}

const val THRESHOLD = 1000L
const val DIVISOR = 1024.0

fun Long.toSpeedString(): String = this.toTrafficString() + "/s"

fun Long.toTrafficString(): String {
    if (this < THRESHOLD) {
        return "$this B"
    }
    val kb = this / DIVISOR
    if (kb < THRESHOLD) {
        return "${String.format("%.1f KB", kb)}"
    }
    val mb = kb / DIVISOR
    if (mb < THRESHOLD) {
        return "${String.format("%.1f MB", mb)}"
    }
    val gb = mb / DIVISOR
    if (gb < THRESHOLD) {
        return "${String.format("%.1f GB", gb)}"
    }
    val tb = gb / DIVISOR
    if (tb < THRESHOLD) {
        return "${String.format("%.1f TB", tb)}"
    }
    return String.format("%.1f PB", tb / DIVISOR)
}

val URLConnection.responseLength: Long
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        contentLengthLong
    } else {
        contentLength.toLong()
    }

val URI.idnHost: String
    get() = host?.replace("[", "")?.replace("]", "") ?: ""

fun String.removeWhiteSpace(): String = replace("\\s+".toRegex(), "")

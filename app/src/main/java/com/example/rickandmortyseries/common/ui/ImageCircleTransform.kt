package com.example.rickandmortyseries.common.ui

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import com.squareup.picasso.Transformation

/**
 * Class that extends Transformation class from Picasso library.
 */
class ImageCircleTransform : Transformation {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)

        val bitmap = Bitmap.createBitmap(size, size, source.config)
        val canvas = Canvas(bitmap)

        try {
            val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            paint.shader = shader

            val r = size / 2f
            canvas.drawCircle(r, r, r, paint)
        } finally {
            source.recycle()
        }

        return bitmap
    }

    override fun key(): String {
        return "circle"
    }
}
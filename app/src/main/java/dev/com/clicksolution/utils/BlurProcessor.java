package dev.com.clicksolution.utils;

import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BlurProcessor {

    private RenderScript rs;

    private static final boolean IS_BLUR_SUPPORTED = true;
    private static final int MAX_RADIUS = 25;

    public BlurProcessor(RenderScript rs) {
        this.rs = rs;
    }

    @Nullable
    public Bitmap blur(@NonNull Bitmap bitmap, float radius, int repeat) {

        if (!IS_BLUR_SUPPORTED) {
            return null;
        }

        if (radius > MAX_RADIUS) {
            radius = MAX_RADIUS;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // Create allocation type
        Type bitmapType = new Type.Builder(rs, Element.RGBA_8888(rs))
                .setX(width)
                .setY(height)
                .setMipmaps(false) // We are using MipmapControl.MIPMAP_NONE
                .create();

        // Create allocation
        Allocation allocation = Allocation.createTyped(rs, bitmapType);

        // Create blur script
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        blurScript.setRadius(radius);

        // Copy data to allocation
        allocation.copyFrom(bitmap);

        // set blur script input
        blurScript.setInput(allocation);

        // invoke the script to blur
        blurScript.forEach(allocation);

        // Repeat the blur for extra effect
        for (int i=0; i<repeat; i++) {
            blurScript.forEach(allocation);
        }

        // copy data back to the bitmap
        allocation.copyTo(bitmap);

        // release memory
        allocation.destroy();
        blurScript.destroy();
        allocation = null;
        blurScript = null;

        return bitmap;
    }
}
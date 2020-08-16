package com.github.cr9ck.clickerapp.presentation.view.game.levels;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.DrawableRes;

public class Emoji {

    private Bitmap emoji;

    public Emoji(Resources res, @DrawableRes int emojiResId) {
        emoji = BitmapFactory.decodeResource(res, emojiResId);
    }

    public void setSize(int width, int height) {
        emoji = Bitmap.createScaledBitmap(emoji, width, height, false);
    }

    public Bitmap getEmoji() {
        return emoji;
    }
}

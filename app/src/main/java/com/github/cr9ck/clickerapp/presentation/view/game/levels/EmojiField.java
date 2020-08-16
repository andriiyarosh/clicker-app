package com.github.cr9ck.clickerapp.presentation.view.game.levels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

public class EmojiField extends SurfaceView implements Runnable, View.OnClickListener {

    private Emoji emoji;
    private Level level;
    private Paint paint;
    private OnItemClickListener listener;
    private AtomicBoolean drawingEmoji = new AtomicBoolean(false);
    private Thread drawingWork;

    public EmojiField(Context context) {
        super(context);
    }

    public EmojiField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmojiField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void run() {
        drawingEmoji.set(true);
        while (drawingEmoji.get()) {
            drawEmoji(level);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public void invalidate() {
        super.invalidate();
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawColor(Color.WHITE);
        if (emoji != null) {
            emoji.setSize(getWidth(), getHeight());
            canvas.drawBitmap(emoji.getEmoji(), 0, 0, paint);
        }
        drawingEmoji.set(false);
        drawingWork.interrupt();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void initLevel(Level level) {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        this.level = level;
        emoji = new Emoji(getContext().getResources(), level.getImage());
//        emoji.setSize(getWidth(), getHeight());
        drawingWork = new Thread(this);
        drawingWork.start();
    }

    private void drawEmoji(Level level) {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawColor(Color.WHITE);
            emoji.setSize(canvas.getWidth(), canvas.getHeight());
            canvas.drawBitmap(emoji.getEmoji(), 0, 0, paint);
            getHolder().unlockCanvasAndPost(canvas);
            drawingEmoji.set(false);
            drawingWork.interrupt();
        }
    }

    @Override
    public void onClick(View view) {
        if (listener == null) return;
        listener.onItemPressed();
    }

    public interface OnItemClickListener {
        void onItemPressed();
    }
}

package com.example.closetifiy_finalproject;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class MultiTouchListener implements View.OnTouchListener {

    private float mScaleFactor = 1.0f;
    private ScaleGestureDetector mScaleGestureDetector;

    public MultiTouchListener(Context context) {
        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                view.setX(motionEvent.getRawX() - view.getWidth() / 2);
                view.setY(motionEvent.getRawY() - view.getHeight() / 2);
                break;
        }

        view.setScaleX(mScaleFactor);
        view.setScaleY(mScaleFactor);

        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            return true;
        }
    }
}
package com.example.constantin.avonotifier.activities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

class DotsDecorator implements DayViewDecorator {
    private HashSet<CalendarDay> dates;
    private LineBackgroundSpan span;

    public DotsDecorator(Collection<CalendarDay> dates, LineBackgroundSpan span) {
        this.dates = new HashSet<>(dates);
        this.span = span;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(span);
    }
}

class TwoDotsSpan implements LineBackgroundSpan {
    float radius;
    int color;

    public TwoDotsSpan(float radius, int color) {
        this.radius = radius;
        this.color = color;
    }

    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence, int start, int end, int lineNum) {

        int oldColor = paint.getColor();
        if (color != 0) {
            paint.setColor(color);
        }

        float middle = (left + right) / 2;
        float dist = 1.4f * radius;
        float center1 = middle - dist;
        float center2 = middle + dist;

        canvas.drawCircle(center1, bottom + radius, radius, paint);
        canvas.drawCircle(center2, bottom + radius, radius, paint);

        paint.setColor(oldColor);
    }
}

class ThreeDotsSpan implements LineBackgroundSpan {
    float radius;
    int color;

    public ThreeDotsSpan(float radius, int color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence, int start, int end, int lineNum) {

        int oldColor = paint.getColor();
        if (color != 0) {
            paint.setColor(color);
        }

        float middle = (left + right) / 2;
        float middleOffset = 3f * radius;
        float center1 = middle - middleOffset;
        float center2 = middle;
        float center3 = middle + middleOffset;

        canvas.drawCircle(center1, bottom + radius, radius, paint);
        canvas.drawCircle(center2, bottom + radius, radius, paint);
        canvas.drawCircle(center3, bottom + radius, radius, paint);

        paint.setColor(oldColor);
    }
}

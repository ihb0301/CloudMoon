package com.goormthon_univ.cloudmoon.calendar_decorator;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import com.goormthon_univ.cloudmoon.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class AllDecorator implements DayViewDecorator {

    private final Drawable drawable;
    private HashSet<CalendarDay> dates;
    public AllDecorator(Collection<CalendarDay> dates, Activity context) {
        drawable = context.getDrawable(R.drawable.calendar_image_all);

        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day){
        Log.e("shouldDecorate:dates",day.toString());
        Log.e("shouldDecorate",CalendarDay.today().toString());
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(drawable);
        view.addSpan(new ForegroundColorSpan(Color.WHITE));
    }

}

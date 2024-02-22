package com.goormthon_univ.cloudmoon.calendar_decorator;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;

import com.goormthon_univ.cloudmoon.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class TodayDecorator implements DayViewDecorator {

    private final Drawable drawable;

    public TodayDecorator(Activity context) {
        drawable = context.getDrawable(R.drawable.calendar_image_sel);

    }

    @Override
    public boolean shouldDecorate(CalendarDay day){
        return day.equals(CalendarDay.today());
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
        //TODO TodayDecorator와 AllDecorator가 겹치는 경우 글자색 버그 있음
        view.addSpan(new ForegroundColorSpan(Color.BLACK));
    }

}

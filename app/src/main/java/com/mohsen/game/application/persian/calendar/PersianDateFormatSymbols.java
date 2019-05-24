package com.mohsen.game.application.persian.calendar;

import java.text.DateFormatSymbols;

public class PersianDateFormatSymbols extends DateFormatSymbols {

    @Override
    public String[] getAmPmStrings()
    {
        String[] amPmStrings = super.getAmPmStrings();
        amPmStrings[0] = "صبح";
        amPmStrings[1] = "عصر";
        return amPmStrings;
    }

    @Override
    public String[] getShortWeekdays(){

        String[] daysLabel = super.getShortWeekdays();
        daysLabel[0] = "";
        daysLabel[1] = "شنبه";
        daysLabel[2] = "یکشنبه";
        daysLabel[3] = "دوشنبه";
        daysLabel[4] = "سه شنبه";
        daysLabel[5] = "چهار شنبه";
        daysLabel[6] = "پنج شنبه";
        daysLabel[7] = "جمعه";


        return  daysLabel;
    }


}

package com.gersonfaneto.yams.utils;

import java.util.concurrent.TimeUnit;

public abstract class TimeConverter {

  public static String convertToDuration(long startTime, long endTime) {
    long totalTime = endTime - startTime;

    if (totalTime < 0) {
      throw new IllegalArgumentException("Total time must be greater than zero!");
    }

    long totalDays = TimeUnit.MILLISECONDS.toDays(totalTime);
    totalTime -= TimeUnit.DAYS.toMillis(totalDays);

    long totalHours = TimeUnit.MILLISECONDS.toHours(totalTime);
    totalTime -= TimeUnit.HOURS.toMillis(totalHours);

    long totalMinutes = TimeUnit.MILLISECONDS.toMinutes(totalTime);
    totalTime -= TimeUnit.MINUTES.toMillis(totalMinutes);

    long totalSeconds = TimeUnit.MILLISECONDS.toSeconds(totalTime);

    return String.format(
        "%d Days %d Hours %d Minutes %d Seconds",
        totalDays, totalHours, totalMinutes, totalSeconds);
  }
}

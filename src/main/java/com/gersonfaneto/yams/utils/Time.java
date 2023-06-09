package com.gersonfaneto.yams.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * <code>Time<code/> contains functions for dealing with: converting, formatting and operating with
 * time units.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 */
public abstract class Time {
  private static final SimpleDateFormat dateMask = new SimpleDateFormat("dd/MM/yyyy");

  /**
   * Converts a time duration in milliseconds to a more human-readable format as a <code>String
   * </code>.
   *
   * @param startTime The starting time in milliseconds.
   * @param endTime   The ending time in milliseconds.
   * @return A properly formatted <code>String</code>.
   * @throws IllegalArgumentException If the total time obtained by subtracting <code>startTime
   *                                  </code> and <code>endTime</code> is lower than 0.
   */
  public static String durationToString(long startTime, long endTime) {
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
        totalDays, totalHours, totalMinutes, totalSeconds
    );
  }

  public static String extractDateFromCalendar(Calendar calendarTime) {
    return dateMask.format(calendarTime.getTime());
  }
}

/*
 * Copyright (c) 2024 nullvoxel@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vanillage.raytraceantixray.util;

import java.util.concurrent.TimeUnit;

/**
 * Simple time formatter from BukkitUtils.
 * @author TauCubed
 */
public class TimeFormatter {

    public static final TimeFormatter STANDARD = new TimeFormatter(", ", "d", "h", "m", "s", "ms", "\u03bcs", "ns");
    public static final TimeFormatter STANDARD_SPACED = new TimeFormatter(", ", " d", " h", " m", " s", " ms", " \u03bcs", " ns");
    public static final TimeFormatter ASCII = new TimeFormatter(", ", "d", "h", "m", "s", "ms", "us", "ns");
    public static final TimeFormatter ASCII_SPACED = new TimeFormatter(", ", " d", " h", " m", " s", " ms", " us", " ns");

    final String separator;
    final String days, hours, minutes, seconds, milliseconds, microseconds, nanoseconds;

    public TimeFormatter(String separator, String days, String hours, String minutes, String seconds, String milliseconds, String microseconds, String nanoseconds) {
        this.separator = separator;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        this.microseconds = microseconds;
        this.nanoseconds = nanoseconds;
    }

    public String format(TimeUnit unit, long time, TimeUnit greater, TimeUnit lesser) {
        return formatNanos(unit.toNanos(time), greater, lesser);
    }

    public String formatNanos(long nanos, TimeUnit greater, TimeUnit lesser) {
        StringBuilder sb = new StringBuilder();
        TimeSplitter.splitNanos(nanos, greater, lesser, (tu, t) -> {
            if (!sb.isEmpty()) {
                sb.append(separator);
            }

            sb.append(t);
            sb.append(switch (tu) {
                case DAYS -> days;
                case HOURS -> hours;
                case MINUTES -> minutes;
                case SECONDS -> seconds;
                case MILLISECONDS -> milliseconds;
                case MICROSECONDS -> microseconds;
                case NANOSECONDS -> nanoseconds;
            });
        });

        return sb.toString();
    }

}

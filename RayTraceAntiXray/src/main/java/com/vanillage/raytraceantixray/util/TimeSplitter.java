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
import java.util.function.BiConsumer;

/**
 * Simple time splitter utility for use in TimeFormatter.<br>
 * From BukkitUtils
 * @author TauCubed
 */
public class TimeSplitter {

    public static void splitNanos(long nanos, TimeUnit greater, TimeUnit lesser, BiConsumer<TimeUnit, Long> consumer) {
        int greaterId = toUnitId(greater);
        int lesserId = toUnitId(lesser);

        // days
        if (greaterId > 5) {
            if (lesserId <= 6 && (nanos / 86400000000000L > 0L || lesserId == 6)) {
                consumer.accept(TimeUnit.DAYS, nanos / 86400000000000L);
            }

            nanos %= 86400000000000L;
        }

        // hours
        if (greaterId > 4) {
            if (lesserId <= 5 && (nanos / 3600000000000L > 0L || lesserId == 5)) {
                consumer.accept(TimeUnit.HOURS, nanos / 3600000000000L);
            }

            nanos %= 3600000000000L;
        }

        // minutes
        if (greaterId > 3) {
            if (lesserId <= 4 && (nanos / 60000000000L > 0L || lesserId == 4)) {
                consumer.accept(TimeUnit.MINUTES, nanos / 60000000000L);
            }

            nanos %= 60000000000L;
        }

        // seconds
        if (greaterId > 2) {
            if (lesserId <= 3 && (nanos / 1000000000L > 0L || lesserId == 3)) {
                consumer.accept(TimeUnit.SECONDS, nanos / 1000000000L);
            }

            nanos %= 1000000000L;
        }

        // milliseconds
        if (greaterId > 1) {
            if (lesserId <= 2 && (nanos / 1000000L > 0L || lesserId == 2)) {
                consumer.accept(TimeUnit.MILLISECONDS, nanos / 1000000L);
            }

            nanos %= 1000000L;
        }

        // microseconds
        if (greaterId > 0) {
            if (lesserId <= 1 && (nanos / 1000L > 0L || lesserId == 1)) {
                consumer.accept(TimeUnit.MICROSECONDS, nanos / 1000L);
            }

            nanos %= 1000L;
        }

        // nanoseconds
        if (lesserId == 0) {
            consumer.accept(TimeUnit.NANOSECONDS, nanos);
        }
    }

    private static int toUnitId(TimeUnit src) {
        return switch (src) {
            case NANOSECONDS -> 0;
            case MICROSECONDS -> 1;
            case MILLISECONDS -> 2;
            case SECONDS -> 3;
            case MINUTES -> 4;
            case HOURS -> 5;
            case DAYS -> 6;
        };
    }

}

// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

import java.time.*;

public class DurationSecondsFormatter implements DoubleFormatter {
    private static Duration convert(double seconds) { return Duration.ofNanos((long)(1_000_000_000 * seconds)); }
    @Override public String plain(double value) {
        if (Math.abs(value) >= 0.000_001)
            return new DurationFormatter().plain(convert(value));
        return new CustomUnitFormatter("s").plain(value);
    }
    @Override public String detail(double value) {
        if (Math.abs(value) >= 0.000_001)
            return new DurationFormatter().detail(convert(value));
        return new CustomUnitFormatter("s").detail(value);
    }
}

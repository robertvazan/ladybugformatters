// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

/*
 * A gallery of default formatters. Apps can define their own formatter gallery.
 */
public class Pretty {
    public static DecimalFormatter number() { return new DecimalFormatter(); }
    public static ScientificFormatter scientific() { return new ScientificFormatter(); }
    public static PercentsFormatter percents() { return new PercentsFormatter(); }
    public static BytesFormatter bytes() { return new BytesFormatter(); }
    public static PixelsFormatter pixels() { return new PixelsFormatter(); }
    public static DurationFormatter duration() { return new DurationFormatter(); }
    public static TimeFormatter time() { return new TimeFormatter(); }
    public static AgeFormatter age() { return new AgeFormatter(); }
    public static UnitFormatter unit(String unit) { return new CustomUnitFormatter(unit); }
    public static ObjectFormatter object() { return new ObjectFormatter(); }
}

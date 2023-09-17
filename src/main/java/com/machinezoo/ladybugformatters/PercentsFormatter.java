// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

public class PercentsFormatter implements DoubleFormatter {
    private final int precision;
    public PercentsFormatter(int precision) { this.precision = precision; }
    public PercentsFormatter() { this(3); }
    public PremultipliedPercentsFormatter premultiplied() { return new PremultipliedPercentsFormatter(precision); }
    @Override public String plain(double value) { return premultiplied().plain(100 * value); }
    @Override public String detail(double value) { return premultiplied().detail(100 * value); }
}

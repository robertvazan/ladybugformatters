// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

public class PercentsFormatter implements DoubleFormatter {
    private int precision = 3;
    public PercentsFormatter precision(int precision) {
        this.precision = precision;
        return this;
    }
    public PremultipliedPercentsFormatter premultiplied() { return new PremultipliedPercentsFormatter()
        .precision(precision); }
    @Override public String plain(double value) { return premultiplied().plain(100 * value); }
    @Override public String detail(double value) { return premultiplied().detail(100 * value); }
}

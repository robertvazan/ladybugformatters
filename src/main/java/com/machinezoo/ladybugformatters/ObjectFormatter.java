// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

import java.time.*;
import java.util.*;
import com.machinezoo.hookless.time.*;
import com.machinezoo.pushmode.dom.*;
import com.machinezoo.stagean.*;

@ApiIssue("This should be extensible via service providers.")
public class ObjectFormatter implements ObjectTakingFormatter {
    private static ObjectTakingFormatter wrap(DoubleFormatter typed) {
        return new ObjectTakingFormatter() {
            @Override public String plain(Object value) { return typed.plain(((Number)value).doubleValue()); }
            @Override public String detail(Object value) { return typed.detail(((Number)value).doubleValue()); }
            @Override public DomContent format(Object value) { return typed.format(((Number)value).doubleValue()); }
        };
    }
    private static ObjectTakingFormatter wrap(InstantFormatter typed) {
        return new ObjectTakingFormatter() {
            @Override public String plain(Object value) { return typed.plain((Instant)value); }
            @Override public String detail(Object value) { return typed.detail((Instant)value); }
            @Override public DomContent format(Object value) { return typed.format((Instant)value); }
        };
    }
    private static ObjectTakingFormatter wrap(DurationTakingFormatter typed) {
        return new ObjectTakingFormatter() {
            @Override public String plain(Object value) { return typed.plain((Duration)value); }
            @Override public String detail(Object value) { return typed.detail((Duration)value); }
            @Override public DomContent format(Object value) { return typed.format((Duration)value); }
        };
    }
    private static ObjectTakingFormatter wrap(ReactiveDurationFormatter typed) {
        return new ObjectTakingFormatter() {
            @Override public String plain(Object value) { return typed.plain((ReactiveDuration)value); }
            @Override public String detail(Object value) { return typed.detail((ReactiveDuration)value); }
            @Override public DomContent format(Object value) { return typed.format((ReactiveDuration)value); }
        };
    }
    private static ObjectTakingFormatter empty() {
        return new ObjectTakingFormatter() {
            @Override public String plain(Object value) { return "-"; }
            @Override public String detail(Object value) { return "-"; }
        };
    }
    private static ObjectTakingFormatter present(ObjectTakingFormatter inner) {
        return new ObjectTakingFormatter() {
            @Override public String plain(Object value) { return inner.plain(((Optional<?>)value).get()); }
            @Override public String detail(Object value) { return inner.detail(((Optional<?>)value).get()); }
            @Override public DomContent format(Object value) { return inner.format(((Optional<?>)value).get()); }
        };
    }
    private static ObjectTakingFormatter presentInt(DoubleFormatter inner) {
        return new ObjectTakingFormatter() {
            @Override public String plain(Object value) { return inner.plain(((OptionalInt)value).getAsInt()); }
            @Override public String detail(Object value) { return inner.detail(((OptionalInt)value).getAsInt()); }
            @Override public DomContent format(Object value) { return inner.format(((OptionalInt)value).getAsInt()); }
        };
    }
    private static ObjectTakingFormatter presentLong(DoubleFormatter inner) {
        return new ObjectTakingFormatter() {
            @Override public String plain(Object value) { return inner.plain(((OptionalLong)value).getAsLong()); }
            @Override public String detail(Object value) { return inner.detail(((OptionalLong)value).getAsLong()); }
            @Override public DomContent format(Object value) { return inner.format(((OptionalLong)value).getAsLong()); }
        };
    }
    private static ObjectTakingFormatter presentDouble(DoubleFormatter inner) {
        return new ObjectTakingFormatter() {
            @Override public String plain(Object value) { return inner.plain((OptionalDouble)value); }
            @Override public String detail(Object value) { return inner.detail((OptionalDouble)value); }
            @Override public DomContent format(Object value) { return inner.format((OptionalDouble)value); }
        };
    }
    private static ObjectTakingFormatter stringifier() {
        return new ObjectTakingFormatter() {
            @Override public String plain(Object value) { return value.toString(); }
            @Override public String detail(Object value) { return value.toString(); }
        };
    }
    private static ObjectTakingFormatter transparent() {
        return new ObjectTakingFormatter() {
            @Override public String plain(Object value) { return ((DomContent)value).text(); }
            @Override public String detail(Object value) { return ((DomContent)value).text(); }
            @Override public DomContent format(Object value) { return (DomContent)value; }
        };
    }
    private static ObjectTakingFormatter resolve(Object value) {
        if (value == null)
            return empty();
        if (value instanceof DomContent)
            return transparent();
        if (value instanceof Number)
            return wrap(new DecimalFormatter());
        /*
         * This is a bit controversial. We are assuming most apps want age rather than the exact time.
         * Exact time can still be found in the tooltip.
         */
        if (value instanceof Instant)
            return wrap(new AgeFormatter());
        if (value instanceof Duration)
            return wrap((DurationTakingFormatter)new DurationFormatter());
        if (value instanceof ReactiveDuration)
            return wrap((ReactiveDurationFormatter)new DurationFormatter());
        if (value instanceof Optional<?> optional)
            return optional.isPresent() ? present(resolve(optional.get())) : empty();
        if (value instanceof OptionalInt optional)
            return optional.isPresent() ? presentInt(new DecimalFormatter()) : empty();
        if (value instanceof OptionalLong optional)
            return optional.isPresent() ? presentLong(new DecimalFormatter()) : empty();
        if (value instanceof OptionalDouble optional)
            return optional.isPresent() ? presentDouble(new DecimalFormatter()) : empty();
        return stringifier();
    }
    @Override public String plain(Object value) { return resolve(value).plain(value); }
    @Override public String detail(Object value) { return resolve(value).detail(value); }
    @Override public DomContent format(Object value) { return resolve(value).format(value); }
}

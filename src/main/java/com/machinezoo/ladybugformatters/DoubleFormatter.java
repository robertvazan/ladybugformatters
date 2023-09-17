// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

import java.util.*;
import com.machinezoo.pushmode.dom.*;

/**
 * Formatter taking double as input.
 */
public interface DoubleFormatter {
    /**
     * Formats a value into a plain string representation.
     * This is a rounded format that will be presented to the user by default.
     *
     * @param value
     *            value to format
     * @return rounded formatting of the value
     */
    String plain(double value);
    /**
     * Formats a value into a detailed string representation.
     * This is an exact format that will be shown upon request, usually in a hover tooltip.
     *
     * @param value
     *            value to format
     * @return exact formatting of the value
     */
    String detail(double value);
    /**
     * Formats a value into an HTML fragment.
     * Exact value will be shown as a hover tooltip.
     *
     * @param value
     *            value to format
     * @return HTML fragment with formatted value
     */
    default DomContent format(double value) {
        var plain = plain(value);
        var detail = detail(value);
        if (Objects.equals(plain, detail))
            return new DomText(plain);
        return Html.abbr()
            .title(detail(value))
            .add(plain(value));
    }
    /**
     * Formats a nullable value into a plain string representation.
     * If the value is null, a dash ("-") is returned.
     *
     * @param value
     *            value to format
     * @return rounded formatting of the value or "-" if the value is null
     */
    default String plain(Double value) { return value != null ? plain((double)value) : "-"; }
    /**
     * Formats a nullable value into a detailed string representation.
     * If the value is null, a dash ("-") is returned.
     *
     * @param value
     *            value to format
     * @return exact formatting of the value or "-" if the value is null
     */
    default String detail(Double value) { return value != null ? detail((double)value) : "-"; }
    /**
     * Formats a nullable value into an HTML fragment.
     * If the value is null, a dash ("-") is returned.
     *
     * @param value
     *            value to format
     * @return HTML fragment with formatted value or "-" if the value is null
     */
    default DomContent format(Double value) { return value != null ? format((double)value) : new DomText("-"); }
    /**
     * Formats an optional value into a plain string representation.
     * If the value is empty, a dash ("-") is returned.
     *
     * @param value
     *            value to format
     * @return rounded formatting of the value or "-" if the value is empty
     */
    default String plain(OptionalDouble value) { return value.isPresent() ? plain(value.getAsDouble()) : "-"; }
    /**
     * Formats an optional value into a detailed string representation.
     * If the value is empty, a dash ("-") is returned.
     *
     * @param value
     *            value to format
     * @return exact formatting of the value or "-" if the value is empty
     */
    default String detail(OptionalDouble value) { return value.isPresent() ? detail(value.getAsDouble()) : "-"; }
    /**
     * Formats an optional value into an HTML fragment.
     * If the value is empty, a dash ("-") is returned.
     *
     * @param value
     *            value to format
     * @return HTML fragment with formatted value or "-" if the value is empty
     */
    default DomContent format(OptionalDouble value) { return value.isPresent() ? format(value.getAsDouble()) : new DomText("-"); }
}

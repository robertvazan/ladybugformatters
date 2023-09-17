// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

import java.util.*;
import com.machinezoo.pushmode.dom.*;

public interface DoubleFormatter {
    String plain(double value);
    String detail(double value);
    default DomContent format(double value) {
        var plain = plain(value);
        var detail = detail(value);
        if (Objects.equals(plain, detail))
            return new DomText(plain);
        return Html.abbr()
            .title(detail(value))
            .add(plain(value));
    }
    default String plain(Double value) { return value != null ? plain((double)value) : "-"; }
    default String detail(Double value) { return value != null ? detail((double)value) : "-"; }
    default DomContent format(Double value) { return value != null ? format((double)value) : new DomText("-"); }
    default String plain(OptionalDouble value) { return value.isPresent() ? plain(value.getAsDouble()) : "-"; }
    default String detail(OptionalDouble value) { return value.isPresent() ? detail(value.getAsDouble()) : "-"; }
    default DomContent format(OptionalDouble value) { return value.isPresent() ? format(value.getAsDouble()) : new DomText("-"); }
}

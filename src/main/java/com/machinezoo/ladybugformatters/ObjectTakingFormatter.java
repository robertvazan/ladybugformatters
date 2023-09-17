// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

import java.util.*;
import com.machinezoo.pushmode.dom.*;

public interface ObjectTakingFormatter {
    String plain(Object value);
    String detail(Object value);
    default DomContent format(Object value) {
        var plain = plain(value);
        var detail = detail(value);
        if (Objects.equals(plain, detail))
            return new DomText(plain);
        return Html.abbr()
            .title(detail(value))
            .add(plain(value));
    }
}

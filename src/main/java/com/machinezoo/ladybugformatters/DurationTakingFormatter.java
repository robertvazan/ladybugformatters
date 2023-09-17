// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

import java.time.*;
import java.util.*;
import com.machinezoo.pushmode.dom.*;

public interface DurationTakingFormatter {
    String plain(Duration value);
    String detail(Duration value);
    default DomContent format(Duration value) {
        var plain = plain(value);
        var detail = detail(value);
        if (Objects.equals(plain, detail))
            return new DomText(plain);
        return Html.time()
            .datetime(value.toString())
            .title(detail)
            .add(plain);
    }
}

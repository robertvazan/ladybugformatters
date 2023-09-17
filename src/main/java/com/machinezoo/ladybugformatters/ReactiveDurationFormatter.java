// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

import java.util.*;
import com.machinezoo.hookless.time.*;
import com.machinezoo.pushmode.dom.*;

public interface ReactiveDurationFormatter {
    String plain(ReactiveDuration value);
    String detail(ReactiveDuration value);
    default DomContent format(ReactiveDuration value) {
        var plain = plain(value);
        var detail = detail(value);
        if (Objects.equals(plain, detail))
            return new DomText(plain);
        return Html.span()
            .title(detail)
            .add(plain);
    }
}

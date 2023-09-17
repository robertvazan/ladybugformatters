// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

import java.time.*;
import java.util.*;
import com.machinezoo.hookless.time.*;
import com.machinezoo.pushmode.dom.*;

public class ObjectFormatter implements ObjectTakingFormatter {
	private static ObjectTakingFormatter from(DoubleFormatter typed) {
		return new ObjectTakingFormatter() {
			@Override
			public String plain(Object value) {
				return typed.plain(((Number)value).doubleValue());
			}
			@Override
			public String detail(Object value) {
				return typed.detail(((Number)value).doubleValue());
			}
			@Override
			public DomContent format(Object value) {
				return typed.format(((Number)value).doubleValue());
			}
		};
	}
	private static ObjectTakingFormatter from(InstantFormatter typed) {
		return new ObjectTakingFormatter() {
			@Override
			public String plain(Object value) {
				return typed.plain((Instant)value);
			}
			@Override
			public String detail(Object value) {
				return typed.detail((Instant)value);
			}
			@Override
			public DomContent format(Object value) {
				return typed.format((Instant)value);
			}
		};
	}
	private static ObjectTakingFormatter from(DurationTakingFormatter typed) {
		return new ObjectTakingFormatter() {
			@Override
			public String plain(Object value) {
				return typed.plain((Duration)value);
			}
			@Override
			public String detail(Object value) {
				return typed.detail((Duration)value);
			}
			@Override
			public DomContent format(Object value) {
				return typed.format((Duration)value);
			}
		};
	}
	private static ObjectTakingFormatter from(ReactiveDurationFormatter typed) {
		return new ObjectTakingFormatter() {
			@Override
			public String plain(Object value) {
				return typed.plain((ReactiveDuration)value);
			}
			@Override
			public String detail(Object value) {
				return typed.detail((ReactiveDuration)value);
			}
			@Override
			public DomContent format(Object value) {
				return typed.format((ReactiveDuration)value);
			}
		};
	}
	private static ObjectTakingFormatter nil() {
		return new ObjectTakingFormatter() {
			@Override
			public String plain(Object value) {
				return null;
			}
			@Override
			public String detail(Object value) {
				return null;
			}
			@Override
			public DomContent format(Object value) {
				return null;
			}
		};
	}
	private static ObjectTakingFormatter optional(ObjectTakingFormatter inner) {
		return new ObjectTakingFormatter() {
			@Override
			public String plain(Object value) {
				return inner.plain(((Optional<?>)value).get());
			}
			@Override
			public String detail(Object value) {
				return inner.detail(((Optional<?>)value).get());
			}
			@Override
			public DomContent format(Object value) {
				return inner.format(((Optional<?>)value).get());
			}
		};
	}
	private static ObjectTakingFormatter optionalInt(DoubleFormatter inner) {
		return new ObjectTakingFormatter() {
			@Override
			public String plain(Object value) {
				return inner.plain(((OptionalInt)value).getAsInt());
			}
			@Override
			public String detail(Object value) {
				return inner.detail(((OptionalInt)value).getAsInt());
			}
			@Override
			public DomContent format(Object value) {
				return inner.format(((OptionalInt)value).getAsInt());
			}
		};
	}
	private static ObjectTakingFormatter optionalLong(DoubleFormatter inner) {
		return new ObjectTakingFormatter() {
			@Override
			public String plain(Object value) {
				return inner.plain(((OptionalLong)value).getAsLong());
			}
			@Override
			public String detail(Object value) {
				return inner.detail(((OptionalLong)value).getAsLong());
			}
			@Override
			public DomContent format(Object value) {
				return inner.format(((OptionalLong)value).getAsLong());
			}
		};
	}
	private static ObjectTakingFormatter optionalDouble(DoubleFormatter inner) {
		return new ObjectTakingFormatter() {
			@Override
			public String plain(Object value) {
				return inner.plain(((OptionalDouble)value).getAsDouble());
			}
			@Override
			public String detail(Object value) {
				return inner.detail(((OptionalDouble)value).getAsDouble());
			}
			@Override
			public DomContent format(Object value) {
				return inner.format(((OptionalDouble)value).getAsDouble());
			}
		};
	}
	private static ObjectTakingFormatter stringifier() {
		return new ObjectTakingFormatter() {
			@Override
			public String plain(Object value) {
				return value.toString();
			}
			@Override
			public String detail(Object value) {
				return null;
			}
		};
	}
	private static ObjectTakingFormatter identify(Object value) {
		if (value instanceof Number)
			return from(new DecimalFormatter());
		if (value instanceof Instant)
			return from(new AgeFormatter());
		if (value instanceof Duration)
			return from((DurationTakingFormatter)new DurationFormatter());
		if (value instanceof ReactiveDuration)
			return from((ReactiveDurationFormatter)new DurationFormatter());
		if (value instanceof Optional) {
			var optional = (Optional<?>)value;
			return optional.isPresent() ? optional(identify(optional.get())) : nil();
		}
		if (value instanceof OptionalInt)
			return optionalInt(new DecimalFormatter());
		if (value instanceof OptionalLong)
			return optionalLong(new DecimalFormatter());
		if (value instanceof OptionalDouble)
			return optionalDouble(new DecimalFormatter());
		return stringifier();
	}
	@Override
	public String plain(Object value) {
		if (value == null)
			return null;
		return identify(value).plain(value);
	}
	@Override
	public String detail(Object value) {
		if (value == null)
			return null;
		return identify(value).detail(value);
	}
	@Override
	public DomContent format(Object value) {
		if (value == null)
			return null;
		if (value instanceof DomContent)
			return (DomContent)value;
		return identify(value).format(value);
	}
}

// Part of Ladybug Formatters: https://ladybugformatters.machinezoo.com/
package com.machinezoo.ladybugformatters;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class DecimalFormatterTest {
    private final DecimalFormatter f = new DecimalFormatter();
    private final ScientificFormatter sf = new ScientificFormatter();
    @Test public void plain() {
        assertEquals("0", f.plain(0));
        assertEquals("1.23", f.plain(1.2345));
        assertEquals("12.3", f.plain(12.345));
        assertEquals("123", f.plain(123.456));
        assertEquals("1.23 K", f.plain(1_234.567));
        assertEquals("12.3 K", f.plain(12_345.678));
        assertEquals("123 K", f.plain(123_456.789));
        assertEquals("1.23 M", f.plain(1_234_567.891));
        assertEquals("12.3 M", f.plain(12_345_678.912));
        assertEquals("123 M", f.plain(123_456_789.012));
        assertEquals("1.23 G", f.plain(1_234_567_890.123));
        assertEquals("12.3 G", f.plain(12_345_678_900.123));
        assertEquals("123 G", f.plain(123_456_789_012.34));
        assertEquals(sf.plain(1.23456789012e12), f.plain(1.23456789012e12));
        assertEquals("-1.23", f.plain(-1.2345));
        assertEquals("-1.23 K", f.plain(-1_234.567));
        assertEquals("-1.23 M", f.plain(-1_234_567.891));
        assertEquals("-1.23 G", f.plain(-1_234_567_890.123));
        assertEquals("0.123", f.plain(0.12345));
        assertEquals("0.0123", f.plain(0.012345));
        assertEquals("0.00123", f.plain(0.0012345));
        assertEquals(sf.plain(0.000012345), f.plain(0.000012345));
    }
    @Test public void detail() {
        assertEquals("0", f.detail(0));
        assertEquals("1.2345", f.detail(1.2345));
        assertEquals("12.3456", f.detail(12.3456));
        assertEquals("123.4567", f.detail(123.4567));
        assertEquals("1.234567 K", f.detail(1_234.567));
        assertEquals("12.345678 K", f.detail(12_345.678));
        assertEquals("123.456789 K", f.detail(123_456.789));
        assertEquals("1.23456789 M", f.detail(1_234_567.89));
        assertEquals("12.3456789 M", f.detail(12_345_678.9));
        assertEquals("123.456789 M", f.detail(123_456_789));
        assertEquals(1_234_567_890.12345 * 0.000_000_001 + " G", f.detail(1_234_567_890.12345));
        assertEquals(12_345_678_901.23456 * 0.000_000_001 + " G", f.detail(12_345_678_901.23456));
        assertEquals(123_456_789_012.34567 * 0.000_000_001 + " G", f.detail(123_456_789_012.34567));
        assertEquals(sf.plain(1.23456789012345e12), f.detail(1.23456789012345e12));
        assertEquals("-1.2345", f.detail(-1.2345));
        assertEquals("-1.234567 K", f.detail(-1_234.567));
        assertEquals("-1.23456789 M", f.detail(-1_234_567.89));
        assertEquals(-1_234_567_890.12345 * 0.000_000_001 + " G", f.detail(-1_234_567_890.12345));
        assertEquals("0.12345", f.detail(0.12345));
        assertEquals("0.0123456", f.detail(0.0123456));
        assertEquals("0.00123456", f.detail(0.00123456));
        assertEquals("0.000123456", f.detail(0.000123456));
        assertEquals(sf.plain(0.0000123456), f.detail(0.0000123456));
    }
}

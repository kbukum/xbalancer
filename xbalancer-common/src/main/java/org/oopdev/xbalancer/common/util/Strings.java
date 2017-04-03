package org.oopdev.xbalancer.common.util;

/**
 * Provides frequently needed methods for string operations.
 */
public final class Strings {

    private Strings() {
    }

    public static boolean has(String value) {
        return value != null && !value.trim().equals("");
    }
}

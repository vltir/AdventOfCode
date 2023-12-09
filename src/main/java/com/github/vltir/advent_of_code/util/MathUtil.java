package com.github.vltir.advent_of_code.util;

public class MathUtil {
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b; // Verwendung von GCD für die LCM-Berechnung
    }

    public static long lcm(long[] numbers) {
        long result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = lcm(result, numbers[i]);
        }
        return result;
    }
}

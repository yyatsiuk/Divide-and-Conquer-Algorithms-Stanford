package com.yatsiuk.divide_and_conquer;

public class IntegerMultiplication {

    public static void main(String[] args) {
        long result = performeKaratsuba(567861, 123431);
        System.out.println(result);
    }
    
    private static long performeKaratsuba(long x, long y) {
        if (x < 10 || y < 10) {
            return x * y;
        }

        int maxBase = (int) Math.max(Math.log10(x), Math.log10(y)) + 1;
        int halfMax = Math.round(maxBase >> 1);

        long a = x / (int) Math.pow(10, halfMax);
        long b = x % (int) Math.pow(10, halfMax);
        long c = y / (int) Math.pow(10, halfMax);
        long d = y % (int) Math.pow(10, halfMax);


        long z1 = performeKaratsuba(a, c);
        long z2 = performeKaratsuba(b, d);
        long z3 = performeKaratsuba((a + b), (c + d));

        return (long) ((z1 * Math.pow(10, (2 * halfMax))) + ((z3 - z2 - z1) * Math.pow(10, halfMax)) + z2);
    }
}

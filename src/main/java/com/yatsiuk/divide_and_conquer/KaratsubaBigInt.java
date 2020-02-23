package com.yatsiuk.divide_and_conquer;

import java.math.BigInteger;


public class KaratsubaBigInt {
    public static BigInteger karatsubaMultiply(BigInteger x, BigInteger y) {
        BigInteger a;
        BigInteger b;
        BigInteger c;
        BigInteger d;

        String stringX = String.valueOf(x);
        String stringY = String.valueOf(y);
        int lengthX = stringX.length();
        int lengthY = stringY.length();
        int maxLength = Math.max(lengthX, lengthY);

        if (maxLength == 1) {
            return x.multiply(y);
        }

        if (lengthX > lengthY) {
            String formatter = "%0";
            formatter += String.valueOf(lengthX);
            formatter += "d";
            stringY = String.format(formatter, new BigInteger(stringY));
            lengthY = stringY.length();
        }

        if (lengthY > lengthX) {
            String formatter = "%0";
            formatter += String.valueOf(lengthY);
            formatter += "d";
            stringX = String.format(formatter, new BigInteger(stringX));
            lengthX = stringX.length();
        }

        a = new BigInteger(stringX.substring(0, maxLength / 2));
        b = new BigInteger(stringX.substring(maxLength / 2));
        c = new BigInteger(stringY.substring(0, maxLength / 2));
        d = new BigInteger(stringY.substring(maxLength / 2));

        BigInteger ac = karatsubaMultiply(a, c);
        BigInteger bd = karatsubaMultiply(b, d);
        BigInteger abcd = karatsubaMultiply((a.add(b)), (c.add(d)));
        BigInteger gauss = abcd.subtract(bd).subtract(ac);

        BigInteger sum1;
        BigInteger sum2;

        if (stringX.length() % 2 == 0) {
            sum1 = ac.multiply(BigInteger.valueOf(10).pow(maxLength));
            sum2 = gauss.multiply(BigInteger.valueOf(10).pow(maxLength / 2));
        } else {
            sum1 = ac.multiply(BigInteger.valueOf(10).pow(lengthX + 1));
            sum2 = gauss.multiply(BigInteger.valueOf(10).pow(lengthX / 2 + 1));
        }

        return sum1.add(sum2).add(bd);
    }

    public static void testKaratsuba(BigInteger x, BigInteger y) {
        System.out.println(karatsubaMultiply(x, y));
    }

    public static void main(String[] args) {
        testKaratsuba(
                new BigInteger("3141592653589793238462643383279502884197169399375105820974944592"),
                new BigInteger("2718281828459045235360287471352662497757247093699959574966967627")
        );
    }
}


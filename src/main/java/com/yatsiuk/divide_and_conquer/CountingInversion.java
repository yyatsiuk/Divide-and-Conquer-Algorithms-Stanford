package com.yatsiuk.divide_and_conquer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Arrays;

public class CountingInversion {
    public static void main(String[] args) throws IOException {
        int[] ints = Files.readAllLines(FileSystems.getDefault().getPath("IntegerArray.txt"))
                .stream()
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(sortAndCountInversion(ints));
    }

    private static InverseObj sortAndCountInversion(int[] arr) {
        if (arr.length == 1) {
            return new InverseObj(0, arr);
        }

        int[] first = new int[arr.length / 2];
        int[] second = new int[arr.length - first.length];
        System.arraycopy(arr, 0, first, 0, first.length);
        System.arraycopy(arr, first.length, second, 0, second.length);

        InverseObj inversion1 = sortAndCountInversion(first);
        InverseObj inversion2 = sortAndCountInversion(second);
        InverseObj combineInv1andInv2 = mergeAndCountSplitInv(inversion1.getSortedArr(), inversion2.getSortedArr());

        long inverse = inversion1.getCountOfInv() + inversion2.getCountOfInv() + combineInv1andInv2.getCountOfInv();

        return new InverseObj(inverse, combineInv1andInv2.getSortedArr());
    }


    private static InverseObj mergeAndCountSplitInv(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;
        long count = 0;
        int[] result = new int[arr1.length + arr2.length];
        long mid = (result.length / 2) - 1L;

        for (int index = 0; index < result.length; index++) {
            if (i == arr1.length) {
                result[index] = arr2[j++];
            } else if (j == arr2.length) {
                result[index] = arr1[i++];
            } else if (arr1[i] <= arr2[j]) {
                result[index] = arr1[i++];
            } else {
                result[index] = arr2[j++];
                count += (mid - i + 1);
            }
        }

        return new InverseObj(count, result);
    }


    private static class InverseObj {
        private long countOfInv;
        private int[] sortedArr;

        public InverseObj(long countOfInv, int[] sortedArr) {
            this.countOfInv = countOfInv;
            this.sortedArr = sortedArr;
        }

        public int[] getSortedArr() {
            return sortedArr;
        }

        public long getCountOfInv() {
            return countOfInv;
        }

        @Override
        public String toString() {
            return "Inverse count=" + countOfInv + "\nSorted array=" + Arrays.toString(sortedArr);
        }
    }
}

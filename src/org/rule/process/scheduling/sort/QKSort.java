package org.rule.process.scheduling.sort;

/**
 * 快速排序
 * Created by Naga on 2015/11/20.
 * Blog : http://blog.csdn.net/lemon_tree12138
 */
public class QKSort {

    public static long[] quickSort(long[] array) {
        if (array != null) {
            return quickSort(array, 0, array.length - 1);
        }

        return null;
    }

    private static long[] quickSort(long[] array, int beg, int end) {
        if (beg >= end || array == null) {
            return null;
        }

        int p = partition(array, beg, end);

        quickSort(array, beg, p - 1);
        quickSort(array, p + 1, end);

        return array;
    }

    /**
     * 找到分界点
     *
     * @param array
     * @param beg
     * @param end
     * @return
     */
    private static int partition(long[] array, int beg, int end) {
        long last = array[end];
        int i = beg - 1;

        for (int j = beg; j <= end - 1; j++) {
            if (array[j] <= last) {
                i++;
                if (i != j) {
                    array = getSwap(array, i, j);
                }
            }
        }

        if ((i + 1) != end) {
            array = getSwap(array, i + 1, end);
        }

        return i + 1;
    }

    /**
     * 交换array数组中的第i个元素和第j个元素
     *
     * @param a
     * @param i
     * @param j
     * @return
     */
    private static long[] getSwap(long[] a, int i, int j) {
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];

        return a;
    }
}
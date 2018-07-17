package sort;

import java.util.Arrays;

/**
 * @author ahscuml
 * @date 2018/7/15
 * @time 15:51
 */
public class InversionCount {
    private InversionCount() {
    }

    public static long sort(Comparable[] arr) {
        int n = arr.length;
        return sort(arr, 0, n - 1);
    }

    /**
     * 递归排序(用作分割)
     */
    public static long sort(Comparable[] arr, int l, int r) {
        if (l >= r) {
            return 0L;
        }
        int mid = l + (r - l) / 2;
        // 求出 arr[l...mid] 范围的逆序数
        long countl = sort(arr, l, mid);
        // 求出 arr[mid+1...r] 范围的逆序数
        long countr = sort(arr, mid + 1, r);
        // 两个count分别是上一个merge的结果，这就相当于将所有的merge结果相加
        return countl + countr + merge(arr, l, mid, r);
    }

    /**
     * 合并操作,就是一个排序的过程
     */
    private static long merge(Comparable[] arr, int l, int mid, int r) {
        // 逆序数对个数初始化
        long count = 0L;
        // merge操作
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {  // 如果左半部分元素已经全部处理完毕
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {  // 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i - l];
                i++;
            } else if (aux[j - l].compareTo(aux[i - l]) >= 0) {  // 左半部分所指元素 <= 右半部分所指元素
                arr[k] = aux[i - l];
                i++;
            } else { // 右半部分所指元素 < 左半部分所指元素
                arr[k] = aux[j - l];
                j++;
                // 此时, 因为右半部分j所指的元素小
                // 这个元素和左半部分的所有未处理的元素都构成了逆序数对(不包括右边的是因为右边的在上一次迭代的时候已经计算过了)
                // 左半部分此时未处理的元素个数为 mid - i + 1(注意i的意思，i是将要处理的元素)
                count = count + (long) (mid - i + 1);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int N = 1000000;

        // 测试1: 测试随机数组
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        System.out.println("Test Inversion Count for Random Array, n = " + N + " :" + sort(arr));

        // 测试2: 测试完全有序的数组
        // 结果应该为0
        arr = SortTestHelper.generateOrderedArray(N);
        System.out.println("Test Inversion Count for Ordered Array, n = " + N + " :" + sort(arr));

        // 测试3: 测试完全逆序的数组
        // 结果应改为 N*(N-1)/2
        arr = SortTestHelper.generateInversedArray(N);
        System.out.println("Test Inversion Count for Inversed Array, n = " + N + " :" + sort(arr));
    }
}

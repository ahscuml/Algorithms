package sort;

import java.lang.reflect.Method;

/**
 * @author ahscuml
 * @date 2018/7/11
 * @time 19:30
 */
public class SortTestHelper {
    private SortTestHelper() {
    }

    /**
     * 生成有N个元素的随机数组
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer((int) (Math.random() * (rangeR - rangeL + 1) + rangeL));
        }
        return arr;
    }

    /**
     * 生成一个近乎有序的数组
     * 原理是生成一个有序数组，然后再随机进行交换
     */
    public static Integer[] generateNearlyOrderdArray(int n, int swapTimes) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer(i);
        }
        for (int i = 0; i < swapTimes; i++) {
            //定义了随机交换的位置
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }
        return arr;
    }

    /**
     * 判断数组是否有序
     */
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
     */
    public static void testSort(String sortClassName, Comparable[] arr) {
        //通过反射机制，通过排序的类名，运行排序函数
        try {
            //通过sortClassName获得排序函数的class对象
            Class sortClass = Class.forName(sortClassName);
            //通过排序对象的class对象获得排序方法
            Method sortMethod = sortClass.getMethod("sort", new Class[]{Comparable[].class});
            //排序参数只有一个，是可比较数组arr
            Object[] params = new Object[]{arr};
            long startTime = System.currentTimeMillis();
            //调用排序函数
            sortMethod.invoke(null, params);
            long endTime = System.currentTimeMillis();
            assert isSorted(arr);
            System.out.println(sortClass.getSimpleName() + " : " + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 生成一个完全有序的数组
    public static Integer[] generateOrderedArray(int n) {

        return generateNearlyOrderdArray(n, 0);
    }

    // 生成一个完全逆序的数组
    public static Integer[] generateInversedArray(int n) {

        Integer[] arr = generateOrderedArray(n);
        for (int i = n / 2 - 1; i >= 0; i--) {
            Integer t = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = t;
        }
        return arr;
    }
}


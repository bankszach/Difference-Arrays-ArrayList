package edu.zachbanks.daa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates key functional and performance differences
 * between Java arrays and {@link java.util.ArrayList}.
 *
 * <p>Highlights:</p>
 * <ul>
 *   <li>Arrays are fixed-length, support primitive storage,
 *       and offer constant-time indexed access.</li>
 *   <li>ArrayList is resizable, stores reference types
 *       (boxing primitives), and provides rich mutator APIs.</li>
 *   <li>A micro-benchmark illustrates insertion‐time contrast.</li>
 * </ul>
 *
 * <p>Compile:</p>
 * <pre>{@code
 * javac -d out src/edu/zachbanks/daa/DifferenceDemo.java
 * }</pre>
 *
 * <p>Run:</p>
 * <pre>{@code
 * java -cp out edu.zachbanks.daa.DifferenceDemo
 * }</pre>
 *
 * @author Zach
 */
public class DifferenceDemo {

    /**
     * Entry point.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        /* ---------- Static-size array ---------- */
        int[] arr = {1, 2, 3, 4, 5};        // length = 5, elements are primitives
        arr[2] = 99;                        // overwrite in place
        System.out.println("Array contents     : "
                           + Arrays.toString(arr));

        /* ---------- Resizable ArrayList ---------- */
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5));
        list.add(6);                        // auto-expands if capacity reached
        list.set(2, 99);                    // replace element at index 2
        System.out.println("ArrayList contents : " + list);

        /* ---------- Micro-benchmark (insertion) ---------- */
        final int N = 100_000;

        long tStartArr = System.nanoTime();
        for (int i = 0; i < N; i++) {
            arr[i % arr.length] = i;        // no reallocation
        }
        long tEndArr = System.nanoTime();

        List<Integer> bench = new ArrayList<>();
        long tStartList = System.nanoTime();
        for (int i = 0; i < N; i++) {
            bench.add(i);                   // may reallocate internally
        }
        long tEndList = System.nanoTime();

        System.out.printf("Array push 100 k ints : %.2f ms%n",
                          (tEndArr - tStartArr) / 1e6);
        System.out.printf("List  push 100 k ints : %.2f ms%n",
                          (tEndList - tStartList) / 1e6);
    }
}

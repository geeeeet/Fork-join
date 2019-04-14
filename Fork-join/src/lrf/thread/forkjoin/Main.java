package lrf.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        final int SIZE = 100000000;
        int[] numbers = new int[SIZE];
        long start0 = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            numbers[i] =(int) (Math.random()*10);
        }
        long end0 = System.currentTimeMillis();
        System.out.println("生成数组耗时:"+(end0-start0));
        Counter counter = new Counter(numbers,0,numbers.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        long start = System.currentTimeMillis();
        int count = forkJoinPool.invoke(counter);
        long end = System.currentTimeMillis();
        System.out.println("处理耗时:"+(end-start));
        System.out.println(count);
    }
}

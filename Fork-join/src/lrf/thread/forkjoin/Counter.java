package lrf.thread.forkjoin;

import java.util.concurrent.RecursiveTask;

public class Counter extends RecursiveTask<Integer>{

    public static final int THRESHOLD = 1000;
    private int[] values;
    private int from;
    private int to;

    public Counter(int[] values,int from,int to) {
        this.values = values;
        this.from = from;
        this.to = to;
    }
    @Override
    protected Integer compute() {
        if (to-from<THRESHOLD) {
            int count = 0;
            for (int i = from; i < to; i++) {
                if (values[i] > 5) {
                    count++;
                }
            }
            return count;
        } else {
            int mid = (from+to)/2;
            Counter counter1 = new Counter(values,from,mid);
            Counter counter2 = new Counter(values,mid,to);
            invokeAll(counter1,counter2);
            return counter1.join()+counter2.join();
        }
    }
}

/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.Timer;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        
        // TO BE IMPLEMENTED
        for(int i=from+1;i<to;i++){
            int j = i-1;
            while(j>=from&&helper.less(xs[i], xs[j])){
                j--;
            }
            //correct the index which need to be moved
            j++;
            helper.swapInto(xs, j, i);
        }
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }
    
    //n : time of repeatation
    //xs: the array needs to be sorted
    public double repeatInsertionSort(int n, X[] xs) {
        // repeat time insertion sort
        InsertionSort is =new InsertionSort();
        Timer timer = new Timer();
        for (int i = 0; i < n; i++) {
            is.sort(xs, 0, xs.length); 
            timer.lap();
        }
        timer.pause();
        return timer.meanLapTime();
    }
    
    
    public static void main(String[] args) {
        System.out.println("size of array, random, ordered, partially-ordered, reverse-ordered:");
        Random rm = new Random();
        // repeatation time
        //int nn = 5;
        // the size of the array
        for(int m = 100;m<=100000;m+=100){
        //generate test array(supplier)
            Integer[] randomArray = new Integer[m];
            Integer[] orderedArray = new Integer[m];
            Integer[] reverseOrderedArray = new Integer[m];
            Integer[] halfOrderedArray = new Integer[m];
            for(int i=0;i<m;i++){
                randomArray[i] = rm.nextInt();
                orderedArray[i] = i;
                reverseOrderedArray[i] = m-i;
                if(i<m/2){halfOrderedArray[i] = i;}
                else{halfOrderedArray[i] = rm.nextInt();}
            }
            InsertionSort is =new InsertionSort();
            double time1 = is.repeatInsertionSort(10, randomArray);
            double time2 = is.repeatInsertionSort(10, orderedArray);
            double time3 = is.repeatInsertionSort(10, halfOrderedArray);
            double time4 = is.repeatInsertionSort(10, reverseOrderedArray);
            System.out.println(m+","+time1+","+time2+","+time3+","+time4);
        }
    }
}
        
        /*final Function<Integer,Integer[]> random = n -> {
            Integer[] randomArray = new Integer[n];
            for(int i=0;i<n;i++){
                randomArray[i] = rm.nextInt();
            }
            return randomArray;
        };
        final Function<Integer,Integer[]> ordered = n -> {
            Integer[] orderedArray = new Integer[n];
            for(int i=0;i<n;i++){
                orderedArray[i] = i;
            }
            return orderedArray;
        };
        final Function<Integer,Integer[]> reverseOrdered = n -> {
            Integer[] reverseOrderedArray = new Integer[n];
            for(int i=0;i<n;i++){
                reverseOrderedArray[i] = n-i;
            }
            return reverseOrderedArray;
        };
        final Function<Integer,Integer[]> halfOrdered = n -> {
            Integer[] halfOrderedArray = new Integer[n];
            for(int i=0;i<n;i++){
                if(i<n/2){halfOrderedArray[i] = i;}
                else{halfOrderedArray[i] = rm.nextInt();}
            }
            return halfOrderedArray;
        };      
        //function:sort
        final Consumer<Integer[]> function = xs -> {
            InsertionSort is = new InsertionSort();
            is.sort(xs,0,xs.length);
        };
        //run and time it 
        for(int m=500;m<=10000;m+=500){
            double time1 = new Timer().runinsertionsortrepeat(10,m, random, function);
            double time2 = new Timer().runinsertionsortrepeat(10,m, ordered, function);
            double time3 = new Timer().runinsertionsortrepeat(10,m, halfOrdered, function);
            double time4 = new Timer().runinsertionsortrepeat(10,m, reverseOrdered, function);
            System.out.println(m+","+time1+","+time2+","+time3+","+time4);
        }*/
        
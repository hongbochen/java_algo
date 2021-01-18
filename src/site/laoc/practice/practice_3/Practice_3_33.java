package site.laoc.practice.practice_3;

import java.util.NoSuchElementException;

/**
 * 使用循环数组高效实现队列类
 */
public class Practice_3_33<T> {

    private int curIndex;
    private int theSize;
    private static final int ELE_LENGTH = 5;
    private T [] eles;

    public Practice_3_33(){
        doClear();
    }

    private void doClear(){
        eles = (T [])new Object[ELE_LENGTH];
        theSize = 0;
        curIndex = 0;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void push(T x){
        if(size() == ELE_LENGTH)
            throw new IndexOutOfBoundsException();

        int ind = (curIndex + theSize) % ELE_LENGTH;
        eles[ind] = x;
        theSize++;
    }

    public T pop(){
        if(size() == 0)
            throw new NoSuchElementException();

        T x = eles[curIndex];

        eles[curIndex] = null;
        curIndex++;
        if(curIndex == ELE_LENGTH)
            curIndex = 0;

        theSize--;
        return x;
    }

    public static void main(String[] args){
        Practice_3_33<Integer> practice_3_33 = new Practice_3_33<>();
        practice_3_33.push(1);
        practice_3_33.push(2);
        practice_3_33.push(3);

        System.out.println(practice_3_33.pop());
        System.out.println(practice_3_33.pop());

        practice_3_33.push(4);
        practice_3_33.push(5);

        System.out.println(practice_3_33.pop());
        System.out.println(practice_3_33.pop());

        practice_3_33.push(6);

        System.out.println(practice_3_33.pop());
        System.out.println(practice_3_33.pop());
    }
}

package site.laoc.queue;

import java.util.NoSuchElementException;

public class ArrayQueue<T> {

    private static final int DEFAULT_SIZE = 10;
    private int theSize;
    private T [] theItems;

    public ArrayQueue(){
        doClear();
    }

    public T remove(){
        if(size() == 0)
            throw new NoSuchElementException();

        T x = theItems[size() - 1];
        theSize--;
        return x;
    }

    public void add(T x){
        if(theItems.length == size())
            ensureCapacity(size() * 2 + 1);

        for(int i = 0;i < size();i++)
            theItems[i+1] = theItems[i];

        theItems[0] = x;
        theSize++;
    }

    public boolean isEmpty(){
        return size() == 0;
    }


    public int size(){
        return theSize;
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_SIZE);
    }

    private void ensureCapacity(int newCapacity){
        if(newCapacity < theSize)
            return;

        //扩充容量
        T[] old = theItems;
        theItems = (T []) new Object[newCapacity];

        for(int i = 0;i < size();i++)
            theItems[i] = old[i];
    }

    public void add(){

    }
}

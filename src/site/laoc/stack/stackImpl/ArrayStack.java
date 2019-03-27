package site.laoc.stack.stackImpl;

import java.util.NoSuchElementException;

public class ArrayStack<T> {

    private static final int DEFAULT_ACPACITY = 10;

    private int theSize;
    private T [] theItems;

    public ArrayStack(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_ACPACITY);
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void trimToSize(){
        ensureCapacity(size());
    }

    public void push(T x){
        push(0,x);
    }

    public T pop(){
        if(size() == 0)
            throw new NoSuchElementException();

        return remove(0);
    }

    private T remove(int idx){
        T x = theItems[idx];

        for(int i = idx;i < size()-1;i++)
            theItems[i] = theItems[i+1];

        theSize--;
        return x;
    }

    private void push(int idx,T x){
        if(theItems.length == size())
            ensureCapacity(size() * 2 + 1);

        for(int i = theSize; i > idx; i--)
            theItems[i] = theItems[i-1];

        theItems[idx] = x;
        theSize++;
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < theSize)
            return;

        // 扩容
        T[] old = theItems;
        theItems = (T [])new Object[newCapacity];
        for(int i = 0;i < size();i++){
            theItems[i] = old[i];
        }
    }
}

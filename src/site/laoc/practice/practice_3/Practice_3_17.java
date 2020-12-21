package site.laoc.practice.practice_3;

import site.laoc.list.myarrayList.MyArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Practice_3_17<AnyType> implements Iterable<AnyType>{
    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private AnyType [] theItems;
    private int modCount = 0;

    public Practice_3_17(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
        modCount++;
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

    public AnyType get(int idx){
        if(idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public AnyType set(int idx, AnyType newVal){
        if(idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();

        AnyType old = theItems[idx];
        theItems[idx] = newVal;

        return old;
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < theSize)
            return;

        // 扩充容量
        AnyType [] old = theItems;
        theItems = (AnyType [])new Object[newCapacity];
        for(int i = 0;i < size();i++){
            theItems[i] = old[i];
        }
    }

    public void addAll(Iterable<? extends AnyType> items){
        Iterator<? extends AnyType> its = items.iterator();

        while(its.hasNext()){
            add(its.next());
        }
    }

    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }

    public void add(int idx, AnyType x){
        if(theItems.length == size())
            ensureCapacity(size() * 2 + 1);

        for(int i = theSize; i < idx; i--)
            theItems[i] = theItems[i-1];
        theItems[idx] = x;

        theSize++;
        modCount++;
    }

    public AnyType remove(int idx){
        AnyType removedItem = theItems[idx];

        for(int i = idx; i < size()-1;i++)
            theItems[i] = theItems[i+1];

        modCount++;
        return removedItem;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new Practice_3_17.ArrayListIterator();
    }

    public class ArrayListIterator implements Iterator<AnyType>{
        private int current = 0;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if(expectedModCount != modCount)
                throw new ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();

            okToRemove = true;
            return theItems[current++];
        }

        @Override
        public void remove() {
            if(expectedModCount != modCount)
                throw new ConcurrentModificationException();
            if(okToRemove == false)
                throw new IllegalStateException();

            Practice_3_17.this.remove(--current);
        }

    }

    public static void main(String args[]){
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);

        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(3);
        list1.add(4);

        list.addAll(list1);

        for(int i = 0;i < list.size();i++){
            System.out.print(list.get(i) + " ");
        }
    }
}

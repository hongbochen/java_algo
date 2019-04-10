package site.laoc.list.myarrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayListForListIterator<AnyType> implements Iterable<AnyType>{

    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private AnyType [] theItems;

    public MyArrayListForListIterator(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
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
    }

    public AnyType remove(int idx){
        AnyType removedItem = theItems[idx];

        for(int i = idx; i < size()-1;i++)
            theItems[i] = theItems[i+1];

        theSize--;
        return removedItem;

    }

    public void print(){
        for(int i = 0;i < size();i++){
            System.out.print(theItems[i] + " ");
        }
    }

    @Override
    public ListIterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    public class ArrayListIterator implements ListIterator<AnyType> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return theItems[current++];
        }

        @Override
        public boolean hasPrevious() {
            return current != 0;
        }

        @Override
        public AnyType previous() {
            if(!hasPrevious())
                throw new NoSuchElementException();
            return theItems[--current];
        }

        @Override
        public int nextIndex() {
            return current;
        }

        @Override
        public int previousIndex() {
            if(!hasPrevious())
                return 0;
            return current-1;
        }

        @Override
        public void remove() {
            MyArrayListForListIterator.this.remove(--current);
        }

        @Override
        public void set(AnyType anyType) {
            MyArrayListForListIterator.this.set(current,anyType);
        }

        @Override
        public void add(AnyType anyType) {
            MyArrayListForListIterator.this.add(current++,anyType);
        }
    }

    public static void main(String args[]){
//        MyArrayListForListIterator<Integer> list = new MyArrayListForListIterator<>();
//        list.add(1);
//        list.add(2);
//
//        MyArrayListForListIterator<Integer> list1 = new MyArrayListForListIterator<>();
//        list1.add(3);
//        list1.add(4);
//
//        list.addAll(list1);
//
//        for(int i = 0;i < list.size();i++){
//            System.out.print(list.get(i) + " ");
//        }

        MyArrayListForListIterator<Iterator> lst = new MyArrayListForListIterator<>();
        ListIterator<Integer> ito = (ListIterator)lst.iterator();
        ito.add(1);
        ito.add(2);
        ito.add(3);
        ito.add(4);
        lst.print();
        System.out.println();
        lst.remove(1);
        lst.print();
    }
}

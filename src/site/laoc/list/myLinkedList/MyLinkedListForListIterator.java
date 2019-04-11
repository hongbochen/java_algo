package site.laoc.list.myLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedListForListIterator<T extends Comparable<T>> implements Iterable<T>{

    private static class Node<T extends Comparable<T>>{

        public T data;
        private Node<T> prev;
        private Node<T> next;

        public Node(T d,Node<T> p,Node<T> n){
            data = d;
            prev = p;
            next = n;
        }
    }

    private int theSize = 0;
    private int modCount = 0;
    private Node<T> beginMaker;
    private Node<T> endMarker;

    public MyLinkedListForListIterator(){
        doClear();
    }

    public int size(){
        return this.theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean add(T x){
        add(size(),x);
        return true;
    }

    public void print(){
        Node<T> p = beginMaker.next;
        while(p != endMarker){
            System.out.print(p.data + " ");
            p = p.next;
        }

        System.out.println();
    }

    public T get(int idx){
        return getNode(idx).data;
    }

    public T set(int idx,T x){
        Node<T> p = getNode(idx);
        T oldVal = p.data;
        p.data = x;

        return oldVal;
    }

    public T remove(int idx){
        return remove(getNode(idx));
    }

    private T remove(Node<T> p){
        p.prev.next = p.next;
        p.next.prev = p.prev;
        theSize--;
        modCount++;

        return p.data;
    }

    public void removeData(T x){
        while(this.contains(x)){
            Node<T> n = null;

            for(Node<T> node = beginMaker; node.next != null;node = node.next){
                if(node.data == x) {
                    n = node;
                    break;
                }
            }

            if(n != null){
                this.remove(n);
            }
        }
    }

    public void add(int idx,T x){
        addBefore(getNode(idx, 0, size()),x);
    }

    private void addBefore(Node<T> p, T x){
        Node<T> newNode = new Node<>(x,p.prev,p);
        p.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private Node<T> getNode(int idx){
        return getNode(idx,0,size());
    }

    private Node<T> getNode(int idx,int lower,int upper){
        Node<T> p;

        if(idx < lower || idx > upper){
            throw new IndexOutOfBoundsException();
        }

        if(idx < size() / 2){
            p = beginMaker.next;
            for(int i = 0;i < idx;i++){
                p = p.next;
            }
        }else{
            p = endMarker;
            for(int i = size();i > idx;i--){
                p = p.prev;
            }
        }

        return p;
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        beginMaker = new Node<>(null,null,null);
        endMarker = new Node<>(null,beginMaker,null);

        beginMaker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public boolean contains(T x){
        if(x == null)
            throw new NullPointerException();

        for(Node<T> node = beginMaker; node.next != null;node = node.next){
            if(node.data == x)
                return true;
        }

        return false;
    }

    public void removeAll(Iterable<? extends T> items){
        Iterator<? extends T> ito = items.iterator();

        while(ito.hasNext()){
            T t = ito.next();
            removeData(t);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public ListIterator<T> listIterator(){
        return new LinkedListListItarator();
    }

    private class LinkedListListItarator implements ListIterator<T> {
        private Node<T> current = beginMaker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();

            T data = current.data;
            current = current.next;
            okToRemove = true;

            return data;
        }

        @Override
        public boolean hasPrevious() {
            return current.prev.prev != beginMaker;
        }

        @Override
        public T previous() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasPrevious())
                throw new NoSuchElementException();

            T data = current.prev.prev.data;
            current = current.prev;
            okToRemove = true;

            return data;
        }

        @Override
        public int nextIndex() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();
            int idx = 0;

            Node<T> p = beginMaker.next;

            while(p != current){
                idx++;
                p = p.next;
            }

            return idx;
        }

        @Override
        public int previousIndex() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasPrevious())
                throw new NoSuchElementException();
            int idx = 0;

            Node<T> p = beginMaker.next;

            while(p != current.prev.prev){
                idx++;
                p = p.next;
            }

            return idx;
        }

        @Override
        public void remove() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();

            MyLinkedListForListIterator.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }

        @Override
        public void set(T t) {
            Node<T> p = current.prev;
            p.data = t;
        }

        @Override
        public void add(T t) {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();

            MyLinkedListForListIterator.this.add(t);
            expectedModCount++;
            okToRemove = true;
        }
    }

    private class LinkedListIterator implements Iterator<T>{
        private Node<T> current = beginMaker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();

            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();

            MyLinkedListForListIterator.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    public static void main(String [] args){

//        MyLinkedListForListIterator<Integer> ll = new MyLinkedListForListIterator<>();
//        ll.add(1);
//        ll.add(2);
//        ll.add(3);
//        ll.add(2);
//        ll.add(4);
//
//        for(int i = 0;i < ll.size();i++){
//            System.out.print(ll.get(i) + " ");
//        }
//
//        System.out.println();
//
//        MyLinkedListForListIterator<Integer> ll1 = new MyLinkedListForListIterator<>();
//        ll1.add(1);
//        ll1.add(2);
//        ll.removeAll(ll1);
//
//        for(int i = 0;i < ll.size();i++){
//            System.out.print(ll.get(i) + " ");
//        }

        MyLinkedListForListIterator<Integer> lst = new MyLinkedListForListIterator<>();
        ListIterator<Integer> ito = lst.listIterator();

        ito.add(1);
        ito.add(2);
        lst.print();
        //ito.set(3); //pass
        //lst.print();
//        ito.remove();  // pass
//        lst.print();

//        ListIterator<Integer> ito1 = lst.listIterator();
//        System.out.println(ito1.hasNext()); // pass
//        System.out.println(ito1.nextIndex()); // pass
//        System.out.println(ito1.next()); // pass

//        ito1.next();
//        ito1.next();
//        System.out.println(ito1.hasPrevious());// pass
//        System.out.println(ito1.previousIndex());// pass
//        System.out.println(ito1.previous());// pass
    }
}

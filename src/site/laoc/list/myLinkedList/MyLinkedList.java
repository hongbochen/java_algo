package site.laoc.list.myLinkedList;

import java.util.*;

public class MyLinkedList<T> implements Iterable<T>{

    private static class Node<T>{

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

    public MyLinkedList(){
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

    public void print(){
        Node<T> p = beginMaker;

        while(p != endMarker.prev){
            p = p.next;
            System.out.print(p.data + " ");
        }

        System.out.println();
    }



    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public Iterator<T> reverseIterator() {
        return new LinkedListReverseIterator();
    }

    private class LinkedListReverseIterator implements Iterator<T>{
        private Node<T> current = endMarker.prev;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != beginMaker;
        }

        @Override
        public T next() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();

            T nextItem = current.data;
            current = current.prev;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.next);
            expectedModCount++;
            okToRemove = false;
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

        public Node<T> nextNode(){
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();

            Node<T> c = current;
            current = current.next;
            okToRemove = true;
            return c;
        }

        @Override
        public void remove() {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    public static void main(String [] args){

//        MyLinkedList<Integer> ll = new MyLinkedList<>();
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
//        MyLinkedList<Integer> ll1 = new MyLinkedList<>();
//        ll1.add(1);
//        ll1.add(2);
//        ll.removeAll(ll1);
//
//        for(int i = 0;i < ll.size();i++){
//            System.out.print(ll.get(i) + " ");
//        }

        MyLinkedList<Integer> ll = new MyLinkedList<>();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.print();

        Iterator<Integer> ito = ll.reverseIterator();
        while(ito.hasNext()){

            System.out.print(ito.next() + " ");
        }
    }
}

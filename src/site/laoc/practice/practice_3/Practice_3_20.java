package site.laoc.practice.practice_3;

/**
 * Lazy deletion
 * 懒惰删除，删除进行标记，当被删除的元素个数与未被删除的元素个数一样的时候，遍历统一删除
 */

import site.laoc.list.myLinkedList.MyLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 优点：
 *      1：思想简单粗暴易于理解，误删可有恢复空间
 * 缺点：
 *      1：需要额外的域且节点不及时释放，需要更多的空间；执行时间也会微微地多一点点。
 */
public class Practice_3_20<T> {

    private static class Node<T>{

        public T data;
        public byte del; // 标记是否删除
        private Node<T> prev;
        private Node<T> next;

        public Node(T d, Node<T> p, Node<T> n){
            data = d;
            prev = p;
            next = n;
        }
    }

    private int theSize = 0;
    private int modCount = 0;
    private Node<T> beginMaker;
    private Node<T> endMarker;

    private int deled = 0;      // 被删除的元素的个数

    public Practice_3_20(){
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

    /**
     * TODO
     * 懒惰删除，先设置附加的位，然后判断被删除的元素个数与未被删除的元素个数是否相同
     * @param p 被删除的元素
     * @return
     */
    private T remove(Node<T> p){
        p.del = 1;
        theSize--;
        deled++;

        if(theSize == deled){
            // 删除所有被标记的元素
            Node<T> node = beginMaker;
            while(node.next != endMarker){
                Node<T> nd = node.next;

                if(nd.del == 1){
                    removeMarkDeled(nd);
                }
            }
        }

        return p.data;
    }

    private T removeMarkDeled(Node<T> p){
        p.prev.next = p.next;
        p.next.prev = p.prev;
        modCount++;
        deled--;

        return p.data;
    }

    public void removeData(T x){
        while(this.contains(x)){
            Node<T> n = null;

            for(Node<T> node = beginMaker; node.next != null; node = node.next){
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

    private Node<T> getNode(int idx, int lower, int upper){
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
        deled = 0;
        modCount++;
    }

    public boolean contains(T x){
        if(x == null)
            throw new NullPointerException();

        for(Node<T> node = beginMaker; node.next != null; node = node.next){
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

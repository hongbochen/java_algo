package site.laoc.practice.practice_3;

/**
 * Lazy deletion
 * 懒惰删除，删除进行标记，当被删除的元素个数与未被删除的元素个数一样的时候，遍历统一删除
 */

import site.laoc.list.myLinkedList.MyLinkedList;
import java.util.Iterator;

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
    private int nodeled = 0;    // 未被删除的元素

    public Practice_3_20(){
        doClear();
    }

    public int size(){
        return this.nodeled;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean add(T x){
        add(theSize,x);
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
        deled++;
        nodeled--;

        if(nodeled <= deled){
            // 删除所有被标记的元素
            Node<T> node = beginMaker;
            while(node.next != endMarker){
                node = node.next;

                if(node.del == 1){
                    removeMarkDeled(node);
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
        theSize--;

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
        addBefore(getNode(idx),x);
    }

    private void addBefore(Node<T> p, T x){
        Node<T> newNode = new Node<>(x,p.prev,p);
        newNode.del = 0;
        p.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        nodeled++;
        modCount++;
    }

    private Node<T> getNode(int idx){


        if(idx < 0 || idx > size()){
            throw new IndexOutOfBoundsException();
        }

        Node<T> p = beginMaker;

        int index = -1;
        while(p.next != null){
            p = p.next;

            if(p.del == 0){
                index++;
            }

            if(index == idx){
                break;
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
        nodeled = 0;
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
            System.out.print("(" + p.data + "," + p.del + ") ");
        }

        System.out.println();
    }


    public static void main(String [] args){

        Practice_3_20 list = new Practice_3_20();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list.size());
        list.print();
//
        list.remove(0);
        list.print();
        list.remove(0);
        list.print();
        list.remove(0);
        list.print();
        list.remove(0);
        list.print();
    }
}

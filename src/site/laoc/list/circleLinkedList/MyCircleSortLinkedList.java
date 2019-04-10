package site.laoc.list.circleLinkedList;

public class MyCircleSortLinkedList<T extends Comparable<T>> {

    private static class Node<T extends Comparable<T>>{
        T data;
        Node<T> next;

        public Node(T data,Node<T> n){
            this.data = data;
            this.next = n;
        }
    }

    private int theSize = 0;
    private int modCount = 0;
    private Node<T> startNode;

    public MyCircleSortLinkedList(){
        doClear();
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public T get(int idx){
        return getNode(idx).data;
    }

    private Node<T> getNode(int idx){
        if(idx < 0 || idx >= size()){
            throw new IndexOutOfBoundsException();
        }

        Node<T> p = startNode;
        for(int i = 0;i < idx+1;i++){
            p = p.next;
        }

        return p;
    }

    public void add(T x){
        Node<T> p = startNode;

        Node<T> tmp = startNode;

        while(p.next != startNode){
            tmp = p;
            p = p.next;
            if(p.data.compareTo(x) > 0){
                break;
            }
        }

        if(p == startNode){
            Node<T> n = new Node<>(x,p.next);
            p.next = n;
        }else if(p.next == startNode && p.data.compareTo(x) > 0){ // 在之前添加
            Node<T> n = new Node<>(x,p);
            tmp.next = n;
        }else{ //在之后添加
            Node<T> n = new Node<>(x,p.next);
            p.next = n;
        }

        theSize++;
        modCount++;
    }

    public void print(){
        Node<T> p = startNode;

        while(p.next != startNode){
            p = p.next;

            System.out.print(p.data + " ");
        }
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        startNode = new Node<>(null,null);
        startNode.next = startNode;
        theSize = 0;
        modCount++;
    }

    public static void  main(String[] args){
        MyCircleSortLinkedList<Integer> ll = new MyCircleSortLinkedList<>();
        ll.add(1);
        ll.add(3);
        ll.add(2);
        ll.add(5);
        ll.add(4);
        ll.print();

        System.out.println();
        System.out.println(ll.get(2));
    }
}


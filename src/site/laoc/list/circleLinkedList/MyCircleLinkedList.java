package site.laoc.list.circleLinkedList;

public class MyCircleLinkedList<T> {

    private static class Node<T>{
        Node<T> next;
        T data;

        public Node(T data,Node<T> next){
            this.data = data;
            this.next = next;
        }
    }

    private int theSize = 0;
    private int modCount = 0;
    private Node<T> startNode;

    public MyCircleLinkedList(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void add(T data){
        Node<T> n = new Node<>(data,null);
        add(n);
    }

    public void add(Node<T> n){
        add(size(),n);
    }

    public T get(int idx){
        return getNode(idx).data;
    }

    public void set(int idx,T data){
        Node<T> n = getNode(idx);
        n.data = data;
    }

    public void remove(int idx){
        if(idx >= size() || idx < 0)
            throw new IndexOutOfBoundsException();

        Node<T> p = startNode;
        for(int i = 0;i < idx-1; i++)
            p = p.next;

        p.next = p.next.next;
        theSize--;
        modCount++;
    }


    private Node<T> getNode(int idx){
        if(idx >= size() || idx < 0)
            throw new IndexOutOfBoundsException();

        Node<T> p = startNode.next;
        for(int i = 0;i < idx; i++)
            p = p.next;

        return p;
    }

    public void add(int idx,Node<T> n){
        if(idx > size() || idx < 0)
            throw new IndexOutOfBoundsException();

        Node<T> p = startNode;
        for(int i = 0;i < idx; i++)
            p = p.next;

        n.next = p.next;
        p.next = n;

        theSize++;
        modCount++;
    }

    private void doClear(){
        startNode = new Node<>(null,null);
        startNode.next = startNode;
        theSize = 0;
        modCount++;
    }

    public static void main(String args[]){
        MyCircleLinkedList<Integer> list = new MyCircleLinkedList<>();
        list.add(2);
        list.add(3);
        list.add(4);

        for(int i = 0;i < list.size();i++){
            System.out.print(list.get(i) + " ");
        }

        list.remove(0);

        for(int i = 0;i < list.size();i++){
            System.out.print(list.get(i) + " ");
        }
    }

}

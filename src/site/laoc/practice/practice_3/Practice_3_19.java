package site.laoc.practice.practice_3;

public class Practice_3_19<T> {

    private class Node<T>{
        private T data;
        private Node<T> prev;
        private Node<T> next;

        public Node(T data,Node<T> prev,Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private int theSize = 0;
    private int modCount = 0;
    private Node<T> currentNode = null;

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean add(T x){
        if(x == null)
            throw new NullPointerException();

        if(currentNode == null){
            theSize++;
            modCount++;
            currentNode = new Node<>(x,null,null);
            return true;
        }

        Node<T> newNode = new Node<>(x,currentNode,null);
        currentNode.next = newNode;
        currentNode = newNode;
        theSize++;
        modCount++;

        return true;
    }

    public T get(int idx){
        Node<T> tmpNode = getNode(idx);

        return tmpNode.data;
    }

    public T set(int idx,T t){
        if(t == null)
            throw new NullPointerException();

        Node<T> tmpNode = getNode(idx);

        Node<T> newNode = new Node<>(t,tmpNode.prev,tmpNode.next);
        tmpNode.prev.next = newNode;
        tmpNode.next.prev = newNode;

        return tmpNode.data;
    }

    public T remove(int idx){
        return remove(getNode(idx));
    }

    private T remove(Node<T> node){
        if(node.prev != null)
            node.prev.next = node.next;

        if(node.next != null)
            node.next.prev = node.prev;

        theSize--;
        modCount++;
        return node.data;
    }

    public void add(int idx,T t){
        addBefore(getNode(idx),t);
    }

    private void addBefore(Node<T> node,T x){
        Node<T> newNode = new Node<>(x,node.prev,node);
        if(node.prev != null)
            node.prev.next = newNode;

        node.prev = newNode;
        theSize++;
        modCount++;
    }

    private Node<T> getNode(int idx){
        if(idx >= size())
            throw new IndexOutOfBoundsException();

        int steps = size() - idx - 1;
        Node<T> tmpNode = currentNode;
        for(int i = 0;i < steps;i++){
            tmpNode = tmpNode.prev;
        }

        return tmpNode;
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        currentNode = null;
        modCount++;
    }

    public boolean contains(T x){
        if(x == null)
            throw new NullPointerException();

        Node<T> tmpNode = currentNode;
        while(tmpNode != null){
            if(tmpNode.data == x){
                return true;
            }

            tmpNode = tmpNode.prev;
        }

        return false;
    }

    public void print(){
        Node<T> tmpNode = getNode(0);
        while(tmpNode != null){
            System.out.print(tmpNode.data + " ");

            tmpNode = tmpNode.next;
        }

        System.out.println();
    }

    public static void main(String args[]){
        Practice_3_19<Integer> list = new Practice_3_19<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.print();

        System.out.println("size: " + list.size());

        list.remove(0);
        list.print();

        System.out.println(list.contains(2));
    }

}

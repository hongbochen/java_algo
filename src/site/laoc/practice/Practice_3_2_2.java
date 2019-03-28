package site.laoc.practice;

public class Practice_3_2_2<T> {

    private class Node<T>{
        T data;
        Node<T> next;
        Node<T> pre;

        public Node(T data,Node<T> next,Node<T> pre){
            this.data = data;
            this.next = next;
            this.pre = pre;
        }
    }

    private int theSize;
    private Node<T> firstNode;
    private Node<T> endNode;

    public Practice_3_2_2(){
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

    public void exchangeBefore(int idx){
        if(idx <= 0){
            throw new IndexOutOfBoundsException();
        }

        Node<T> m = getNode(idx);
        Node<T> p = m.pre;
        Node<T> pp = m.pre.pre;

        p.next = m.next;
        m.next.pre = p;

        m.pre = p.pre;
        p.pre.next = m;
        p.pre = m;
        m.next = p;
    }

    public T get(int idx){
        return getNode(idx).data;
    }

    public T remove(int idx){
        return remove(getNode(idx));
    }

    private T remove(Node<T> p){
        p.pre.next = p.next;
        p.next.pre = p.pre;
        theSize--;

        return p.data;
    }

    public boolean add(T x){
        add(size(),x);
        return true;
    }

    public void add(int idx,T x){
        addBefore(getNode(idx),x);
    }

    private void addBefore(Node<T> p, T x){
        Node<T> newNode = new Node<>(x,p,p.pre);

        p.pre.next = newNode;
        p.pre = newNode;

        theSize++;
    }

    private Node<T> getNode(int idx){
        return getNode(idx,0,size());
    }

    private Node<T> getNode(int idx,int lower,int upper){
        Node<T> p;

        //System.out.println(idx + ";" + lower + ";" + upper);

        if(idx < lower || idx > upper){
            throw new IndexOutOfBoundsException();
        }

        if(idx < size() / 2){
            p = firstNode.next;

            for(int i = 0;i < idx;i++){
                p = p.next;
            }
        }else{
            p = endNode;

            for(int i = size();i > idx;i--){
                p = p.pre;
            }
        }

        return p;
    }

    private void doClear(){
        firstNode = new Node<>(null,null,null);
        endNode = new Node<>(null,null,firstNode);
        firstNode.next = endNode;

        theSize = 0;
    }

    public static void main(String [] args){
        Practice_3_2_2<Integer> p = new Practice_3_2_2<>();
        p.add(0);
        p.add(1);
        p.add(2);
        p.add(3);

        for(int i = 0;i < p.size();i++){
            System.out.print(p.get(i) + " ");
        }

        System.out.println();

        p.exchangeBefore(1);

        for(int i = 0;i < p.size();i++){
            System.out.print(p.get(i) + " ");
        }
    }

}

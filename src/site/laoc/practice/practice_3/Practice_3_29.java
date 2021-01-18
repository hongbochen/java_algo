package site.laoc.practice.practice_3;

public class Practice_3_29<T> {

    class Node<T>{
        T x;
        Node<T> next;
        Node<T> prev;

        public Node(T x,Node<T> next, Node<T> prev){
            this.x = x;
            this.next = next;
            this.prev = prev;
        }
    }

    private int theSize;
    private Node<T> startNode;
    private Node<T> endNode;

    public Practice_3_29(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        startNode = new Node<>(null,null,null);
        endNode = new Node<>(null,null,startNode);

        startNode.next = endNode;
    }

    public int size(){
        return theSize;
    }

    public void add(T x){
        Node<T> node = new Node<>(x,endNode,endNode.prev);

        endNode.prev.next = node;
        endNode.prev = node;
        theSize++;
    }

    private Node<T> getNode(int index){
        if(index >= size())
            throw new IndexOutOfBoundsException();

        Node<T> temp = startNode;
        for(int i = 0;i <= index;i++){
            temp = temp.next;
        }

        return temp;
    }

    public T get(int index){
        Node<T> temp = getNode(index);

        return temp.x;
    }

    public T remove(int index){
        Node<T> node = getNode(index);

        node.next.prev = node.prev;
        node.prev.next = node.next;

        theSize--;
        return node.x;
    }

    public boolean isEmpty(){
        return startNode.next == endNode;
    }

    public void print(){
        Node<T> node = startNode;

        while(node != endNode.prev){
            node = node.next;

            System.out.print(node.x + " ");
        }

        System.out.println();
    }

    /**
     * 倒叙打印
     */
    public void revengePrint(){
        Node<T> node = endNode;

        while(node != startNode.next){
            node = node.prev;

            System.out.print(node.x + " ");
        }

        System.out.println();
    }

    public static void main(String args[]){
        Practice_3_29<Integer> list = new Practice_3_29<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

//        list.print();
//
//        list.remove(3);
//        list.print();

        list.revengePrint();
    }
}

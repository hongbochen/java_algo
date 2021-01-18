package site.laoc.practice.practice_3;

/**
 * 自调整表 - 链表实现
 */
public class Practice_3_30_b<T> {

    class Node<T>{
        T x;
        Node<T> next;

        public Node(T x,Node<T> next){
            this.x = x;
            this.next = next;
        }
    }

    private int theSize;
    private Node<T> startNode;

    public Practice_3_30_b(){
        doClear();
    }

    private void doClear(){
        this.theSize = 0;
        startNode = new Node<>(null,null);
    }

    private int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void add(T x){
        Node<T> node = new Node<>(x,startNode.next);
        startNode.next = node;

        theSize++;
    }

    public T find(T x){
        Node<T> preNode = startNode;
        Node<T> node = preNode.next;

        while(node != null){
            if(node.x == x){
                break;
            }

            preNode = node;
            node = node.next;
        }

        if(node == null)
            return null;

        // 不等于null，则移位
        preNode.next = node.next;
        node.next = startNode.next;
        startNode.next = node;

        return node.x;
    }

    public void print(){
        Node<T> node = startNode.next;

        while(node != null){
            System.out.print(node.x + " ");

            node = node.next;
        }

        System.out.println();
    }

    public static void main(String[] args){
        Practice_3_30_b<Integer> practice_3_30_b = new Practice_3_30_b<>();

        practice_3_30_b.add(1);
        practice_3_30_b.add(2);
        practice_3_30_b.add(3);
        practice_3_30_b.add(4);
        practice_3_30_b.add(5);

        practice_3_30_b.print();

        practice_3_30_b.find(3);
        practice_3_30_b.print();

        practice_3_30_b.find(4);
        practice_3_30_b.print();

        practice_3_30_b.find(4);
        practice_3_30_b.print();
    }
}

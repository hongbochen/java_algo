package site.laoc.practice.practice_3;

/**
 *
 */
public class Practice_3_37<T> {

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
    private Node<T> endNode;

    public Practice_3_37(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        startNode = new Node<>(null,null);
        endNode = new Node<>(null,null);

        startNode.next = endNode;
    }

    public void add(T x){
        Node<T> tmpNode = startNode;

        while(tmpNode.next != endNode){
            tmpNode = tmpNode.next;
        }

        Node<T> newNode = new Node<>(x,endNode);
        tmpNode.next = newNode;
        theSize++;
    }

    public int size(){
        return theSize;
    }

    private Node<T> getNode(int index){
        Node<T> tmpNode = startNode;

        if(index >= size())
            throw new IndexOutOfBoundsException();

        for(int i = 0;i <= index;i++){
            tmpNode = tmpNode.next;
        }

        return tmpNode;
    }

    // 给定一个迭代器p，在p之前插入一个元素
    public void insertBefore(int index,T x){
        Node<T> tNode = getNode(index);

        /**
         * 思路：
         *  与之前那个题的思路一下，在P之后插入元素，然后换两个位置的值即可
         */

        Node<T> nNode = new Node<>(tNode.x,tNode.next);
        tNode.next = nNode;
        tNode.x = x;

        theSize++;
    }

    /**
     * 删除存储在位置P（由一个迭代器指出）的项
     * @param index
     * @return
     */
    public T removePosNode(int index){
        Node<T> tNode = getNode(index);

        Node<T> rNode = tNode.next;

        T tmp = tNode.x;
        tNode.next = rNode.next;
        tNode.x = rNode.x;
        theSize--;

        return tmp;
    }

    public void print(){
        Node<T> tmpNode = startNode;

        while(tmpNode.next != endNode){
            tmpNode = tmpNode.next;

            System.out.print(tmpNode.x + " ");
        }

        System.out.println();
    }

    public static void main(String [] args){
        Practice_3_37<Integer> practice_3_37 = new Practice_3_37<>();
        practice_3_37.add(1);
        practice_3_37.add(2);
        practice_3_37.add(3);
        practice_3_37.add(4);
        practice_3_37.add(5);
        practice_3_37.add(6);

        practice_3_37.print();

        //practice_3_37.insertBefore(3,7);
        //practice_3_37.print();

        practice_3_37.removePosNode(3);
        practice_3_37.print();
    }
}

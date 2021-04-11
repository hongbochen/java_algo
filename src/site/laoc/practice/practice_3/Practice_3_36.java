package site.laoc.practice.practice_3;

/**
 * 设我们有到单链表的一个节点的引用，而且保证它不是该表的最后的节点。
 * 我们没有到任何其他节点的引用。描述一个O(1)算法，该算法从逻辑上从
 * 该链表删除存储在这样一个节点上的值，同时保持链表的完整性。
 *
 *  思路：
 *  例如，单链表为1->2->3->4->5->6
 *  我们当前节点的引用为3，我们要删除3，只要我们把值4复制到3，然后把4删除即可
 */
public class Practice_3_36<T> {

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

    public Practice_3_36(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        startNode = null;
    }

    public int size(){
        return theSize;
    }

    private Node<T> getLastNode(){
        Node<T> tNode = startNode;

        while(tNode.next != null){
            tNode = tNode.next;
        }

        return tNode;
    }

    public void add(T x){
        if(startNode == null){
            startNode = new Node<>(x,null);
        }else{
            Node<T> tNode = getLastNode();

            Node<T> node = new Node<>(x,null);
            tNode.next = node;
        }
        theSize++;
    }

    public Node<T> getIndexNode(int index){
        if(index >= size())
            throw new IndexOutOfBoundsException();

        Node<T> tNode = startNode;

        for(int i = 0;i < index;i++){
            tNode = tNode.next;
        }

        return tNode;
    }

    public void removeNode(Node<T> tNode){
        Node<T> tmp = tNode;

        tmp.x = tmp.next.x;
        tmp.next = tmp.next.next;
        theSize--;
    }

    public void remove(int index){
        removeNode(getIndexNode(index));
    }

    public void print(){
        Node<T> tNode = startNode;

        while(tNode != null){
            System.out.print(tNode.x + " ");
            tNode = tNode.next;
        }

        System.out.println();
    }

    public static void main(String  args[]){
        Practice_3_36<Integer> practice_3_36 = new Practice_3_36<>();
        practice_3_36.add(1);
        practice_3_36.add(2);
        practice_3_36.add(3);
        practice_3_36.add(4);
        practice_3_36.add(5);
        practice_3_36.add(6);

        practice_3_36.print();

        practice_3_36.remove(2);
        practice_3_36.print();

    }
}
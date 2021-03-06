package site.laoc.practice.practice_3;

import java.util.NoSuchElementException;

/**
 * 使用单链表高效实现栈类，不用头节点和尾节点
 * @param <T>
 */
public class Practice_3_31<T> {

    class Node<T>{
        T x;
        Node<T> next;

        public Node(T x, Node<T> next){
            this.x = x;
            this.next = next;
        }
    }

    private int theSize;
    private Node<T> curNode;

    public Practice_3_31(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void push(T x){
        Node<T> node = new Node<>(x,null);

        if(curNode == null){
            curNode = node;
        }else{
            node.next = curNode;
            curNode = node;
        }

        theSize++;
    }

    public T pop(){
        if(size() == 0)
            throw new NoSuchElementException();

        Node<T> node = curNode;
        curNode = node.next;

        node.next = null;
        theSize--;
        return node.x;
    }

    public static void main(String[] args){
        Practice_3_31<Integer> practice_3_31 = new Practice_3_31<>();
        practice_3_31.push(1);
        practice_3_31.push(2);
        practice_3_31.push(3);
        practice_3_31.push(4);

        System.out.println(practice_3_31.pop());
        System.out.println(practice_3_31.pop());
        System.out.println(practice_3_31.pop());
        System.out.println(practice_3_31.pop());
    }
}

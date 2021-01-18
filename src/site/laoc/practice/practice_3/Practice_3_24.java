package site.laoc.practice.practice_3;

/**
 * 编写只用一个数组而实现两个栈的例程。
 *
 * 两个栈，分别从数组的头部和尾部开始。
 */
public class Practice_3_24<T> {

    private int firstSize = 0;
    private int lastSize = 0;

    private static int ELE_LENGTH = 10;
    private T [] elems;

    public Practice_3_24(){
        elems = (T[])new Object[ELE_LENGTH];
        firstSize = 0;
        lastSize = 0;
    }

    public int firstSize(){
        return this.firstSize;
    }

    public int lastSize(){
        return this.lastSize;
    }

    private void checkBounds(){
        if(firstSize() + lastSize() >= ELE_LENGTH){
            throw new IndexOutOfBoundsException();
        }
    }

    public void firstPush(T t){
        checkBounds();

        elems[firstSize] = t;
        firstSize++;
    }

    public void lastPush(T t){
        checkBounds();

        int index = ELE_LENGTH - lastSize - 1;
        elems[index] = t;
        lastSize++;
    }

    public T firstPop(){
        if(firstSize == 0)
            throw new IndexOutOfBoundsException();

        int index = firstSize-1;

        T t = elems[index];
        elems[index] = null;
        firstSize--;

        return t;
    }

    public T lastPop(){
        if(lastSize == 0)
            throw new IndexOutOfBoundsException();

        int index = ELE_LENGTH - lastSize;

        T t = elems[index];
        elems[index] = null;
        lastSize--;

        return t;
    }

    public void printFirst(){
        for(int i = 0;i < firstSize;i++){
            System.out.print(elems[i] + " ");
        }

        System.out.println();
    }

    public void printLast(){
        for(int i = ELE_LENGTH-lastSize;i < ELE_LENGTH;i++){
            System.out.print(elems[i] + " ");
        }

        System.out.println();
    }

    public static void main(String args[]){
        Practice_3_24<Integer> practice_3_24 = new Practice_3_24<Integer>();

        practice_3_24.firstPush(1);
        practice_3_24.firstPush(2);
        practice_3_24.firstPush(3);

        practice_3_24.lastPush(4);
        practice_3_24.lastPush(5);
        practice_3_24.lastPush(6);

        practice_3_24.printFirst();
        practice_3_24.printLast();

        System.out.println(practice_3_24.firstPop());
        System.out.println(practice_3_24.lastPop());
    }
}

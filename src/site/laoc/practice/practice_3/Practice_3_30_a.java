package site.laoc.practice.practice_3;

/**
 * 自调整表（数组实现）
 */
public class Practice_3_30_a<T> {

    private int theSize = 0;
    private static int ELE_LENGTH = 10;
    private T [] eles;

    public Practice_3_30_a(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        eles = (T [])new Object[ELE_LENGTH];
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void checkCap(){
        if(size() == ELE_LENGTH)
            throw new OutOfMemoryError();
    }

    public void add(T x){
        checkCap();

        for(int i = size();i > 0;i--){
            eles[i] = eles[i-1];
        }

        eles[0] = x;
        theSize++;
    }

    public T find(T x){
        int i = 0;

        for(i = 0; i < size();i++){
            if(eles[i] == x){
                break;
            }
        }

        if(i == size()){
            return null;
        }

        // 挪动位置
        T tmp = eles[i];

        for(int m = i; m > 0;m--){
            eles[m] = eles[m-1];
        }

        eles[0] = tmp;
        return tmp;
    }


    public void print(){
        for(int i = 0;i < size();i++){
            System.out.print(eles[i] + " ");
        }

        System.out.println();
    }


    public static void main(String[] args){
        Practice_3_30_a practice_3_30_a = new Practice_3_30_a();
        practice_3_30_a.add(1);
        practice_3_30_a.add(2);
        practice_3_30_a.add(3);
        practice_3_30_a.add(4);
        practice_3_30_a.add(5);

        practice_3_30_a.print();

        System.out.println("搜索数据：" + practice_3_30_a.find(3));
        practice_3_30_a.print();

        System.out.println("搜索数据：" + practice_3_30_a.find(3));
        practice_3_30_a.print();
    }

}

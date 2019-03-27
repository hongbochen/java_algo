package site.laoc.list.listExampls.array;

public class ArratADT {

    int [] arr = new int[10];

    public int[] extendArray(){
        // 数组扩大
        int newArray[] = new int[arr.length * 2];

        for(int i = 0; i < arr.length; i++){
            newArray[i] = arr[i];
        }

        return newArray;
    }

}

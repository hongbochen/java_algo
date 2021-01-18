package laoc.site.effective;

import java.util.EnumSet;

public class Test {

    public static void main(String[] args){

        long sum = 0L;

        Long time1 = System.currentTimeMillis();
        for(long i = 0; i < Integer.MAX_VALUE;i++){
            sum = sum + i;
        }
        Long time2 = System.currentTimeMillis();

        System.out.println(sum);
        System.out.println("运行时间：" + (time2-time1));
    }
}

package site.laoc.practice.practice_3;

import site.laoc.stack.stackImpl.ArrayStack;

/**
 * 后缀表达式求值
 */
public class Practice_3_22 {

    // 例子 1 + (2 + 3) * 4 - 5 = 16
    // 后缀表达式 1 2 3 + 4 x + 5 -
    // 具体逻辑见 documents/表达式.md

    public double suffixCal(String con[]){

        ArrayStack<Double> stack = new ArrayStack();

        for(int i = 0;i < con.length; i++){
            String c = con[i];

            if(!isCalSym(c)){
                // 不是运算符号,则压栈
                Double dd = Double.parseDouble(c);
                stack.push(dd);
            }else{
                // 如果是运算符号，则弹出运算，再压栈
                Double d1 = stack.pop();
                Double d2 = stack.pop();

                Double tmp = 0D;
                if(c.equals("+")){
                    tmp = d1 + d2;
                }else if(c.equals("-")){
                    tmp = d2 - d1;
                }else if(c.equals("*")){
                    tmp = d1 * d2;
                }else{
                    tmp = d2 / d1;
                }

                stack.push(tmp);
            }
        }

        return stack.pop();
    }

    private boolean isCalSym(String c){
        if(c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")){
            return true;
        }

        return false;
    }

    public static void main(String args[]){
        String con[] = {"1","2","3","+","4","*","+","5","-"};

        Practice_3_22 p = new Practice_3_22();
        System.out.println(p.suffixCal(con));
    }
}

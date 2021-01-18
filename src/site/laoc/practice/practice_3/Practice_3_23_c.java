package site.laoc.practice.practice_3;

import site.laoc.stack.stackImpl.ArrayStack;

/**
 * 后缀表达式转中缀表达式
 * 数字压栈，遇到
 */
public class Practice_3_23_c {

    private boolean isSym(char c){
        if(c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')')
            return true;
        return false;
    }

    public String suffixToMed(String con){
        String tmp = "";

        ArrayStack<Character> stack = new ArrayStack<>();

        for(int i = 0;i < con.length();i++){
            char t = con.charAt(i);

            if(!isSym(t)){
                // 不是符号，则入栈
                stack.push(t);
            }else{
                //  是符号，则出栈
                if(tmp.length() == 0){
                    tmp = "(" + stack.pop() + t + stack.pop() + ")";
                }else{
                    tmp = "(" + tmp + t + stack.pop() + ")";
                }
            }
        }

        return tmp;
    }

    public static void main(String[] args){
        String tt = "123+4*+5-";

        Practice_3_23_c p = new Practice_3_23_c();

        System.out.println(p.suffixToMed(tt));
    }
}

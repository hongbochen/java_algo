package site.laoc.practice.practice_3;

import site.laoc.stack.stackImpl.LinkedListStack;

// 中缀表达式转后缀表达式，
public class Practice_3_23_b {
    private boolean isSym(char c){
        if(c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '^')
            return true;
        return false;
    }

    private boolean aBigTob(char a,char b){
        if(b == '+' || b == '-'){
            return true;
        }

        if((b == '*' || b == '/' || b == '^') && (a == '(')){
            return true;
        }

        if((b == '*' || b == '/' || b == '^') && (a == '*' || a == '/' || a == '^')){
            return true;
        }

        return false;
    }

    public void medToBack(String med){
        LinkedListStack<Character> ls = new LinkedListStack<>();

        for(int i = 0;i < med.length();i++){
            char tmp = med.charAt(i);

            if(isSym(tmp)){
                //查看是否是右括号
                if(tmp == ')'){
                    char intmp;
                    do{
                        intmp = ls.pop();
                        if(intmp != '('){
                            System.out.print(intmp + " ");
                        }
                    }while(intmp != '(');
                }else{
                    while(!ls.isEmpty() && aBigTob(ls.getTop(),tmp) && ls.getTop() != '('){
                        char inc = ls.pop();
                        System.out.print(inc + " ");
                    }

                    ls.push(tmp);
                }
            }else{
                System.out.print(tmp + " ");
            }
        }

        while(!ls.isEmpty()){
            System.out.print(ls.pop() + " ");
        }
    }

    public static void main(String args[]){
        Practice_3_23_b p = new Practice_3_23_b();
        p.medToBack("1+(2^2+3)*4-5");
    }

}

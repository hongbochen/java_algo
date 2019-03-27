package site.laoc.stack.stackExample;

import site.laoc.stack.stackImpl.LinkedListStack;

/**
 * 中缀表达式转后缀表达式（逆波兰）
 * 1：从左向右遍历中缀表达式
 * 2：如果是数组，则输出
 * 3：如果是运算符，判断与栈顶符号的优先级
 * 4：如果当前符号比栈顶的运算符的优先级高，则该符号入栈
 * 5：如果当前符号比栈顶的运算符的优先级低，则栈内元素出栈，输出；接着进行判断运算符与栈顶元素；最后，该运算符入栈
 * 6：当前符号为右括号的时候，栈内元素一直出栈，一直到左括号。
 * 7：当中缀表达式遍历完成之后，将栈内元素全部输出
 */
public class MedToBack {

    private boolean isSym(char c){
        if(c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')')
            return true;
        return false;
    }

    private boolean aBigTob(char a,char b){
        if(b == '+' || b == '-'){
            return true;
        }

        if((b == '*' || b == '/') && (a == '(')){
            return true;
        }

        if((b == '*' || b == '/') && (a == '*' || a == '/')){
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
}


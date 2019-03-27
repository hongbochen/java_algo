package site.laoc.stack.stackExample;

import site.laoc.stack.stackImpl.LinkedListStack;

/**
 * 中缀表达式计算
 */
public class MedCal {

    private boolean isSym(char c){
        if(c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')')
            return true;
        return false;
    }

    public Double calMed(String medStr){
        LinkedListStack<Double> ls = new LinkedListStack<>();

        Double sum = 0D;

        for(int i = 0;i < medStr.length();i++){
            char tmp = medStr.charAt(i);

            if(isSym(tmp)){
                Double tmpValue = 0D;

                Double val1 = ls.pop();
                Double val2 = ls.pop();

                if(tmp == '+'){
                    tmpValue = val1 + val2;
                }else if(tmp == '-'){
                    tmpValue = val1 - val2;
                }else if(tmp == '*'){
                    tmpValue = val1 * val2;
                }else if(tmp == '/'){
                    tmpValue = val1 / val2;
                }

                ls.push(tmpValue);

                if(ls.size() == 1){
                    sum = ls.pop();
                }
            }else if(tmp == ' '){

            }else{
                Double intmp = Double.parseDouble(String.valueOf(tmp));
                ls.push(intmp);
            }
        }

        return sum;
    }
}

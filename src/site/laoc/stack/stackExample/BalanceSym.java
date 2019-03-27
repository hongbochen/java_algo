package site.laoc.stack.stackExample;

import site.laoc.stack.stackImpl.LinkedListStack;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Stack;

// 平衡符号
public class BalanceSym {

    //{}.().[]判断
    public void balanceSym(String fileName){
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];

        try{
            FileInputStream fis = new FileInputStream(file);
            fis.read(fileContent);
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        String con = "";
        try {
            con =  new String(fileContent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
        }

        LinkedListStack<Character> chaStack = new LinkedListStack<>();

        for(int i = 0;i < con.length();i++){
            char c = con.charAt(i);
            if(c == '(' || c == '{' || c == '['){
                chaStack.push(c);
            }else if(c == ')'){
                char tmp = chaStack.pop();
                if(tmp != '('){
                    throw new IllegalStateException();
                }
            }else if(c == '}'){
                char tmp = chaStack.pop();
                if(tmp != '{'){
                    throw new IllegalStateException();
                }
            }else if(c == ']'){
                char tmp = chaStack.pop();
                if(tmp != '['){
                    throw new IllegalStateException();
                }
            }
        }

        //如果还有，则不符合规范

        try{
            chaStack.pop();
        }catch(Exception e){
            System.out.println("该文档中的符号是平衡的");
            return;
        }

        System.out.println("该文档中的符号不平衡");
    }
}

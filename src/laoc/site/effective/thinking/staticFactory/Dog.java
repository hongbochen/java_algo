package laoc.site.effective.thinking.staticFactory;

/**
 * 静态工厂方法
 */
public class Dog {
    private String name;
    private int age;
    private String color;

    private Dog(){

    }

    public static Dog newDogWithName(String name){
        Dog dog = new Dog();
        dog.name = name;
        return dog;
    }

    public static Dog newDogWithColor(String color){
        Dog dog = new Dog();
        dog.color = color;

        return dog;
    }

    public static void main(String[] args){
        Dog dog1 = Dog.newDogWithName("狗子");
        //Dog dog2 =  Dog.newDogWithColor("黄色");
        Dog dog3 = Dog.newDogWithName("狗子2");

        System.out.println(dog1 == dog3);
    }
}

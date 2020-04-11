package chap5;

public class ExtendsTest {
    public static void main(String[] args) {
        Father father = new Father("father1", 44);
        Father son = new Son("baby1", 1, "baby");
        Son son2 = new Son("baby2", 424, "baby2");
        System.out.println("~~~~~~~~~~~~~~");

        // 只有father中存在 work方法
//        father.work();
//        son.work();
//        son2.work();
//        System.out.println("~~~~~~~~~~~~~~");

        // father，son中都有 say()
        father.say();
        son.say();
        son2.say();
        System.out.println("~~~~~~~~~~~~~~");

        ((Son) son).cry();
        son2.cry();
//        ((Son)father).cry(); // 会报错

    }
}


class Son extends Father {

    String nickName;


    public Son(String name, Integer age, String nickName) {
        super(name, age);
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

//    public static void eat() {
//        System.out.println("子类 只能喝奶");
//    }

    public static void say() {
        System.out.println("子类 只会哭");
    }

    public void cry() {
        System.out.println("子类 cry cry。。。。");
    }

}

class Father {
    static int id = 0;
    String name;
    Integer age;

    public Father(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static void eat() {
        System.out.println("父类 筷子吃");
    }

    public static void say() {
        System.out.println("父类  正常说话");
    }

    public void work() {
        System.out.println("父类  work的技能");
    }

}
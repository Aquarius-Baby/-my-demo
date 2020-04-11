package chap5;

public class ObjectTest {
    public static void main(String[] args) {
        String a = "hello";
        String b = "hello";
        String c = a;
        String d = new String("hello");
        String e = new String("hello");
        String t1 = "he";
        String t2 = "llo";
        String f = t1 + t2;
        String f1 = "he" + "llo";
        String g = t1 + new String("llo");

        System.out.println(a == b); // true
        System.out.println(a == b); // true
        System.out.println(a == c); // true
        System.out.println(a == d); // false
        System.out.println(a == d.intern()); //true
        System.out.println(d == e); // false
        System.out.println(a == f); // false
        System.out.println(a == f1); // true
        System.out.println(d == f); // false
        System.out.println(d == f1); // false
        System.out.println(f == g); // false
    }
}

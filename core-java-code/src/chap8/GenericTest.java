package chap8;

/**
 * @author cmy
 * @date 2020/3/27 10:43 上午
 */
public class GenericTest {
    public static void main(String[] args) {
        MyArray<String> myArray = new MyArray<String>("123");

    }
}


class MyArray<T> {

    private T key;

    public MyArray(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey() { //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }
}

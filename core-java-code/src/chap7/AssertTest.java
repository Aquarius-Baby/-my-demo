package chap7;


/**
 * @author cmy
 * @date 2020/3/24 7:58 下午
 */
public class AssertTest {
    // 在edit configuration中的 vm-options， 加上 -ea
    // 执行报Exception in thread "main" java.lang.AssertionError: ERROE ::::: 1和0不相等！错
    // Exception in thread "main" java.lang.AssertionError: ERROE ::::: 1和0不相等！
    public static void main(String[] args) {
        assert (1 == 1) : "ok";
        assert (1 == 0) : "ERROE ::::: 1和0不相等！";
    }
}

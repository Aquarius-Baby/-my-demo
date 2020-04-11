package chap7;

/**
 * @author cmy
 * @date 2020/3/23 9:42 下午
 */
public class TryFinallyTest {
    public static void main(String[] args) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(f(2)); // 0
        System.out.println(f(3)); // 9
        System.out.println("=============================");
        System.out.println(t(2)); // -1
        System.out.println(t(3)); // -1
        System.out.println("=============================");
        System.out.println(t2(2)); // 4
        System.out.println(t2(3)); // 9
        System.out.println("=============================");
        System.out.println(t3(2)); // -1
        System.out.println(t3(3)); // -1
    }

    public static int f(int n) {
        try {
            int r = n * n;
            return r;
        } finally {
            if (n == 2) {
                return 0;
            }
        }
    }

    public static int t(int n) {
        int res = 0;
        try {
            res = n * n;
            return res;
        } finally {
            res = -1;
            return res;
        }
    }

    public static int t2(int n) {
        int res = 0;
        try {
            res = n * n;
            return res;
        } finally {
            res = -1;
        }
    }

    public static int t3(int n) {
        int res = 0;
        try {
            res = n * n;
        } finally {
            res = -1;
        }
        return res;
    }
}

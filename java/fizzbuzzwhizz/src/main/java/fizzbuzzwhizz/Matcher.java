package fizzbuzzwhizz;

/**
 * Created by wangbo on 2017/8/13.
 */

@FunctionalInterface
public interface Matcher {
    boolean matches(int n);

    static Matcher times(int times) {
        return n -> n % times == 0;
    }

    static Matcher contains(int number) {
        return n -> String.valueOf(n).contains(String.valueOf(number));
    }

    static Matcher always(boolean bool) {
        return n -> bool;
    }
}

package fizzbuzzwhizz;

/**
 * Created by wangbo on 2017/8/13.
 */
@FunctionalInterface
public interface Action {
    String to(int n);

    static Action to(final String str) {
        return n->str;
    }

    static Action nop() {
        return n -> String.valueOf(n);
    }
}

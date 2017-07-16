package magicbowen;

/**
 * Created by wangbo on 2017/7/16.
 */
public enum Operator {
    ADD("+") {
        @Override
        int operate(int a, int b) {
            return a + b;
        }
    },
    SUB("-") {
        @Override
        int operate(int a, int b) {
            return a - b;
        }
    },
    MUL("*") {
        @Override
        int operate(int a, int b) {
            return a * b;
        }
    },
    DIV("/") {
        @Override
        int operate(int a, int b) {
            return a / b;
        }
    };

    private final Object desc;

    private Operator(String desc) {
        this.desc = desc;
    }

    abstract int operate(int a, int b);
}

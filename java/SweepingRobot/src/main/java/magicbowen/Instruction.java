package magicbowen;

/**
 * Created by wangbo on 2017/7/8.
 */
enum Instruction {
    FORWARD("forward"), BACKWARD("backward"), TURN_LEFT("turn left"), TURN_RIGHT("turn right");

    Instruction(String name) {
        this.name = name;
    }

    private String name;
}

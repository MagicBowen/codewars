package magicbowen;

import java.util.Vector;

/**
 * Created by wangbo on 2017/7/9.
 */
public class CompositeInstruction implements Instruction {

    public CompositeInstruction(Instruction... instructions) {
        for (Instruction instruction : instructions) {
            this.instructions.add(instruction);
        }
    }

    public void run(Position p, Direction d) {
        Position position = p;
        Direction direction = d;
        for (Instruction instruction : instructions) {
            instruction.run(position, direction);
            position = instruction.getPosition();
            direction = instruction.getDirection();
        }
    }

    public Position getPosition() {
        return instructions.lastElement().getPosition();
    }

    public Direction getDirection() {
        return instructions.lastElement().getDirection();
    }

    private Vector<Instruction> instructions = new Vector<Instruction>();
}

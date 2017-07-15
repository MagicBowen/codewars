package magicbowen;

import java.util.ArrayList;
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
        if(instructions.isEmpty()) throw new NullPointerException();
        return instructions.get(instructions.size() - 1).getPosition();
    }

    public Direction getDirection() {
        if(instructions.isEmpty()) throw new NullPointerException();
        return instructions.get(instructions.size() - 1).getDirection();
    }

    private ArrayList<Instruction> instructions = new ArrayList<Instruction>();
}

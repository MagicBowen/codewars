package magicbowen;

public interface Instruction {
    void run(Position p, Direction d);
    Position  getPosition();
    Direction getDirection();
}

class CommonInstruction implements Instruction {
    public void run(Position p, Direction d) {
        position = changePosition(p, d);
        direction = changeDirection(d);
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    protected Position  changePosition(Position p, Direction d) { return p; }
    protected Direction changeDirection(Direction d) { return d; }

    protected Position position;
    protected Direction direction;
}

class ForwardInstruction extends CommonInstruction {
    @Override
    protected Position changePosition(Position p, Direction d) {
        return p.forward(d.getUnit());
    }
}

class BackwardInstruction extends CommonInstruction {
    @Override
    public Position changePosition(Position p, Direction d) {
        return p.backward(d.getUnit());
    }
}

class TurnLeftInstruction extends CommonInstruction {
    public Direction changeDirection(Direction d) {
        return d.leftSide();
    }
}

class TurnRightInstruction extends CommonInstruction {
    public Direction changeDirection(Direction d) {
        return d.rightSide();
    }
}

class FowardsInstruction extends CommonInstruction {
    FowardsInstruction(int steps) {
        this.steps = steps;
    }

    @Override
    public Position changePosition(Position p, Direction d) {
        Position destination = p;
        for (int i = 0; i < steps; i++) {
            destination = destination.forward(d.getUnit());
        }
        return destination;
    }

    private final int steps;
}

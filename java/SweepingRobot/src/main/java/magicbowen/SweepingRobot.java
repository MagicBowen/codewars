package magicbowen;


public class SweepingRobot {
    SweepingRobot() {
        position = new Position();
        direction = Direction.NORTH;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void execute(Instruction instruction) {
        switch (instruction) {
            case FORWARD:
                position = position.forward(direction.getUnit());
                break;
            case BACKWARD:
                position = position.backward(direction.getUnit());
                break;
            case TURN_LEFT:
                direction = direction.leftSide();
                break;
            case TURN_RIGHT:
                direction = direction.rightSide();
                break;
        }
    }

    @Override
    public String toString() {
        return String.format("SweepingRobot(%s, %s)", position, direction);
    }

    private Position  position;
    private Direction direction;
}

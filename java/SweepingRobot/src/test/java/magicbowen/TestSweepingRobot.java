package magicbowen;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;  
import static org.hamcrest.Matchers.*;  

public class TestSweepingRobot {

    public void assertRobot(int x, int y, Direction direction) {
        assertThat(new Position(x, y), equalTo(robot.getPosition()));
        assertThat(direction, equalTo(robot.getDirection()));
    }

    @BeforeMethod()
    public void initRobot() {
        robot = new SweepingRobot();
    }

    @Test()
    public void testInitPosition() {
        assertRobot(0, 0, Direction.NORTH);
    }

    @Test()
    public void testMoveForward() {
        robot.execute(Instruction.FORWARD);
        assertRobot(0, 1, Direction.NORTH);
    }

    @Test()
    public void testMoveBackward() {
        robot.execute(Instruction.BACKWARD);
        assertRobot(0, -1, Direction.NORTH);
    }

    @Test()
    public void testTurnLeft() {
        robot.execute(Instruction.TURN_LEFT);
        assertRobot(0, 0, Direction.WEST);
    }

    @Test()
    public void testTurnRight() {
        robot.execute(Instruction.TURN_RIGHT);
        assertRobot(0, 0, Direction.EAST);
    }

    @Test()
    public void testTurnRightAndForward() {
        robot.execute(Instruction.TURN_RIGHT);
        robot.execute(Instruction.FORWARD);
        assertRobot(1, 0, Direction.EAST);
    }

    private SweepingRobot robot;
}
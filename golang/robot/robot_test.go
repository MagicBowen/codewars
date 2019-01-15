package robot

import "testing"

type position struct {
	x, y int
	heading Direction
}

func assertPosition(r *Robot, p position, t *testing.T) {
	if r.x != p.x || r.y != p.y || r.heading != p.heading {
		t.Errorf("robot%v's position is not (%d, %d, %v)", r, p.x, p.y, p.heading)
	}
}

func TestInitialPosition(t *testing.T) {
	assertPosition(NewRobot(), position{0, 0, North}, t)
}

func TestTurnRight(t *testing.T) {
	assertPosition(NewRobot().Execute(TurnRightCommand{}), position{0, 0, East}, t)
}

func TestTurnLeft(t *testing.T) {
	assertPosition(NewRobot().Execute(TurnLeftCommand{}), position{0, 0, West}, t)
}

func TestNorthForward(t *testing.T) {
	assertPosition(NewRobot().Execute(ForwardCommand{}), position{0, 1, North}, t)
}

func TestEastForward(t *testing.T) {
	assertPosition((&Robot{0, 0, East}).Execute(ForwardCommand{}), position{1, 0, East}, t)
}

func TestSouthBackward(t *testing.T) {
	assertPosition((&Robot{0, 0, South}).Execute(BackwardCommand{}), position{0, 1, South}, t)
}

func TestWestBackward(t *testing.T) {
	assertPosition((&Robot{0, 0, West}).Execute(BackwardCommand{}), position{1, 0, West}, t)
}

func TestWestTurnRount(t *testing.T) {
	assertPosition((&Robot{0, 0, West}).Execute(TurnRoundCommand{}), position{0, 0, East}, t)
}

func TestEastForwardsN(t *testing.T) {
	assertPosition((&Robot{0, 0, East}).Execute(ForwardsCommand{5}), position{5, 0, East}, t)
}

func TestNorthBackwardsN(t *testing.T) {
	assertPosition((&Robot{0, 0, North}).Execute(BackwardsCommand{3}), position{0, -3, North}, t)
}

func TestRepeatCommand(t *testing.T) {
	r := NewRobot().Execute(RepeatCommand{3, TurnRightCommand{}})
	assertPosition(r, position{0, 0, West}, t)
}

func TestCommands(t *testing.T) {
	c := Commands{[]Command{TurnRightCommand{}, ForwardsCommand{8}, TurnRoundCommand{}, BackwardCommand{}}}
	r := NewRobot().Execute(c)
	assertPosition(r, position{9, 0, West}, t)
}
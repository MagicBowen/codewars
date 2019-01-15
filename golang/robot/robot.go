package robot

import "fmt"

type Robot struct{
	x,y int
	heading Direction
}

func NewRobot() *Robot {
	return & Robot{0, 0, North}
}

func (r *Robot) String() string {
	return fmt.Sprintf("(%d, %d, %s)", r.x, r.y, r.heading)
}

type Command interface {
	run(r *Robot)
}

func (r *Robot) Execute(c Command) *Robot {
	c.run(r)
	return r
}
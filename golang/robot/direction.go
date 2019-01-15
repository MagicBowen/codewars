package robot

type Direction int

const (
	North Direction = iota
	East
	South
	West
)

func (d Direction) String() string {
	switch d {
	case North:
		return "north"
	case East:
		return "east"
	case South:
		return "south"
	case West:
		return "west"
	}
	return "unknown-direction"
}

func (d Direction) left() Direction {
	return Direction((d + 3) % 4)
}

func (d Direction) right() Direction {
	return Direction((d + 1) % 4)
}

type move int

const (
	ForwardStep move = 1
	BackwardStep move = -1
)

func (d Direction) nextStep(x, y int, step move) (int, int) {
	switch d {
	case North:
		return x, y + int(step)
	case South:
		return x, y - int(step)
	case East:
		return x + int(step), y
	case West:
		return x - int(step), y
	}
	return x, y
}

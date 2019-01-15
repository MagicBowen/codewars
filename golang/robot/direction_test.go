package robot

import "testing"

func getDirectionOp(d Direction, opStr string) (func() Direction) {
	if opStr == "left" {
		return d.left
	}
	if opStr == "right" {
		return  d.right
	}
	return nil
}

func TestDirection(t *testing.T) {
	var examples = []struct {
		d Direction
		action string
		expect Direction
	}{
		{North, "left",  West},
		{North, "right", East},
		{East,  "left",  North},
		{East,  "right", South},
		{South, "left",  East},
		{South, "right", West},
		{West,  "left",  South},
		{West,  "right", North},
	}

	for _, e := range examples {
		result := getDirectionOp(e.d, e.action)()
		if  result!= e.expect {
			t.Errorf("Direction %v's %s is %v but expect %v\n", e.d, e.action, result, e.expect)
		}
	}
}
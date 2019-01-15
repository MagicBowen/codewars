package fizzbuzzwhizz

import "testing"

func TestGame(t *testing.T) {
	examples := []struct {
		n uint
		expect string
	} {
        {3, "Fizz"},
        {5, "Buzz"},
        {7, "Whizz"},
        {8, "8"},
        {13, "Fizz"},
        {3 * 5, "FizzBuzz"},
        {3 * 7, "FizzWhizz"},
        {35, "Fizz"},
        {70, "BuzzWhizz"},
        {3 * 5 * 7, "FizzBuzzWhizz"},
	}

	for _, e := range examples {
		result := Apply(e.n)
		if result != e.expect {
			t.Errorf("Apply(%d) should be %s, but %s\n", e.n, e.expect, result)
		}
	}
}
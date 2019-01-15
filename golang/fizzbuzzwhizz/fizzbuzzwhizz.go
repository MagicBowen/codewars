package fizzbuzzwhizz

import (
	"fmt"
	"strings"
	"strconv"
)

func Apply(n uint) string {
	fizz := atom(times(3), to("Fizz"))
	buzz := atom(times(5), to("Buzz"))
	whizz := atom(times(7), to("Whizz"))

	allOf := allOf(fizz, buzz, whizz)
	contains := atom(contains(3), to("Fizz"))
	defaultRule := atom(always(true), nop())

	rule := anyOf(contains, allOf, defaultRule)
	return rule(n)	
}

type action func(uint) string
type matcher func(uint) bool
type rule func(uint) string

func atom(m matcher, a action) rule {
	return rule(
		func (n uint) string {
			if m(n) {
				return a(n)
			}
			return ""
		},
	)
}

func anyOf(rs ...rule) rule {
	return rule(
		func (n uint) string {
			for _, r := range rs {
				result := r(n)
				if (result != "") {
					return result
				}
			}
			return ""
		},
	)
}

func allOf(rs ...rule) rule {
	return rule(
		func (n uint) string {
			var result string
			for _, r := range rs {
				result += r(n)
			}
			return result
		},
	)
}

func to(str string) action {
	return action(
		func (n uint) string {
			return fmt.Sprintf("%s", str)
		},
	)
}

func nop() action {
	return action(
		func (n uint) string {
			return fmt.Sprintf("%d", n)
		},
	)
}

func times(times uint) matcher {
	return matcher(
		func (n uint) bool {
			return n % times == 0
		},
	)
}

func contains(number uint) matcher {
	return matcher(
		func (n uint) bool {
			return strings.Contains(strconv.Itoa(int(n)), strconv.Itoa(int(number)))
		},
	)
}

func always(b bool) matcher {
	return matcher(
		func (n uint) bool {
			return b
		},
	)
}
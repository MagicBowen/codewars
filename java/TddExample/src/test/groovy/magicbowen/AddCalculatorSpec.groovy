package magicbowen

import spock.lang.Specification


class AddCalculatorSpec extends Specification {

    def "1 + 2 = 3" () {
        given:
        def calculator = new AddCalculator()

        expect:
        result == calculator.calculate(a, b)

        where:
        a | b | result
        1 | 2 | 3
        -1| -2| -3
        2 | -5| -3
    }
}
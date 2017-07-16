package magicbowen

import spock.lang.Specification

/**
 * Created by wangbo on 2017/7/16.
 */
class OperatorSpec extends Specification {
    def "should return 2 when 5 sub 3"() {
        given:
        def op = Operator.SUB
        when:
        def result = op.operate(5, 3)
        then:
        result == 2
    }

    def "should return correct result when a mul b"() {
        given:
        def op = Operator.MUL
        expect:
        result == op.operate(a, b)
        where:
        a | b | result
        1 | 2 | 2
        -1| -2| 2
        2 | -5| -10
    }
}

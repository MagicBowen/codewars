package fizzbuzzwhizz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangbo on 2017/8/13.
 */
public class RuleTest {

    @Test
    public void testTimesAtomRule() {
        Rule rule = Rule.atom(Matcher.times(3), Action.to("Fizz"));
        assertEquals("Fizz", rule.apply(3));
        assertEquals("Fizz", rule.apply(6));
        assertEquals("", rule.apply(8));
    }

    @Test
    public void testContainsAtomRule() {
        Rule rule = Rule.atom(Matcher.contains(5), Action.to("Buzz"));
        assertEquals("Buzz", rule.apply(5));
        assertEquals("Buzz", rule.apply(15));
        assertEquals("", rule.apply(20));
    }

    @Test
    public void testAlwaysAtomRule() {
        Rule rule = Rule.atom(Matcher.always(true), Action.to("xxx"));
        assertEquals("xxx", rule.apply(5));
        assertEquals("xxx", rule.apply(13));
        assertEquals("xxx", rule.apply(20));
    }
    @Test
    public void testAlwaysNopAtomRule() {
        Rule rule = Rule.atom(Matcher.always(true), Action.nop());
        assertEquals("5", rule.apply(5));
        assertEquals("13", rule.apply(13));
        assertEquals("20", rule.apply(20));
    }

    @Test
    public void anyOf() {
        Rule fizz = Rule.atom(Matcher.times(3), Action.to("Fizz"));
        Rule buzz = Rule.atom(Matcher.times(5), Action.to("Buzz"));
        Rule whizz = Rule.atom(Matcher.times(7), Action.to("Whizz"));
        Rule rules = Rule.anyOf(fizz, buzz, whizz);

        assertEquals("Fizz", rules.apply(6));
        assertEquals("Buzz", rules.apply(10));
        assertEquals("Whizz", rules.apply(14));
        assertEquals("", rules.apply(11));
    }

    @Test
    public void allOf() {
        Rule fizz = Rule.atom(Matcher.times(3), Action.to("Fizz"));
        Rule buzz = Rule.atom(Matcher.times(5), Action.to("Buzz"));
        Rule rules = Rule.allOf(fizz, buzz);

        assertEquals("Fizz", rules.apply(3));
        assertEquals("Buzz", rules.apply(5));
        assertEquals("FizzBuzz", rules.apply(15));
    }

}
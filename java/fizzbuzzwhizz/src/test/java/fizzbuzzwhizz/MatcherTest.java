package fizzbuzzwhizz;

import fizzbuzzwhizz.Matcher;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wangbo on 2017/8/13.
 */
public class MatcherTest {
    @Test
    public void times() {
        assertTrue(Matcher.times(3).matches(6));
        assertFalse(Matcher.times(3).matches(7));
    }

    @Test
    public void contains() {
        assertTrue(Matcher.contains(4).matches(14));
        assertFalse(Matcher.contains(4).matches(15));
    }

    @Test
    public void always() {
        assertTrue(Matcher.always(true).matches(8));
        assertFalse(Matcher.always(false).matches(8));
    }

}
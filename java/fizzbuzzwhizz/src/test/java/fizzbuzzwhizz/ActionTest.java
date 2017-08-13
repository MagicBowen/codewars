package fizzbuzzwhizz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangbo on 2017/8/13.
 */
public class ActionTest {

    @Test
    public void testActions() {
        assertEquals("Fizz", Action.to("Fizz").to(3));
        assertEquals("Buzz", Action.to("Buzz").to(5));
    }

    @Test
    public void testNopAction() {
        assertEquals("3", Action.nop().to(3));
    }
}
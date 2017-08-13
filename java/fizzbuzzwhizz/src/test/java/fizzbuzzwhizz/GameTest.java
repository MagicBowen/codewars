package fizzbuzzwhizz;

import fizzbuzzwhizz.Game;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wangbo on 2017/8/13.
 */
public class GameTest {
    private void matches(int n, String str) {
        assertEquals(str, Game.apply(n));
    }

    @Test
    public void testGame() {
        matches(3, "Fizz");
        matches(5, "Buzz");
        matches(7, "Whizz");
        matches(8, "8");
        matches(13, "Fizz");
        matches(3 * 5, "FizzBuzz");
        matches(3 * 7, "FizzWhizz");
        matches(35, "Fizz");
        matches(70, "BuzzWhizz");
        matches(3 * 5 * 7, "FizzBuzzWhizz");
    }
}
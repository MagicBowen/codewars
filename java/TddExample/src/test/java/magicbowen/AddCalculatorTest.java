package magicbowen;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;  
import static org.hamcrest.Matchers.*;  

public class AddCalculatorTest
{
    private Calculator calculator;

    @Before
    public void createCalculator() {
        calculator = new AddCalculator();
    }

    @Test
    public void shouldReturn3When1And2() {
        assertThat(3, equalTo(calculator.calculate(1, 2)));
    }
}

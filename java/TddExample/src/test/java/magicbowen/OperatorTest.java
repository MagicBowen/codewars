package magicbowen;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Created by wangbo on 2017/7/16.
 */
public class OperatorTest {
    @Test
    public void shouldReturn3When1Add2() {
        Operator op = Operator.ADD;
        assertThat(3, equalTo(op.operate(1, 2)));
    }

}
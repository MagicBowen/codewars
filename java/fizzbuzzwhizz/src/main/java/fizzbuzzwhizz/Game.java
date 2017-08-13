package fizzbuzzwhizz; /**
 * Created by wangbo on 2017/8/13.
 */

import static fizzbuzzwhizz.Action.*;
import static fizzbuzzwhizz.Matcher.*;
import static fizzbuzzwhizz.Rule.*;

public class Game {
    public static String apply(int n) {
        Rule fizz = atom(times(3), to("Fizz"));
        Rule buzz = atom(times(5), to("Buzz"));
        Rule whizz = atom(times(7), to("Whizz"));

        Rule allOf = allOf(fizz, buzz, whizz);
        Rule contains = atom(contains(3), to("Fizz"));
        Rule defaultRule = atom(always(true), nop());

        Rule rule = anyOf(contains, allOf, defaultRule);
        return rule.apply(n);
    }
}

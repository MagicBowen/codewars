package fizzbuzzwhizz; /**
 * Created by wangbo on 2017/8/13.
 */

import static fizzbuzzwhizz.Action.*;
import static fizzbuzzwhizz.Matcher.*;
import static fizzbuzzwhizz.Rule.*;

public class Game {
    private static Rule FizzBuzzWhizz() {
        Rule fizz = atom(times(3), to("Fizz"));
        Rule buzz = atom(times(5), to("Buzz"));
        Rule whizz = atom(times(7), to("Whizz"));

        Rule allOf = allOf(fizz, buzz, whizz);
        Rule contains = atom(contains(3), to("Fizz"));
        Rule defaultRule = atom(always(true), nop());

        Rule rule = anyOf(contains, allOf, defaultRule);
        return rule;
    }
    public static void main(String[] args) {
        Rule rule = FizzBuzzWhizz();
        for (int i = 1; i <= 100; i++) {
            System.out.println(rule.apply(i));
        }
    }
}

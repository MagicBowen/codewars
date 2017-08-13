package fizzbuzzwhizz; /**
 * Created by wangbo on 2017/8/13.
 */

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@FunctionalInterface
public interface Rule {
    String apply(int n);

    static Rule atom(Matcher matcher, Action action) {
        return n -> matcher.matches(n) ? action.to(n) : "";
    }

    static Rule anyOf(Rule... rules) {
        return n -> stringStream(n, rules).filter(s -> !s.isEmpty()).findFirst().orElse("");
    }

    static Rule allOf(Rule... rules) {
        return n -> stringStream(n ,rules).collect(joining());
    }

    static Stream<String> stringStream(int n, Rule[] rules) {
        return Arrays.stream(rules).map(r -> r.apply(n));
    }

}

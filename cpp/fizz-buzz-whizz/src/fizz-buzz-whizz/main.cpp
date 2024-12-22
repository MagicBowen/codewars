#include <iostream>
#include <string>
#include <sstream>
#include <tuple>
#include <algorithm>

// Matcher 基类模板
template <typename T>
struct Matcher;

// Matcher 实现：判断是否能被某个数整除
template <unsigned int Divisor>
struct DivisibleMatcher {
    constexpr bool match(unsigned int n) const {
        return n % Divisor == 0;
    }
};

// Matcher 实现：判断是否包含某个数字
template <unsigned int Digit>
struct ContainsMatcher {
    bool match(unsigned int n) const {
        std::string numStr = std::to_string(n);
        std::string digitStr = std::to_string(Digit);
        return numStr.find(digitStr) != std::string::npos;
    }
};

// Matcher 实现：始终返回固定值
template <bool VALUE>
struct AlwaysMatcher {
    constexpr bool match(unsigned int /*n*/) const {
        return VALUE;
    }
};

// Action 基类模板
template <typename T>
struct Action;

// Action 实现：返回预定义的字符串
template <const char* Str>
struct StringAction {
    std::string act(unsigned int /*n*/) const {
        return std::string(Str);
    }
};

// Action 实现：返回数字本身的字符串
struct NumberAction {
    std::string act(unsigned int n) const {
        return std::to_string(n);
    }
};

// Atom 规则：如果匹配器匹配，则执行动作
template <typename Matcher, typename Action>
struct Atom {
    Matcher matcher;
    Action action;

    std::string apply(unsigned int n) const {
        if (matcher.match(n)) {
            return action.act(n);
        }
        return "";
    }
};

// AnyOf 规则：依次应用子规则，返回第一个非空结果
template <typename... Rules>
struct AnyOf {
    std::tuple<Rules...> rules;

    std::string apply(unsigned int n) const {
        return apply_impl(n, std::index_sequence_for<Rules...>{});
    }

private:
    template <std::size_t... Is>
    std::string apply_impl(unsigned int n, std::index_sequence<Is...>) const {
        std::string result;
        ((result.empty() ? (result = std::get<Is>(rules).apply(n)) : ""), ...);
        return result;
    }
};

// AllOf 规则：组合多个子规则的结果
template <typename... Rules>
struct AllOf {
    std::tuple<Rules...> rules;

    std::string apply(unsigned int n) const {
        return apply_impl(n, std::index_sequence_for<Rules...>{});
    }

private:
    template <std::size_t... Is>
    std::string apply_impl(unsigned int n, std::index_sequence<Is...>) const {
        std::string result;
        ((result += std::get<Is>(rules).apply(n)), ...);
        return result;
    }
};

// 常量字符串定义
constexpr char fizzStr[] = "Fizz";
constexpr char buzzStr[] = "Buzz";
constexpr char whizzStr[] = "Whizz";

// 定义具体的 Matchers
using Div3 = DivisibleMatcher<3>;
using Div5 = DivisibleMatcher<5>;
using Div7 = DivisibleMatcher<7>;
using Cont3 = ContainsMatcher<3>;

// 定义具体的 Atom 规则
using AtomFizz = Atom<DivisibleMatcher<3>, StringAction<fizzStr>>;
using AtomBuzz = Atom<DivisibleMatcher<5>, StringAction<buzzStr>>;
using AtomWhizz = Atom<DivisibleMatcher<7>, StringAction<whizzStr>>;
using AtomContains3 = Atom<ContainsMatcher<3>, StringAction<fizzStr>>;
using AtomDefault = Atom<AlwaysMatcher<true>, NumberAction>;

// 定义 AnyOf 主规则，按照优先级顺序
using FizzBuzzWhizz = AnyOf<
    AtomContains3,                             // 优先级最高：包含3
    AllOf<AtomFizz, AtomBuzz, AtomWhizz>,      // 同时是3,5,7的倍数
    AllOf<AtomFizz, AtomBuzz>,                 // 同时是3和5的倍数
    AllOf<AtomFizz, AtomWhizz>,                // 同时是3和7的倍数
    AllOf<AtomBuzz, AtomWhizz>,                // 同时是5和7的倍数
    AtomFizz,                                  // 单独的3的倍数
    AtomBuzz,                                  // 单独的5的倍数
    AtomWhizz,                                 // 单独的7的倍数
    AtomDefault                                // 默认
>;

int main() {
    FizzBuzzWhizz fbw;

    for (unsigned int i = 1; i <= 100; ++i) {
        std::cout << fbw.apply(i) << std::endl;
    }

    return 0;
}

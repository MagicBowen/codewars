#include <iostream>
#include <array>
#include <string_view>
#include <utility>
#include <cstdint>
#include <tuple>

// 定义最大数字
constexpr uint32_t MAX_NUMBER = 100;

// 辅助函数：检查数字是否包含特定的数字
constexpr bool contains_digit(uint32_t number, char digit) {
    while (number > 0) {
        if (number % 10 == (digit - '0')) {
            return true;
        }
        number /= 10;
    }
    return false;
}

// ---------------------- Matcher 定义 ---------------------- //

// 3的倍数匹配器
struct Times3Matcher {
    static constexpr bool match(uint32_t n) {
        return n % 3 == 0;
    }
};

// 5的倍数匹配器
struct Times5Matcher {
    static constexpr bool match(uint32_t n) {
        return n % 5 == 0;
    }
};

// 7的倍数匹配器
struct Times7Matcher {
    static constexpr bool match(uint32_t n) {
        return n % 7 == 0;
    }
};

// 包含数字3的匹配器
struct Contains3Matcher {
    static constexpr bool match(uint32_t n) {
        return contains_digit(n, '3');
    }
};

// 总是匹配的匹配器
struct AlwaysMatcher {
    static constexpr bool match(uint32_t /*n*/) {
        return true;
    }
};

// ---------------------- Action 定义 ---------------------- //

// Fizz动作
struct FizzAction {
    static constexpr std::string_view apply(uint32_t /*n*/) {
        return "Fizz";
    }
};

// Buzz动作
struct BuzzAction {
    static constexpr std::string_view apply(uint32_t /*n*/) {
        return "Buzz";
    }
};

// Whizz动作
struct WhizzAction {
    static constexpr std::string_view apply(uint32_t /*n*/) {
        return "Whizz";
    }
};

// 默认动作（返回空字符串，表示需要输出数字本身）
struct DefaultAction {
    static constexpr std::string_view apply(uint32_t /*n*/) {
        return "";
    }
};

// ---------------------- Rule 定义 ---------------------- //

// Atom规则：单个Matcher和Action
template <typename Matcher, typename Action>
struct AtomRule {
    static constexpr std::string_view apply(uint32_t n) {
        if (Matcher::match(n)) {
            return Action::apply(n);
        }
        return "";
    }
};

// AnyOf规则：多个Rule中任意一个匹配
template <typename... Rules>
struct AnyOfRule {
    static constexpr std::string_view apply(uint32_t n) {
        std::string_view result = "";
        ((result.empty() ? (result = Rules::apply(n)) : result), ...);
        return result;
    }
};

// AllOf规则：多个Rule全部匹配并组合其输出
template <typename... Rules>
struct AllOfRule {
    static constexpr std::string_view apply(uint32_t n) {
        std::string_view result = "";
        ((Rules::Matcher::match(n) ? (result += Rules::Action::apply(n)) : result), ...);
        return result;
    }
};

// ---------------------- 定义具体规则 ---------------------- //

// 包含数字3的规则，优先级最高
using Contains3Rule = AtomRule<Contains3Matcher, FizzAction>;

// 3、5、7倍数的单独规则
using Times3Rule = AtomRule<Times3Matcher, FizzAction>;
using Times5Rule = AtomRule<Times5Matcher, BuzzAction>;
using Times7Rule = AtomRule<Times7Matcher, WhizzAction>;

// 组合所有倍数规则
using MultiplesCombinedRule = AllOfRule<Times3Rule, Times5Rule, Times7Rule>;
using MultiplesRule = AnyOfRule<
    Times3Rule,
    Times5Rule,
    Times7Rule,
    AllOfRule<Times3Rule, Times5Rule>,
    AllOfRule<Times3Rule, Times7Rule>,
    AllOfRule<Times5Rule, Times7Rule>,
    MultiplesCombinedRule
>;

// 默认规则
using DefaultRule = AtomRule<AlwaysMatcher, DefaultAction>;

// 最终应用规则，优先检查包含规则，其次检查倍数规则，最后是默认规则
using ApplyRule = AnyOfRule<Contains3Rule, MultiplesRule, DefaultRule>;

// ---------------------- 生成结果数组 ---------------------- //

// 计算单个数字的输出
constexpr std::string_view compute_output(uint32_t n) {
    return ApplyRule::apply(n);
}

// 生成包含1到MAX_NUMBER每个数字对应输出的数组
template <std::size_t... I>
constexpr auto generate_results(std::index_sequence<I...>) {
    return std::array<std::string_view, MAX_NUMBER>{
        compute_output(I + 1)...
    };
}

constexpr auto results = generate_results(std::make_index_sequence<MAX_NUMBER>{});

// ---------------------- 运行时打印结果 ---------------------- //

int main() {
    for (uint32_t i = 1; i <= MAX_NUMBER; ++i) {
        if (!results[i - 1].empty()) {
            std::cout << results[i - 1] << '\n';
        } else {
            std::cout << i << '\n';
        }
    }
    return 0;
}
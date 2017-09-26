package magicbowen;

/**
 * Created by wangbo on 2017/9/26.
 */
public interface StatementFormatter {
    void onCustomer(String name);
    void onRental(String movieName, double charge);
    void onTotal(double charge, int points);
    String statement();
}

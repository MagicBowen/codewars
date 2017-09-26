package magicbowen;


import java.util.ArrayList;
import java.util.List;

public class Customer {
    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String statement(StatementFormatter formatter) {
        formatter.onCustomer(getName());
        for (Rental rental : rentals) {
            formatter.onRental(rental.getMovie().getTitle(), rental.getCharge());
        }
        formatter.onTotal(getTotalCharge(), getTotalRenterPoints());
        return formatter.statement();
    }

    private String getName() {
        return name;
    }

    private double getTotalCharge() {
        double result = 0;
        for (Rental rental : rentals) {
            result += rental.getCharge();
        }
        return result;
    }

    private int getTotalRenterPoints() {
        int result = 0;
        for (Rental rental : rentals) {
            result += rental.getPoints();
        }
        return result;
    }

    private String name;
    private List<Rental> rentals = new ArrayList<>();
}

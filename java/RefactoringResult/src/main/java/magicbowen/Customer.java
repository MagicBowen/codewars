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

    public String statement() {
        String result = "Rental record for " + getName() + "\n";
        for (Rental rental : rentals) {
            result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.getCharge()) + "\n";
        }
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalRenterPoints()) + " frequent renter points\n";
        return result;
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

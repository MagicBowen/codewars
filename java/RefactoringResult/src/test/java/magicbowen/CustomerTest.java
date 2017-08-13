package magicbowen;

import org.junit.Test;

import static magicbowen.MovieType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CustomerTest {
    @Test
    public void statement() throws Exception {
        Customer customer = new Customer("Robert");
        customer.addRental(new Rental(new Movie("The Beautiful Life", NEW_RELEASE_TYPE), 5));
        customer.addRental(new Rental(new Movie("Tom and Jerry", CHILDREN_TYPE), 4));
        customer.addRental(new Rental(new Movie("Animal World", REGULAR_TYPE), 2));
        String statement = new StringBuilder()
                .append("Rental record for Robert\n")
                .append("\tThe Beautiful Life\t15.0\n")
                .append("\tTom and Jerry\t3.0\n")
                .append("\tAnimal World\t2.0\n")
                .append("Amount owed is 20.0\n")
                .append("You earned 4 frequent renter points\n")
                .toString();
        assertThat(statement, equalTo(customer.statement()));
    }

}
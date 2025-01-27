package pkg1;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Ticket class to create ticket objects.
 */
public class Ticket {
    private String row;
    private int seat;
    private double price;
    private Person person;

    /**
     *Ticket constructor to create ticket objects.
     */
    public Ticket(String row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    /**
     *Method to set the row of the ticket.
     */
    public void setRow(String row) {
        this.row = row;
    }

    /**
     *Method to set the seat of the ticket.
     */
    public void setSeat(int seat) {
        this.seat = seat;
    }

    /**
     *Method to set the price of the ticket.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *Method to set the person of the ticket.
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     *Method to get the row of the ticket.
     */
    public String getRow() {
        return row;
    }

    /**
     *Method to get the seat of the ticket.
     */
    public int getSeat() {
        return seat;
    }

    /**
     *Method to get the price of the ticket.
     */
    public double getPrice() {
        return price;
    }

    /**
     *Method to get the person of the ticket.
     */
    public Person person() {
        return person;
    }

    /**
     *Method to get the ticket info.
     */
    public void getTicketInfo() {
        System.out.println("Ticket info; ");
        System.out.println();
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: " + price);
        System.out.println("Person info;");
        person.getPerson();
    }

    /**
     * Method to save the ticket info to the file.
     */
    public void save() {
        String fileName = row + seat + ".txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("Ticket info\n");
            fileWriter.write("Row :" + row + "\n");
            fileWriter.write("Seat :" + seat + "\n");
            fileWriter.write("Price :" + price + "\n");
            fileWriter.write("Person info :\n");
            fileWriter.write("Name :" + person.getName() + "\n");
            fileWriter.write("Surname :" + person.getSurname() + "\n");
            fileWriter.write("Email :" + person.getEmail() + "\n");

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while saving file");
        }

    }
}
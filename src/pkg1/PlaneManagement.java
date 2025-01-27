package pkg1;

import java.util.InputMismatchException;
import java.util.Scanner;


public class PlaneManagement {
    //Array for the seats
    private static final int[][] seats = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    //Array to store ticket objects
    private static final Ticket[] tickets = new Ticket[52];

    //Variable to keep track of the number of tickets booked
    private static int ticketCount = 0;

    private static final Scanner input = new Scanner(System.in);

    /**
     * Checking if the row letter is valid.
     * @param row The inputted row
     * @return True only if the row is valid (A/B/C/D).
     */
    public static boolean rowCheck(String row){
        return (row.equals("A") || row.equals("D") || row.equals("B") || row.equals("C"));
    }

    /**
     * Checking if the column and row are valid.
     * @param column The inputted column
     * @param row The inputted row
     * @return True only if the row and column are valid.
     */
    public static boolean seatNumCheck(int column, String row) {
        return (((row.equals("A") || row.equals("D")) && (column < 15 && column > 0)) || ((row.equals("B") || row.equals("C")) && (column < 13 && column > 0)));
    }

    /**
     * Check if any tickets are booked.
     * @return false if no tickets are sold.
     */
    public static boolean checkTicketsEmpty(){
        if (ticketCount == 0) {
            System.out.println("No tickets have been sold yet");
            return false;
        }else {
            return true;
        }
    }

    /**
     * Check if all tickets are booked.
     * @return true if all tickets have been sold.
     */
    public static boolean checkTicketsFull(){
        if (ticketCount == 52) {
            System.out.println("All tickets have been sold");
            return true;
        }else {
            return false;
        }
    }

    /**
     * Converts the row letter to its relevant index in seats array
     * @param row The row letter to convert
     * @return The index of the row in seats array
     */
    public static int rowNumber(String row){
        int rowIndex = 1;

        switch (row){
            case "A":
                rowIndex = 0;
                break;
            case "B":
                rowIndex = 1;
                break;
            case "C":
                rowIndex = 2;
                break;
            case "D":
                rowIndex = 3;
                break;
        }
        return rowIndex;
    }

    /**
     * Method to buy a seat.
     * User should enter row letter and seat number then it will be checked whether available.
     * After user enters personal info the ticket will be booked and saved to a text file.
     * User can exit to the menu by entering 'Q'.
     */
    public static void buy_seat() {
        String row;  //Inputted row letter
        String name;
        String surName;
        String email;
        String seatNoInput; //Inputted seat number
        int seatNum; //Seat number
        int rowIndex; // Index of the row
        double price; //Price of the seat

        System.out.println("if you want to go back the menu, please enter 'Q'");
        System.out.println();

        System.out.print("Enter row letter: ");
        row = input.next().toUpperCase();
        if (row.equals("Q")){
            return;
        }
        if (rowCheck(row)) {
            System.out.print("Enter seat number: ");
            seatNoInput = input.next();
            if (seatNoInput.equalsIgnoreCase("Q")){
                return;
            }
            else {
                seatNum = Integer.parseInt(seatNoInput);
            }
            if (seatNumCheck(seatNum,row)){
                rowIndex = rowNumber(row);
                if (seats[rowIndex][seatNum -1] == 0){
                    seats[rowIndex][seatNum-1] = 1;
                    System.out.println();
                    System.out.println("Seat " + row + seatNum + " is available");
                    //Assign the price to the relevant seats
                    if(seatNum<6){
                        price = 200;
                    }
                    else if (seatNum<10){
                        price = 150;
                    }
                    else {
                        price = 180;
                    }

                    System.out.println("Seat price is: " + price);

                    System.out.println("Do you want to book the seat? (Y/N) ");
                    input.nextLine();
                    String answer = input.nextLine().toUpperCase();
                    if (answer.equals("N")){
                        return;
                    }
                    System.out.println("**************************************************\n");

                    System.out.println("Please enter your information to book the seat");


                    do {
                        System.out.print("Enter your Name : ");
                        name = input.nextLine();
                        if (name.isEmpty()){
                            System.out.println("Name cannot be empty");
                        }
                    }while (name.isEmpty());

                    if (name.equalsIgnoreCase("Q")){
                        return;
                    }

                    do{
                        System.out.print("Enter your Surname : ");
                        surName = input.nextLine();
                        if (surName.isEmpty()){
                            System.out.println("Surname cannot be empty");
                        }
                    }while (surName.equalsIgnoreCase("Q"));

                    if (surName.equals("Q")){
                        return;
                    }

                    while (true){
                        System.out.print("Enter your Email : ");
                        email = input.nextLine();
                        if (email.isEmpty()){
                            System.out.println("Email cannot be empty");
                        }
                        else if (!email.contains("@") || !email.contains(".")){
                            System.out.println("Invalid email\nTry again");
                        }
                        else {
                            break;
                        }
                    }

                    if (email.equalsIgnoreCase("Q")){
                        return;
                    }

                    Ticket ticket = new Ticket(row,seatNum, price,new Person(name,surName,email));
                    tickets[ticketCount++] = ticket;

                    ticket.save();
                    System.out.println("Tickets have been booked successfully");

                }else {
                    System.out.println("Seat " + row + seatNum + " is already booked\nTry a different seat");
                }
            }
            else {
                System.out.println("Invalid seat number\nTry again");
            }
        }else {
            System.out.println("Invalid letter\nTry again");
        }
    }

    /**
     * Method to cancel a seat.
     * User should enter row letter and seat number then it will be checked whether booked.
     * Seat will be cancelled if it is booked.
     * User can exit to the menu by entering 'Q'.
     */
    public static void cancel_seat() {
        String row; //Inputted row letter
        String seatNoInput; //Inputted seat number
        int seatNum; // Seat number
        int rowIndex; // Index of the row
        System.out.println("\nYou can cancel your ticket by entering the row letter and seat number");
        System.out.println("if you want to go back the menu, please enter 'Q'");
        System.out.println();

        System.out.print("Enter row letter: ");
        row = input.next().toUpperCase();
        if (row.equals("Q")){
            return;
        }

        if (rowCheck(row)) {
            System.out.print("Enter seat number: ");
            seatNoInput = input.next();
            if (seatNoInput.equalsIgnoreCase("Q")){
                return;
            }
            else {
                seatNum = Integer.parseInt(seatNoInput);
            }
            if (seatNumCheck(seatNum,row)){
                rowIndex = rowNumber(row);
                if (seats[rowIndex][seatNum -1] == 1){
                    seats[rowIndex][seatNum-1] = 0;

                    for (int i=0; i<ticketCount ; i++){
                        if (tickets[i]!=null && tickets[i].getRow().equals(row) && tickets[i].getSeat() == seatNum){
                            tickets[i] = null;
                        }
                    }

                    System.out.println("Seat " + row + seatNum + " has been cancelled successfully");
                }else {
                    System.out.println("Seat " + row + seatNum + " is not booked\nTry a different seat");
                }
            }
            else {
                System.out.println("Invalid seat number\nTry again");
            }
        }else {
            System.out.println("Invalid letter\nTry again");
        }
    }

    /**
     * Method to find first available seat.
     * Method loops through the seats array and finds the first available seat.
     * Then shows the first available seat.
     */
    public static void find_first_available() {
        char[] rows = {'A','B','C','D'}; //Array to store row letters
        int[] temporary = {100,100}; //Temporary array to store the first available seat
        for (int i=0; i<seats.length ; i++ ){
            for (int j=0; j<seats[i].length; j++){
                if (seats[i][j] ==0) {
                   if(j < temporary[1]){
                      temporary[0] = i;
                      temporary[1] = j;
                   }
                }
            }

        }
        System.out.println("First available seat is: " + rows[temporary[0]] + (temporary[1]+1));
    }

    /**
     * Method to show the seating plan.
     * Method loops through the seats array and shows the seating plan.
     * This shows 'O' for available seats and 'X' for booked seats.
     */
    public static void show_seating_plan(){
        char[][] seating_plan = {{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}};
        for (int i=0; i<seats.length ; i++ ){
            for (int j=0; j<seats[i].length; j++){
                if (seats[i][j] ==1) {
                    seating_plan[i][j] = 'X';
                }
            }
        }
        for (int i=0; i<seats.length ; i++ ){
            for (int j=0; j<seats[i].length; j++){
                System.out.print(seating_plan[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Method to print tickets information and total sales.
     * Method loops through seats array and prints ticket info.
     * Then prints the total sales
     */
    public static void print_tickets_info(){
        double total=0;
        for (int i=0; i<tickets.length; i++){
            if (tickets[i]!=null){
                tickets[i].getTicketInfo();
                System.out.println("\n------------------------------------\n");
                total += tickets[i].getPrice();
            }
        }
        System.out.print("Total sales : " + total);
    }

    /**
     * Method to search a ticket
     * User is asked to enter row letter and seat number.
     * Then prints the ticket info if seat is booked.
     */
    public static void search_ticket(){
        String row; //Inputted row letter
        int seatNum; //Seat number
        int rowIndex; //Index of the row
        System.out.print("Enter row letter: ");
        row = input.next().toUpperCase();
        if (rowCheck(row)) {
            System.out.print("Enter seat number: ");
            seatNum = input.nextInt();
            if (seatNumCheck(seatNum,row)){
                rowIndex = rowNumber(row);
                if (seats[rowIndex][seatNum -1] == 1){

                    for (int i=0; i<ticketCount ; i++){
                        if (tickets[i]!=null && tickets[i].getRow().equals(row) && tickets[i].getSeat() == seatNum){
                            tickets[i].getTicketInfo();
                        }
                    }
                }else {
                    System.out.println("This seat is available");
                }
            }
            else {
                System.out.println("Invalid seat number\nTry again");
            }
        }else {
            System.out.println("Invalid letter\nTry again");
        }
    }

    /**
     * Main method
     * User can select an option from the menu.
     * Runs the relevant method based on the user's input.
     * Method loops until user selects to quit.
     */

    public static void main (String[]args){

                System.out.println("Welcome to the Plane Management application");

                boolean menu = true; //Variable to loop the menu

                while (menu) {
                    try {

                        System.out.print("""

                                **************************************************
                                *                  MENU OPTIONS                  *
                                **************************************************

                                     1) Buy a seat\s
                                     2) Cancel a seat\s
                                     3) Find first available seat\s
                                     4) Show seating plan\s
                                     5) Print tickets information and total sales\s
                                     6) Search ticket\s
                                     0) Quit\s

                                **************************************************

                                Please select an option:\s""");
                        int option = input.nextInt();

                        switch (option) {
                            case 0:
                                menu = false;
                                break;
                            case 1:
                                if (!checkTicketsFull()) {
                                    buy_seat();
                                }
                                break;
                            case 2:
                                if (checkTicketsEmpty()) {
                                    cancel_seat();
                                }

                                break;
                            case 3:
                                find_first_available();
                                break;
                            case 4:
                                show_seating_plan();
                                break;
                            case 5:
                                if (checkTicketsEmpty()) {
                                    print_tickets_info();
                                }
                                break;
                            case 6:
                                if (checkTicketsEmpty()) {
                                    search_ticket();
                                }

                                break;
                            default:
                                System.out.println("Invalid input\nTry again");
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input\nTry again");
                        input.nextLine();
                    }
                }
                input.close();
            }
}
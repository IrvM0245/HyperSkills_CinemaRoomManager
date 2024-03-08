package cinema;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        // Write your code here
        int ticketsShell = 0;
        double percentageShell = 0;
        int profitShell = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int n1 = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int n2 = scanner.nextInt();
        String[][] places = new String[n1][n2];
        fillWithS(places);
        printPlaces(places);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            try {
                int command = scanner.nextInt();
                switch (command) {
                    case 1: {
                        printPlaces(places);
                        break;
                    }
                    case 2: {
                        profitShell += buyingTicket(scanner, places);
                        ticketsShell++;
                        break;
                    }
                    case 3: {
                        System.out.println("Number of purchased tickets: " + ticketsShell);
                        calculatePercentageShell(n1 * n2, ticketsShell);
                        System.out.println("Current income: $" + profitShell);
                        calculateProfit(n1, n2);
                        break;
                    }
                    case 0: {
                        exit = true;
                        break;
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    private static void fillWithS(String[][] places) {
        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[0].length; j++) {
                places[i][j] = "S";
            }
        }
    }

    private static void printPlaces(String[][] places) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 0; i < places[0].length; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < places.length; i++) {
            System.out.print(i + 1 + " ");
            Arrays.stream(places[i]).forEach(place -> System.out.print(place + " "));
            System.out.print("\n");
        }
    }

    private static void calculateProfit(int n1, int n2) {
        int totalProfit = 0;
        if (n1 * n2 < 60) {
            int TICKET_COST = 0;
            TICKET_COST = 10;
            totalProfit = ((n1 * n2) * TICKET_COST);
        } else {
            int ticketCostFirstHalf = 10;
            int ticketCostSecondHalf = 8;
            int profitFirstHalf = 0;
            int profitSecondHalf = 0;
            int firstHalf = n1 / 2;
            int secondHalf = 0;
            if (n1 % 2 == 0) {
                secondHalf = n1 / 2;
            } else {
                secondHalf = n1 / 2 + n1 % 2;
            }
            profitFirstHalf = (firstHalf * n2) * ticketCostFirstHalf;
            profitSecondHalf = (secondHalf * n2) * ticketCostSecondHalf;
            totalProfit = profitFirstHalf + profitSecondHalf;
        }
        System.out.println("Total income: $"+totalProfit);
        //System.out.println("$" + totalProfit);
    }

    private static int ticketPrice(int row, int seat, String[][] places) {
        int ticketPrice = 10;
        int rowplace = row + 1;
        if (places.length * places[0].length > 60) {
            if (rowplace > places.length / 2) {
                ticketPrice = 8;
            }
        }
        System.out.println("Ticket price: " + "$" + ticketPrice);
        return ticketPrice;
    }

    private static int buyingTicket(Scanner scanner, String[][] places) {
        boolean buyComplete = false;
        int ticketPrice = 0;
        while (!buyComplete) {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt() - 1;
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt() - 1;
            if (row < places.length  && seat < places.length ) {
                if (places[row][seat].equals("S")) {
                    ticketPrice = ticketPrice(row, seat, places);
                    places[row][seat] = "B";
                    printPlaces(places);
                    buyComplete = true;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            } else {
                System.out.println("Wrong input!");
            }
        }
        return ticketPrice;
    }

    private static void calculatePercentageShell(int seats, double ticketsShell) {
        int scale = 2;
        double percentage = (ticketsShell / seats) * 100;
        System.out.println("Percentage: " + BigDecimal.valueOf(percentage).setScale(scale, BigDecimal.ROUND_HALF_UP) + "%");
    }
}
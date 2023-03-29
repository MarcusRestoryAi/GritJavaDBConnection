import java.sql.*;
import java.util.Scanner;

public class Main {
    //Inloggningsuppgifter till DB
    static String DBurl = "jdbc:mysql://localhost:3306/gritjavaweb?useTimezone=true&serverTimezone=GMT";
    //static String DBurl = "jdbc:mysql://localhost:3306/gritjavaweb";
    static String user = "root";
    static String pass = "SokrateS13";

    static Scanner scan;

    public static void main(String[] args) throws SQLException {
        System.out.println("Applikation startad");
        scan = new Scanner(System.in);

        while (true) {
            //Skriver ut meny för användare
            System.out.println("Gör ditt menyval");
            System.out.println("----------------");
            System.out.println("1. Mata in data till DB");
            System.out.println("2. Hämta data från DB");
            System.out.println("3. Avsluta");

            //Användaren gör ett val
            System.out.print("Gör ditt val: ");
            int menyVal = Integer.parseInt(scan.nextLine());

            //SwitchCase
            switch (menyVal) {
                case 1:
                    writeDataToDB();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }

            if (menyVal == 3) break;
        }
    }

    static void writeDataToDB() throws SQLException {
        //Initiera objekt
        Connection conn = null;
        PreparedStatement ps = null;

        //Be användaren mata in info
        System.out.print("Skriv in bookens titel: ");
        String bookTitle = scan.nextLine();
        System.out.print("Skriv in bookens författare: ");
        String bookAuthor = scan.nextLine();
        System.out.print("Skriv in bookens pris: ");
        int bookPrice = Integer.parseInt(scan.nextLine());

        //Establera koppling till DB
        conn = DriverManager.getConnection(DBurl, user, pass);

        //Bygga upp vår SQL Prepared Statement
        ps = conn.prepareStatement("INSERT INTO books(books_title, books_author, books_price) VALUES (?, ?, ?)");

        //Använd SetString för att byta ut ? placeholder
        ps.setString(1, bookTitle);
        ps.setString(2, bookAuthor);
        ps.setInt(3, bookPrice);

        //ExecuteUpdate för att skicka SQL commando till Databas
        ps.executeUpdate();

        //Stänga kopplingen
        conn.close();

        System.out.println("Insert Successful. Press enter to continue...");
        scan.nextLine();
    }
}
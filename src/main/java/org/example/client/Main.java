package org.example.client;
import org.example.client.network.UserClient;
import java.util.Scanner;
import static org.example.client.utils.AppValidator.isEmailValid;

public class Main {

    static Scanner scanner;
    static String userName;
    static String userEmail;

    public static void main(String[] args) throws Exception {
        start();
    }
    private static void start() throws Exception {
        scanner = new Scanner(System.in);

        System.out.print("To log in, enter your username: ");
        userName = scanner.nextLine();
        System.out.print("Input email in format example@gmail.com: ");
        userEmail = scanner.nextLine();
        if (!isEmailValid(userEmail)) {
            UserClient client = new UserClient();
            client.start(userEmail, userName);
        } else {
            System.out.print("Wrong email input.");
            System.exit(0);
        }
    }
}

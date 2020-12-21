package info.wonlee.assessment.accela;

import java.util.Scanner;

/**
 * User: wonlee
 * Date: 21/12/2020
 */

public class ConsoleInput {
    public static Scanner scanner = new Scanner(System.in);

    public static String waitInput() {
        return scanner.nextLine();
    }
}

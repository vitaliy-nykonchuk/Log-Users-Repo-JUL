package views;

import java.util.Scanner;

public class UserDeleteView {

    // Получение данных.
    public String[] getData() {
        Scanner scanner = new Scanner(System.in);
        String title = "Enter user's ID: ";
        System.out.print(title);
        String id = scanner.nextLine();
        return new String[] {id};
    }

    // Вывод результата.
    public void getOutput(String output) {
        System.out.println(output);
    }
}

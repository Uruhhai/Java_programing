package lab3;

import lab5.BookFileStorage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryModel model = new LibraryModel();
        BookFileStorage storage = new BookFileStorage();
        LibraryView view = new LibraryView(scanner);
        LibraryController controller = new LibraryController(model, view, storage);

        controller.run();
    }
}
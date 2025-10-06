package nik.legault;

public class Main {
    public static void main(String[] args) {
        AddressBook book = new AddressBook();
        System.out.println("Addreess book created");

        book.addBuddy("Jared", "9999", "985");
        System.out.println(book.toString());

        book.addBuddy("Nolan", "8888", "cincy");
        System.out.println(book.toString());

    }
}
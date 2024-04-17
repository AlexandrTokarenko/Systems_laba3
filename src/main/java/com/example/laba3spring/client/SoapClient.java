package com.example.laba3spring.client;

import com.example.laba3spring.server.DataService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.Scanner;

public class SoapClient {
    public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(DataService.class);
        factory.setAddress("http://localhost:8080/Service/ServiceData");
        DataService client = (DataService) factory.create();

        int item;
        do {
            item = menu();
            switch (item) {
                case 1:
                    System.out.println("Введіть ID книги:");
                    Scanner scanner = new Scanner(System.in);
                    int bookId = scanner.nextInt();
                    String bookInfo = client.getBookInfo(bookId);
                    System.out.println("Інформація про книгу: " + bookInfo);
                    System.out.println();
                    break;
                case 2:
                    String allBooks = client.getAllBooks();
                    System.out.println("Список усіх книг:\n" + allBooks);
                    System.out.println();
                    break;
            }
        } while (item != 0);

    }

    private static int menu() {

        System.out.println("Введіть номер операції:");
        System.out.println("1. Отримати інформацію про книгу за ID");
        System.out.println("2. Отримати список усіх книг");
        System.out.println("0. Вихід");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
}
package com.bank;

import java.sql.Connection;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            Scanner sc = new Scanner(System.in);

            System.out.print("From Account ID: ");
            int fromId = sc.nextInt();

            System.out.print("To Account ID: ");
            int toId = sc.nextInt();

            System.out.print("Amount: ");
            double amount = sc.nextDouble();

            TransactionService service = new TransactionService();

            if (service.transfer(con, fromId, toId, amount)) {
                con.commit();
                System.out.println(" Transaction Successful");
            } else {
                con.rollback();
                System.out.println(" Transaction Failed");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

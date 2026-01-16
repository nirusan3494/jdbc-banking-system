package com.bank;

import java.sql.Connection;

public class TransactionService {

    private AccountDAO dao = new AccountDAO();

    public boolean transfer(Connection con,
                            int fromId,
                            int toId,
                            double amount) throws Exception {

        if (!dao.hasSufficientBalance(con, fromId, amount)) {
            return false;
        }

        int debit = dao.debit(con, fromId, amount);
        int credit = dao.credit(con, toId, amount);

        return debit == 1 && credit == 1;
    }
}

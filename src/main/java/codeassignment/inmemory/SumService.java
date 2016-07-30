package codeassignment.inmemory;

import codeassignment.transactions.Transaction;

/**
 * Created by dela on 30.07.2016.
 */
interface SumService {
    void save(Transaction transaction);

    double getSum(long id);
}

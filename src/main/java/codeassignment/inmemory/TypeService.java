package codeassignment.inmemory;

import codeassignment.transactions.Transaction;

import java.util.List;

/**
 * Created by dela on 30.07.2016.
 */
interface TypeService {
    void save(Transaction transaction);

    List<Long> getIdsForType(String type);
}

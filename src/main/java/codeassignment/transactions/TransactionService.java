package codeassignment.transactions;

import codeassignment.rest.Status;

import java.util.List;

/**
 * Created by dela on 29.07.2016.
 */
public interface TransactionService {
    Status save(Transaction transaction);

    Transaction get(long id);

    List<Long> getIdsForType(String type);


}

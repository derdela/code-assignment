package transactions;

import rest.Status;

/**
 * Created by dela on 29.07.2016.
 */
public interface TransactionService {
    Status save(Transaction transaction);
}

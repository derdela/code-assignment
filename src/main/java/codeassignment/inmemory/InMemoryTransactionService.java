package codeassignment.inmemory;

import codeassignment.transactions.Status;
import codeassignment.transactions.Transaction;
import codeassignment.transactions.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dela on 30.07.2016.
 */
@Component
public class InMemoryTransactionService implements TransactionService {
    private final HashMap<Long, Transaction> transactions = new HashMap<>();

    @Autowired
    private TypeService typeService;

    @Autowired
    private SumService sumService;

    @Override
    public Status save(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
        typeService.save(transaction);
        sumService.save(transaction);
        return new Status("ok");
    }

    public Transaction get(long id) {
        return transactions.get(id);
    }

    @Override
    public List<Long> getIdsForType(String type) {
        return typeService.getIdsForType(type);
    }

    @Override
    public double getSum(long id) {
        return sumService.getSum(id);
    }
}

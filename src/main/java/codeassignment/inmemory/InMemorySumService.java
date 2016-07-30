package codeassignment.inmemory;

import codeassignment.transactions.Transaction;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dela on 30.07.2016.
 */
@Component
class InMemorySumService implements SumService {

    private Map<Long, Double> sums;

    InMemorySumService() {
        this.sums = new HashMap<>();
    }

    @Override
    public void save(Transaction transaction) {
        saveSum(transaction.getId(), transaction.getAmount());
        updateParentSum(transaction);
    }

    private void updateParentSum(Transaction transaction) {
        if (transaction.getParentId().isPresent()) {
            saveSum(transaction.getParentId().getAsLong(), transaction.getAmount());
        }
    }

    private void saveSum(long asLong, double amount) {
        double sum = sums.getOrDefault(asLong, .0);
        sum += amount;
        sums.put(asLong, sum);
    }

    @Override
    public double getSum(long id) {
        return sums.get(id);
    }
}

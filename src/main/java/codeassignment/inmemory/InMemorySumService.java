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

    private final Map<Long, Double> sums;

    InMemorySumService() {
        this.sums = new HashMap<>();
    }

    @Override
    public void save(Transaction transaction) {
        double sum = sums.getOrDefault(transaction.getId(), .0);
        sum += transaction.getAmount();
        sums.put(transaction.getId(), sum);
        saveParent(transaction);
    }

    private void saveParent(Transaction transaction) {
        if (transaction.getParentId().isPresent()) {
            double sum = sums.getOrDefault(transaction.getParentId().getAsLong(), .0);
            sum += transaction.getAmount();
            sums.put(transaction.getParentId().getAsLong(), sum);
        }
    }

    @Override
    public double getSum(long id) {
        return sums.get(id);
    }
}

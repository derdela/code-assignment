package codeassignment.inmemory;

import codeassignment.transactions.Transaction;
import helper.TransactionBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dela on 30.07.2016.
 */
public class InMemorySumServiceTest {

    private static final double DELTA = 1e-15;

    private SumService sumService;

    @Before
    public void initSumservice() {
        sumService = new InMemorySumService();
    }

    @Test
    public void
    should_save_and_get_sum_for_transactionID() {
        // Given
        Transaction transaction = aTransaction(1).withAmount(5000).build();

        // When
        sumService.save(transaction);
        double result = sumService.getSum(transaction.getId());

        // Then
        assertEquals(transaction.getAmount(), result, DELTA);
    }

    @Test
    public void
    should_save_and_get_sum_for_transctionID_including_child() {
        // Given
        Transaction transaction1 = aTransaction(1).withId(1).withAmount(5000).build();
        Transaction transaction2 = aTransaction(2).withId(2).withAmount(10000).withParentId(transaction1.getId()).build();

        // When
        sumService.save(transaction1);
        sumService.save(transaction2);
        double result1 = sumService.getSum(transaction1.getId());
        double result2 = sumService.getSum(transaction2.getId());

        // Then
        assertEquals(transaction1.getAmount() + transaction2.getAmount(), result1, DELTA);
        assertEquals(transaction2.getAmount(), result2, DELTA);
    }


    private TransactionBuilder aTransaction(int seed) {
        return TransactionBuilder.aTransaction(seed);
    }

}
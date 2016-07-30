package codeassignment.inmemory;

import codeassignment.transactions.Status;
import codeassignment.transactions.Transaction;
import helper.TransactionBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dela on 30.07.2016.
 */
public class InMemoryTransactionServiceTest {

    private static final double DELTA = 1e-15;

    @Mock
    private TypeService typeService;

    @Mock
    private SumService sumService;

    @InjectMocks
    private InMemoryTransactionService transactionService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void
    should_save_and_return_a_transaction() {
        // Given
        Transaction transaction = aTransaction(1).withId(1).build();

        // When
        Status status = transactionService.save(transaction);

        // Then
        verify(typeService).save(transaction);
        verify(sumService).save(transaction);
        assertEquals(transaction, transactionService.get(1));
        assertEquals(new Status("ok"), status);
    }

    @Test
    public void
    should_return_transactionIDs_with_certain_type() {
        // Given
        Transaction cars1 = aTransaction(1).withId(1).withType("cars").build();
        Transaction cars2 = aTransaction(2).withId(2).withType("cars").build();
        List<Long> transactionsList = new ArrayList<>();
        transactionsList.add(cars1.getId());
        transactionsList.add(cars2.getId());
        when(typeService.getIdsForType("cars")).thenReturn(transactionsList);

        // When
        List<Long> result = transactionService.getIdsForType("cars");

        // Then
        assertEquals(transactionsList, result);
    }

    @Test
    public void
    should_return_sum_for_a_transactionID() {
        // Given
        Transaction transaction = aTransaction(1).withAmount(5000).build();
        when(sumService.getSum(transaction.getId())).thenReturn(transaction.getAmount());

        // When
        double result = transactionService.getSum(transaction.getId());

        // Then
        assertEquals(transaction.getAmount(), result, DELTA);
    }


    private TransactionBuilder aTransaction(int seed) {
        return TransactionBuilder.aTransaction(seed);
    }
}
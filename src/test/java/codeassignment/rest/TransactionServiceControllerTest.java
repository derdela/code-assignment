package codeassignment.rest;

import codeassignment.transactions.Transaction;
import codeassignment.transactions.TransactionService;
import helper.TransactionBuilder;
import helper.TransactionPayloadBuilder;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by dela on 29.07.2016.
 */
public class TransactionServiceControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionServiceController transactionServiceController = new TransactionServiceController();

    private static final double DELTA = 1e-15;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test public void
    should_return_status_ok_when_saving_transaction() {
        // Given
        when(transactionService.save(any())).thenReturn(new Status("ok"));
        JSONObject transaction = aTransactionPayload(1).build();

        // When
        Status result = transactionServiceController.update(10, transaction.toString());

        // Then
        assertEquals("ok", result.getStatus());
    }

    @Test public void
    should_save_transaction() {
        // Given
        JSONObject transaction = aTransactionPayload(1).build();
        when(transactionService.save(any())).thenReturn(new Status("ok"));

        // When
        transactionServiceController.update(10, transaction.toString());

        // Then
        verify(transactionService).save(new Transaction(10, transaction.getDouble("amount"), transaction.getString("type"), transaction.getLong("parent_id")));

    }

    @Test public void
    should_get_transaction() {
        // Given
        Transaction transaction = aTransaction(1).withId(10).build();
        when(transactionService.get(10)).thenReturn(transaction);

        // When
        Transaction result = transactionServiceController.get(10);

        // Then
        assertEquals(transaction, result);

    }

    @Test
    public void
    should_get_all_transactionIDs_for_type() {
        // Given
        Transaction cars1 = aTransaction(1).withId(1).build();
        Transaction cars2 = aTransaction(2).withId(2).build();
        List<Long> transactionsList = new ArrayList<>();
        transactionsList.add(cars1.getId());
        transactionsList.add(cars2.getId());
        when(transactionService.getIdsForType(any())).thenReturn(transactionsList);

        // When
        List<Long> result = transactionServiceController.getIdsForType("cars");

        // Then
        assertEquals(transactionsList, result);

    }

    @Test
    public void
    should_return_sum_of_transaction() {
        // Given
        Transaction transaction = aTransaction(1).withAmount(5000).build();
        when(transactionService.getSum(transaction.getId())).thenReturn(transaction.getAmount());

        // When
        Sum result = transactionServiceController.getSum(transaction.getId());

        // Then
        assertEquals(transaction.getAmount(), result.getSum(), DELTA);
    }

    private TransactionPayloadBuilder aTransactionPayload(int seed) {
        return TransactionPayloadBuilder.aTransaction(seed);
    }

    private TransactionBuilder aTransaction(int seed) {
        return TransactionBuilder.aTransaction(seed);
    }
}
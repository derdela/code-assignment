package rest;

import helper.TransactionBuilder;
import helper.TransactionPayloadBuilder;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import transactions.Transaction;
import transactions.TransactionService;

/**
 * Created by dela on 29.07.2016.
 */
public class TransactionServiceControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionServiceController transactionServiceController = new TransactionServiceController();

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
        verify(transactionService).save(new Transaction(10, transaction.getDouble("amount"), transaction.getString("type")));
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

    private TransactionPayloadBuilder aTransactionPayload(int seed) {
        return TransactionPayloadBuilder.aTransaction(seed);
    }

    private TransactionBuilder aTransaction(int seed) {
        return TransactionBuilder.aTransaction(seed);
    }
}
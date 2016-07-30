package rest;

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
    should_return_status_ok() {
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

    private TransactionPayloadBuilder aTransactionPayload(int seed) {
        return TransactionPayloadBuilder.aTransaction(seed);
    }
}
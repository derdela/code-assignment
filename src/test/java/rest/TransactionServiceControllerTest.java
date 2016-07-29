package rest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dela on 29.07.2016.
 */
public class TransactionServiceControllerTest {

    private TransactionServiceController transactionServiceController = new TransactionServiceController();

    @Test public void
    should_return_status_ok() {
        Status result = transactionServiceController.update(10);
        assertEquals("ok", result.getStatus());
    }

}
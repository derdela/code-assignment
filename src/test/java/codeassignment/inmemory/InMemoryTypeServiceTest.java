package codeassignment.inmemory;

import codeassignment.transactions.Transaction;
import helper.TransactionBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dela on 30.07.2016.
 */
public class InMemoryTypeServiceTest {

    private InMemoryTypeService typeService;

    @Before
    public void initTypeService() {
        typeService = new InMemoryTypeService();
    }

    @Test
    public void
    should_save_and_return_transactionIDs_for_types() {
        // Given
        Transaction cars1 = aTransaction(1).withId(1).withType("cars").build();
        Transaction cars2 = aTransaction(2).withId(2).withType("cars").build();
        Transaction champagne = aTransaction(3).withId(3).withType("champange").build();

        // When
        typeService.save(cars1);
        typeService.save(cars2);
        typeService.save(champagne);
        List<Long> result = typeService.getIdsForType("cars");

        // Then
        List<Long> expected = new ArrayList<>();
        expected.add(cars1.getId());
        expected.add(cars2.getId());
        assertEquals(expected, result);
    }

    private TransactionBuilder aTransaction(int seed) {
        return TransactionBuilder.aTransaction(seed);
    }
}
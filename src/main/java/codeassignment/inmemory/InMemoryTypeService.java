package codeassignment.inmemory;

import codeassignment.transactions.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dela on 30.07.2016.
 */
@Component
class InMemoryTypeService implements TypeService {

    private final Map<String, List<Long>> types;

    InMemoryTypeService() {
        types = new HashMap<>();
    }

    @Override
    public void save(Transaction transaction) {
        List<Long> ids = types.getOrDefault(transaction.getType(), new ArrayList<>());
        ids.add(transaction.getId());
        types.put(transaction.getType(), ids);
    }

    @Override
    public List<Long> getIdsForType(String type) {
        return types.get(type);
    }
}

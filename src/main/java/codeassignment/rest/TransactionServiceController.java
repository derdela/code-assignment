package codeassignment.rest;

import codeassignment.transactions.Transaction;
import codeassignment.transactions.TransactionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by dela on 29.07.2016.
 */
@RestController
class TransactionServiceController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("transactionservice/transaction/{id}")
    public Transaction get(@PathVariable("id") long id) {
        return transactionService.get(id);
    }

    @RequestMapping(value = "transactionservice/transaction/{id}", method = PUT)
    public Status update(@PathVariable("id") long id, @RequestBody String body) {
        JSONObject json = new JSONObject(body);
        return transactionService.save(new Transaction(id, json.getDouble("amount"), json.getString("type")));
    }

    @RequestMapping("transactionservice/types/{type}")
    @ResponseBody
    public List<Long> getIdsForType(@PathVariable("type") String type) {
        return transactionService.getIdsForType(type);
    }
}

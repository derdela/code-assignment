package rest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import transactions.Transaction;
import transactions.TransactionService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by dela on 29.07.2016.
 */
@RestController
class TransactionServiceController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "transactionservice/transaction/{id}", method = PUT)
    public Status update(@PathVariable(value = "id") long id, @RequestBody String body) {
        JSONObject json = new JSONObject(body);
        return transactionService.save(new Transaction(id,json.getDouble("amount"), json.getString("type")));
    }
}

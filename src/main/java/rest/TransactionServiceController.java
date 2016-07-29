package rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by dela on 29.07.2016.
 */
@RestController
class TransactionServiceController {
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/transactionservice/transaction/{id}", method = PUT)
    Status update(@PathVariable(value = "id") long id) {
        return new Status("ok");
    }
}

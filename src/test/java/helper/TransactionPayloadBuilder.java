package helper;

import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by dela on 30.07.2016.
 */
public class TransactionPayloadBuilder {

    private int seed;
    private String type;
    private double amount;

    private TransactionPayloadBuilder(int seed) {
        this.seed = seed;
        defaultValues();
    }

    private void defaultValues() {
        this.amount = seed;
        this.type = "cars";
    }

    public static TransactionPayloadBuilder aTransaction(int seed) {
        return new TransactionPayloadBuilder(seed);
    }

    public JSONObject build() {
        JSONObject json = new JSONObject();
        json.put("amount", this.amount);
        json.put("type", this.type);

        return json;
    }
}

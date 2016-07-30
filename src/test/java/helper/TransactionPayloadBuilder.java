package helper;

import org.json.JSONObject;

/**
 * Created by dela on 30.07.2016.
 */
public class TransactionPayloadBuilder {

    private int seed;
    private String type;
    private double amount;
    private long parentId;

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

    public TransactionPayloadBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public TransactionPayloadBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public TransactionPayloadBuilder withParentId(long parentId) {
        this.parentId = parentId;
        return this;
    }
}

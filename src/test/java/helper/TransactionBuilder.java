package helper;

import codeassignment.transactions.Transaction;

/**
 * Created by dela on 30.07.2016.
 */
public class TransactionBuilder {
    private int seed;
    private long id;
    private String type;
    private double amount;

    private TransactionBuilder(int seed) {
        this.seed = seed;
        defaultValues();
    }

    private void defaultValues() {
        this.amount = seed;
        this.id = 31 * seed;
        this.type = "cars";
    }

    public static TransactionBuilder aTransaction(int seed) {
        return new TransactionBuilder(seed);
    }

    public Transaction build() {
        return new Transaction(id, amount, type);
    }

    public TransactionBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder withType(String type) {
        this.type = type;
        return this;
    }
}

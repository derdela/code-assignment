package helper;

import codeassignment.transactions.Transaction;

import java.util.OptionalLong;

/**
 * Created by dela on 30.07.2016.
 */
public class TransactionBuilder {
    private final int seed;
    private long id;
    private String type;
    private double amount;
    private OptionalLong parentId;

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
        return parentId == null ? new Transaction(id, amount, type) : new Transaction(id, amount, type, parentId.getAsLong());
    }

    public TransactionBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public TransactionBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder withParentId(long parentId) {
        this.parentId = OptionalLong.of(parentId);
        return this;
    }
}

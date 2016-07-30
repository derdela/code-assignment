package codeassignment.transactions;

import java.util.OptionalLong;

/**
 * Created by dela on 29.07.2016.
 */
public class Transaction {
    private final long id;
    private final double amount;
    private final String type;
    private final OptionalLong parentId;

    public Transaction(long id, double amount, String type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.parentId = OptionalLong.empty();
    }

    public Transaction(long id, double amount, String type, long parentId) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.parentId = OptionalLong.of(parentId);
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public OptionalLong getParentId() {
        return parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return getId() == that.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}

package codeassignment.transactions;

/**
 * Created by dela on 29.07.2016.
 */
public class Status {
    private final String status;

    public Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status1 = (Status) o;

        return getStatus() != null ? getStatus().equals(status1.getStatus()) : status1.getStatus() == null;

    }

    @Override
    public int hashCode() {
        return getStatus() != null ? getStatus().hashCode() : 0;
    }
}

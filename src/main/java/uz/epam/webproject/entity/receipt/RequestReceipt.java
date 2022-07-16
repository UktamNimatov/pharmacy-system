package uz.epam.webproject.entity.receipt;

import uz.epam.webproject.entity.AbstractEntity;

import java.io.Serial;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.StringJoiner;

public class RequestReceipt implements AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private String illnessDescription;
    private long userId;
    private RequestReceiptStatus status;
    private Timestamp orderedTime;
    private Timestamp respondedTime;

    public RequestReceipt() {
    }

    public RequestReceipt(String illnessDescription, long userId, RequestReceiptStatus status, Timestamp orderedTime) {
        this.illnessDescription = illnessDescription;
        this.userId = userId;
        this.status = status;
        this.orderedTime = orderedTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIllnessDescription() {
        return illnessDescription;
    }

    public void setIllnessDescription(String illnessDescription) {
        this.illnessDescription = illnessDescription;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public RequestReceiptStatus getStatus() {
        return status;
    }

    public void setStatus(RequestReceiptStatus status) {
        this.status = status;
    }

    public Timestamp getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(Timestamp orderedTime) {
        this.orderedTime = orderedTime;
    }

    public Timestamp getRespondedTime() {
        return respondedTime;
    }

    public void setRespondedTime(Timestamp respondedTime) {
        this.respondedTime = respondedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestReceipt that = (RequestReceipt) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(illnessDescription, that.illnessDescription) &&
                status == that.status &&
                Objects.equals(orderedTime, that.orderedTime) &&
                Objects.equals(respondedTime, that.respondedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, illnessDescription, userId, status, orderedTime, respondedTime);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RequestReceipt.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("illnessDescription='" + illnessDescription + "'")
                .add("userId=" + userId)
                .add("status=" + status)
                .add("orderedTime=" + orderedTime)
                .add("respondedTime=" + respondedTime)
                .toString();
    }
}

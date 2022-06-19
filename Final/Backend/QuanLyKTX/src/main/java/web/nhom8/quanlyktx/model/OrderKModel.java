package web.nhom8.quanlyktx.model;

import java.sql.Date;

public class OrderKModel {
    private int OrderId;
    private int TypeId;
    private int StudentId;
    private String Topic;
    private String Content;
    private Date SentDate;
    private int State;

    public OrderKModel(int orderId, int typeId, int studentId, String topic, String content, Date sentDate, int state) {
        OrderId = orderId;
        TypeId = typeId;
        StudentId = studentId;
        Topic = topic;
        Content = content;
        SentDate = sentDate;
        State = state;
    }

    public int getOrderId() {
        return OrderId;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getSentDate() {
        return SentDate;
    }

    public void setSentDate(Date sentDate) {
        SentDate = sentDate;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    @Override
    public String toString() {
        return "OrderK{" +
                "OrderId=" + OrderId +
                ", TypeId=" + TypeId +
                ", StudentId=" + StudentId +
                ", Topic='" + Topic + '\'' +
                ", Content='" + Content + '\'' +
                ", SentDate=" + SentDate +
                ", State=" + State +
                '}';
    }
}

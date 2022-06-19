package web.nhom8.quanlyktx.model;

public class OrderTypeModel {
    private int TypeId;
    private String TypeName;

    public OrderTypeModel(int typeId, String typeName) {
        TypeId = typeId;
        TypeName = typeName;
    }

    public int getTypeId() {
        return TypeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    @Override
    public String toString() {
        return "OrderType{" +
                "TypeId=" + TypeId +
                ", TypeName='" + TypeName + '\'' +
                '}';
    }
}

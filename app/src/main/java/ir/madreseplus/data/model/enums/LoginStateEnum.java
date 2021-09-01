package ir.madreseplus.data.model.enums;

public enum LoginStateEnum {
    NOTREGISTERED(103), REGISTERED(102);

    private int id;

    LoginStateEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static LoginStateEnum fromValue(int id) {
        switch (id) {
            case 103:
                return NOTREGISTERED;
            case 102:
                return REGISTERED;
            default:
                return null;
        }
    }
}

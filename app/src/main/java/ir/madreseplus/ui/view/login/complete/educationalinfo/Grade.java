package ir.madreseplus.ui.view.login.complete.educationalinfo;

/**
 * root created on 8/17/20
 */

public class Grade {
    private int id;
    private String name;

    public Grade(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package ir.madreseplus.data.model.enums;

public enum ArtCategory {
    ALL(""), EXAM("آزمون"), RESOURCES("منابع"), SCHEDULING("برنامه ریزی"), TEST("تست");


    private String catName;

    ArtCategory(String catName) {
        this.catName = catName;
    }

    public String getCatName() {
        return catName;
    }
}

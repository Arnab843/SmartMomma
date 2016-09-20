package com.mindmines.smartmomma.Dataclass;

/**
 * Created by pc on 9/15/2016.
 */

public class Category {
    public static final String TABLE="category";

    public static final String CODE="ccode";
    public static final String NAME="cname";
    public static final String DES="cdescription";

    private String code,name,description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

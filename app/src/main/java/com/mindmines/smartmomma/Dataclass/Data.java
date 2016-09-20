package com.mindmines.smartmomma.Dataclass;

/**
 * Created by pc on 9/15/2016.
 */

public class Data {

    public static final String TABLE="data";


    public static final String ID="id";
    public static final String NAME="name";
    public static final String RATING_ID="ratingid";
    public static final String CATEGORY_ID="catetoryid";
    public static final String SHORTDES="shortdes";
    public static final String ADDITIONAL="additional";
    public static final String REASONS="reasons";
    public static final String CATEGORY_NAME="categoryname";
    public static final String CATEGORY_DES="categorydes";
    public static final String RATING_NAME="ratingname";
    public static final String RATING_LEVEL="ratinglevel";

    private String name,ratingid,categoryid,shortdes,additional,reasons,categoryname,categorydes,ratingname,ratinglevel;

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getCategorydes() {
        return categorydes;
    }

    public void setCategorydes(String categorydes) {
        this.categorydes = categorydes;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRatingid() {
        return ratingid;
    }

    public void setRatingid(String ratingid) {
        this.ratingid = ratingid;
    }

    public String getRatinglevel() {
        return ratinglevel;
    }

    public void setRatinglevel(String ratinglevel) {
        this.ratinglevel = ratinglevel;
    }

    public String getRatingname() {
        return ratingname;
    }

    public void setRatingname(String ratingname) {
        this.ratingname = ratingname;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getShortdes() {
        return shortdes;
    }

    public void setShortdes(String shortdes) {
        this.shortdes = shortdes;
    }
}

package com.mindmines.smartmomma.Dataclass;

/**
 * Created by pc on 9/19/2016.
 */

public class Cate {

    int image;String name;

    public Cate(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public Cate() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.stackroute.keepnote.model;

import java.util.Date;

public class Category {
    private String CategoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryCreatedBy;
    private Date categoryCreationDate = new Date();

    /*
     * This class should have five fields
     * (categoryId,categoryName,categoryDescription,
     * categoryCreatedBy,categoryCreationDate). This class should also contain the
     * getters and setters for the fields along with the toString method. The value
     * of categoryCreationDate should not be accepted from the user but should be
     * always initialized with the system date.
     */

    public Category() {
    }

    public Category(String categoryId, String categoryName, String categoryDescription, String categoryCreatedBy, Date categoryCreationDate) {
        CategoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryCreatedBy = categoryCreatedBy;
        this.categoryCreationDate = categoryCreationDate;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryCreatedBy() {
        return categoryCreatedBy;
    }

    public void setCategoryCreatedBy(String categoryCreatedBy) {
        this.categoryCreatedBy = categoryCreatedBy;
    }

    public Date getCategoryCreationDate() {
        return categoryCreationDate;
    }

    public void setCategoryCreationDate(Date categoryCreationDate) {
        this.categoryCreationDate = categoryCreationDate;
    }

    @Override
    public String toString() {
        return "Category{" +
                "CategoryId='" + CategoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", categoryCreatedBy='" + categoryCreatedBy + '\'' +
                ", categoryCreationDate=" + categoryCreationDate +
                '}';
    }
}

package org.example;

public class Experience {
    private String companyName;
    private String position;
    private String dateRange;
    private String description;

    public Experience(String companyName, String position, String dateRange, String description) {
        this.companyName = companyName;
        this.position = position;
        this.dateRange = dateRange;
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPosition() {
        return position;
    }

    public String getDateRange() {
        return dateRange;
    }

    public String getDescription() {
        return description;
    }
}
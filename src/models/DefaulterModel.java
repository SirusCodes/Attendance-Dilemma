package models;

public class DefaulterModel {

    private String name;
    private double percentage;

    public DefaulterModel(String name, double percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "DefaulterModel{" +
                "name='" + name + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}

package attestation;

import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private String name;
    private double amount;
    private ArrayList<Product> productPackage = new ArrayList<>();


    public Person(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ArrayList<Product> getProductPackage() {
        return productPackage;
    }

    public void setProductPackage(Product productPackage) {
        this.productPackage.add(productPackage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(amount, person.amount) == 0 && Objects.equals(name, person.name) && Objects.equals(productPackage, person.productPackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, productPackage);
    }

    @Override
    public String toString() {
        return "attestation.Person{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", productPackage=" + productPackage +
                '}';
    }
}


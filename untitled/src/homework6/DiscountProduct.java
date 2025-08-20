package homework6;

import java.time.LocalDateTime;
import java.util.Objects;

public class DiscountProduct extends Product {

    private LocalDateTime discountValidityPeriod;
    private double discount;

    public DiscountProduct(String name, double price, LocalDateTime discountValidityPeriod, double discount) {
        super(name, price);
        this.discountValidityPeriod = discountValidityPeriod;
        this.discount = discount;
    }

    public LocalDateTime getDiscountValidityPeriod() {
        return discountValidityPeriod;
    }

    public void setDiscountValidityPeriod(LocalDateTime discountValidityPeriod) {
        this.discountValidityPeriod = discountValidityPeriod;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        LocalDateTime currentTime = LocalDateTime.now();

        if (currentTime.isAfter(discountValidityPeriod)) {
            discount = 0;
            return super.price;
        } else {
            return super.price * (1 - discount / 100);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountProduct that = (DiscountProduct) o;
        return Double.compare(discount, that.discount) == 0 && Objects.equals(discountValidityPeriod, that.discountValidityPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discountValidityPeriod, discount);
    }

}


public class TV {

    private String model;
    private String company;
    private double screenDiagonal;
    private double weight;
    private double price;
    private boolean isOn = false;

    public TV(String model, String company, double screenDiagonal, double weight, double price) {
        this.model = model;
        this.company = company;
        this.screenDiagonal = screenDiagonal;
        this.weight = weight;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getScreenDiagonal() {
        return screenDiagonal;
    }

    public void setScreenDiagonal(double screenDiagonal) {
        this.screenDiagonal = screenDiagonal;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void turnOnOffTheTV() {
        if(isOn){
            isOn = false;
            System.out.println("Выключаем телевизор " + company + " " + model + ". Телевизор выключен");
        }else {
            isOn = true;
          System.out.println("Включаем телевизор " + company + " " + model + ". Телевизор включен");
        }
    }

    @Override
    public String toString() {
        return "TV{" +
                "company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", screenDiagonal=" + screenDiagonal +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }
}

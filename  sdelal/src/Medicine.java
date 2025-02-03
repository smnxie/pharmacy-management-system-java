public class Medicine {
    private int id;
    private String name;
    private String manufacturer;
    private String batchNumber;
    private double price;

    public Medicine(int id, String name, String manufacturer, String batchNumber, double price) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.batchNumber = batchNumber;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getManufacturer() { return manufacturer; }
    public String getBatchNumber() { return batchNumber; }
    public double getPrice() { return price; }
}


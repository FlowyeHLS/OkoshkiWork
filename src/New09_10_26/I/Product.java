package New09_10_26.I;

public class Product
{
    private final String sku;
    private final String name;
    private final int price;

    public Product(String sku, String name, int price)
    {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku()
    {
        return sku;
    }

    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }
}
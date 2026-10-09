package New09_10_26.I;

import java.util.List;
import java.util.Random;

public class Customer implements Runnable
{
    private final String name;
    private final Warehouse warehouse;
    private final List<Product> products;
    private final Random random = new Random();

    private int successfulOrders;
    private int failedOrders;
    private long revenue;

    public Customer(String name, Warehouse warehouse, List<Product> products)
    {
        this.name = name;
        this.warehouse = warehouse;
        this.products = products;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 50; i++)
        {
            Product product = products.get(random.nextInt(products.size()));
            int quantity = random.nextInt(5) + 1;

            if (warehouse.reserve(product.getSku(), quantity))
            {
                successfulOrders++;
                revenue += (long) product.getPrice() * quantity;
            }
            else
            {
                failedOrders++;
            }
        }
    }

    public String getName()
    {
        return name;
    }

    public int getSuccessfulOrders()
    {
        return successfulOrders;
    }

    public int getFailedOrders()
    {
        return failedOrders;
    }

    public long getRevenue()
    {
        return revenue;
    }
}
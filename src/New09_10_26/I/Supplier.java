package New09_10_26.I;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Supplier extends Thread
{
    private final Warehouse warehouse;
    private final List<String> skus;
    private final Random random = new Random();

    private volatile boolean open = true;
    private int totalSupplied;

    public Supplier(Warehouse warehouse)
    {
        this.warehouse = warehouse;

        Map<String, Integer> stock = warehouse.getStockSnapshot();
        this.skus = new ArrayList<>(stock.keySet());
    }

    @Override
    public void run()
    {
        while (open)
        {
            String sku = skus.get(random.nextInt(skus.size()));

            warehouse.restock(sku, 1);
            totalSupplied++;

            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                interrupt();
                break;
            }
        }
    }

    public void stopSupplying()
    {
        open = false;
    }

    public int getTotalSupplied()
    {
        return totalSupplied;
    }
}
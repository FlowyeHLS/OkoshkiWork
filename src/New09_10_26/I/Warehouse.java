package New09_10_26.I;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Warehouse
{
    private final Map<String, Integer> stock = new LinkedHashMap<>();
    private final Map<String, Product> productsBySku = new LinkedHashMap<>();
    private int totalSold;

    public Warehouse(List<Product> products)
    {
        for (Product product : products)
        {
            productsBySku.put(product.getSku(), product);
        }
    }

    public static Warehouse fromCsv(Path file, List<Product> products)
            throws IOException
    {
        Warehouse warehouse = new Warehouse(products);
        List<String> lines = Files.readAllLines(file);

        for (int i = 1; i < lines.size(); i++)
        {
            String[] parts = lines.get(i).split(";");

            String sku = parts[0];
            int quantity = Integer.parseInt(parts[2]);

            warehouse.stock.put(sku, quantity);
        }

        return warehouse;
    }

    public synchronized boolean reserve(String sku, int qty)
    {
        int available = stock.getOrDefault(sku, 0);

        if (available < qty)
        {
            return false;
        }

        stock.put(sku, available - qty);
        totalSold += qty;
        return true;
    }

    public synchronized void restock(String sku, int qty)
    {
        stock.put(sku, stock.getOrDefault(sku, 0) + qty);
    }

    public synchronized int getTotalSold()
    {
        return totalSold;
    }

    public synchronized Map<String, Integer> getStockSnapshot()
    {
        return new LinkedHashMap<>(stock);
    }

    public Product getProduct(String sku)
    {
        return productsBySku.get(sku);
    }
}
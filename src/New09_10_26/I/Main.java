package New09_10_26.I;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main
{
    private static final Path SOURCE_FOLDER = Path.of("src/New09_10_26/I");
    private static final Path STOCK_FILE = SOURCE_FOLDER.resolve("stock.csv");
    private static final Path REPORT_FILE = SOURCE_FOLDER.resolve("report.txt");
    private static final Random RANDOM = new Random();

    public static void main() throws IOException, InterruptedException
    {
        List<Product> products = List.of(
                new Product("A_1", "Keyboard", 3000),
                new Product("A_2", "Mouse", 10000),
                new Product("A_3", "HeadSet", 5000),
                new Product("A_4", "Monik", 18000),
                new Product("A_5", "MousePAd", 2200),
                new Product("A_6", "Mic", 5500),
                new Product("A_7", "Vebka", 3000)
        );

        Map<String, Integer> initialStock = new LinkedHashMap<>();
        List<String> csvLines = new ArrayList<>();
        csvLines.add("art;name;kolovo");

        for (Product product : products)
        {
            int quantity = RANDOM.nextInt(11) + 10;
            initialStock.put(product.getSku(), quantity);

            csvLines.add(
                    product.getSku() + ";"
                            + product.getName() + ";"
                            + quantity
            );
        }

        Files.write(STOCK_FILE, csvLines);
        Warehouse warehouse = Warehouse.fromCsv(STOCK_FILE, products);

        Supplier supplier = new Supplier(warehouse);
        List<Customer> customers = new ArrayList<>();
        List<Thread> customerThreads = new ArrayList<>();

        supplier.start();

        for (int i = 1; i <= 5; i++)
        {
            Customer customer = new Customer(
                    "Pokypatel-" + i,
                    warehouse,
                    products
            );

            customers.add(customer);

            Thread thread = new Thread(customer);
            customerThreads.add(thread);
            thread.start();
        }

        for (Thread thread : customerThreads)
        {
            thread.join();
        }

        supplier.stopSupplying();
        supplier.join();

        int successfulOrders = 0;
        int failedOrders = 0;
        long revenue = 0;

        for (Customer customer : customers)
        {
            successfulOrders += customer.getSuccessfulOrders();
            failedOrders += customer.getFailedOrders();
            revenue += customer.getRevenue();
        }

        Map<String, Integer> finalStock = warehouse.getStockSnapshot();

        int initialTotal = initialStock.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int finalTotal = finalStock.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int suppliedTotal = supplier.getTotalSupplied();
        int soldTotal = warehouse.getTotalSold();

        boolean balanceCorrect = initialTotal + suppliedTotal - soldTotal == finalTotal;

        List<String> report = new ArrayList<>();
        report.add("Ostatki na sklade:");

        for (Product product : products)
        {
            report.add(
                    product.getSku() + ";"
                            + product.getName() + ";"
                            + finalStock.getOrDefault(product.getSku(), 0)
            );
        }

        report.add("");
        report.add("Yspeh zakaz: " + successfulOrders);
        report.add("Unluck zakaz: " + failedOrders);
        report.add("Sell product: " + soldTotal);
        report.add("Polych product: " + suppliedTotal);
        report.add("Babos polychen: " + revenue);
        report.add("Nachal ostatok: " + initialTotal);
        report.add("Itogovi ostatok: " + finalTotal);
        report.add("BalanceCorrect: " + balanceCorrect);

        Files.write(REPORT_FILE, report);

        System.out.println("BalanceCorrect: " + balanceCorrect);
        System.out.println("Otchet: " + REPORT_FILE.toAbsolutePath());

        if (!balanceCorrect)
        {
            throw new IllegalStateException("Beda beda");
        }
    }
}
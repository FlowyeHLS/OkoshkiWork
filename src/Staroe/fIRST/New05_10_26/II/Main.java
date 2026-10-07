package Staroe.fIRST.New05_10_26.II;

public class Main {

    static int counter = 0;

    public static void main() throws InterruptedException
    {

        int correct100000 = 0;
        int min100000 = Integer.MAX_VALUE;
        int max100000 = Integer.MIN_VALUE;

        for (int test = 0; test < 10; test++)
        {
            counter = 0;

            Runnable task = () ->
            {
                for (int i = 0; i < 100000; i++)
                {
                    counter++;
                }
            };

            Thread t1 = new Thread(task);
            Thread t2 = new Thread(task);
            Thread t3 = new Thread(task);
            Thread t4 = new Thread(task);

            t1.start();
            t2.start();
            t3.start();
            t4.start();

            t1.join();
            t2.join();
            t3.join();
            t4.join();

            if (counter == 400000)
                correct100000++;


            min100000 = Math.min(min100000, counter);
            max100000 = Math.max(max100000, counter);
        }

        System.out.println("100000 inc:");
        System.out.println("Res 400000: " + correct100000);
        System.out.println("Min: " + min100000);
        System.out.println("Max: " + max100000);


        int correct100 = 0;
        int min100 = Integer.MAX_VALUE;
        int max100 = Integer.MIN_VALUE;

        for (int test = 0; test < 10; test++)
        {
            counter = 0;

            Runnable task = () ->
            {
                for (int i = 0; i < 100; i++)
                    counter++;
            };

            Thread t1 = new Thread(task);
            Thread t2 = new Thread(task);
            Thread t3 = new Thread(task);
            Thread t4 = new Thread(task);

            t1.start();
            t2.start();
            t3.start();
            t4.start();

            t1.join();
            t2.join();
            t3.join();
            t4.join();

            if (counter == 400)
                correct100++;


            min100 = Math.min(min100, counter);
            max100 = Math.max(max100, counter);
        }

        System.out.println();
        System.out.println("100 inc:");
        System.out.println("Res 400: " + correct100);
        System.out.println("Min: " + min100);
        System.out.println("Max: " + max100);
    }
}
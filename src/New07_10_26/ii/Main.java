package New07_10_26.ii;

public class Main
{

    static class Printer
    {
        public synchronized void printDocument(String name)
        {
            for (int i = 1; i <= 5; i++)
            {
                System.out.println(name + ": str " + i);

                try
                {
                    Thread.sleep(50);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main()
    {
        Printer printer = new Printer();

        Thread vanya = new Thread(() -> printer.printDocument("Vanya"));
        Thread masha = new Thread(() -> printer.printDocument("Masha"));
        Thread oleg = new Thread(() -> printer.printDocument("Oleg"));

        vanya.start();
        masha.start();
        oleg.start();
    }
}
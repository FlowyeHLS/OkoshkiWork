package Staroe.fIRST.New07_10_26.i;

public class Main
{
    static class Loader implements Runnable
    {

        @Override
        public void run()
        {
            try
            {
                while (!Thread.currentThread().isInterrupted())
                {
                    System.out.println("Downloading...");
                    Thread.sleep(300);
                }
            }
            catch (InterruptedException e)
            {
                System.out.println("He have a stop ");
                Thread.currentThread().interrupt();
            }

            System.out.println("Down end");
        }
    }

    public static void main()
    {
        Thread thread = new Thread(new Loader());

        thread.start();

        try
        {
            Thread.sleep(2000);

            thread.interrupt();
            thread.join();

            System.out.println("End: " + !thread.isAlive());
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            System.out.println("Was igl end");
        }
    }
}
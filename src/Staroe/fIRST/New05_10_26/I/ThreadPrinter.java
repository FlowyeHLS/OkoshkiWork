package Staroe.fIRST.New05_10_26.I;

public class ThreadPrinter
{

    public static void printThreadInfo()
    {
        Thread currentThread = Thread.currentThread();

        for (int i = 0; i < 5; i++)
        {
            System.out.println
            (
                    "Name: " + currentThread.getName() + ", prioritet: " + currentThread.getPriority()
            );

            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
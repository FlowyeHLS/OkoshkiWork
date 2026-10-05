package New05_10_26.I;

public class ThreadTask implements Runnable
{
    @Override
    public void run()
    {
        ThreadPrinter.printThreadInfo();
    }
}
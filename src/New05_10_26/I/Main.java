package New05_10_26.I;

public class Main
{

    public static void main() throws InterruptedException
    {

        Runnable lambdaTask = () ->
        {
            ThreadPrinter.printThreadInfo();
        };

        Runnable classTask = new ThreadTask();

        Runnable anonymousTask = new Runnable()
        {
            @Override
            public void run()
            {
                ThreadPrinter.printThreadInfo();
            }
        };

        Thread lambdaThread = new Thread(lambdaTask);
        Thread classThread = new Thread(classTask);
        Thread anonymousThread = new Thread(anonymousTask);

        lambdaThread.setName("LambdaThread");
        classThread.setName("ClassThread");
        anonymousThread.setName("AnonymousThread");

        lambdaThread.setPriority(Thread.MIN_PRIORITY);
        classThread.setPriority(Thread.NORM_PRIORITY);
        anonymousThread.setPriority(Thread.MAX_PRIORITY);

        lambdaThread.start();
        classThread.start();
        anonymousThread.start();

        lambdaThread.join();
        classThread.join();
        anonymousThread.join();

        System.out.println("End");
    }
}

package chapter_11;

import java.util.concurrent.*;

public class Mapping {
    private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

    public static String intern(String s) {
        String result = map.get(s);
        if (result == null) {
            result = map.putIfAbsent(s, s);
            if (result == null) {
                result = s;
            }
        }
        return result;
    }

    public static void main(String[] args)  {
        System.out.println("Starting...");
        var executor = Executors.newFixedThreadPool(100);
        try {
            long timeTaken = time(executor, 5, () -> {
                System.out.println(Thread.currentThread());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + " finished execution.");
            });

            executor.shutdown();

            System.out.printf("Time: %d%n", timeTaken);
        } catch (InterruptedException interruptedEx) {
            interruptedEx.printStackTrace();
        }
    }

    public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);

        System.out.println("About to loop in timer...");
        for (int i = 0; i < concurrency; i++) {
            System.out.println("Looping in timer...");
            executor.execute(() -> {
                ready.countDown();
                try {
                    start.await();
                    System.out.println("Action runs...");
                    action.run();
                    System.out.println("Action ended.");
                } catch (InterruptedException interruptedEx) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            });
        }

        ready.await();
        long startNanos = System.nanoTime();
        start.countDown();
        done.await();
        return System.nanoTime() - startNanos;
    }
}

package com.imarkov.parking.custom;

import java.util.concurrent.*;

//@Component
//@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class ThreadExecPool {
    private static final int CORE_POOL_SIZE = 4;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 25;
    private static final long KEEP_ALIVE_TIME = 60L;

    private static volatile ThreadExecPool instance;
    private final ThreadPoolExecutor executor;

    private ThreadExecPool() {
        this.executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_CAPACITY)
        );
        // Optional: Set a custom ThreadFactory for naming threads
        this.executor.setThreadFactory(Executors.defaultThreadFactory());

        // Optional: Add a shutdown hook to cleanly close the pool when the JVM exits
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    public static ThreadExecPool getInstance() {
        if (instance == null) {
            synchronized(ThreadExecPool.class) {
                if (instance == null) {
                    instance = new ThreadExecPool();
                }
            }
        }

        return instance;
    }

    public void submit(Runnable task) {
        Future<?> future = executor.submit(task);

        try {
            future.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e); // TODO: handle properly
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        System.out.println("Shutting down Task Executor...");
        executor.shutdown(); // Initiates an orderly shutdown
        try {
            // Wait a specified time for tasks to finish
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Forcefully shut down if time runs out
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

package com.zero.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class SecondAsyncService {

    /**
     * 如果你正在使用的是Spring 5.x 或更早的版本，你可以继续使用AsyncResult。
     * @return  结果
     * @throws InterruptedException 异常
     */
    @Async
    public CompletableFuture<String> asyncGetData() throws InterruptedException {
        System.out.println("Execute method asynchronously " + Thread.currentThread().getName());
        Thread.sleep(4000);
        return new AsyncResult<>(super.getClass().getSimpleName() + " response !!! ").completable();
    }

    /**
     * 如果你正在使用的是Spring 5.x 或更早的版本，你可以继续使用AsyncResult。
     * @return  结果
     * @throws InterruptedException 异常
     */
    @Async
    public ListenableFuture<String> asyncGetData2() throws InterruptedException {
        System.out.println("Execute method asynchronously " + Thread.currentThread().getName());
        Thread.sleep(4000);
        return new AsyncResult<>(super.getClass().getSimpleName() + " response !!! ");
    }

    /**
     * 如果你正在使用的是Spring 6.x 或更新的版本，应该考虑使用新的方式来处理异步操作。在Spring 6.x中，推荐使用CompletableFuture或者ListenableFuture。
     * @return  结果
     * @throws InterruptedException 异常
     */
    @Async
    public CompletableFuture<String> asyncGetData3() throws InterruptedException {
        System.out.println("Execute method asynchronously " + Thread.currentThread().getName());
        Thread.sleep(4000);
        return CompletableFuture.supplyAsync(() -> super.getClass().getSimpleName() + " response !!! ");
    }
    
}

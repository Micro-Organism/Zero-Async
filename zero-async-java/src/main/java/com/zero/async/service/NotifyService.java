package com.zero.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


@Service
@Slf4j
public class NotifyService {

    FirstAsyncService firstService;
    SecondAsyncService secondService;

    @Autowired
    public NotifyService(FirstAsyncService firstService, SecondAsyncService secondService) {
        this.firstService = firstService;
        this.secondService = secondService;
    }

    public CompletableFuture<String> asyncMergeServicesResponse() throws InterruptedException {
        CompletableFuture<String> firstServiceResponse = firstService.asyncGetData();
        CompletableFuture<String> secondServiceResponse = secondService.asyncGetData();

        // Merge responses from FirstAsyncService and SecondAsyncService
        return firstServiceResponse.thenCompose(firstServiceValue -> secondServiceResponse.thenApply(secondServiceValue -> firstServiceValue + secondServiceValue));
    }

    public void noAsync() {
        System.out.println("Execute method asynchronously. " + Thread.currentThread().getName());
    }

    public void withAsync() {
        System.out.println("Execute method asynchronously. " + Thread.currentThread().getName());
    }

    @Async("threadPoolTaskExecutor")
    public void mockError() {
//        int ss = 12 / 0;
        throw new RuntimeException("mock error");
    }

    /**
     * 如果你正在使用的是Spring 5.x 或更早的版本，你可以继续使用AsyncResult。
     * @return  执行结果
     */
    @Async
    public Future<String> asyncMethodWithReturnType() {
        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<String>("hello world !!!!");
        } catch (InterruptedException e) {
            log.error("e: ", e);
        }
        return null;
    }

    /**
     * 如果你正在使用的是Spring 6.x 或更新的版本，应该考虑使用新的方式来处理异步操作。在Spring 6.x中，推荐使用CompletableFuture或者ListenableFuture。
     * @return  结果
     */
    @Async
    public CompletableFuture<String> asyncMethodWithReturnType2() {
        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return CompletableFuture.supplyAsync(() -> "hello world !!!!");
        } catch (InterruptedException e) {
            log.error("e: ", e);
        }
        return null;
    }

}

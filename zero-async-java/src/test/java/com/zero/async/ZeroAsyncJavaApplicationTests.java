package com.zero.async;

import com.zero.async.service.NotifyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
class ZeroAsyncJavaApplicationTests {

    @Autowired
    NotifyService notifyService;

    @BeforeTestMethod
    public void before() {
        System.out.println("init some data");
    }

    @AfterTestMethod
    public void after() {
        System.out.println("clean some data");
    }

    @Test
    public void execute() throws ExecutionException, InterruptedException {
        System.out.println("your method test Code");
        System.out.println("Invoking an asynchronous method. " + Thread.currentThread().getName());
        notifyService.noAsync();
        notifyService.withAsync();

    }

    @Test
    public void mockError() throws ExecutionException, InterruptedException {
        notifyService.mockError();
    }

    @Test
    public void testAsyncAnnotationForMethodsWithReturnType() throws InterruptedException, ExecutionException {
        System.out.println("Invoking an asynchronous method. " + Thread.currentThread().getName());
        Future<String> future = notifyService.asyncMethodWithReturnType();

        while (true) {
            if (future.isDone()) {
                System.out.println("Result from asynchronous process - " + future.get());
                break;
            }
            System.out.println("Continue doing something else. ");
            Thread.sleep(1000);
        }
    }

    @Test
    public void testAsyncAnnotationForMergedServicesResponse() throws InterruptedException, ExecutionException {
        System.out.println("Invoking an asynchronous method. " + Thread.currentThread().getName());
        CompletableFuture<String> completableFuture = notifyService.asyncMergeServicesResponse();

        while (true) {
            if (completableFuture.isDone()) {
                System.out.println("Result from asynchronous process - " + completableFuture.get());
                break;
            }
            System.out.println("Continue doing something else. ");
            Thread.sleep(1000);
        }
    }


}

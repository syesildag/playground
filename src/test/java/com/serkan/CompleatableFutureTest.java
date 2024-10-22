package com.serkan;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;

public class CompleatableFutureTest {

    @Test
    public void CompletableFutureGet() {

        var cf = new CompletableFuture<Boolean>();
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10);
                cf.complete(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        var result = false;
        try {
            result = cf.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(result);
    }

    @Test
    public void CompletableFutureGetWithException() {

        var cf = new CompletableFuture<Boolean>();
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10);
                cf.completeExceptionally(new RuntimeException("get test failed!"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        assertThrows(ExecutionException.class, () -> cf.get());
    }

    @Test
    public void CompletableFutureJoin() {

        var cf = new CompletableFuture<Boolean>();
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10);
                cf.complete(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        assertTrue(cf.join());
    }

    @Test
    public void CompletableFutureJoinWithException() {

        var cf = new CompletableFuture<Boolean>();
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10);
                cf.completeExceptionally(new RuntimeException("join test failed!"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        assertThrows(RuntimeException.class, () -> cf.join());
    }
}

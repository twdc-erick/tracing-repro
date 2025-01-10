package repro.handler;

import repro.FibonacciUtil;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class WorkerHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext routingContext) {
        System.out.println("Handling request with the worker verticle.");
        FibonacciUtil.fibonacci(40);
        routingContext.response().end();
        System.out.println("Request completed");
    }
}

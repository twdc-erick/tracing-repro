package repro.verticle;

import repro.handler.EventLoopHandler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

public class EventLoopVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        try {
            initialize(startPromise);
        } catch (Throwable t) {
            System.out.println("Unexpected error during WebVerticle startup");
            System.exit(1);
        }
    }

    private void initialize(Promise<Void> startPromise) {
        EventLoopHandler eventLoopHandler = new EventLoopHandler();

        int port = 8080;
        HttpServer httpServer = vertx.createHttpServer(new HttpServerOptions()
                .setTcpFastOpen(true)
                .setMaxHeaderSize(16384)
                .setCompressionSupported(true)
                .setCompressionLevel(3)
                .setAcceptBacklog(10000)
                .setPort(port));

        Router baseRouter = Router.router(vertx);
        baseRouter.get().handler(eventLoopHandler);

        httpServer.requestHandler(baseRouter).listen(port, handler -> {
            if (handler.succeeded()) {
                startPromise.complete();
                System.out.println("Finished initializing event loop verticle on port: " + port);
            } else {
                startPromise.fail(handler.cause());
            }
        });
    }
}

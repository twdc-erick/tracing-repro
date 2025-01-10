package repro;

import repro.verticle.EventLoopVerticle;
import repro.verticle.WorkerVerticle;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class Main {

    public static void main(String[] args) {

        VertxOptions options = new VertxOptions()
                .setPreferNativeTransport(true);
        Vertx vertx = Vertx.vertx(options);

        vertx.deployVerticle(
                EventLoopVerticle.class.getCanonicalName(),
                new DeploymentOptions().setInstances(1));

        vertx.deployVerticle(
                WorkerVerticle.class.getCanonicalName(),
                new DeploymentOptions()
                        .setThreadingModel(ThreadingModel.WORKER)
                        .setInstances(1));
    }
}

package br.alvesfred.vertx.samples;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Server Verticle Sample
 *
 * @author alvesfred
 */
public class ServerVertxSample extends AbstractVerticle {

	static final String MSG = "Vertx-Sample!";
	static final int PORT   = 8080;

	@Override
	public void start(Future<Void> startFuture) {
		vertx.createHttpServer().requestHandler(req -> {
			req.response().putHeader("Content-Type", "plain/text").end(MSG);
		}).listen(PORT, ar -> {
			if (ar.succeeded()) {
				startFuture.complete();
				return;
			}
			
			startFuture.fail(ar.cause());
		});
	}
}

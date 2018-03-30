package br.alvesfred.vertx.samples;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.WebClient;

/**
 * Base Test
 *
 * @author alvesfred
 *
 */
public abstract class BaseVertxTestSamples extends AbstractVerticle {

	/*
	 * Logger
	 */
	protected static final Logger logger =
			LoggerFactory.getLogger(BaseVertxTestSamples.class);

	/**
	 * Web Client support
	 * 
	 * @param vertx
	 * @return
	 */
	protected WebClient buildWebClient(Vertx vertx) {
		final WebClient webClient = WebClient.create(vertx);
		return webClient;
	}

	/**
	 * Default configuration
	 *
	 * @author dfvp
	 *
	 */
	static class Config {
		static final int PORT         = 8080;
		static final int RESPONSE_200 = 200;

		static final String LOCALHOST = "localhost";
		static final String MSG       = "Vertx-Sample!";
	}
}

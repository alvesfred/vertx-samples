package br.alvesfred.vertx.samples;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

/**
 * Vertx Samples
 *
 * @author alvesfred
 *
 */
@DisplayName("Vertx Samples")
@ExtendWith(VertxExtension.class)
public class VertxTestSamples extends BaseVertxTestSamples {

	  @Test
	  @DisplayName("HTTP service - 5 requests")
	  void testSampleVerticle(Vertx vertx, VertxTestContext ctx) {
	    final WebClient webClient = buildWebClient(vertx);
	    final int MAX = 5;

	    Checkpoint deploymentCheckpoint = ctx.checkpoint();
	    Checkpoint requestCheckpoint    = ctx.checkpoint(MAX);

		vertx.deployVerticle(new VertxTestSamples(), ctx.succeeding(id -> {
			deploymentCheckpoint.flag();

			IntStream.range(0, MAX).forEach(i -> {
				webClient.get(Config.PORT, Config.LOCALHOST, "/").as(
					BodyCodec.string()).send(ctx.succeeding(resp -> {
						ctx.verify(() -> {
							assertThat(resp.statusCode()).isEqualTo(Config.RESPONSE_200);
							assertThat(resp.body()).contains(Config.MSG);

							requestCheckpoint.flag();
						});
				}));
			});
		}));
	  }
}

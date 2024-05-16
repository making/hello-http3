package com.example;

import io.netty.handler.ssl.util.SelfSignedCertificate;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.Http3SslContextSpec;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.server.HttpServer;

import java.time.Duration;

public class Application {

	// https://projectreactor.io/docs/netty/milestone/reference/http-server.html#HTTP3
	public static void main(String[] args) throws Exception {
		SelfSignedCertificate cert = new SelfSignedCertificate();
		Http3SslContextSpec serverCtx = Http3SslContextSpec.forServer(cert.privateKey(), null, cert.certificate());
		DisposableServer server = HttpServer.create()
			.port(8443)
			.protocol(HttpProtocol.HTTP3)
			.secure(spec -> spec.sslContext(serverCtx))
			.idleTimeout(Duration.ofSeconds(5))
			.http3Settings(spec -> spec.maxData(10000000)
				.maxStreamDataBidirectionalLocal(1000000)
				.maxStreamDataBidirectionalRemote(1000000)
				.maxStreamsBidirectional(100))
			.handle((request, response) -> response.header("server", "reactor-netty")
				.sendString(Mono.just("Hello HTTP/3!")))
			.bindNow();
		server.onDispose().block();
	}

}
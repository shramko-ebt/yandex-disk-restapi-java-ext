
package com.yandex.disk.rest;

import com.squareup.okhttp.OkHttpClient;
import com.yandex.disk.rest.okhttp.LoggingInterceptor;
import retrofit.client.OkClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class HttpClient extends OkClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private static final int CONNECT_TIMEOUT_MILLIS = 30 * 1000;
    private static final int READ_TIMEOUT_MILLIS = 30 * 1000;
    private static final int WRITE_TIMEOUT_MILLIS = 30 * 1000;

    protected OkHttpClient client;

    private static OkHttpClient makeClient() {
        OkHttpClient client = new OkHttpClient();

        if (logger.isDebugEnabled()) {
            client.networkInterceptors().add(new LoggingInterceptor());
        }

        client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        client.setWriteTimeout(WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);

        client.setFollowSslRedirects(true);
        client.setFollowRedirects(true);

        return client;
    }

    public HttpClient() {
        this(makeClient());
    }

    public HttpClient(final OkHttpClient client) {
        super(client);
        this.client = client;
    }

    public OkHttpClient getClient() {
        return client;
    }
}

package com.topideal.supplychain.ocp.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * httpclient请求池
 * @author zhangzhihao
 * @date 2016-8-29 上午10:55:28
 */
public class HttpClientFactory {
//    private static final PoolingHttpClientConnectionManager CONNECTION_MANAGER = new PoolingHttpClientConnectionManager();;
//
//    private static final Logger logger = Logger.getLogger(HttpClientFactory.class);
//
//    private static final HttpClient HTTP_CLIENT = HttpClients
//            .custom().setConnectionManager(CONNECTION_MANAGER)
//            .build();
//
//    public static HttpClient getHttpClient() {
//        return HTTP_CLIENT;
//    }
//
//    public static CloseableHttpClient createSSLClientDefault(){
//        try {
//            SSLContext ctx = SSLContext.getInstance("TLS");
//            X509TrustManager tm = new X509TrustManager() {
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
//                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
//            };
//            ctx.init(null, new TrustManager[] { tm }, null);
//            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//            SchemeRegistry registry = new SchemeRegistry();
//            registry.register(new Scheme("https", 443, ssf));
//            ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
//            return new DefaultHttpClient(mgr);
//        } catch (Exception ex) {
//            logger.error(ex);
//            return null;
//        }
//    }

    //private static final ClientConnectionManager CONNECTION_MANAGER = new PoolingClientConnectionManager();
    private static final Logger logger = LoggerFactory.getLogger(HttpClientFactory.class);

    private static PoolingHttpClientConnectionManager CONNECTION_MANAGER = null;

    private static CloseableHttpClient httpClient;

    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    static {
        try {
            SSLContext ctx = SSLContext.getInstance("SSLv3");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setStaleConnectionCheckEnabled(true)
                    .build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(ctx))
                    .build();
            CONNECTION_MANAGER = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .setConnectionManager(CONNECTION_MANAGER).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }

    }

    public static CloseableHttpClient createSSLClientDefault(){
        try {
            //创建自定义的httpclient对象
            CloseableHttpClient client = HttpClients.custom().setConnectionManager(CONNECTION_MANAGER).build();
            return client;
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            return null;
        }
    }
}
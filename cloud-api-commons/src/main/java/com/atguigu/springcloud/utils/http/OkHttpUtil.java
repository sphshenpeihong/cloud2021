package com.atguigu.springcloud.utils.http;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.lang.NonNull;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Shen Peihong
 * @version 1.0
 * @since 1.0.0.1
 */
@Slf4j
public class OkHttpUtil {

    private static volatile OkHttpClient okHttpClient = null;
    private Map<String, String> headerMap;
    private Map<String, String> paramMap;
    private String url;
    private Request.Builder request;

    private static final String JSON_HEADER = "application/json; charset=utf-8";

    /**
     * 初始化okHttpClient，并且允许https访问
     */
    private OkHttpUtil() {
        if (okHttpClient == null) {
            synchronized (OkHttpUtil.class) {
                if (okHttpClient == null) {
                    TrustManager[] trustManagers = buildTrustManagers();
                    okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .sslSocketFactory(createSSLSocketFactory(trustManagers), (X509TrustManager) trustManagers[0])
                            .hostnameVerifier((hostName, session) -> true)
                            .retryOnConnectionFailure(true)
                            .build();
                    addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
                }
            }
        }
    }

    /**
     * 创建OkHttpUtils
     *
     * @return 实例对象
     */
    public static OkHttpUtil builder() {
        return new OkHttpUtil();
    }

    /**
     * 添加url
     *
     * @param url 请求URL
     * @return 当前对象
     */
    public OkHttpUtil url(String url) {
        this.url = url;
        return this;
    }

    /**
     * 添加单个参数
     *
     * @param key   参数名
     * @param value 参数值
     * @return 当前对象
     */
    public OkHttpUtil addParam(String key, String value) {
        if (paramMap == null) {
            paramMap = new LinkedHashMap<>(16);
        }
        paramMap.put(key, value);
        return this;
    }

    /**
     * 添加多个参数
     *
     * @return 当前对象
     */
    public OkHttpUtil addParams(@NonNull Map<String, String> paramMap) {
        if (this.paramMap == null) {
            this.paramMap = new LinkedHashMap<>(16);
        }
        this.paramMap.putAll(paramMap);
        return this;
    }

    /**
     * 添加实体类
     *
     * @param o 实体类对象
     * @return 当前对象
     */
    public OkHttpUtil addParams(@NonNull Object o) throws IllegalAccessException {
        if (this.paramMap == null) {
            this.paramMap = new LinkedHashMap<>(16);
        }

        Class<?> clazz = o.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(o);
            // 属性对应的值不为null时，则添加
            if (value != null) {
                paramMap.put(field.getName(), (String) field.get(o));
            }
        }
        return this;
    }

    /**
     * 添加请求头
     *
     * @param key   参数名
     * @param value 参数值
     * @return 当前对象
     */
    public OkHttpUtil addHeader(String key, String value) {
        if (headerMap == null) {
            headerMap = new LinkedHashMap<>(16);
        }
        headerMap.put(key, value);
        return this;
    }

    /**
     * 初始化get方法
     *
     * @return 当前对象
     */
    public OkHttpUtil get() {
        StringBuilder urlBuilder = new StringBuilder(url);
        if (paramMap != null) {
            urlBuilder.append("?");
            try {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    urlBuilder.append(URLEncoder.encode(entry.getKey(), "utf-8")).
                            append("=").
                                      append(URLEncoder.encode(entry.getValue(), "utf-8")).
                                      append("&");
                }
            } catch (UnsupportedEncodingException e) {
                log.info("参数加密出错");
            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }
        request = new Request.Builder().get().url(urlBuilder.toString());
        return this;
    }

    /**
     * 初始化post方法
     *
     * @param isJsonPost true等于json的方式提交数据，类似postman里post方法的raw
     *                   false等于普通的表单提交
     * @return 当前对象
     */
    public OkHttpUtil post(boolean isJsonPost) {
        RequestBody requestBody;
        if (isJsonPost) {
            String json = "";
            if (paramMap != null) {
                json = JSON.toJSONString(paramMap);
            }
            requestBody = RequestBody.create(MediaType.parse(JSON_HEADER), json);
        } else {
            FormBody.Builder formBody = new FormBody.Builder();
            if (paramMap != null) {
                paramMap.forEach(formBody::add);
            }
            requestBody = formBody.build();
        }
        request = new Request.Builder().post(requestBody).url(url);
        return this;
    }

    /**
     * 同步请求
     *
     * @return 返参（json字符串）
     */
    public String sync() {
        setHeader(request);
        String resp = null;
        try {
            Response response = okHttpClient.newCall(request.build()).execute();
            // assert response.body() != null;
            if (response.body() == null) {
                return null;
            }
            resp = response.body().string();
        } catch (IOException e) {
            log.error("OkHttp发送HTTP同步请求出错，请求地址url = [{}]，e = [{}]", url, e.getMessage());
        }
        return resp;
    }

    /**
     * 异步请求，有返回值
     *
     * @return 返参（json字符串）
     */
    public String async() {
        StringBuilder buffer = new StringBuilder("");
        setHeader(request);
        okHttpClient.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("OkHttp发送HTTP异步请求出错，请求地址url = [{}]，e = [{}]", url, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // assert response.body() != null;
                if (response.body() == null) {
                    return;
                }
                buffer.append(response.body().string());
            }
        });
        return buffer.toString();
    }

    /**
     * 异步请求，带有接口回调
     *
     * @param callBack 回调函数
     */
    public void async(ICallBack callBack) {
        setHeader(request);
        okHttpClient.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("OkHttp发送HTTP异步请求出错，请求地址url = [{}]，e = [{}]", url, e.getMessage());
                callBack.onFailure(call, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // assert response.body() != null;
                if (response.body() == null) {
                    return;
                }
                callBack.onSuccessful(call, response.body().string());
            }
        });
    }

    /**
     * 为request添加请求头
     *
     * @param request 请求头对象
     */
    private void setHeader(Request.Builder request) {
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }


    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     *
     * @return
     */
    private static SSLSocketFactory createSSLSocketFactory(TrustManager[] trustAllCerts) {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            log.info("处理SSL证书出错");
        }
        return ssfFactory;
    }

    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }

    /**
     * 自定义一个接口回调
     */
    public interface ICallBack {

        void onSuccessful(Call call, String data);

        void onFailure(Call call, String errorMsg);

    }

}


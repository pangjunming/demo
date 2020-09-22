package com.example.server.util;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: zhanglu7
 * Date: 14-11-18
 * Time: 14-11-17 9:28
 * To change this template use File | Settings | File Templates.
 * @author chenlin19
 * @date 2018-10-17 17:05:29
 */
public class HttpClientUtil {

    /**
     * CHARSET_UTF8
     */
    public static String CHARSET_UTF8 = "utf-8";

    /**
     * get utf-8
     * @param url
     * @return
     */
    public static String sendGetRequest(String url) throws Exception{
        return sendGetRequst(url,CHARSET_UTF8);
    }
    /**
     *  get any
     * @param url
     * @param charset
     * @return
     */
    public static String sendGetRequst(String url,String charset) throws Exception{
        String response = null;
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(2, false));
        client.getHttpConnectionManager().getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,charset);
        client.getHttpConnectionManager().getParams().setSoTimeout(10000);
        client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        GetMethod method = new GetMethod(url);
        InputStream in = null;
        try {
            int status = client.executeMethod(method);
            if(status != HttpStatus.SC_OK){
                throw new Exception("http request response not 200");
            }else{
                in = method.getResponseBodyAsStream();
                response = IOUtils.toString(in,charset);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            if(in != null) {
                IOUtils.closeQuietly(in);
            }
            if(method != null) {
                method.releaseConnection();
            }
        }
        return response;
    }

    /**
     * post utf-8
     * @param url
     * @param paras
     * @return
     */
    public static String sendPostRequest(String url,Map<String,String> paras) throws Exception{
        return sendPostRequest(url,paras,CHARSET_UTF8);
    }

    /**
     * post any
     * @param url
     * @param paras
     * @param charset
     * @return
     */
    public static String sendPostRequest(String url,Map<String,String> paras,String charset) throws Exception{
        String response = null;
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,charset);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler(2,false));
        client.getHttpConnectionManager().getParams().setSoTimeout(10000);
        client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        if(MapUtils.isNotEmpty(paras)){
            Set set = paras.entrySet();
            Iterator<Entry> it = set.iterator();
            while(it.hasNext()){
                Entry<String,String> entry = it.next();
                method.addParameter(entry.getKey(), entry.getValue());
            }
        }
        InputStream in = null;
        try {
            int status = client.executeMethod(method);
            if(status != HttpStatus.SC_OK){
                throw new Exception("http request response not 200");
            }else{
                in = method.getResponseBodyAsStream();
                response = IOUtils.toString(in,charset);
            }
        } catch (Exception e) {
            throw e;
        }finally {
            if(in != null) {
                IOUtils.closeQuietly(in);
            }
            if(method != null) {
                method.releaseConnection();
            }
        }
        return response;
    }


}

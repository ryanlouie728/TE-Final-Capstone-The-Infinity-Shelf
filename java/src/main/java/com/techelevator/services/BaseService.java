package com.techelevator.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseService {
    private String API_BASE = "https://gateway.marvel.com/v1/public/";
    protected RestTemplate restTemplate = new RestTemplate();

    public Map<String,String> getBaseParams() {
        String ts = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        String hash = getMd5(ts + "90c51b8bd6d1f8bb56a93ef7b1169f08dd57e5f3" + "f6cf199b6d9288752e9f8dc52e8d5f97");
        Map<String, String> params = new HashMap<>();
        params.put("ts", ts);
        params.put("apikey", "f6cf199b6d9288752e9f8dc52e8d5f97");
        params.put("hash", hash);
        params.put("noVariants", "true");
        return params;
    }

    public Map<String,String> getBaseParams(Map<String, String> extraParams) {
        Map<String, String> params = getBaseParams();
        params.putAll(extraParams);
        return params;
    }

    public HttpEntity<?> getBaseEntity() {
        HttpHeaders headers = getAuthorizedHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return entity;
    }

    public String getApiUrl(String endpoint) {
        String url = UriComponentsBuilder.fromHttpUrl(API_BASE + endpoint)
                .queryParam("apikey", "{apikey}")
                .queryParam("ts", "{ts}")
                .queryParam("hash", "{hash}")
                .queryParam("noVariants", "{noVariants}")
                .encode()
                .toUriString();
        return url;
    }

    public String getApiUrl(String endpoint, Map<String, String> extraParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE + endpoint)
                .queryParam("apikey", "{apikey}")
                .queryParam("ts", "{ts}")
                .queryParam("hash", "{hash}")
                .queryParam("noVariants", "{noVariants}");
        for (Map.Entry<String, String> param : extraParams.entrySet()) {
            builder.queryParam(param.getKey(), "{" + param.getKey()+ "}");
        }
        return builder.encode().toUriString();
    }

    protected HttpHeaders getAuthorizedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        // For specifying wrong message digest algorithms
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



}

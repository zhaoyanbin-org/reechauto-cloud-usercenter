package com.reechauto.usercenter.gateway.fallbacks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.reechauto.usercenter.common.resp.ResponseData;


@Component
public class ZuulFallbackProvider implements FallbackProvider {
	  @Override
	  public String getRoute() {
	    // 表明是为哪个微服务提供回退，*表示为所有微服务提供回退
	    return "*";
	  }

	  @Override
	  public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
	    if (cause instanceof HystrixTimeoutException) {
	      return response(HttpStatus.GATEWAY_TIMEOUT);
	    } else {
	      return this.fallbackResponse();
	    }
	  }

	  public ClientHttpResponse fallbackResponse() {
	    return this.response(HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	  private ClientHttpResponse response(final HttpStatus status) {
	    return new ClientHttpResponse() {
	      @Override
	      public HttpStatus getStatusCode() throws IOException {
	        return status;
	      }

	      @Override
	      public int getRawStatusCode() throws IOException {
	        return status.value();
	      }

	      @Override
	      public String getStatusText() throws IOException {
	        return status.getReasonPhrase();
	      }

	      @Override
	      public void close() {
	      }

	      @Override
	      public InputStream getBody() throws IOException {
	    	  ObjectMapper objectMapper = new ObjectMapper();
              String content = objectMapper.writeValueAsString(ResponseData.error(status.value(), "微服务不可用，请稍后再试"));
              return new ByteArrayInputStream(content.getBytes());
	      }

	      @Override
	      public HttpHeaders getHeaders() {
	        // headers设定
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        return headers;
	      }
	    };
	  }
	}
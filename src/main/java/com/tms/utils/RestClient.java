package com.tms.utils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RestClient {

	Logger logger = LoggerFactory.getLogger(RestClient.class);
	private final RestTemplate restTemplate = new RestTemplate();

	private static <T> T parse(String str, Class<T> responseClass) {
		try {
			if (str.isBlank() || responseClass == null)
				return null;
			return new ObjectMapper().readValue(str, responseClass);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseClass) {
		logger.info("url: {}", url);
		URI uri = UriComponentsBuilder.fromHttpUrl(url).encode(StandardCharsets.US_ASCII).build(true).toUri();
		return exec(() -> restTemplate.getForEntity(uri, responseClass), responseClass);
	}

	public <T, R> ResponseEntity<T> postForEntityLogin(String url, R requestBody, Class<T> responseClass) {
		return exec(() -> restTemplate.postForEntity(url, requestBody, responseClass), responseClass);
	}

	public <T, R> ResponseEntity<T> postForEntity(String url, R requestBody, Class<T> responseClass) {
		logger.info("url: {}, request: {}", url, requestBody);
		return exec(() -> restTemplate.postForEntity(url, requestBody, responseClass), responseClass);
	}

	public <R, T> ResponseEntity<T> postForObject(String url, R requestBody, Class<T> responseClass) {
		logger.info("url: {}, request: {}", url, requestBody);
		return exec(() -> ResponseEntity.ok(restTemplate.postForObject(url, requestBody, responseClass)),
				responseClass);
	}

	public <R, T> ResponseEntity<T> putForEntity(String url, R requestBody, Class<T> responseClass) {
		logger.info("url: {}, request: {}", url, requestBody);
		return exec(() -> {
			restTemplate.put(url, requestBody);
			return ResponseEntity.ok().build();
		}, responseClass);
	}

	public <R> ResponseEntity putForEntityOrThrowEx(String url, R requestBody) {
		logger.info("url: {}", url);
		return execOrThrowEx(() -> {
			restTemplate.put(url, requestBody);
			return ResponseEntity.ok().build();
		});
	}

	public <T> ResponseEntity<T> getForEntityOrThrowEx(String url, Class<T> responseClass) {
		logger.info("url: {}", url);
		return execOrThrowEx(() -> restTemplate.getForEntity(url, responseClass));
	}

	public <T> ResponseEntity<T> getParametrizedWithExchangeOrThrowEx(String url,
			ParameterizedTypeReference<T> typeReference) {
		return getWithExchangeOrThrowEx(url, typeReference);
	}

	public <T> ResponseEntity<T> getWithExchangeOrThrowEx(String url,
			ParameterizedTypeReference<T> responseTypeReference) {
		logger.info("url: {}", url);
		return execOrThrowEx(() -> restTemplate.exchange(url, HttpMethod.GET, null, responseTypeReference));
	}

	public <T> ResponseEntity<T> getParametrizedWithExchange(String url,
			ParameterizedTypeReference<T> responseTypeReference) {
		logger.info("url: {}", url);
		return exec(() -> restTemplate.exchange(url, HttpMethod.GET, null, responseTypeReference), null);
	}

	public <T, R> ResponseEntity<T> postForEntityOrThrowEx(String url, R requestBody, Class<T> responseClass) {
		logger.info("url: {}, request: {}", url, requestBody);
		return execOrThrowEx(() -> restTemplate.postForEntity(url, requestBody, responseClass));
	}

	public <T, R> ResponseEntity<T> postForObjectOrThrowEx(String url, R requestBody, Class<T> responseClass) {
		logger.info("url: {}, request: {}", url, requestBody);
		return execOrThrowEx(() -> {
			restTemplate.postForObject(url, requestBody, responseClass);
			return ResponseEntity.ok().build();
		});
	}

	private <T> ResponseEntity<T> exec(Supplier<ResponseEntity> supplier, Class<T> response) {
		try {
			return supplier.get();
		} catch (HttpStatusCodeException httpException) {
			httpException.printStackTrace();
			logger.error("HttpClientException: {}", httpException.getLocalizedMessage());
			logger.error("HttpStatusCodeException: ", httpException);
			return ResponseEntity.status(httpException.getStatusCode())
					.body(parse(httpException.getResponseBodyAsString(), response));
		} catch (Exception exception) {
			logger.error("Exception: {}", exception.getLocalizedMessage());

			return ResponseEntity.status(500).build();
		}
	}

	private ResponseEntity execOrThrowEx(Supplier supplier) {
		try {
			return (ResponseEntity) supplier.get();
		} catch (Exception ex) {
			logger.error("HttpClientException: {}", ex.getLocalizedMessage());
			logger.error("HttpClientException: ", ex);
			throw new RuntimeException(ex);
		}
	}

	public <T> ResponseEntity<T> getParameterizedTypeWithExchange(String url,
			ParameterizedTypeReference<T> typeReference) {
		logger.info("url: {}", url);
		return exec1(() -> restTemplate.exchange(url, HttpMethod.GET, null, typeReference));
	}

	private ResponseEntity exec1(Supplier supplier) {
		return (ResponseEntity) supplier.get();
	}

	public <T> ResponseEntity<T> getForObject(String url, Class<T> responseClass) {
		logger.info("url: {} responseClass {} ", url, responseClass.getSimpleName());
		return exec(() -> ResponseEntity.ok(restTemplate.getForObject(url, responseClass)), responseClass);
	}

	public <T> ResponseEntity<T> getForObject(String url, HttpEntity<T> requestEntity, Class<T> responseClass) {
		logger.info("url: {}", url);
		return exec(() -> restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseClass), responseClass);
	}

	public <T> ResponseEntity<T> postForObjectWithExchange(String url, HttpEntity<T> requestEntity,
			Class<T> responseClass) {
		logger.info("url: {}", url);
		return exec(() -> restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseClass), responseClass);
	}

}

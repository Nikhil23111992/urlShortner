package proj.nikhil.urlShortner.controller;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import proj.nikhil.urlshortner.dto.UrlRequest;
import proj.nikhil.urlshortner.dto.UrlResponse;

/**
 * @author
 *
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UrlShortnerControllerTest {
	@Autowired
	TestRestTemplate testRestTemplate;

	@Autowired
	ObjectMapper mapper ;
	
	

	@Test
	@SuppressWarnings("all")
	public void test_createShortURL_Success() throws Exception {

		
		HttpHeaders headers = new HttpHeaders();
		
		UrlRequest urlRequest = new UrlRequest();
		urlRequest.setUrl("http:google.com");
		HttpEntity<UrlRequest> entity = new HttpEntity<UrlRequest>(urlRequest,
				headers);

	

		ResponseEntity<UrlResponse> responseEntity = testRestTemplate
				.exchange("/api/url/save", HttpMethod.POST, entity, UrlResponse.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}
	
	
	@Test
	@SuppressWarnings("all")
	public void test_getShortURL_Success() throws Exception {

		
		HttpHeaders headers = new HttpHeaders();
		
		HttpEntity entity = new HttpEntity<>(headers);

		 URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080").path("/api/url/getShortUrl")
	                .queryParam("shorturl", "http://tinyurl/2").build().toUri();
	

		ResponseEntity<UrlResponse> responseEntity = testRestTemplate
				.getForEntity(uri, UrlResponse.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

}

package PAF.day22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureMockMvc
@SpringBootTest
class Day22ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void shouldReturn200() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/api/rsvp")
				.queryParam("q", "ben")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		String payload = result.getResponse().getContentAsString();

		Assertions.assertEquals(200, status);

		System.out.println("payload: " + payload);
	}

	
	@Test
	void shouldReturn404() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/api/rsvp")
				.queryParam("q", "mummy")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		String payload = result.getResponse().getContentAsString();

		Assertions.assertEquals(404, status);

		System.out.println("payload: " + payload);
	}

	@Test
	void shouldReturn201() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/api/rsvps/count")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		String payload = result.getResponse().getContentAsString();

		Assertions.assertEquals(201, status);
		assertNotNull(payload);

		System.out.println("payload: " + payload);
	}

	@Test
	void contextLoads() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/api/rsvps")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		String payload = result.getResponse().getContentAsString();

		Assertions.assertEquals(200, status);
		assertNotNull(payload);

		System.out.println("payload: " + payload);
	}

	@Test
	void shouldUpdateSuccessfully() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.post("/api/rsvp")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("name","ben")
				.param("phone","77777777")
				.param("email","ben@yahoo.com")
				.param("confirmation_date","2022-04-23")
				.param("comments","Will be late");

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		String payload = result.getResponse().getContentAsString();

		Assertions.assertEquals(201, status);
		assertNotNull(payload);

		System.out.println("payload: " + payload);
	}

}

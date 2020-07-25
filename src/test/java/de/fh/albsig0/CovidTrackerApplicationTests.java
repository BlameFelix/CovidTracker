package de.fh.albsig0;

import de.fh.albsig0.controller.CovidController;
import de.fh.albsig0.resources.Country;
import de.fh.albsig0.resources.CovidExceptions;
import de.fh.albsig0.resources.CovidParent;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;

import static java.util.Objects.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CovidTrackerApplicationTests {
	@InjectMocks
	CovidController cc = new CovidController();

	@Mock
	JsonReader js = Mockito.mock(JsonReader.class);

	@BeforeAll
	static void init() {
		MockitoAnnotations.initMocks(CovidTrackerApplicationTests.class);
	}

	@BeforeAll // test if the API is working first
	static void testAPI() throws IOException, JSONException {
		Assertions.assertFalse(isNull(new JsonReader().readJsonFromUrl("https://covid2019-api.herokuapp.com/v2/country/germany")));
	}

	@Test
	void testName() {
		CovidController cc = new CovidController();
		Country c = (Country) cc.returnCountry("germany");
		Assertions.assertEquals("germany", c.getName());
	}

	@Test
	void mockCountry() throws IOException, JSONException {
		when(js.readJsonFromUrl("https://covid2019-api.herokuapp.com/v2/country/germany")).thenReturn(new JSONObject("{\"dt\":\"07-25-2020\",\"data\":{\"recovered\":189696,\"active\":6807,\"location\":\"Germany\",\"confirmed\":17,\"deaths\":9120},\"ts\":1.5956352E9}"));

		Country c = (Country) cc.returnCountry("germany");

		Assertions.assertEquals(17, c.getConfirmed());
	}

	@Test
	void mockError() throws IOException, JSONException {
		when(js.readJsonFromUrl("https://covid2019-api.herokuapp.com/v2/country/germany")).thenReturn(null);

		CovidExceptions c = (CovidExceptions) cc.returnCountry("germany");

		Assertions.assertEquals("Country not found! Use English country name", c.getErrorMessage());
	}

}

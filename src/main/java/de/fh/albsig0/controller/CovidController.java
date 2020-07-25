package de.fh.albsig0.controller;

import com.google.inject.Inject;
import de.fh.albsig0.JsonReader;
import de.fh.albsig0.resources.Country;
import de.fh.albsig0.resources.CovidExceptions;
import de.fh.albsig0.resources.CovidParent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
public class CovidController {

    protected static final Logger JSON_LOGGER = LogManager.getLogger();

    @Inject
    public JsonReader jr = new JsonReader();

    @RequestMapping("/countries/{country_name}")
    public CovidParent returnCountry(@PathVariable("country_name") String name) {
        try {
            JSONObject response = jr.readJsonFromUrl("https://covid2019-api.herokuapp.com/v2/country/" + name);
            JSONObject data = (JSONObject) response.get("data");
            JSON_LOGGER.debug("Got JSON Data as " + data.toString());
            Country country = new Country(name, data.getInt("confirmed"), data.getInt("deaths"), data.getInt("recovered"), data.getInt("active"));
            JSON_LOGGER.debug("Returning country as " + country.toString());
            return country;
        } catch (JSONException exception) {
            return new CovidExceptions("API error");
        } catch (NullPointerException exception) {
            return new CovidExceptions("Country not found! Use English country name");
        } catch (IOException exception) {
            return new CovidExceptions("Could not read from the API");
        } catch (ClassCastException exception) {
            return new CovidExceptions("Country not found! Use English country name");
        }
    }
}

package de.fh.albsig0.controller;

import de.fh.albsig0.JsonReader;
import de.fh.albsig0.resources.Country;
import de.fh.albsig0.resources.CovidExceptions;
import de.fh.albsig0.resources.CovidParent;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CovidController {
    @RequestMapping("/countries/{country_name}")
    public CovidParent returnCountry(@PathVariable("country_name") String name) {
        try {
            JSONObject response = JsonReader.readJsonFromUrl("https://covid2019-api.herokuapp.com/v2/country/" + name);
            JSONObject data = (JSONObject) response.get("data");
            Country country = new Country(name, data.getInt("confirmed"), data.getInt("deaths"), data.getInt("recovered"), data.getInt("active"));
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

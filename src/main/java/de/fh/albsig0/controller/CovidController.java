package de.fh.albsig0.controller;

import de.fh.albsig0.JsonReader;
import de.fh.albsig0.resources.Country;
import de.fh.albsig0.resources.CovidExceptions;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class CovidController {

    @RequestMapping("/test")
    public String test() {
        return "Hallo welt";
    }

    @RequestMapping("c/{country_name}")
    public Country trythis(@PathVariable("country_name") String name) throws IOException {
        JsonReader jr = new JsonReader();
        JSONObject response = jr.readJsonFromUrl("https://covid2019-api.herokuapp.com/v2/country/" + name);
        JSONObject data = (JSONObject) response.get("data");
        Country country = new Country(name, data.getInt("confirmed"), data.getInt("deaths"), data.getInt("recovered"), data.getInt("active"));
        return country;
    }

    @RequestMapping("/countries/{country_name}")
    public ModelAndView returnCountry(@PathVariable("country_name") String name) {
        try {
            JSONObject response = JsonReader.readJsonFromUrl("https://covid2019-api.herokuapp.com/v2/country/" + name);
            JSONObject data = (JSONObject) response.get("data");
            Country country = new Country(name, data.getInt("confirmed"), data.getInt("deaths"), data.getInt("recovered"), data.getInt("active"));
            return country;
        } catch (JSONException exception) {
            return new CovidExceptions("API error");
        } catch (NullPointerException exception) {
            return new CovidExceptions("Country not found! Use English country code");
        } catch (IOException exception) {
            return new CovidExceptions("Could not read from the API");
        }
    }
}

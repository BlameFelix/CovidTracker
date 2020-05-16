package de.fh.albsig0.controller;

import de.fh.albsig0.JsonReader;
import de.fh.albsig0.entity.Country;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CovidController {

    @RequestMapping("/countries/{country_name}")
    public Country returnCountry(@PathVariable("country_name") String name) throws IOException {
        JsonReader jr = new JsonReader();
        JSONObject response = jr.readJsonFromUrl("https://covid2019-api.herokuapp.com/v2/country/" + name);
        JSONObject data = (JSONObject) response.get("data");
        Country country = new Country(name, data.getInt("confirmed"), data.getInt("deaths"), data.getInt("recovered"), data.getInt("active"));
        return country;
    }
}

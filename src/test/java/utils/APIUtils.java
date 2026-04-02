package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.stream.Collectors;

public class APIUtils {

    public List<String> getFlightsFromAPI() {

        String url = "https://mocki.io/v1/f446336e-7e56-4143-8637-97b4d6b36476";

        Response response = RestAssured.get(url);

        List<String> flights = response.jsonPath()
                .getList("flights.flight_number");

        return flights;
    }
}
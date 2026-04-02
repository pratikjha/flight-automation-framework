package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pages.FlightPage;
import db.DBUtils;
import org.testng.Assert;
import java.util.List;
import utils.APIUtils;




public class FlightStepDefs {

    @Given("user is on flight search page")
    public void openFlightPage() {
        Hooks.driver.get("file:///Users/pratikjha/Desktop/flight_search.html");
    }

    FlightPage page = new FlightPage();

    @When("user searches flights from {string} to {string}")
    public void searchFlights(String from, String to) {
        page.search(from, to);
    }

    @Then("flights should be displayed")
    public void validateFlights() {
        assert page.getFlightNumbers().size() > 0;
    }



    @Then("validate flight data with database")
    public void validateDB() throws Exception {

        DBUtils db = new DBUtils();

        List<String> uiFlights = page.getFlightNumbers();
        List<String> dbFlights = db.getFlights("DEL", "BLR");

        System.out.println("UI Flights: " + uiFlights);
        System.out.println("DB Flights: " + dbFlights);

        Assert.assertEquals(uiFlights, dbFlights);
    }

    @Then("validate flight data with api and database")
    public void validateAll() throws Exception {

        FlightPage page = new FlightPage();
        DBUtils db = new DBUtils();
        APIUtils api = new APIUtils();

        List<String> uiFlights = page.getFlightNumbers();
        List<String> dbFlights = db.getFlights("DEL", "BLR");
        List<String> apiFlights = api.getFlightsFromAPI();

        System.out.println("UI: " + uiFlights);
        System.out.println("DB: " + dbFlights);
        System.out.println("API: " + apiFlights);

        // Validation
        Assert.assertEquals(uiFlights, dbFlights);
        Assert.assertEquals(uiFlights, apiFlights);
    }

    @Then("validate flight data with api only")
    public void validateAPIOnly() {

        APIUtils api = new APIUtils();
        List<String> apiFlights = api.getFlightsFromAPI();

        Assert.assertTrue(apiFlights.size() > 0);
    }
}

package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class CompletionSteps {

    private Response response;
    private RequestSpecification request;

    @Given("User has the todo tasks")
    public void user_has_the_todo_tasks() {
        request = given().baseUri("http://jsonplaceholder.typicode.com");
        response = request.get("/todos");
    }

    public void user_belongs_to_the_city_FanCode() {
        response = given().baseUri("http://jsonplaceholder.typicode.com").get("/users");

        response.then().statusCode(200);

       response = response.then().extract().response();
        int filteredUsersCount = 0;

        // Parse JSON response and filter users
        for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
            double latitude = Double.parseDouble(response.jsonPath().getString("[" + i + "].address.geo.lat"));
            double longitude = Double.parseDouble(response.jsonPath().getString("[" + i + "].address.geo.lng"));

            if (latitude >= -40 && latitude <= 5 && longitude >= 5 && longitude <= 100) {
                // User belongs to FanCode city
                filteredUsersCount++;
                       }
        }

        System.out.println("Number of users belonging to FanCode city: " + filteredUsersCount);
    }

    @Then("User Completed task percentage should be greater than 50%")
    public void user_completed_task_percentage_should_be_greater_than_50() {
        // Calculate completed task percentage
        response.then().statusCode(200);
        int totalTodos = response.jsonPath().getList("$").size();
        int completedTodos = response.jsonPath().getList("$").stream().filter(todo -> todo.getBoolean("completed")).toList().size();
        double completionPercentage = (completedTodos * 100.0) / totalTodos;
        Assert.assertTrue("Completion percentage should be greater than 50%", completionPercentage > 50);
    }
}

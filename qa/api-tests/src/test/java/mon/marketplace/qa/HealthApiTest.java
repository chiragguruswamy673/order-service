package mon.marketplace.qa;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HealthApiTest {

    @BeforeClass
    public void setup() {
        io.restassured.RestAssured.baseURI = "http://localhost";
        io.restassured.RestAssured.port = 8080;
    }

    @Test
    public void healthEndpoint_shouldReturnUp() {
        given()
                .when()
                .get("/actuator/health")
                .then()
                .statusCode(200)
                .body("status", equalTo("UP"));
    }
}
package mon.marketplace.qa;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class OrderApiTest {

    @BeforeClass
    public void setup() {
        io.restassured.RestAssured.baseURI = "http://localhost";
        io.restassured.RestAssured.port = 8080;
    }

    @Test
    public void createOrder_thenRetrieveIt() {
        Map<String,Object> payload = Map.of(
                "userId", "u-123",
                "items", Map.of("sku-1", 2),
                "total", 999.00
        );

        String id = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/orders")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .get("/orders/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id));
    }

    @Test
    public void createOrder_missingUserId_shouldFail() {
        String payload = """
        {
          "items": {"sku-1": 2},
          "total": 999.00
        }
        """;

        given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/orders")
                .then()
                .statusCode(400)
                .body("error", containsString("userId"));
    }

    @Test
    public void listOrders_shouldReturnArray() {
        given()
                .get("/orders")
                .then()
                .statusCode(200)
                .body("$", notNullValue());
    }

    @Test
    public void deleteOrder_thenNotFound() {
        String id = given()
                .contentType(ContentType.JSON)
                .body(Map.of("userId","u-del","items",Map.of("sku-2",1),"total",10))
                .post("/orders")
                .then()
                .statusCode(201)
                .extract().path("id");

        given().delete("/orders/" + id).then().statusCode(204);

        given().get("/orders/" + id).then().statusCode(404);
    }

    @Test
    public void updateOrderStatus_shouldWork() {
        String id = given()
                .contentType(ContentType.JSON)
                .body(Map.of("userId","u-upd","items",Map.of("sku-3",1),"total",20))
                .post("/orders")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .contentType(ContentType.TEXT)
                .body("shipped")
                .patch("/orders/" + id + "/status")
                .then()
                .statusCode(200)
                .body("status", equalTo("shipped"));
    }
}
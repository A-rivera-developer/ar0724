package junit.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.tool.store.StoreApplication;
import com.tool.store.dto.RentalAgreementDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = StoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTests {

    @LocalServerPort
    int randomServerPort;
    @Autowired
    private TestRestTemplate restTemplate;
    private final ObjectMapper objectMapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    /*
  Test will send requests from the resources/requests folder and compare them to files from the resources/response folder.
  The format should be "req" followed by an ID for the requests and "resp" followed by a matching ID for the responses.
  Example:
      Test case 1:
          Request Json is in file: req01
          Expected Response json is in the file: resp01
   For this example only the status for bad request is being checked to simplify the case
   */
    @Test
    public void shouldReturnBadRequestErrorSinceDiscountIsGreaterThan100() throws URISyntaxException, IOException {
        // Build URL
        String baseUrl = "http://localhost:" + randomServerPort + "/checkout";
        URI uri = new URI(baseUrl);
        // Set header content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Read request json file
        String jsonReq = Files.lines(Paths.get("src/test/resources/requests/req01.json")).collect(Collectors.joining("\n"));
        HttpEntity<String> request = new HttpEntity<>(jsonReq, headers);
        // Post json request from file to endpoint
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        // Check for bad request status (discount was invalid)
        assertSame(result.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    /*
    Test will send requests from the resources/requests folder and compare them to files from the resources/response folder.
    The format should be "req" followed by an ID for the requests and "resp" followed by a matching ID for the responses.
    Example:
        Test case 1:
            Request Json is in file: req01
            Expected Response json is in the file: resp01
     */
    @ParameterizedTest
    @CsvSource({"req02,resp02", "req03,resp03", "req04,resp04", "req05,resp05", "req06,resp06"})
    public void shouldTestVariousScenariosFromJsonRequestFiles(String requestJson, String responseJson) throws URISyntaxException, IOException {
        // Build URL
        String baseUrl = "http://localhost:" + randomServerPort + "/checkout";
        URI uri = new URI(baseUrl);
        // Set header content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Read request json file
        String jsonReq = Files.lines(Paths.get("src/test/resources/requests/" + requestJson + ".json")).collect(Collectors.joining("\n"));
        HttpEntity<String> request = new HttpEntity<>(jsonReq, headers);

        // Post json request from file to endpoint
        ResponseEntity<RentalAgreementDto> result = this.restTemplate.postForEntity(uri, request, RentalAgreementDto.class);

        // Read Expected result json file
        String expectedJsonResp = Files.lines(Paths.get("src/test/resources/responses/" + responseJson + ".json")).collect(Collectors.joining("\n")).replaceAll("\\s", "");
        RentalAgreementDto expectedDto = objectMapper.readValue(expectedJsonResp, RentalAgreementDto.class);

        // Compare expected result to actual result from endpoint
        assertThat(expectedDto).usingRecursiveComparison().isEqualTo(result.getBody());
    }
}

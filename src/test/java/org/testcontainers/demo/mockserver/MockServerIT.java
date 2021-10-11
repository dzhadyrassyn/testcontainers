package org.testcontainers.demo.mockserver;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.testcontainers.demo.AbstractIntegrationTest;
import org.testcontainers.demo.data.Subject;
import org.testcontainers.demo.rest.SubjectRestService;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockServerIT extends AbstractIntegrationTest {

    @Autowired
    private SubjectRestService subjectRestService;

    @Test
    public void test() {

        MockServerClient mockServerClient = new MockServerClient(mockServer.getHost(), mockServer.getServerPort());
        String responseBody = "[{\"name\":\"Math\"},{\"name\":\"Russian\"}]";
        mockServerClient
            .when(request()
                .withPath("/subjects"))
            .respond(response()
                .withHeader("Content-type", MediaType.APPLICATION_JSON_VALUE)
                .withBody(responseBody));

        List<Subject> subjects = subjectRestService.fetchAllGrades();
        assertThat(subjects).isNotNull();
        assertThat(subjects.size()).isEqualTo(2);

        DocumentContext context = JsonPath.parse(responseBody);
        assertThat((String) context.read("$[0].name")).isEqualTo(subjects.get(0).getName());
        assertThat((String) context.read("$[1].name")).isEqualTo(subjects.get(1).getName());
    }
}

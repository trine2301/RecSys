package recsys.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

import jakarta.inject.Inject

@MicronautTest
class TransactionControllerSpec extends Specification {

    @Inject
    @Client("/transactions")
    HttpClient client

    void "test get accounting transactions"() {
        when:
            HttpRequest request = HttpRequest.GET('/accounting')
            String response = client.toBlocking().retrieve(request)

        then:
            response != null
    }

    void "test get bank transactions"() {
        when:
            HttpRequest request = HttpRequest.GET('/bank')
            String response = client.toBlocking().retrieve(request)

        then:
            response != null
    }

    void "test get period comparisons"() {
        when:
            HttpRequest request = HttpRequest.GET('/period_comparison')
            String response = client.toBlocking().retrieve(request)

        then:
            response != null
    }
}

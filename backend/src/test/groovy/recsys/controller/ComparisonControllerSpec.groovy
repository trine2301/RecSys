package recsys.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import recsys.service.TransactionService
import spock.lang.Specification

import jakarta.inject.Inject
import java.time.LocalDate

@MicronautTest
class ComparisonControllerSpec extends Specification {

    @Inject
    @Client("/period_comparison")
    HttpClient client

    @Inject
    TransactionService transactionService

    def "test getDiscrepancy endpoint"() {
        given: "A start and end date"
            LocalDate startDate = LocalDate.parse("2023-01-01")
            LocalDate endDate = LocalDate.parse("2023-01-31")

        and: "A discrepancy amount returned by the service"
            transactionService.getDiscrepancyAmount(startDate, endDate) >> 100.0

        when: "We call the getDiscrepancy endpoint"
            HttpRequest request = HttpRequest.GET("/total_discrepancy?startDate=${startDate}&endDate=${endDate}")
            double result = client.toBlocking().retrieve(request, Double.class)

        then: "We expect the discrepancy amount returned by the service"
            result == 100.0
    }


}

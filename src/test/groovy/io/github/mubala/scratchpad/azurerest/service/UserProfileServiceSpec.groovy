package io.github.mubala.scratchpad.azurerest.service

import io.github.mubala.scratchpad.azurerest.Application
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

@MicronautTest(application = Application.class)
class UserProfileServiceSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @Shared
    @AutoCleanup
    RxHttpClient rxClient = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    void 'test save a document '() {
        given:
        String  []list = ["db1", "db2", "db3" ]

        when:
        HttpRequest request = HttpRequest.POST('/databases', list)
        String []response = rxClient.toBlocking().retrieve(request)

        then:
        response == list
    }
}

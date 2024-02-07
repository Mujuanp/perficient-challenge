package com.perficient.challenge.error;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.exceptions.BeanInstantiationException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * A Handler for all Exceptions that occurred into application.
 */
@Slf4j
@Singleton
@RequiredArgsConstructor
@Requires(classes = {Exception.class, ExceptionHandler.class})
@SerdeImport(BeanInstantiationException.class)
public class ChallengeExceptionHandler implements ExceptionHandler<Exception, HttpResponse<ErrorMessage>> {


    public HttpResponse<ErrorMessage> handle(HttpRequest request, Exception exception) {
        log.error("Handled exception: ", exception);

        ErrorMessage message = new ErrorMessage();
        message.setMessage(
                exception instanceof BeanInstantiationException ?
                        exception.getCause().getLocalizedMessage() :
                        exception.getMessage());
        message.setStatus(false);
        return HttpResponse.serverError(message).status(HttpStatus.BAD_REQUEST);
    }

}

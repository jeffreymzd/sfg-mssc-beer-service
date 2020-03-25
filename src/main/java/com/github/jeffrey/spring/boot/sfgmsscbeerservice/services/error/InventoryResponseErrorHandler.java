package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.error;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by jeffreymzd on 3/24/20
 */
@Component
public class InventoryResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            // handle SERVER_ERROR
        } else if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException();
            }
        }
    }
}
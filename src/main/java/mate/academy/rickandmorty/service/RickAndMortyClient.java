package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ResponseCharacterDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyResponseDataDto;
import mate.academy.rickandmorty.exception.ApiException;
import mate.academy.rickandmorty.exception.HttpResponseException;
import mate.academy.rickandmorty.exception.ObjectMapperException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyClient {
    private static final String GET_ALL_CHARACTERS_URL =
            "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public List<ResponseCharacterDto> getAllCharacters() {
        String nextUrl = GET_ALL_CHARACTERS_URL;
        List<ResponseCharacterDto> characters = new ArrayList<>();
        while (nextUrl != null) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                                .GET()
                                .uri(URI.create(nextUrl))
                                .build();
            RickAndMortyResponseDataDto responseDataDto = getResponseObject(httpRequest);
            nextUrl = responseDataDto.getInfo().getNext();
            characters.addAll(responseDataDto.getCharacters());
        }
        return characters;
    }

    private RickAndMortyResponseDataDto getResponseObject(HttpRequest httpRequest) {
        try {
            return objectMapper.readValue(
                    getHttpResponse(httpRequest).body(),
                    RickAndMortyResponseDataDto.class);
        } catch (JsonProcessingException e) {
            throw new ObjectMapperException("URL: " + httpRequest.uri()
                    + " Failed to read httpResponse: ", e);
        }
    }

    private HttpResponse<String> getHttpResponse(HttpRequest httpRequest) {
        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new HttpResponseException("Received non-200 status code. "
                        + "Response status code: "
                        + response.statusCode());
            }
            return response;
        } catch (IOException | InterruptedException e) {
            throw new ApiException("URL: "
                    + httpRequest.uri()
                    + " Cannot get all characters from API: ", e);
        }
    }
}

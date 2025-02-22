package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParameters;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty characters API",
        description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RequestMapping("/characters")
@RestController
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get a random character",
            description = "Returns a random character from Rick and Morty universe",
            responses = {
                @ApiResponse(responseCode = "200",
                    description = "Character successfully retrieved",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDto.class)))})
    @GetMapping("/random")
    CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Find characters by name",
            description = "Returns characters based on search parameter such as name",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Character(s) successfully retrieved",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CharacterDto.class)
                            )
                    )
            })
    @GetMapping("/search")
    List<CharacterDto> search(
            @Parameter(description = "Search parameter, name or "
                    + "part of a name, to look for", example = "Alice")
            CharacterSearchParameters searchParameters) {
        return characterService.search(searchParameters);
    }
}

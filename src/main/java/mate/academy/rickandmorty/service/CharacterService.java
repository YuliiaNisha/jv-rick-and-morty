package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParameters;

public interface CharacterService {
    void fetchToDb();

    CharacterDto getRandomCharacter();

    List<CharacterDto> search(CharacterSearchParameters searchParameters);
}

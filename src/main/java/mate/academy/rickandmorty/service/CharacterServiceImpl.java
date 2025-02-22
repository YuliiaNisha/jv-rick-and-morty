package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ResponseCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParameters;
import mate.academy.rickandmorty.exception.CharacterNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final SpecificationBuilder<Character, CharacterSearchParameters> specificationBuilder;

    @Override
    public void fetchToDb() {
        List<ResponseCharacterDto> charactersFromApi = rickAndMortyClient.getAllCharacters();
        characterRepository.saveAll(
                characterMapper.toModelList(charactersFromApi)
        );
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long randomId = new Random().nextInt((int) (characterRepository.count() + 1));
        Character randomCharacter =
                characterRepository.findById(randomId)
                        .orElseThrow(
                                () -> new CharacterNotFoundException(
                                        "Can't get random character from DB by id: "
                                                + randomId
                                ));
        return characterMapper.toDto(randomCharacter);
    }

    @Override
    public List<CharacterDto> search(CharacterSearchParameters searchParameters) {
        Specification<Character> specification = specificationBuilder.build(searchParameters);
        return characterRepository.findAll(specification)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}

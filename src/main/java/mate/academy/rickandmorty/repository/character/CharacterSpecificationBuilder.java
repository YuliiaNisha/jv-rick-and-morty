package mate.academy.rickandmorty.repository.character;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParameters;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterSpecificationBuilder
        implements SpecificationBuilder<Character, CharacterSearchParameters> {
    private final SpecificationProviderManager<Character> specificationProviderManager;

    @Override
    public Specification<Character> build(CharacterSearchParameters searchParameters) {
        if (searchParameters == null) {
            throw new IllegalArgumentException("Search Parameters cannot be null");
        }
        Specification<Character> specification = Specification.where(null);
        if (!searchParameters.getName().isEmpty()) {
            specification = specificationProviderManager.getSpecificationProvider(
                    Character.SpecificationKey.NAME.getValue()
            )
                    .getSpecification(searchParameters.getName());
        }
        return specification;
    }
}

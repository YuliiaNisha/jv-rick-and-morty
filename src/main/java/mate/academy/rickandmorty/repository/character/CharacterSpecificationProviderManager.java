package mate.academy.rickandmorty.repository.character;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterSpecificationProviderManager
        implements SpecificationProviderManager<Character> {
    private final List<SpecificationProvider<Character>> specificationProviders;

    @Override
    public SpecificationProvider<Character> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException("Can't provide specification "
                        + "for key: " + key)
                );
    }
}

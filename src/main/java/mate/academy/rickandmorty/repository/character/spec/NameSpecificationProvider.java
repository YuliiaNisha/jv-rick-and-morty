package mate.academy.rickandmorty.repository.character.spec;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecificationProvider implements SpecificationProvider<Character> {

    @Override
    public String getKey() {
        return Character.SpecificationKey.NAME.getValue();
    }

    @Override
    public Specification<Character> getSpecification(String param) {
        return new Specification<Character>() {
            @Override
            public Predicate toPredicate(Root<Character> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + param.toLowerCase() + "%");
            }
        };
    }
}

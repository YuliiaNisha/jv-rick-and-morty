package mate.academy.rickandmorty.repository;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T, S> {
    Specification<T> build(S searchParameters);
}

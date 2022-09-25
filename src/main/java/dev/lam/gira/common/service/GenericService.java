package dev.lam.gira.common.service;

import dev.lam.gira.common.model.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface GenericService <T extends BaseEntity, D, I> {
    JpaRepository<T, I> getRepository(); // Factory method
    ModelMapper getModelMapper();

    default List<T> findAll() {
        return getRepository().findAll();
    }

    default List<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable).stream().collect(Collectors.toList());
    }

    default Optional<T> findById(I id) {
        return getRepository().findById(id);
    }

    default List<D> findAllDTO(Class<D> clazz) {
        return getRepository().findAll()
                .stream()
                .map(entity -> getModelMapper().map(entity, clazz))
                .collect(Collectors.toList());
    }

    default T save(T entity) {
        return getRepository().save(entity);
    }

    default void deleteById(I id) {
        getRepository().deleteById(id);
    }

    default T update(T entity, I id) {
        return getRepository().save(entity);
    }
}

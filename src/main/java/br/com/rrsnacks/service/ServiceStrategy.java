package br.com.rrsnacks.service;

import java.util.List;
import java.util.Optional;

public interface ServiceStrategy<T> {

    List<T> getAll();

    Optional<T> getById(Long id);

    T create(T t);
}

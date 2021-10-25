package com.sda.eventine.repository;

import com.sda.eventine.model.SomeModel;
import org.springframework.data.repository.CrudRepository;

public interface Repo extends CrudRepository<SomeModel, Long> {
}

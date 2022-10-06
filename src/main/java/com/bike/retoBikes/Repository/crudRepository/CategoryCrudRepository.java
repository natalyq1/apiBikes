package com.bike.retoBikes.Repository.crudRepository;

import com.bike.retoBikes.Model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryCrudRepository extends CrudRepository<Category, Integer> {
}

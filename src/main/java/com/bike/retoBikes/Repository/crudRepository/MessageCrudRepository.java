package com.bike.retoBikes.Repository.crudRepository;

import com.bike.retoBikes.Model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCrudRepository extends CrudRepository<Message,Integer> {
}

package com.backend.backend.repository;

import com.backend.backend.model.Text;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends MongoRepository<Text, String> {


}

package com.example.demo.repository;

import com.example.demo.data.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends MongoRepository<UserData, String> {

}

package com.example.demo.service;

import java.util.ArrayList;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import com.example.demo.data.UserData;
import com.example.demo.repository.UserDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDataService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserDataRepository userDataRepository;

    public UserData setUser(String username, int age) {
        UserData newUser = this.findUserByUsername(username);
        // check if there is already a user
        if (newUser == null) {
            newUser = new UserData();
            newUser.setUsername(username);
        }
        newUser.setAge(age);
        mongoTemplate.save(newUser);
        return newUser;
    }

    public UserData findUserByUsername(String username) {
        return mongoTemplate.findById(username, UserData.class);
    }

    public boolean removeUser(String username) {
        UserData res = this.findUserByUsername(username);
        if (res != null) {
            mongoTemplate.remove(res);
        } else {
            return false;
        }
        return true;
    }

}

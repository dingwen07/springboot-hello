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
import com.google.gson.Gson;


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
        UserData newUser = this._findUserByUsername(username);
        // check if there is already a user
        if (newUser == null) {
            newUser = new UserData();
            newUser.setUsername(username);
        }
        newUser.setAge(age);
        mongoTemplate.save(newUser);
        // delete redis cache
        redisTemplate.delete(username);
        return newUser;
    }

    public UserData findUserByUsername(String username) {
        // check if there is already a user in Redis cache
        String cachedUser = redisTemplate.opsForValue().get(username);
        if (cachedUser != null) {
            return new Gson().fromJson(cachedUser, UserData.class);
        }
        // if not, query from MongoDB
        UserData user = this._findUserByUsername(username);
        // save to Redis cache
        if (user != null) {
            redisTemplate.opsForValue().set(username, new Gson().toJson(user));
        }
        return user;
    }

    public boolean removeUser(String username) {
        UserData res = this._findUserByUsername(username);
        if (res != null) {
            mongoTemplate.remove(res);
        } else {
            return false;
        }
        // delete from redis cache
        redisTemplate.delete(username);
        return true;
    }

    private UserData _findUserByUsername(String username) {
        return mongoTemplate.findById(username, UserData.class);
    }

}

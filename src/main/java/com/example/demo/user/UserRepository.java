package com.example.demo.user;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangbin
 */
@Repository
public class UserRepository {
  private Map<Integer, User> users = new HashMap<>();

  public User findById(Integer userId) {
    return users.get(userId);
  }

  public List<User> findAll() {
    return users.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
  }

  public void save(User user) {
    users.put(user.getId(), user);
  }
}

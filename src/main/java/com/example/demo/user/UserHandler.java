package com.example.demo.user;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;


/**
 * @author wangbin
 */
@Service
public class UserHandler {
  private UserRepository userRepository;

  public UserHandler(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
    List<User> all = userRepository.findAll();
    return ok().body(Flux.fromIterable(all), User.class);
  }

  public Mono<ServerResponse> save(ServerRequest serverRequest) {
    Mono<User> userMono = serverRequest.bodyToMono(User.class);
    User block = userMono.block();
    User byId = userRepository.findById(block.getId());
    if (!ObjectUtils.isEmpty(byId)) {
      throw new UserExistException();
    }
    userRepository.save(block);
    return ok().build();
  }
}

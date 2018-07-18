package com.example.demo.routes;

import com.example.demo.user.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

/**
 * @author wangbin
 */
@Configuration
public class UserRouter {
  @Bean
  public RouterFunction<?> route(UserHandler userHandler) {
    return RouterFunctions
            .route(GET("/user"), userHandler::findAll)
            .andRoute(POST("/user"), userHandler::save)
            ;
  }
}

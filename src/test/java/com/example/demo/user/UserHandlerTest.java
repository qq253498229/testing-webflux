package com.example.demo.user;

import com.example.demo.routes.UserRouter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) // 标记测试类
@WebFluxTest(UserHandler.class) // 自动注入WebTestClient
@Import(UserRouter.class) // 引入路由
//@FixMethodOrder(NAME_ASCENDING) // 按照方法名顺序执行
public class UserHandlerTest {

  @Autowired
  private WebTestClient client;

  @MockBean
  private UserRepository userRepository;

  /**
   * 测试查询所有用户方法
   */
  @Test
  public void findAll_ok() {
    List<User> users = Arrays.asList(new User(1, "张三"), new User(2, "李四"));
    when(userRepository.findAll()).thenReturn(users);

    client.get().uri("/user")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(User.class)
            .hasSize(2);
  }

  /**
   * 测试保存用户方法
   */
  @Test
  public void save_ok() {

    client.post().uri("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(new User(1, "张三")))
            .exchange()
            .expectStatus().isOk()
    ;
  }

  /**
   * 测试用户已存在异常
   */
  @Test
  public void save_userExist() {
    when(userRepository.findById(any())).thenReturn(new User(1, "张三"));

    client.post().uri("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(new User(1, "张三")))
            .exchange()
            .expectStatus().is4xxClientError()
    ;
  }
}

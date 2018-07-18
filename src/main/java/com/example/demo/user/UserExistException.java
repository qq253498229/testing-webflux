package com.example.demo.user;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * @author wangbin
 */
@ResponseStatus(CONFLICT)
public class UserExistException extends RuntimeException {
}

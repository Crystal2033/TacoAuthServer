package com.auth.server.repository;

import org.springframework.data.repository.CrudRepository;
import com.auth.server.models.User;
/**
 * @project AuthServer
 * ©Crystal2033
 * @date 12/08/2023
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

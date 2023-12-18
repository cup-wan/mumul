package com.example.mumul.domain.user.dao;

import com.example.mumul.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = User.class)
public interface UserRepository extends JpaRepository<User, String> {
}

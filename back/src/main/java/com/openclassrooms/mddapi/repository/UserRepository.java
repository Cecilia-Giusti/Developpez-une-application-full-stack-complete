package com.openclassrooms.mddapi.repository;
import com.openclassrooms.mddapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import javax.validation.constraints.NotNull;

public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByEmail(String email);

    @NotNull User getById(@NotNull Integer id);

}

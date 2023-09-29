package com.thanksen.osahaneat.Reponsitory;

import com.thanksen.osahaneat.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
    List<UsersEntity> findByUsernameAndPassword(String username, String  password);

    UsersEntity findByUsername(String username);
}

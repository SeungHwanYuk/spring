package dw.gameshop.repository;

import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> { // 받는 타입과 키의 타입 반드시 확인
    Optional<User> findByUserId(String userId);

}

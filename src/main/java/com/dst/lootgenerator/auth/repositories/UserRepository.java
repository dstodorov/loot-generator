package com.dst.lootgenerator.auth.repositories;

import com.dst.lootgenerator.auth.models.User;
import com.dst.lootgenerator.player.models.dtos.PlayerHeroDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findByResetPasswordToken(String token);

    @Query("SELECT new com.dst.lootgenerator.player.models.dtos.PlayerHeroDto(h.id, h.name, h.level, h.heroClass) " +
            "FROM User u JOIN u.heroes h WHERE u.id = :id")
    List<PlayerHeroDto> findPlayerHeroesBaseInfo(@Param("id") Long id);
}

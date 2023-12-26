package com.WebApplication.App.Repositary;
import com.WebApplication.App.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppRepositary extends JpaRepository<User,Integer> {
    List<User> findByUsernameContainingIgnoreCase(String keyword);

}

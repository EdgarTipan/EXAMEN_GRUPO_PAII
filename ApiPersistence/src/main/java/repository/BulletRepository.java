package repository;

import com.example.galaga.galaga.entities.Bullet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulletRepository extends JpaRepository<Bullet, Long> {
}

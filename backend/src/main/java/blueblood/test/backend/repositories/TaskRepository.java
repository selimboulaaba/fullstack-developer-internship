package blueblood.test.backend.repositories;

import blueblood.test.backend.entities.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser_Id(Long userId, Sort sort);
}

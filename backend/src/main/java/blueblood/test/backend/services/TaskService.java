package blueblood.test.backend.services;

import blueblood.test.backend.DTOs.TaskRequest;
import blueblood.test.backend.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getUserTasks(Long userId);
    Optional<Task> getTaskById (Long taskId);
    Task addtask (TaskRequest taskRequest, Long userId);
    Task updateTask (TaskRequest taskRequest, Long taskId, Long userId);
    void deleteTask (Long userId, Long taskId);
}

package blueblood.test.backend.services;

import blueblood.test.backend.DTOs.TaskRequest;
import blueblood.test.backend.entities.Task;
import blueblood.test.backend.entities.Utilisateur;
import blueblood.test.backend.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    @Override
    public List<Task> getUserTasks(Long userId) {
        return taskRepository.findByUser_Id(userId, Sort.by(Sort.Order.desc("id")));
    }

    @Override
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Task addtask(TaskRequest taskRequest, Long userId) {
        Optional<Utilisateur> user = userService.getUserById(userId);
        if (user.isPresent()) {
            Task task = new Task(taskRequest.getTitle(), taskRequest.getDescription(), user.get());
            return taskRepository.save(task);
        }else {
            throw new RuntimeException("User not found!");
        }
    }

    @Override
    public Task updateTask(TaskRequest taskRequest, Long taskId, Long userId) {
        Optional<Task> taskOptional = getTaskById(taskId);
        if (!taskOptional.isPresent()) {
            throw new RuntimeException("Task not found!");
        }
        Optional<Utilisateur> user = userService.getUserById(userId);
        if (!user.isPresent()) {
            throw new RuntimeException("Access denied!");
        }
        Task task = taskOptional.get();

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(taskRequest.isCompleted());

        return taskRepository.saveAndFlush(task);
    }

    @Override
    public void deleteTask(Long userId, Long taskId) {
        Optional<Task> taskOptional = getTaskById(taskId);
        if (!taskOptional.isPresent()) {
            throw new RuntimeException("Task not found!");
        }
        Task task = getTaskById(taskId).get();
        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Access denied!");
        }
        taskRepository.deleteById(taskId);
    }
}

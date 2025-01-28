package blueblood.test.backend.controllers;

import blueblood.test.backend.DTOs.TaskRequest;
import blueblood.test.backend.entities.Task;
import blueblood.test.backend.entities.Utilisateur;
import blueblood.test.backend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("/task")
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<Task>> getUserTasks(Authentication authentication) {
        Long userId = ((Utilisateur) authentication.getPrincipal()).getId();

        return ResponseEntity.ok(taskService.getUserTasks(userId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(Authentication authentication, @PathVariable Long taskId) {
        Long userId = ((Utilisateur) authentication.getPrincipal()).getId();

        Optional<Task> task = taskService.getTaskById(taskId);
        if (task.isPresent()) {
            if (task.get().getUser().getId().equals(userId)) {
                return ResponseEntity.ok(task.get());
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> addtask(Authentication authentication, @RequestBody TaskRequest taskRequest) {
        try {
            Long userId = ((Utilisateur) authentication.getPrincipal()).getId();
            return ResponseEntity.ok(taskService.addtask(taskRequest, userId));
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(Authentication authentication, @RequestBody TaskRequest taskRequest, @PathVariable Long taskId) {
        try {
            Long userId = ((Utilisateur) authentication.getPrincipal()).getId();
            return ResponseEntity.ok(taskService.updateTask(taskRequest, taskId, userId));
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(Authentication authentication, @PathVariable Long taskId) {
        try {
            Long userId = ((Utilisateur) authentication.getPrincipal()).getId();
            taskService.deleteTask(userId, taskId);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }
}

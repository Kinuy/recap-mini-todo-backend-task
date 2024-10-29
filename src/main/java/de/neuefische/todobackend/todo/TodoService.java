package de.neuefische.todobackend.todo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final IdService idService;

    private final ChatGptApiService chatGptApiService;

    public TodoService(TodoRepository todoRepository, IdService idService, ChatGptApiService chatGptApiService) {
        this.todoRepository = todoRepository;
        this.idService = idService;
        this.chatGptApiService = chatGptApiService;
    }

    public List<Todo> findAllTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(NewTodo newTodo) throws Exception {
        String id = idService.randomId();

        String correctedDescription = chatGptApiService.checkTaskSpelling(newTodo.description());
        Todo todoToSave = new Todo(id, correctedDescription, newTodo.status());

        return todoRepository.save(todoToSave);
    }

    public Todo updateTodo(UpdateTodo todo, String id) {
        Todo todoToUpdate = new Todo(id, todo.description(), todo.status());

        return todoRepository.save(todoToUpdate);
    }

    public Todo findTodoById(String id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Todo with id: " + id + " not found!"));
    }

    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }
}

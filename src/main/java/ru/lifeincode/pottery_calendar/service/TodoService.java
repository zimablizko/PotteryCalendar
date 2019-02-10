package ru.lifeincode.pottery_calendar.service;

import org.springframework.stereotype.Service;
import ru.lifeincode.pottery_calendar.model.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "test", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "test", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "test", "Learn Hibernate", new Date(),
                false));
    }

    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }
}
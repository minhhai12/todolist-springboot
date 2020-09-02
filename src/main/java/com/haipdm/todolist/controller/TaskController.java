package com.haipdm.todolist.controller;

//import com.haipdm.todolist.model.Login;
import com.haipdm.todolist.model.Task;
import com.haipdm.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

//    @RequestMapping(value="/login", method = RequestMethod.GET)
//    public String getLogin(){
//
//        return "login";
//    }
//
//    @RequestMapping(value="/login", method=RequestMethod.POST)
//    public String login(@ModelAttribute(name="loginForm") Login loginForm, Model model){
//        String username= loginForm.getUsername();
//        String password= loginForm.getPassword();
//
//        if("admin".equals(username) && "admin".equals(password)){
//            return "home";
//        }
//        //if username or password is wrong
//        model.addAttribute("invalidCredentials",true);
//        //return again login page
//        return "login";
//    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    List<Task> listUnfinished = new ArrayList<>();
    List<Task> listFinished = new ArrayList<>();

    @GetMapping
    public String home(Model model) {
        List<Task> listTask = taskService.listALLTask();
        listUnfinished = new ArrayList<>();
        listFinished = new ArrayList<>();

        for (Task task : listTask) {
            if (task.getFlag_delete() == 0) {
                if (task.getStatus() == 1) {

                    listFinished.add(task);
                } else {
                    listUnfinished.add(task);
                }
            }
        }
        sortListUnfinished(listUnfinished);
        sortListFinished(listFinished);

        model.addAttribute("listTaskUnfinished", listUnfinished);
        model.addAttribute("listTaskFinished", listFinished);
        return "task/home";
    }

    @GetMapping("/create")
    public String createTask(Model model) {
        model.addAttribute("task", new Task());
        return "task/create";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model){
        Task task=taskService.get(id);
        model.addAttribute("task", task);
        return "task/detail";
    }


    @GetMapping("/update/{id}")
    public String edit(@PathVariable int id, Model model) {
        Task task = taskService.get(id);
        model.addAttribute("task", task);
        return "task/update";
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTask(@PathVariable int id) {
        return new ResponseEntity<Task>(taskService.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Task task, Model model) {
        boolean insert = taskService.insertTask(task);
        return "redirect:/task";
    }




    @PostMapping("update/{id}")
    public String update(@PathVariable int id, @ModelAttribute Task task) {
        Task taskUpdate = taskService.get(id);
        taskUpdate.setTask_name(task.getTask_name());
        taskUpdate.setDescription(task.getDescription());
        taskUpdate.setStart_date(task.getStart_date());
        taskUpdate.setEnd_date(task.getEnd_date());
        taskUpdate.setPriority(task.getPriority());

        taskService.save(taskUpdate);
        return "redirect:/task";
    }

    @GetMapping("delete/{id}")
    public String destroy(@PathVariable int id, @ModelAttribute Task task) {
        Task taskDelete = taskService.get(id);
        taskDelete.setFlag_delete(1);
        taskService.save(taskDelete);
        return "redirect:/task";
    }

    @GetMapping("unfinished_task/{id}")
    public String getUnFinishedTask(@PathVariable int id, @ModelAttribute Task task) {
        Task taskFinish = taskService.get(id);
        taskFinish.setStatus(0);
        taskService.save(taskFinish);
        return "redirect:/task";
    }

    @GetMapping("/finished_task/{id}")
    public String getFinishedTask(@PathVariable int id, @ModelAttribute Task task) {
        Task taskFinish = taskService.get(id);
        taskFinish.setStatus(1);
        taskService.save(taskFinish);
        return "redirect:/task";
    }


    public void sortListUnfinished(List<Task> list) {
        Collections.sort(list, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                Date currentDate = new Date();
                Date date1 = null;
                Date date2 = null;

                date1 = task1.getStart_date();
                date2 = task2.getStart_date();
                if ((date1.getTime() - currentDate.getTime()) > (date2.getTime() - currentDate.getTime())) {
                    return 1;
                } else if ((date1.getTime() - currentDate.getTime()) < (date2.getTime() - currentDate.getTime())) {
                    return -1;
                } else {
                    return 0;
                }

            }
        });
    }

    public void sortListFinished(List<Task> list) {
        Collections.sort(list, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task2.getEnd_date().compareTo(task1.getEnd_date());
            }
        });
    }

}

package com.haipdm.todolist.service;

import com.haipdm.todolist.model.Task;
import com.haipdm.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
    @Transactional
    public class TaskService {

        @Autowired
        private TaskRepository tr;

        public List<Task> listALLTask() {
            return tr.findAll();
        }

        public void save(Task task) {
            tr.save(task);
        }

        public Task get(Integer id) {
            return tr.findById(id).get();
        }

        public void delete(Integer id) {
            tr.deleteById(id);
        }

        public List<Task> getListUnfinished() {
            return tr.findListUnfinished();
        }

        public List<Task> getListFinished() {
            return tr.findListFinished();
        }

        public boolean insertTask(Task task){

            try {
                tr.save(task);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }

}
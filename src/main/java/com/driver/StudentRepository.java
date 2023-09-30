package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    HashMap<String,Student> studentDB = new HashMap<>();
    HashMap<String,Teacher> teacherDB = new HashMap<>();
    HashMap<String,List<String>> studentPair = new HashMap<>();
    HashMap<String,List<String>> teacherPair = new HashMap<>();

    public void addStudent(Student student) {
        studentDB.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDB.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(studentPair.get(student) == null)
        {
            List<String> ans = new ArrayList<>();
            ans.add(teacher);
            studentPair.put(student,ans);
        }
        else {
            studentPair.get(student).add(teacher);
        }

        if(teacherPair.get(teacher) == null)
        {
            List<String> ans = new ArrayList<>();
            ans.add(student);
            teacherPair.put(teacher,ans);
        }
        else {
            teacherPair.get(teacher).add(student);
        }
    }

    public Student getStudentByName(String name) {
        return studentDB.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDB.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return teacherPair.get(teacher);
    }

    public List<String> getAllStudents() {
        List<String> ans = new ArrayList<>();
        for(String name : studentDB.keySet())
        {
            ans.add(name);
        }
        return ans;
    }

    public void deleteTeacherByName(String teacher) {
        teacherDB.remove(teacher);

        List<String> studentList = teacherPair.get(teacher);

        for(String sName : studentList)
        {
            studentPair.get(sName).remove(teacher);
        }
        teacherPair.remove(teacher);
    }

    public void deleteAllTeachers() {
        for(String tName : teacherDB.keySet())
        {

        }
        teacherDB.clear();
    }
}

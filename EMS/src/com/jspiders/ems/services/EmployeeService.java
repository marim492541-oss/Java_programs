package com.jspiders.ems.services;

interface EmployeeService {
    public void addEmployee();

    public void viewEmployeeByID();
    public void viewEmployeeByName();
    public void viewEmployeeByJob();
    public void viewallEmployees();

    public void updateSal();
    public void updateJob();
    public void updateComm();

    public void deleteEmployee();
}
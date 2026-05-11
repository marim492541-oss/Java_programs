package com.jspiders.ems.main;

import com.jspiders.ems.services.EmployeeServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("PLEASE SELECT ONE OF THE OPTIONS..");
        System.out.println("    1. Add Employee");
        System.out.println("    2. View Employee");
        System.out.println("    3. Update Employee");
        System.out.println("    4. Delete Employee");
        System.out.println("    0. Exit");

        EmployeeServiceImpl empSer1 = new EmployeeServiceImpl();

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                System.out.println("Invoking addEmployee()..");
                empSer1.addEmployee();
                break;
            case 2:
                System.out.println("Choose how you want to view the employee..");
                System.out.println("    1. View Employee by Id");
                System.out.println("    2. View Employee by Name");
                System.out.println("    3. View Employee by Job");
                System.out.println("    4. View All the Employees");
                int choice2 = sc.nextInt();
                switch (choice2){
                    case 1:
                        System.out.println("Invoking viewEmployeeByEmpID()..");
                        empSer1.viewEmployeeByID();
                        break;
                    case 2:
                        System.out.println("Invoking viewEmployeeByEmpName()..");
                        empSer1.viewEmployeeByName();
                        break;
                    case 3:
                        System.out.println("Invoking viewEmployeeByEmpJob()..");
                        empSer1.viewEmployeeByJob();
                        break;
                    case 4:
                        System.out.println("Invoking viewAllEmployees()..");
                        empSer1.viewallEmployees();
                        break;
                }
                break;
            case 3:
                System.out.println("Choose what you want to update..");
                System.out.println("    1. Update Salary");
                System.out.println("    2. Update Job");
                System.out.println("    3. Update Commission");
                int  choice3 = sc.nextInt();
                switch (choice3){
                    case 1:
                        System.out.println("Invoking updateSalary()..");
                        empSer1.updateSal();
                        break;
                    case 2:
                        System.out.println("Invoking updateJob()..");
                        empSer1.updateJob();
                        break;
                    case 3:
                        System.out.println("Invoking updateCommission()..");
                        empSer1.updateComm();
                        break;
                }
                break;
            case 4:
                System.out.println("Invoking deleteEmployee()..");
                empSer1.deleteEmployee();
                break;
            default:
                 System.exit(0);
                 break;
        }
    }
}

package com.jspiders.ems.services;
import com.jspiders.ems.data.EmployeeDAO;
import com.jspiders.ems.data.EmployeeDTO;
import com.jspiders.ems.util.EmployeeDTOValidator;

import java.sql.SQLOutput;
import java.util.Scanner;
//Service class = Business Logic
public class EmployeeServiceImpl implements EmployeeService {

    private final Scanner sc = new Scanner(System.in);

    @Override
    public void addEmployee() {

        System.out.println("PLEASE ENTER EMPLOYEE DETAILS.. ");
        System.out.print("  Enter Employee ID         : ");
        Integer empID = sc.nextInt();
        sc.nextLine();
        System.out.print("  Enter Employee Name       : ");
        String eName = sc.nextLine().toUpperCase();
        System.out.print("  Enter Employee Job        : ");
        String job = sc.nextLine().toUpperCase();
        System.out.print("  Enter Employee mgr        : ");
        Integer mgr = sc.nextInt();
        System.out.print("  Enter Employee hiredate   : ");
        String hiredate = sc.next();
        System.out.print("  Enter Employee Salary     : ");
        Integer sal = sc.nextInt();
        System.out.print("  Enter Employee Commission : ");
        Integer comm = sc.nextInt();
        System.out.print("  Enter Employee deptno     : ");
        Integer deptno = sc.nextInt();
        System.out.println("EMPLOYEE ADD FEATURE ENABLED..");

        System.out.println();
        System.out.println("SAVING THE EMPLOYEE DATA IN DTO OBJECT..");
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setEmpID(empID);
        empDTO.seteName(eName);
        empDTO.setJob(job);
        empDTO.setMgr(mgr);
        empDTO.setHiredate(hiredate);
        empDTO.setSal(sal);
        empDTO.setComm(comm);
        empDTO.setDeptno(deptno);
        System.out.println("EMPLOYEE DATA SAVED IN DTO OBJECT SUCCESSFULLY..");

        System.out.println();
        System.out.println("VALIDATING THE EMPLOYEE DATA...");
        EmployeeDTOValidator.validator(empDTO);
        System.out.println("VALIDATION DONE SUCCESSFULLY..");

        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.save(empDTO); //to transfer the data from service class to DAO class.
        System.out.println();
        System.out.println("PROCESS COMPLETED!!!");

    }

    @Override
    public void viewEmployeeByID() {

        System.out.println("PLEASE ENTER EMPLOYEE DETAIL.. ");
        System.out.print("    Enter Employee ID : ");
        Integer empID = sc.nextInt();

        System.out.println("EMPLOYEE VIEW FEATURE ENABLED..");

        System.out.println();
        System.out.println("SAVING THE EMPLOYEE ID IN DTO OBJECT..");
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setEmpID(empID);
        System.out.println("EMPLOYEE ID SAVED IN DTO OBJECT SUCCESSFULLY..");

        System.out.println();
        System.out.println("VALIDATING THE EMPLOYEE ID...");
        EmployeeDTOValidator.validateId(empDTO);
        System.out.println("VALIDATION DONE SUCCESSFULLY..");

        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.viewEmployeeByEmpID(empDTO);

        System.out.println();
        System.out.println("PROCESS COMPLETED!!!");
    }

    @Override
    public void viewEmployeeByName() {
        System.out.println("PLEASE ENTER EMPLOYEE DETAIL.. ");
        System.out.print("    Enter Employee Name : ");
        String ename = sc.nextLine().toUpperCase();

        System.out.println("EMPLOYEE VIEW FEATURE ENABLED..");

        System.out.println();
        System.out.println("SAVING THE EMPLOYEE NAME IN DTO OBJECT..");
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.seteName(ename);
        System.out.println("EMPLOYEE NAME SAVED IN DTO OBJECT SUCCESSFULLY..");

        System.out.println();
        System.out.println("VALIDATING THE EMPLOYEE NAME...");
        EmployeeDTOValidator.validateName(empDTO);
        System.out.println("VALIDATION DONE SUCCESSFULLY..");

        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.viewEmployeeByEname(empDTO);
        System.out.println();
        System.out.println("PROCESS COMPLETED!!!");
    }

    @Override
    public void viewEmployeeByJob(){
        System.out.println("PLEASE ENTER EMPLOYEE DETAIL.. ");
        System.out.print("    Enter Employee Job : ");
        String job = sc.nextLine().toUpperCase();

        System.out.println("EMPLOYEE VIEW FEATURE ENABLED..");

        System.out.println();
        System.out.println("SAVING THE EMPLOYEE JOB IN DTO OBJECT..");
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setJob(job);
        System.out.println("EMPLOYEE JOB SAVED IN DTO OBJECT SUCCESSFULLY..");

        System.out.println();
        System.out.println("VALIDATING THE EMPLOYEE JOB...");
        EmployeeDTOValidator.validateJob(empDTO);
        System.out.println("VALIDATION DONE SUCCESSFULLY..");

        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.viewEmployeeByJob(empDTO);
        System.out.println();
        System.out.println("PROCESS COMPLETED!!!");
    }

    @Override
    public void viewallEmployees(){
        System.out.println();
        System.out.println("EMPLOYEE VIEW FEATURE ENABLED..");

        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.viewAllEmployees();

        System.out.println();
        System.out.println("PROCESS COMPLETED!!!");
    }

    @Override
    public void updateSal(){
        System.out.println("PLEASE ENTER EMPLOYEE DETAILS.. ");
        System.out.print("    Enter Employee Salary : ");
        Integer salary = sc.nextInt();
        System.out.print("    Enter Employee Id     :");
        Integer empID = sc.nextInt();

        System.out.println("EMPLOYEE UPDATE FEATURE ENABLED..");

        System.out.println();
        System.out.println("SAVING THE EMPLOYEE DATA IN DTO OBJECT..");
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setSal(salary);
        empDTO.setEmpID(empID);
        System.out.println("EMPLOYEE DATA SAVED IN DTO OBJECT SUCCESSFULLY..");

        System.out.println();
        System.out.println("VALIDATING THE EMPLOYEE DATA...");
        EmployeeDTOValidator.validateSal(empDTO);
        EmployeeDTOValidator.validateId(empDTO);
        System.out.println("VALIDATION DONE SUCCESSFULLY..");

        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.updateSal(empDTO);
        System.out.println();
        System.out.println("PROCESS COMPLETED!!!");

    }

    @Override
    public void updateComm(){
        System.out.println("PLEASE ENTER EMPLOYEE DETAILS.. ");
        System.out.print("    Enter Employee Commission : ");
        Integer commission = sc.nextInt();
        System.out.print("    Enter Employee Id         : ");
        Integer empID = sc.nextInt();

        System.out.println("EMPLOYEE UPDATE FEATURE ENABLED..");

        System.out.println();
        System.out.println("SAVING THE EMPLOYEE DATA IN DTO OBJECT..");
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setComm(commission);
        empDTO.setEmpID(empID);
        System.out.println("EMPLOYEE DATA SAVED IN DTO OBJECT SUCCESSFULLY..");

        System.out.println();
        System.out.println("VALIDATING THE EMPLOYEE DATA...");
        EmployeeDTOValidator.validateComm(empDTO);
        EmployeeDTOValidator.validateId(empDTO);
        System.out.println("VALIDATION DONE SUCCESSFULLY..");

        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.updateComm(empDTO);
        System.out.println();
        System.out.println("PROCESS COMPLETED!!!");
    }

    @Override
    public void updateJob(){
        System.out.println("PLEASE ENTER EMPLOYEE DETAILS.. ");

        System.out.println("    Enter Employee Job : ");
        String job = sc.next().toUpperCase();
        System.out.println("    Enter Employee Id  : ");
        Integer empID = sc.nextInt();

        System.out.println("EMPLOYEE UPDATE FEATURE ENABLED..");

        System.out.println();
        System.out.println("SAVING THE EMPLOYEE DATA IN DTO OBJECT..");
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setJob(job);
        empDTO.setEmpID(empID);
        System.out.println("EMPLOYEE DATA SAVED IN DTO OBJECT SUCCESSFULLY..");

        System.out.println();
        System.out.println("VALIDATING THE EMPLOYEE DATA...");
        EmployeeDTOValidator.validateJob(empDTO);
        EmployeeDTOValidator.validateId(empDTO);
        System.out.println("VALIDATION DONE SUCCESSFULLY..");

        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.updateJob(empDTO);
        System.out.println();
        System.out.println("PROCESS COMPLETED!!!");
    }

    @Override
    public void deleteEmployee() {
        System.out.println("PLEASE ENTER EMPLOYEE DETAILS.. ");
        System.out.print("Enter Employee ID : ");
        Integer empID = sc.nextInt();

        System.out.println("EMPLOYEE DELETE FEATURE ENABLED..");

        System.out.println();
        System.out.println("SAVING THE EMPLOYEE ID IN DTO OBJECT..");
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setEmpID(empID);
        System.out.println("EMPLOYEE ID SAVED IN DTO OBJECT SUCCESSFULLY..");

        System.out.println();
        System.out.println("VALIDATING THE EMPLOYEE ID...");
        EmployeeDTOValidator.validateId(empDTO);
        System.out.println("VALIDATION DONE SUCCESSFULLY..");

        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.deleteEmployee(empDTO);
        System.out.println();
        System.out.println("PROCESS COMPLETED!!!");
    }
}

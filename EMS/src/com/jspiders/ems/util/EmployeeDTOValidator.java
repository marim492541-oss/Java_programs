package com.jspiders.ems.util;

import com.jspiders.ems.data.EmployeeDTO;

import java.time.LocalDate;

public class EmployeeDTOValidator { //validator methods should be static

    public static void validator(EmployeeDTO empDTO){
        validateId(empDTO);
        validateName(empDTO);
        validateJob(empDTO);
        validateMgr(empDTO);
        validateHireDate(empDTO);
        validateSal(empDTO);
        validateComm(empDTO);
        validateDeptNo(empDTO);
    }

    public static void validateId(EmployeeDTO empDTO) {
        if(empDTO.getEmpID()<=0){
            System.err.println("Employee ID is invalid!");
            throw new IllegalArgumentException("Employee ID must be greater than zero.");
        }
    }

    public static void validateName(EmployeeDTO empDTO){
        if(empDTO.geteName().length()<3 || empDTO.geteName().length()>45){
            System.err.println("Employee Name is invalid!");
            throw new IllegalArgumentException("Employee name must be between 3 and 45 characters");
        }
        if(empDTO.geteName()==null || empDTO.geteName().isBlank()){
            System.err.println("Employee Name is invalid!");
            throw new IllegalArgumentException("Employee Name is Required");
        }
    }

    public static void validateJob(EmployeeDTO empDTO) {
        if(empDTO.getJob()==null || empDTO.getJob().isBlank()){
            System.err.println("Employee Job is invalid!");
            throw new IllegalArgumentException("Employee Job is Required");
        }
        if(empDTO.getJob().length()<3 || empDTO.getJob().length()>45){
            System.err.println("Employee Job is invalid!");
            throw new IllegalArgumentException("Employee Job must be between 3 and 45 characters");
        }
    }

    public static void validateMgr(EmployeeDTO empDTO) {
        if(empDTO.getMgr()<=0){
            System.err.println("Employee Mgr is invalid!");
            throw new IllegalArgumentException("Employee mgr must be greater than zero.");
        }
    }

    public static void validateHireDate(EmployeeDTO empDTO) {
        LocalDate today = LocalDate.now();
        LocalDate inputDate = LocalDate.parse(empDTO.getHiredate());
        if(!inputDate.isBefore(today)){
            System.err.println("Employee Hiredate is invalid!");
            throw new IllegalArgumentException("Employee hiredate must be before today.");
        }
    }

    public static void validateSal(EmployeeDTO empDTO) {
        if(empDTO.getSal()<=0){
            System.err.println("Employee Salary is invalid!");
            throw new IllegalArgumentException("Employee Salary must be greater than zero.");
        }
    }

    public static void validateComm(EmployeeDTO empDTO) {
        if(empDTO.getComm()<0){
            System.err.println("Employee Comm is invalid!");
            throw new IllegalArgumentException("Employee ID must be greater than zero.");
        }
    }

    public static void validateDeptNo(EmployeeDTO empDTO) {
        if(empDTO.getDeptno()<=0){
            System.err.println("Employee Dept No is invalid!");
            throw new IllegalArgumentException("Employee ID must be greater than zero.");
        }
    }

}
/* create separate methods call the methods from the validator()
name --------> min-3 and max-45, should not be null or blanks
job ---------> min-3 and max-45, should not be null or blanks
mgr ---------> should not be negative or 0,
hire date ---> should not be null/blank, should not be future date
sal ---------> should not be negative or 0
comm --------> should not be negative
deptno ------> should not be negative or 0

Implement view feature, as a user, I should be able to view the employee by name/emp no/job
Implement update feature, as a user, I should be able to update sal,comm,job of the employee based on emp no
Implement delete feature, as a user, I should be able to delete the emp by emp no
*/

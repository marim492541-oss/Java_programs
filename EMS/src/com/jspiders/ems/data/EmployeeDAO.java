package com.jspiders.ems.data;

import com.jspiders.ems.exceptions.DuplicateEmployeeIdException;

import java.sql.*;

//Database Logic
public class EmployeeDAO {//data access object layer

    public void save(EmployeeDTO empDTO) {

        String insertSql = "insert into ems.emp values(?,?,?,?,?,?,?,?);";
        try
        {
            Connection con = DbConfig.getDbConnection();
            PreparedStatement psm1 = con.prepareStatement(insertSql);

            psm1.setInt(1, empDTO.getEmpID());
            psm1.setString(2,empDTO.geteName());
            psm1.setString(3,empDTO.getJob());
            psm1.setInt(4,empDTO.getMgr());
            psm1.setString(5, empDTO.getHiredate());
            psm1.setInt(6, empDTO.getSal());
            psm1.setInt(7, empDTO.getComm());
            psm1.setInt(8, empDTO.getDeptno());

            int row = psm1.executeUpdate();
            System.out.println( row + " row(s) affected");
        }
        catch (SQLSyntaxErrorException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicateEmployeeIdException("Duplicate employee id not allowed");
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void viewEmployeeByEmpID(EmployeeDTO empDTO) {

        String selectSql = "Select * from ems.emp where empno=?";
        try
        {
            Connection con = DbConfig.getDbConnection();
            PreparedStatement psm1 = con.prepareStatement(selectSql);

            psm1.setInt(1, empDTO.getEmpID());
            ResultSet rs = psm1.executeQuery();

            if(rs.next()) {
                System.out.println();
                System.out.println("--------- THE RESULT : -----------------");
                System.out.println(rs.getInt("empno") + " - " + rs.getString("ename")  + " - " + rs.getString("job") + " - " + rs.getInt("sal"));
            }
            else {
                System.err.println("ERROR : EMPLOYEE ID NOT FOUND");
                throw new RuntimeException("Employee ID does not exist");
            }
        }
        catch (SQLSyntaxErrorException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewEmployeeByEname(EmployeeDTO empDTO) {

        String selectSql = "Select * from ems.emp where ename=?";
        try
        {
            Connection con = DbConfig.getDbConnection();
            PreparedStatement psm1 = con.prepareStatement(selectSql);

            String name = empDTO.geteName().toUpperCase();
            psm1.setString(1, name);
            ResultSet rs = psm1.executeQuery();

            if(rs.next()) {
                System.out.println();
                System.out.println("------THE RESULT : -----------------");
                System.out.println(rs.getInt("empno") + " - " + rs.getString("ename")  + " - " + rs.getString("job") + " - " + rs.getInt("sal"));
            }
            else {
                System.err.println("ERROR : EMPLOYEE NAME NOT FOUND");
                throw new RuntimeException("Employee name does not exist");
            }
        }
        catch (SQLSyntaxErrorException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewEmployeeByJob(EmployeeDTO empDTO) {

        String selectSql = "Select * from ems.emp where job=?";
        try
        {
            Connection con = DbConfig.getDbConnection();
            PreparedStatement psm1 = con.prepareStatement(selectSql);

            String job = empDTO.getJob().toUpperCase();
            psm1.setString(1, job);
            ResultSet rs = psm1.executeQuery();
            if(rs.next()) {
                System.out.println();
                System.out.println("------THE RESULT : -----------------");
                System.out.println(rs.getInt("empno") + " - " + rs.getString("ename")  + " - " + rs.getString("job") + " - " + rs.getInt("sal"));
            }
            else{
                System.err.println("ERROR : EMPLOYEE JOB NOT FOUND");
                throw new RuntimeException("Employee Job does not exist");
            }
        }
        catch (SQLSyntaxErrorException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewAllEmployees() {
        String selectSql = "Select * from ems.emp";
        try
        {
            Connection con = DbConfig.getDbConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectSql);
            System.out.println();
            System.out.println("-------THE RESULTS : ---------------");
            while(rs.next()) {
                System.out.println(rs.getInt("empno") + " - " + rs.getString("ename")  + " - " + rs.getString("job") + " - " + rs.getInt("sal"));
            }
            System.out.println();
        }
        catch (SQLSyntaxErrorException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSal(EmployeeDTO empDTO) {
        String updatesql = "update ems.emp set sal=? where empno=?";
        try
        {
            Connection con = DbConfig.getDbConnection();
            PreparedStatement psm1 = con.prepareStatement(updatesql);

            psm1.setInt(1, empDTO.getSal());
            psm1.setInt(2, empDTO.getEmpID());

            int row = psm1.executeUpdate();
            if(row > 0) {
                System.out.println();
                System.out.println( row + " row(s) affected");
            }
            else{
                System.err.println("ERROR : EMPLOYEE ID NOT FOUND");
                throw new RuntimeException("Employee ID does not exist");
            }
        }
        catch (SQLSyntaxErrorException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateComm(EmployeeDTO empDTO){
        String updatesql = "update ems.emp set comm=? where empno=?";
        try
        {
            Connection con = DbConfig.getDbConnection();
            PreparedStatement psm1 = con.prepareStatement(updatesql);

            psm1.setInt(1, empDTO.getComm());
            psm1.setInt(2, empDTO.getEmpID());

            int row = psm1.executeUpdate();
            if(row > 0) {
                System.out.println();
                System.out.println( row + " row(s) affected");
            }
            else{
                System.err.println("ERROR : EMPLOYEE ID NOT FOUND");
                throw new RuntimeException("Employee ID does not exist");
            }
        }
        catch (SQLSyntaxErrorException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateJob(EmployeeDTO empDTO){
        String updatesql = "update ems.emp set job=? where empno=?";
        try
        {
            Connection con = DbConfig.getDbConnection();
            PreparedStatement psm1 = con.prepareStatement(updatesql);

            psm1.setString(1, empDTO.getJob());
            psm1.setInt(2, empDTO.getEmpID());

            int row = psm1.executeUpdate();
            if(row > 0) {
                System.out.println();
                System.out.println( row + " row(s) affected");
            }
            else{
                System.err.println("ERROR : EMPLOYEE ID NOT FOUND");
                throw new RuntimeException("Employee ID does not exist");
            }
        }
        catch (SQLSyntaxErrorException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(EmployeeDTO empDTO){
        String deletesql = "delete from ems.emp where empno=?";

        try
        {
            Connection con = DbConfig.getDbConnection();
            PreparedStatement psm1 = con.prepareStatement(deletesql);

            psm1.setInt(1, empDTO.getEmpID());

            int row = psm1.executeUpdate();

            if(row > 0) {
                System.out.println();
                System.out.println(row + " row(s) affected");
            }
            else{
                System.err.println("ERROR : EMPLOYEE ID NOT FOUND");
                throw new RuntimeException("Employee ID does not exist");
            }
        }
        catch (SQLSyntaxErrorException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

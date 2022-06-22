package com.example.myapplication;

public class MapModel {
    private String employee_name;
    private String emp_salary;
    private String emp_designation;
    
    public  MapModel(String salary, String designation) {
        this.emp_salary = salary;
        this.emp_designation = designation;
    }
    
    public MapModel(String name, String salary, String designation){
        this.employee_name = name;
        this.emp_salary = salary;
        this.emp_designation = designation;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmp_salary() {
        return emp_salary;
    }

    public void setEmp_salary(String emp_salary) {
        this.emp_salary = emp_salary;
    }

    public String getEmp_designation() {
        return emp_designation;
    }

    public void setEmp_designation(String emp_designation) {
        this.emp_designation = emp_designation;
    }
}

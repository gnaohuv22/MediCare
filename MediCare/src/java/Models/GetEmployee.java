/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author DELL
 */
public class GetEmployee {
    private Employee employee;
    private Branch branch;
    private Province province;
    private EmployeeRole employeeRole;

    public GetEmployee() {
    }

    public GetEmployee(Employee employee, Branch branch, Province province, EmployeeRole employeeRole) {
        this.employee = employee;
        this.branch = branch;
        this.province = province;
        this.employeeRole = employeeRole;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }
    
}

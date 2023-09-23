/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Branch;
import Models.Doctor;

/**
 *
 * @author hoang
 */
public class BranchDAO extends DBContext {
    public ArrayList<Branch> getAllBranches() {
        ArrayList<Branch> list = new ArrayList<>();
        String sql = "SELECT * FROM Branch";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Branch b = new Branch(String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4)));
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println("getAllBranches: " + e);
        }
        return list;
    }
    public Branch getBranchByBranchId(String id) {
        String sql = "SELECT * FROM Branch WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Branch(String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4)));
            }
        } catch (SQLException e) {
            System.out.println("getBranchByBranchId: " + e);
        }
        return null;
    }

    public ArrayList<Doctor> getAllDoctorsByBranchId(String branchId) {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "/****** Script for SelectTopNRows command from SSMS  ******/\n"
                + "SELECT d.*, b.[name] AS branchName, a.[name] AS ARName, \n"
                + "DC.Certificates AS Certificates, Department.id as DepartmentId, Department.[name] AS departmentName, CV.education, CV.introduce, CV.workHistory, CV.startYear\n"
                + "FROM Doctor AS d\n"
                + "\n"
                + "FULL JOIN\n"
                + "\n"
                + "Branch AS b On b.id = d.branchId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "AcademicRank AS a\n"
                + "On a.id = d.ARId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "DoctorCertificates DC \n"
                + "ON d.id = DC.DoctorId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "DoctorService AS DS\n"
                + "ON DS.doctorId = d.id\n"
                + "\n"
                + "FULL JOIN \n"
                + "ServiceTag AS ST\n"
                + "ON ST.id = DS.serviceId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "Department\n"
                + "ON Department.id = ST.departmentId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "CurriculumVitae AS CV\n"
                + "On CV.id = d.CVId\n"
                + "\n"
                + "WHERE d.branchId = ? \n"
                + "GROUP BY d.ARId, d.branchId, d.CVId, d.displayName, d.email, d.id, d.phone, d.profilePicture, d.salary, d.status, d.workplace, \n"
                + "b.[name], a.[name], DC.Certificates,  Department.[name], Department.id, CV.education, CV.introduce, CV.workHistory, CV.startYear, d.[password]\n"
                + "HAVING d.id IS NOT NULL";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(branchId));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("displayName"),
                        String.valueOf(rs.getInt("branchId")),
                        rs.getString("phone"),
                        String.valueOf(rs.getInt("ARId")),
                        String.valueOf(rs.getInt("CVId")),
                        String.valueOf(rs.getFloat("salary")),
                        rs.getString("workplace"),
                        rs.getString("profilePicture"),
                        String.valueOf(rs.getBoolean("status")),
                        rs.getString("password"),
                        rs.getString("branchName"),
                        rs.getString("ARName"),
//                        concatenateNames(rs.getString(15)),
                        rs.getString("Certificates"),
                        String.valueOf(rs.getInt("DepartmentId")),
                        rs.getString("departmentName"),
                        rs.getString("education"),
                        rs.getString("introduce"),
                        rs.getString("workHistory"),
                        String.valueOf(rs.getInt("startYear")));
                list.add(d);
            }

        } catch (SQLException | NumberFormatException e) {
            System.out.println("getAllDoctorsByBranchId: " + e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        BranchDAO bd = new BranchDAO();
        ArrayList<Doctor> list = bd.getAllDoctorsByBranchId("1");
        for (Doctor doctor : list) {
            System.out.println(doctor);
        }
    }
}

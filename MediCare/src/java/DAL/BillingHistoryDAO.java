/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Appointments;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.BillingHistory;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author hoang
 */
public class BillingHistoryDAO extends DBContext {

    public ArrayList<BillingHistory> getListBillingHistory() {
        ArrayList<BillingHistory> list = new ArrayList<>();
        String SQL = "SELECT * FROM [BillingHistory]";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BillingHistory bh = new BillingHistory(String.valueOf(rs.getInt(1)),
                        String.valueOf(rs.getInt(2)),
                        String.valueOf(rs.getFloat(3)),
                        String.valueOf(rs.getDate(4)));
                list.add(bh);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListBillingHistory: " + e.getMessage());
        }
        return null;
    }

    public List<BillingHistory> getListBillingHistoryByOwnerId(String ownerId) {
        List<BillingHistory> list = new ArrayList<>();
        String SQL = "select bh.id, bh.totalCash, bh.createdAt, a.id AS appointmentId,a.plannedAt,d.displayName, fp.email, fp.name, fp.phone, fp.ownerId\n"
                + "from BillingHistory bh\n"
                + "JOIN Appointments a on bh.appointmentId = a.id\n"
                + "JOIN FamilyProfile fp on a.profileId = fp.profileId\n"
                + "JOIN Doctor d on a.doctorId = d.id\n"
                + "where fp.ownerId = ?";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] cash = rs.getString("totalCash").split("\\.");
                BillingHistory bh = new BillingHistory();
                bh.setId(rs.getString("id"));
                bh.setTotalCash(cash[0]);
                bh.setCreatedAt(rs.getString("createdAt"));
                bh.getAppointment().setId(rs.getString("appointmentId"));
                bh.getAppointment().setPlannedAt(rs.getString("plannedAt"));
                bh.getAppointment().getDoctor().setDisplayName(rs.getString("displayName"));
                bh.getAppointment().getFp().setEmail(rs.getString("email"));
                bh.getAppointment().getFp().setName(rs.getString("name"));
                bh.getAppointment().getFp().setPhone(rs.getString("phone"));
                list.add(bh);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListBillingHistoryByOwnerId: " + e.getMessage());
        }
        return null;
    }

    public BillingHistory getListBillingHistoryById(String id) {
        BillingHistory bh = new BillingHistory();
        String SQL = "select bh.id, bh.totalCash, bh.createdAt, a.id AS appointmentId,a.plannedAt,d.displayName, fp.email, fp.name, fp.phone, fp.ownerId\n"
                + "from BillingHistory bh\n"
                + "JOIN Appointments a on bh.appointmentId = a.id\n"
                + "JOIN FamilyProfile fp on a.profileId = fp.profileId\n"
                + "JOIN Doctor d on a.doctorId = d.id\n"
                + "where bh.id = ?";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String[] cash = rs.getString("totalCash").split("\\.");
                bh = new BillingHistory();
                bh.setId(rs.getString("id"));
                bh.setTotalCash(cash[0]);
                bh.setCreatedAt(rs.getString("createdAt"));
                bh.getAppointment().setId(rs.getString("appointmentId"));
                bh.getAppointment().setPlannedAt(rs.getString("plannedAt"));
                bh.getAppointment().getDoctor().setDisplayName(rs.getString("displayName"));
                bh.getAppointment().getFp().setEmail(rs.getString("email"));
                bh.getAppointment().getFp().setName(rs.getString("name"));
                bh.getAppointment().getFp().setPhone(rs.getString("phone"));
            }
            return bh;
        } catch (SQLException e) {
            System.out.println("getListBillingHistoryById: " + e.getMessage());
        }
        return null;
    }

    public List<BillingHistory> getListBillingHistoryByOwnerIdAndName(String search,String ownerId) {
        List<BillingHistory> list = new ArrayList<>();
        String SQL = "select bh.id, bh.totalCash, bh.createdAt, a.id AS appointmentId,a.plannedAt,d.displayName, fp.email, fp.name, fp.phone, fp.ownerId\n"
                + "from BillingHistory bh\n"
                + "JOIN Appointments a on bh.appointmentId = a.id\n"
                + "JOIN FamilyProfile fp on a.profileId = fp.profileId\n"
                + "JOIN Doctor d on a.doctorId = d.id\n"
                + "where fp.ownerId = ?";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] cash = rs.getString("totalCash").split("\\.");
                BillingHistory bh = new BillingHistory();
                bh.setId(rs.getString("id"));
                bh.setTotalCash(cash[0]);
                bh.setCreatedAt(rs.getString("createdAt"));
                bh.getAppointment().setId(rs.getString("appointmentId"));
                bh.getAppointment().setPlannedAt(rs.getString("plannedAt"));
                bh.getAppointment().getDoctor().setDisplayName(rs.getString("displayName"));
                bh.getAppointment().getFp().setEmail(rs.getString("email"));
                bh.getAppointment().getFp().setName(rs.getString("name"));
                bh.getAppointment().getFp().setPhone(rs.getString("phone"));
                list.add(bh);
            }
            Iterator<BillingHistory> iterator = list.iterator();
            while(iterator.hasNext()){
                BillingHistory bh = iterator.next();
                if(!bh.getAppointment().getFp().getName().toLowerCase().contains(search.toLowerCase())){
                    iterator.remove();
                }
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListBillingHistoryByOwnerIdAndName: " + e.getMessage());
        }
        return null;
    }

    public List<BillingHistory> getListBillingHistoryByOwnerIdAndPhone(String search, String ownerId) {
        List<BillingHistory> list = new ArrayList<>();
        String SQL = "select bh.id, bh.totalCash, bh.createdAt, a.id AS appointmentId,a.plannedAt,d.displayName, fp.email, fp.name, fp.phone, fp.ownerId\n"
                + "from BillingHistory bh\n"
                + "JOIN Appointments a on bh.appointmentId = a.id\n"
                + "JOIN FamilyProfile fp on a.profileId = fp.profileId\n"
                + "JOIN Doctor d on a.doctorId = d.id\n"
                + "where fp.ownerId = ?";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] cash = rs.getString("totalCash").split("\\.");
                BillingHistory bh = new BillingHistory();
                bh.setId(rs.getString("id"));
                bh.setTotalCash(cash[0]);
                bh.setCreatedAt(rs.getString("createdAt"));
                bh.getAppointment().setId(rs.getString("appointmentId"));
                bh.getAppointment().setPlannedAt(rs.getString("plannedAt"));
                bh.getAppointment().getDoctor().setDisplayName(rs.getString("displayName"));
                bh.getAppointment().getFp().setEmail(rs.getString("email"));
                bh.getAppointment().getFp().setName(rs.getString("name"));
                bh.getAppointment().getFp().setPhone(rs.getString("phone"));
                list.add(bh);
            }
            Iterator<BillingHistory> iterator = list.iterator();
            while(iterator.hasNext()){
                BillingHistory bh = iterator.next();
                if(!bh.getAppointment().getFp().getPhone().toLowerCase().contains(search.toLowerCase())){
                    iterator.remove();
                }
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListBillingHistoryByOwnerIdAndName: " + e.getMessage());
        }
        return null;
    }

    public List<BillingHistory> getListBillingHistoryByOwnerIdAndEmail(String search, String ownerId) {
        List<BillingHistory> list = new ArrayList<>();
        String SQL = "select bh.id, bh.totalCash, bh.createdAt, a.id AS appointmentId,a.plannedAt,d.displayName, fp.email, fp.name, fp.phone, fp.ownerId\n"
                + "from BillingHistory bh\n"
                + "JOIN Appointments a on bh.appointmentId = a.id\n"
                + "JOIN FamilyProfile fp on a.profileId = fp.profileId\n"
                + "JOIN Doctor d on a.doctorId = d.id\n"
                + "where fp.ownerId = ?";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, ownerId);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] cash = rs.getString("totalCash").split("\\.");
                BillingHistory bh = new BillingHistory();
                bh.setId(rs.getString("id"));
                bh.setTotalCash(cash[0]);
                bh.setCreatedAt(rs.getString("createdAt"));
                bh.getAppointment().setId(rs.getString("appointmentId"));
                bh.getAppointment().setPlannedAt(rs.getString("plannedAt"));
                bh.getAppointment().getDoctor().setDisplayName(rs.getString("displayName"));
                bh.getAppointment().getFp().setEmail(rs.getString("email"));
                bh.getAppointment().getFp().setName(rs.getString("name"));
                bh.getAppointment().getFp().setPhone(rs.getString("phone"));
                list.add(bh);
            }
            Iterator<BillingHistory> iterator = list.iterator();
            while(iterator.hasNext()){
                BillingHistory bh = iterator.next();
                if(!bh.getAppointment().getFp().getEmail().toLowerCase().contains(search.toLowerCase())){
                    iterator.remove();
                }
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListBillingHistoryByOwnerIdAndName: " + e.getMessage());
        }
        return null;
    }
}

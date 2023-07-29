/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dbconfig.DBContext;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import model.KiHoc;

/**
 *
 * @author long0
 */
public class KiHocService {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    public List<KiHoc> getAll() {
        sql = "SELECT * FROM KYHOC";
        List<KiHoc> list = new ArrayList<>();
        try {
            con = DBContext.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KiHoc kh = new KiHoc(rs.getString(1), rs.getString(2), rs.getInt(3));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int add(KiHoc kh) {
        sql = "INSERT INTO KYHOC (ID, TENKYHOC, NAMHOC) VALUES (?,?, ?)";
        try {
            con = DBContext.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getId());
            ps.setObject(2, kh.getTenKyHoc());
            ps.setObject(3, kh.getNamHoc());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(KiHoc kh, String id) {
        sql = "UPDATE KYHOC SET ID= ?, TENKYHOC = ?, NAMHOC = ? WHERE ID = ?";
        try {
            con = DBContext.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getId());
            ps.setObject(2, kh.getTenKyHoc());
            ps.setObject(3, kh.getNamHoc());
            ps.setObject(4, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteKiHoc(String id) {
        sql = "DELETE  FROM KYHOC WHERE ID = ?";
        try {
            con = DBContext.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean checkTrungID(String id) {
        sql = "SELECT * FROM KYHOC WHERE ID = ?";
        try {
            con = DBContext.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

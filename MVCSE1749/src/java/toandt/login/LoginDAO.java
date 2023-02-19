/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toandt.login;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import toandt.util.DBHelper;

/**
 *
 * @author Toan
 */
public class LoginDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1.Connect DB
            con = DBHelper.makeConnection(); //>connect, query
            if (con != null) { //1.1 check connection
                //2.Write SQL statement
                String sql = "Select username "
                        + "From Login "
                        + "Where username = ? And password = ?";
                //3.Create statement object >> inital value = null >> close after use
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.Execute statement to get result
                rs = stm.executeQuery();
                //5.Process result
                if (rs.next()) {
                    result = true; //set value
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }//object mo sau thi phai dong truoc
            if (con != null) {
                con.close();

            }
        }

        return result; //set value
    }

    private List<LoginDTO> accountList;

    public List<LoginDTO> getAccountList() {
        return accountList;
    }

    public void searchLastName(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.Connect DB
            con = DBHelper.makeConnection(); //>connect, query
            if (con != null) { //1.1 check connection
                //2.Write SQL statement
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Login "
                        + "Where lastname Like ?";
                //3.Create statement object >> inital value = null >> close after use
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4.Execute statement to get result
                rs = stm.executeQuery();
                //5.Process result
                
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    LoginDTO dto = new LoginDTO(username, password, fullName, role);
                    if (this.accountList == null) {
                        accountList = new ArrayList<LoginDTO>();
                    } //end accountList has existed
                    this.accountList.add(dto);
                } //end rs has not reached EOF
            } //end connection is existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }//object mo sau thi phai dong truoc
            if (con != null) {
                con.close();

            }
        }
    }
}

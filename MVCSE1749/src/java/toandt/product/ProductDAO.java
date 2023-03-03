/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toandt.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import toandt.util.DBHelper;

/**
 *
 * @author Toan
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void getAvailableProduct()
            throws NamingException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            //1.Connect DB
            con = DBHelper.makeConnection(); //>connect, query
            if (con != null) { //1.1 check connection
                //2.Write SQL statement
                String sql = "Select sku, name, description, price, quantity "
                        + "From Product "
                        + "Where quantity <> 0 and status = False";
                //3.Create statement object >> inital value = null >> close after use
                stm = con.createStatement();
                //4.Execute statement to get result
                rs = stm.executeQuery(sql);
                //5.Process result

                while (rs.next()) {
                    int sku = rs.getInt("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(sku,name,description,price,quantity);
                    if (this.productList == null) {
                        productList = new ArrayList<ProductDTO>();
                    } //end accountList has existed
                    this.productList.add(dto);
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

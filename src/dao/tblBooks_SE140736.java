/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author DELL
 */
import dto.Books_SE140736;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import ui.CustomerTableModelBooks_SE140736;
import ultis.DBUtils_SE140736;

public class tblBooks_SE140736 {

    private String[] headeres = {"Book ID", "Book Name", "Author", "Publisher", "Publisher Year", "For Rent"};
    private int[] indexes = {0, 1, 2, 3, 4, 5};
    private CustomerTableModelBooks_SE140736 modelBook = new CustomerTableModelBooks_SE140736(headeres, indexes);

    public CustomerTableModelBooks_SE140736 getModelBooks() {
        return modelBook;
    }

    public tblBooks_SE140736() {
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData() throws SQLException {
        modelBook.getList().clear();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtils_SE140736.openConnection();
            if (con != null) {
                String sql = "SELECT bookID, bookName, author, publisher, publishedYear, forRent FROM dbo.tblBooks";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Books_SE140736 b = new Books_SE140736(rs.getString("bookID"), rs.getString("bookName"), rs.getString("author"),
                            rs.getString("publisher"), rs.getInt("publishedYear"), rs.getBoolean("forRent"));
                    modelBook.getList().add(b);
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public Books_SE140736 getBookId(String bookID) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT bookID, bookName, author, publisher, publishedYear, forRent FROM dbo.tblBooks WHERE bookID=?";
        try {
            con = DBUtils_SE140736.openConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, bookID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("bookID");
                    String name = rs.getString("bookName");
                    String author = rs.getString("author");
                    String publisher = rs.getString("publisher");
                    int publishedYear = rs.getInt("publishedYear");
                    boolean forRent = rs.getBoolean("forRent");

                    Books_SE140736 b = new Books_SE140736(id, name, author, publisher, publishedYear, forRent);

                    return b;
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public int Insert(Books_SE140736 book) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBUtils_SE140736.openConnection();
            
            if(con != null){
                String sql = "Insert dbo.tblBooks Values(?,?,?,?,?,?)";
                
                ps = con.prepareStatement(sql);
                ps.setString(1, book.getBookID());
                ps.setString(2, book.getBookName());
                ps.setString(3, book.getAuthor());
                ps.setString(4, book.getPublisher());
                ps.setInt(5, book.getPublishedYear());
                ps.setBoolean(6, book.isForRent());
                
                return ps.executeUpdate();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con != null ) con.close();
            if(ps != null ) ps.close();
        }
        return -1;
    }
    public int Save(Books_SE140736 book) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtils_SE140736.openConnection();
            if (con != null) {
                String sql = "UPDATE tblBooks SET bookName =?, author =?, publisher =?, publishedYear =?, forRent=? WHERE bookID =?";
                ps = con.prepareStatement(sql);
                ps = con.prepareStatement(sql);
                ps.setString(1, book.getBookName());
                ps.setString(2, book.getAuthor());
                ps.setString(3, book.getPublisher());
                ps.setInt(4, book.getPublishedYear());
                ps.setBoolean(5, book.isForRent());
                ps.setString(6, book.getBookID());

                return ps.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public int Delete(Books_SE140736 book) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils_SE140736.openConnection();
            if (con != null) {
                String sql = "DELETE FROM dbo.tblBooks WHERE bookID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, book.getBookID());
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public void loadBookAscending() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        modelBook.getList().clear();
        try {
            con = DBUtils_SE140736.openConnection();
            if (con != null) {
                String sql = "SELECT bookID, bookName, author, publisher, publishedYear, forRent FROM dbo.tblBooks ORDER BY bookName ASC;";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Books_SE140736 b = new Books_SE140736(rs.getString("bookID"), rs.getString("bookName"), rs.getString("author"),
                            rs.getString("publisher"), rs.getInt("publishedYear"), rs.getBoolean("forRent"));
                    modelBook.getList().add(b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void loadBookDescending() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        modelBook.getList().clear();
        try {
            con = DBUtils_SE140736.openConnection();
            if (con != null) {
                String sql = "SELECT bookID, bookName, author, publisher, publishedYear, forRent FROM dbo.tblBooks ORDER BY bookName DESC;";   // Lấy tất cả giá trị tăng dần từ bảng LISTBOOK trong database

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Books_SE140736 b = new Books_SE140736(rs.getString("bookID"), rs.getString("bookName"), rs.getString("author"),
                            rs.getString("publisher"), rs.getInt("publishedYear"), rs.getBoolean("forRent"));
                    modelBook.getList().add(b);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public void loadTableBookSearchByName(String bookName) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        modelBook.getList().clear();
        try {
            con = DBUtils_SE140736.openConnection();
            if (con != null) {
                String sql = "SELECT bookID, bookName, author, publisher, publishedYear, forRent FROM dbo.tblBooks WHERE CHARINDEX(N'" + bookName + "', bookName)>0;";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Books_SE140736 b = new Books_SE140736(rs.getString("bookID"), rs.getString("bookName"), rs.getString("author"),
                            rs.getString("publisher"), rs.getInt("publishedYear"), rs.getBoolean("forRent"));
                    modelBook.getList().add(b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean validDatabookID(String bookID) {//xet trung category
        try {
            for (int i = 0; i < modelBook.getList().size(); i++) {
                if (modelBook.getList().get(i).getBookID().compareToIgnoreCase(bookID) == 0) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean validData(String bookID, String bookName, String author, String publisher) {
        if (!bookID.toUpperCase().matches("[a-zA-Z]{1}[0-9]{3}")) {
            JOptionPane.showMessageDialog(null, "Invalid Book ID");
            return false;
        }
        if (bookName.isEmpty() || bookName.length() > 50) {
            JOptionPane.showMessageDialog(null, "Invalid Book Name");
            return false;
        }
        if (author.isEmpty() || author.length() > 50) {
            JOptionPane.showMessageDialog(null, "Invalid Author");
            return false;
        }
        if (publisher.isEmpty() || publisher.length() > 50) {
            JOptionPane.showMessageDialog(null, "Invalid Publisher");
            return false;
        }
        return true;
    }

}

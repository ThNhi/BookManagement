/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.tblBooks_SE140736;
import dto.Books_SE140736;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class CustomerTableModelBooks_SE140736 extends AbstractTableModel {

    private String[] headeres; //tiêu đề
    private int[] indexes; //thứ tự cột trên nguồn(database) hiển thị trên table, trả về vector nguồn
    private Vector<Books_SE140736> data; //dữ liệu nguồn cho table
//    tblBooks_SE140736 dao = new tblBooks_SE140736();

    public Vector<Books_SE140736> getList() {
        return data;
    }

    public CustomerTableModelBooks_SE140736(String[] headeres, int[] indexes) {
        this.headeres = new String[headeres.length]; // tạo tiêu đề
        for (int i = 0; i < headeres.length; i++) {
            this.headeres[i] = headeres[i];
        }

        this.indexes = new int[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            this.indexes[i] = indexes[i]; //thứ tự cột hiển thị
        }

        data = new Vector<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return headeres.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= data.size() || columnIndex < 0 || columnIndex >= headeres.length) {
            return null;
        }

        Books_SE140736 book = data.get(rowIndex);
        switch (indexes[columnIndex]) {
            case 0:
                return book.getBookID();
            case 1:
                return book.getBookName();
            case 2:
                return book.getAuthor();
            case 3:
                return book.getPublisher();
            case 4:
                return book.getPublishedYear();
            case 5:
                return book.isForRent();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        if (column >= 0 || column < headeres.length) {
            return headeres[column];
        } else {
            return "";
        }
    }
}

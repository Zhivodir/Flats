package Flats;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by User on 21.12.2016.
 */
public class Operations {
    private DBConnect dbConnect;
    private Scanner sc;
    private StringBuilder select;
    private boolean where = false;
    private Object[] partsOfSelect = new Object[6];


    public Operations(DBConnect dbConnect) {
        this.dbConnect = dbConnect;
        this.sc = new Scanner(System.in);
        select = new StringBuilder();
        select.append("SELECT * FROM flats");

        while(true){
            System.out.println("Choice a parameter for add to the query:");
            System.out.println("1.District");
            System.out.println("2.Address");
            System.out.println("3.Area");
            System.out.println("4.Quantity of rooms");
            System.out.println("5.Price");
            System.out.println("6.View text of query.");
            System.out.println("7.Send query.");
            System.out.println("8.Clear query.");

            int choice = Integer.parseInt(sc.nextLine());
            select(choice);
        }
    }

    public void select(int choice){
        switch (choice){
            case 1:
                String district = sc.nextLine();
                isWhere();
                select.append(" district=?");
                partsOfSelect[1] = district;
                break;
            case 2:
                String address = sc.nextLine();
                isWhere();
                select.append(" address=?");
                partsOfSelect[2] = address;
                break;
            case 3:
                Double area = Double.parseDouble(sc.nextLine());
                isWhere();
                select.append(" area=?");
                partsOfSelect[3] = area;
                break;
            case 4:
                int numOfRoom = Integer.parseInt(sc.nextLine());
                isWhere();
                select.append(" rooms=?");
                partsOfSelect[4] = numOfRoom;
                break;
            case 5:
                int price = Integer.parseInt(sc.nextLine());
                isWhere();
                select.append(" price=?");
                partsOfSelect[5] = price;
                break;
            case 6:
                viewSelect();
                break;
            case 7:
                sendQuery();
                break;
        }

    }

    public void isWhere() {
        if(where != true) {
            select.append(" WHERE");
            where = true;
        }
    }

    public void viewSelect(){
        System.out.println(select.toString());
    }

    public void sendQuery(){
        try{
            PreparedStatement ps = dbConnect.getConnection().prepareStatement(select.toString());
            int num = 1;
            for(Object object:partsOfSelect){
                if(object != null) {
                    if (object instanceof Integer) {
                        ps.setInt(num, (Integer) object);
                    } else if (object instanceof Double) {
                        ps.setDouble(num, (Double) object);
                    } else if (object instanceof String) {
                        ps.setString(num, object.toString());
                    }
                    num++;
                }
            }

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            for (int i = 1; i <= md.getColumnCount(); i++)
                System.out.print(md.getColumnName(i) + "\t\t");
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }
            rs.close();
            ps.close();
        }catch (SQLException e){e.printStackTrace();}
    }
}

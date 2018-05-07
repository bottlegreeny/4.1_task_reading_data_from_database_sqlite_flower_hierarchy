package data_base;

import java.sql.*;
import java.util.*;


class Flower {
 private String name;
 private String colour;
 private int cost;
 private static int total;

 Flower(String name, String colour, int cost) {
  this.name = name;
  this.colour = colour;
  this.cost = cost;
 }

 public String getName() {
  return name;
 }
 public String getColour() {
  return colour;
 }
 public int getCost() {
  return cost;
 }


 public void printCharacteristics() {

  System.out.println(name + " of " + colour + " colour are available only, " + cost + " pounds");

 }
 public String toString() {
  return name + " " + colour + " " + cost;
 }



 static class Daffodil extends Flower {

  Daffodil(String name, String colour, int cost) {
   super(name, colour, cost);
   System.out.println("Note:");
   total += cost;
  }




 }
 static class Rose extends Flower {

  Rose(String name, String colour, int cost) {
   super(name, colour, cost);
   total += cost;
  }

 }

 static class Chamomile extends Flower {

  Chamomile(String name, String colour, int cost) {
   super(name, colour, cost);
   total += cost;
  }


 }

 static class Cactus extends Flower {

  Cactus(String name, String colour, int cost) {
   super(name, colour, cost);
   total += cost;
  }

 }
 

 public static class SQLiteJDBCDriverConnection {

  public void connect() {
    Connection conn = null;
    try {

     String url = "jdbc:sqlite:C:\\sqlite\\db\\flowers.sqlite";
     
     conn = DriverManager.getConnection(url);

     System.out.println("Connection to SQLite has been established!" + "\n");

    } catch (SQLException e) {
     System.out.println(e.getMessage());
    } finally {
     try {
      if (conn != null) {
       conn.close();
      }
     } catch (SQLException ex) {
      System.out.println(ex.getMessage());
     }
    }
   }
   
  public void main(String[] args) {
   connect();
  }
 }


 public static class Bouquet {

   static Collection fill(Collection < Flower > collection) {

   collection.add(new Daffodil("Daffodils", "yellow", 20));
   collection.add(new Rose("Roses", "red", 30));
   collection.add(new Chamomile("Chamomiles", "white", 40));
   collection.add(new Cactus("Cactuses", "green", 50));
   return collection;

  } 


  public static void main(String[] args) {

   SQLiteJDBCDriverConnection sql = new SQLiteJDBCDriverConnection();

   sql.connect();

   System.out.println("Here is our Flower collection itself: " + "\n" + fill(new ArrayList < Flower > ())+ "\n");

   Connection c = null;
   Statement stmt = null;
   try {
    Class.forName("org.sqlite.JDBC");
    c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Cathy\\eclipse-workspace\\data_base\\flowers");
    c.setAutoCommit(false);
    System.out.println("Opened database successfully!" + "\n");

    stmt = c.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM Flowers;");

    while (rs.next()) {
     String name = rs.getString("name");
     String colour = rs.getString("colour");
     int cost = rs.getInt("cost");
     Flower f = new Flower (rs.getString("name"), rs.getString("colour"), rs.getInt("cost"));
     f.printCharacteristics();
     System.out.println("NAME = " + name);
     System.out.println("COLOUR = " + colour);
     System.out.println("COST = " + cost + "\n");

   
    }
    rs.close();
    stmt.close();
    c.close();
   } catch (Exception e) {
    System.err.println(e.getClass().getName() + ": " + e.getMessage());
    System.exit(0);
   }
   System.out.println("The total sum of your current bouquet is: " + Flower.total + " pounds" + "\n");
   System.out.println("Operation done successfully!");
  }

 }

}

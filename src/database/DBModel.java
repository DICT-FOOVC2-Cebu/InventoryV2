/* 
 * Copyright (C) 2018 Christian Paul Gastardo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DBModel {
    
    Properties properties = new Properties();
    InputStream inputStream;
    String db;
    
    
    
    public void loadPropertiesFile(){
        try {
            inputStream = new FileInputStream("database.properties");
            properties.load(inputStream);
            db = properties.getProperty("db");
        } catch (IOException e) {
            System.out.println("DDDD");
        }
    }

    PreparedStatement pst;

    public void createDataBase() {
        loadPropertiesFile();
        DBConnection con = new DBConnection();
        try {
            pst = con.mkDataBase().prepareStatement("create database if not exists "+db+" DEFAULT CHARACTER SET utf8 \n"
                    + "  DEFAULT COLLATE utf8_general_ci");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`AddItem` (\n"
                    + "  `ProdId` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Image` mediumblob,\n"
                    + "  `SNNo` VARCHAR(20) NOT NULL,\n"
                    + "  `PARNo` VARCHAR(20) ,\n"
                    + "  `IssuedBy` VARCHAR(100) ,\n"
                    + "  `ReceivedBy` VARCHAR(100) ,\n"
                    + "  `Description` text DEFAULT NULL ,\n"
                    + "  `Remarks` text,\n"
                    + "  `Amount` double DEFAULT NULL,\n"
                    + "  `QRCode` VARCHAR(50),\n"
                    + "  `Status` VARCHAR(100),\n"
                    + "  PRIMARY KEY (`ProdId`),\n"
                    + "  UNIQUE INDEX `ProdId` (`ProdId` ASC));");
            pst.execute();
//            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Users` (\n"
//                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
//                    + "  `UserName` VARCHAR(20) NOT NULL,\n"
//                    + "  `Password` VARCHAR(20) NOT NULL,\n"
//                    + "  PRIMARY KEY (`Id`),\n"
//                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
//            pst.execute();
//            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Organize` (\n"
//                    + "  `Id` int(1) NOT NULL ,\n"
//                    + "  `OrgName` varchar(100) DEFAULT NULL,\n"
//                    + "  `OrgWeb` varchar(100) DEFAULT NULL,\n"
//                    + "  `OrgContactNumbers` text DEFAULT NULL,\n"
//                    + "  `OrgContactAddress` text DEFAULT NULL,\n"
//                    + "  `OrgLogo` mediumblob,\n"
//                    + "  `UserId` int(11) DEFAULT NULL,\n"
//                    + "  PRIMARY KEY (`Id`),\n"
//                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
//            pst.execute();
//
//            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Supplyer` (\n"
//                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
//                    + "  `SupplyerName` varchar(100) DEFAULT NULL,\n"
//                    + "  `SupplyerPhoneNumber` text DEFAULT NULL,\n"
//                    + "  `SupplyerAddress` text DEFAULT NULL,\n"
//                    + "  `SuplyerDescription` text DEFAULT NULL,\n"
//                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
//                    + "  `Date` date NOT NULL,\n"
//                    + "  PRIMARY KEY (`Id`),\n"
//                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
//            pst.execute();
//
//            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Brands` (\n"
//                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
//                    + "  `BrandName` varchar(70) DEFAULT NULL,\n"
//                    + "  `Description` text DEFAULT NULL,\n"
//                    + "  `SupplyerId` varchar(20)  DEFAULT NULL,\n"
//                    + "  `CreatorId` int DEFAULT NULL,\n"
//                    + "  `Date` date,\n"
//                    + "  PRIMARY KEY (`Id`),\n"
//                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
//
//            pst.execute();
//
//            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Catagory` (\n"
//                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
//                    + "  `CatagoryName` varchar(70) DEFAULT NULL,\n"
//                    + "  `CatagoryDescription` text DEFAULT NULL,\n"
//                    + "  `BrandId` varchar(20) DEFAULT NULL,\n"
//                    + "  `SupplyerId` int(11) DEFAULT NULL,\n"
//                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
//                    + "  `Date` date,\n"
//                    + "  PRIMARY KEY (`Id`),\n"
//                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
//
//            pst.execute();
//            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Unit` (\n"
//                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
//                    + "  `UnitName` varchar(50) DEFAULT NULL,\n"
//                    + "  `UnitDescription` text DEFAULT NULL,\n"
//                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
//                    + "  `Date` date,\n"
//                    + "  PRIMARY KEY (`Id`),\n"
//                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
//
//            pst.execute();
//
//            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`RMA` (\n"
//                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
//                    + "  `RMAName` varchar(100) DEFAULT NULL,\n"
//                    + "  `RMADays` varchar(11) NOT NULL,\n"
//                    + "  `Comment` text DEFAULT NULL,\n"
//                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
//                    + "  `Date` date,\n"
//                    + "  PRIMARY KEY (`Id`),\n"
//                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
//
//            pst.execute();
//            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`Products` (\n"
//                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
//                    + "  `ProductId` varchar(20) NOT NULL,\n"
//                    + "  `ProductName` varchar(150) NOT NULL,\n"
//                    + "  `Quantity` varchar(11) NOT NULL DEFAULT '0', \n"
//                    + "  `Description` text ,\n"
//                    + "  `SupplyerId` varchar(11) NOT NULL,\n"
//                    + "  `BrandId` varchar(11) NOT NULL,\n"
//                    + "  `CatagoryId` varchar(11) NOT NULL,\n"
//                    + "  `UnitId` varchar(11) NOT NULL,\n"
//                    + "  `PursesPrice` varchar(100) NOT NULL,\n"
//                    + "  `SellPrice` varchar(100) NOT NULL,\n"
//                    + "  `RMAId` varchar(11) NOT NULL,\n"
//                    + "  `UserId` varchar(11) NOT NULL,\n"
//                    + "  `Date` date NOT NULL,\n"
//                    + "  PRIMARY KEY (`Id`),\n"
//                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
//            pst.execute();
//
//            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`Customer` (\n"
//                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
//                    + "  `CustomerName` varchar(200) NOT NULL,\n"
//                    + "  `CustomerContNo` varchar(200) DEFAULT NULL,\n"
//                    + "  `CustomerAddress` text,\n"
//                    + "  `TotalBuy` varchar(50) DEFAULT NULL,\n"
//                    + "  `CreatorId` varchar(11) DEFAULT NULL,\n"
//                    + "  `Date` datetime NOT NULL,\n"
//                    + "  PRIMARY KEY (`Id`),\n"
//                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
//            pst.execute();
//
//            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`Sell` (\n"
//                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
//                    + "  `SellId` varchar(10) NOT NULL,\n"
//                    + "  `CustomerId` varchar(11) NOT NULL,\n"
//                    + "  `ProductId` varchar(11) NOT NULL,\n"
//                    + "  `PursesPrice` double NOT NULL,\n"
//                    + "  `SellPrice` double NOT NULL,\n"
//                    + "  `Quantity` int(10) NOT NULL,\n"
//                    + "  `TotalPrice` double NOT NULL,\n"
//                    + "  `WarrentyVoidDate` varchar(20) NOT NULL,\n"
//                    + "  `SellerId` int(11) NOT NULL,\n"
//                    + "  `SellDate` datetime NOT NULL,\n"
//                    + "  PRIMARY KEY (`Id`)\n"
//                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
//            pst.execute();
            
            System.out.println("Create Database Sucessfuly");

        } catch (SQLException ex) {
            System.err.println(ex);
//            try {
//                Parent root = FXMLLoader.load(getClass().getResource("/view/Server.fxml"));
//                Scene scene = new Scene(root);
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.setTitle("Server Configuration");
//                stage.showAndWait();
//            } catch (IOException ex1) {
//                Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        }
    }
}

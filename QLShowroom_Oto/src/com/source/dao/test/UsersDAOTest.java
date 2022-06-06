package com.source.dao.test;

import org.testng.annotations.Test;

import com.source.dao.UsersDAO;
import com.source.model.Users;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class UsersDAOTest {
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }


  @Test
  public void deleteTest() {
    throw new RuntimeException("Test not implemented");
  }

 
  //============================
  @Test
  public void testInsert() {
      System.out.println("insert");
      Users entity = null;
      UsersDAO instance = new UsersDAO();
      instance.insert(entity);
    
  }

  @Test
  public void testUpdate() {
      System.out.println("update");
      Users entity = null;
      UsersDAO instance = new UsersDAO();
      instance.update(entity);
     
  }

  @Test
  public void testDelete() {
      System.out.println("delete");
      String maNV = "";
      UsersDAO instance = new UsersDAO();
      instance.delete(maNV);
    
  }

  //============================

  @Test
  public void selectAllTest() {
	  System.out.println("select");
	  UsersDAO instance = new UsersDAO();
      List expResult = null;
      List result = instance.selectAll();
      assertEquals(result, expResult);
  }

  @Test
  public void selectByIdTest() {
	  System.out.println("selectById");
	  UsersDAO instance = new UsersDAO();
      List expResult = null;
      List result = instance.selectAll();
      assertEquals(result, expResult);
  }

  


}

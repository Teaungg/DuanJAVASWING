package com.source.dao.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class HoaDonDAOTest {
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

  @Test
  public void insertTest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void selectAllTest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void selectByIdTest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void selectByKeywordTest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void selectBySqlTest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void updateTest() {
    throw new RuntimeException("Test not implemented");
  }
}

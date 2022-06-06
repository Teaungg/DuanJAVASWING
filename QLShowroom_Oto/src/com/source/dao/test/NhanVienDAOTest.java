package com.source.dao.test;

import org.testng.annotations.Test;

import com.source.dao.GioHangDAO;
import com.source.dao.NhanVienDAO;
import com.source.model.GioHang;
import com.source.model.NhanVien;

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

public class NhanVienDAOTest {
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
 


  @Test()
  public void deleteTest() {
	  try {
		  System.out.println("delete");
	      String id = "";
	      NhanVienDAO dao = new NhanVienDAO();
	      dao.delete(id);
	} catch (Exception e) {
		// TODO: handle exception
	}
	  
  }

  @BeforeClass
  public void insertTest() {
	  try {
		  System.out.println("insert");
	      NhanVien entity = null;
	      NhanVienDAO dao = new NhanVienDAO();
	      dao.insert(entity);
	} catch (Exception e) {
		System.out.println("insert fail");
	}
	  
  }

  @Test
  public void selectAllTest() {
	  try {
		  System.out.println("select");
		  NhanVienDAO dao = new NhanVienDAO();
	      List expResult = null;
	      List result = dao.selectAll();
	      assertEquals(result, expResult);
	} catch (Exception e) {
		// TODO: handle exception
	}
	  
  }

  @Test
  public void selectByIdTest() {
	  try {
		  System.out.println("selectById");
	      String id = "";
	      NhanVienDAO dao = new NhanVienDAO();
	      NhanVien expResult = null;
	      NhanVien result = dao.selectById(id);
	      assertEquals(result, expResult);
	} catch (Exception e) {
		// TODO: handle exception
	}
	  
  }
  @Test
  public void updateTest() {
	  try {
		  System.out.println("update");
	      NhanVien entity = null;
	      NhanVienDAO dao = new NhanVienDAO();
	      dao.insert(entity);
	} catch (Exception e) {
		// TODO: handle exception
	}
	 
  }
 
  @Test
  public void deleteTest2() {
	  
		  System.out.println("delete");
	      String id = "";
	      NhanVienDAO dao = new NhanVienDAO();
	      dao.delete(id);
	
	  
  }

  @Test
  public void insertTest2() {
	  
		  System.out.println("insert");
	      NhanVien entity = null;
	      NhanVienDAO dao = new NhanVienDAO();
	      dao.insert(entity);
	
	  
  }

  @Test
  public void selectAllTest2() {
	  System.out.println("select");
	  NhanVienDAO dao = new NhanVienDAO();
      List expResult = null;
      List result = dao.selectAll();
      assertEquals(result, expResult);
  }

  @Test
  public void selectByIdTest2() {
	  System.out.println("selectById");
      String id = "";
      NhanVienDAO dao = new NhanVienDAO();
      NhanVien expResult = null;
      NhanVien result = dao.selectById(id);
      assertEquals(result, expResult);
  }
  @Test
  public void updateTest2() {
	  System.out.println("update");
      NhanVien entity = null;
      NhanVienDAO dao = new NhanVienDAO();
      dao.insert(entity);
  }
 
}

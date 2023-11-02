/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controller.Test;

import DAL.FamilyProfileDAO;
import Models.FamilyProfile;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author phuon
 */
public class FamilyProfileDAOTest {
    
    @Test
    public void testgetFamilyProfileListByUserOwnerIdWithOwnerIdValidAnd(){
        FamilyProfileDAO fpDAO = new FamilyProfileDAO();
        
        List<FamilyProfile> listClient;
        listClient = fpDAO.getFamilyProfileListByUserOwnerId("x");
        Assert.assertNull(listClient);
    }
}

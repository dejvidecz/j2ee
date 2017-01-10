package model;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

/**
 * Created by Dejv on 10.01.17.
 */
@RunWith(Arquillian.class)
public class BazarTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Bazar.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beanss.xml");
    }

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void testTest(){
        Bazar b = new Bazar();
        b.setName("Test");



        Assert.assertTrue(true);
    }
}

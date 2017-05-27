package org.yuezhang.practices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.yuezhang.practices.KDNode;

/**
 * Created by yuezhang on 5/27/17.
 */
public class KDNodeTest {
    double[] point;
    KDNode kd;

    @Before
    public void setUp() {
        point = new double[] {3.0, 4.0};
        kd = new KDNode(point);
    }

    @Test
    public void testEquals() {
        assertTrue(kd.equals(new KDNode(point)));
    }

    @Test
    public void testDistanceTo() {
        KDNode original = new KDNode(new double[] {0.0, 0.0});
        assertEquals("distanceTo failed",5.0, kd.distanceTo(original), 0.0);
    }
}

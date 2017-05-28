package org.yuezhang.practices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by yuezhang on 5/27/17.
 */
public class KDTree2DTest {
    // an instance under test
    private KDTree kdtree;
    private KDNode original;

    @Before
    public void setUp() {
        kdtree = new KDTree(2);
        original = new KDNode(new double[] {0.0, 0.0});
    }

    @Test
    public void testInsert() {
        KDNode root = kdtree.insert(original);
        assertTrue("insert into empty KDTree failed", root.equals(original));
        double[][] points = new double[][] {{0.1, 0.9}, {1.0, -1.0}, {1.0, 1.0}, {1.0, 1.0},
                {-5.0, -3.0}, {-0.5, 3.0}};

        for (double[] point : points) {
            root = kdtree.insert(point);
            assertTrue("insert into KDTree failed", root.equals(original));
        }
    }

    @Test
    public void testFindClosest() {
        double[][] points = new double[][] {{0.0, 0.0}, {0.1, 0.9}, {1.0, -1.0}, {1.0, 1.0}, {1.0, 1.0},
                {-5.0, -3.0}, {-1.0, 3.0}};

        for (double[] point : points) {
            kdtree.insert(point);
        }

        KDNode target1 = new KDNode(new double[] {0.0, 0.0});
        KDNode result1 = new KDNode(new double[] {0.0, 0.0});

        assertTrue("Find closest test 1 failed", result1.equals(kdtree.findClosest(target1)));

        KDNode target2 = new KDNode(new double[] {0.5, 0.9});
        KDNode result2 = new KDNode(new double[] {0.1, 0.9});

        assertTrue("Find closest test 2 failed", result2.equals(kdtree.findClosest(target2)));

        KDNode target3 = new KDNode(new double[] {0.6, 0.9});
        KDNode result3 = new KDNode(new double[] {1.0, 1.0});

        assertTrue("Find closest test 3 failed", result3.equals(kdtree.findClosest(target3)));

        KDNode target4 = new KDNode(new double[] {-6.0, 2.0});
        KDNode result4 = new KDNode(new double[] {-1.0, 3.0});

        assertTrue("Find closest test 4 failed", result4.equals(kdtree.findClosest(target4)));

        KDNode target5 = new KDNode(new double[] {-6.000001, 2.0});
        KDNode result5 = new KDNode(new double[] {-5.0, -3.0});

        assertTrue("Find closest test 4 failed", result5.equals(kdtree.findClosest(target5)));
    }
}

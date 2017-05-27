package org.yuezhang.practices;

import java.util.Arrays;

/**
 * Created by yuezhang on 5/27/17.
 */
public class KDNode {
    public KDNode left, right;

    private final double[] points;

    public KDNode(double[] points) {
        this.points = points;
        this.left = null;
        this.right = null;
    }

    /**
     * single dimension value accessor
     *
     * @param dim int
     * @return double
     */
    public double getDistInDim(int dim) {
        return this.points[dim];
    }

    /**
     * compare if current node is equal to the passed in node
     *
     * @param kd KDNode
     * @return boolean
     */
    public boolean equals(KDNode kd) {
        boolean same = true;

        for (int dim = 0; dim < points.length; dim++) {
            if (this.getDistInDim(dim) != kd.getDistInDim(dim)) {
                same = false;
                break;
            }
        }

        return same;
    }

    /**
     * calculate the distance from current node to the passed in node
     *
     * @param kd KDNode
     * @return double
     */
    public double distanceTo(KDNode kd) {
        double distance = 0.0;

        for (int dim = 0; dim < points.length; dim++) {
            double distInCurrDim = this.getDistInDim(dim) - kd.getDistInDim(dim);
            distance += distInCurrDim * distInCurrDim;
        }

        return Math.sqrt(distance);
    }
}
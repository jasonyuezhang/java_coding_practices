package org.yuezhang.practices;

/**
 * Created by yuezhang on 5/27/17.
 */
public class KDTree {
    private int size = 0;
    private KDNode root = null;

    private final int dimension;

    public KDTree(int dimensions) {
        this.dimension = dimensions;
    }

    /**
     * KDTree size accessor
     *
     * @return int
     */
    public int size() {
        return this.size;
    }

    /**
     * insert multi-dimension value into KDTree, return the root of current tree
     *
     * @param points double[]
     * @return KDNode
     */
    public KDNode insert(double[] points) {
        this.root = insertRec(this.root, new KDNode(points), 0);

        return this.root;
    }

    /**
     * insert KDNode into KDTree, return the root of current tree
     *
     * @param kd KDNode
     * @return KDNode
     */
    public KDNode insert(KDNode kd) {
        this.root = insertRec(this.root, kd, 0);

        return this.root;
    }

    /**
     * recursively find the empty level and insert the passed in node,
     * return current root
     *
     * @param root KDNode
     * @param kd KDNode
     * @param depth int
     * @return KDNode
     */
    private KDNode insertRec (KDNode root, KDNode kd, int depth) {
        final int currDim = depth % this.dimension;

        // base cases
        if (root == null) {
            this.size++;
            return kd;
        }

        // if root equals passed in node, don't insert
        if (root.equals(kd)) {
            return root;
        }

        // recursive call
        if (kd.getDistInDim(currDim) <= root.getDistInDim(currDim)) {
            root.left = insertRec(root.left, kd, depth + 1);
        } else {
            root.right = insertRec(root.right, kd, depth + 1);
        }

        this.size++;
        return root;
    }

    /**
     * find the closest node of the passed in node
     *
     * @param target KDNode
     * @return KDNode
     */
    public KDNode findClosest (KDNode target) {
        return findClosestRec(this.root, null, target, 0);
    }

    /**
     * find the closest node of the passed in node recursively
     *
     * @param root KDNode
     * @param closest KDNode
     * @param target KDNode
     * @param depth int
     * @return KDNode
     */
    private KDNode findClosestRec (KDNode root, KDNode closest, KDNode target, int depth) {
        final int currDim = depth % this.dimension;

        // base cases
        if (root == null) {
            return closest;
        } else if (root.equals(target)) {
            return root;
        }

        // if root is the closest node, set root as closest
        if (closest == null || target.distanceTo(root) <= target.distanceTo(closest)) {
            closest = root;
        }

        // if target falls to the left of the panel, check left space, otherwise, check the right space
        // if the target lays on the panel, check the left space
        KDNode firstRoot = root.right, secondRoot = root.left;
        if (target.getDistInDim(currDim) <= closest.getDistInDim(currDim)) {
            firstRoot = root.left;
            secondRoot = root.right;
        }

        // recursive call
        closest = findClosestRec(firstRoot, closest, target, depth + 1);

        // search optimization - if the distance from target to closest if less than
        // the distance from target to current root panel, the other side of panel
        // is impossible to have a better choice, skip search
        if (Math.abs(target.getDistInDim(currDim) - root.getDistInDim(currDim)) <= target.distanceTo(closest)) {
            closest = findClosestRec(secondRoot, closest, target, depth + 1);
        }

        return closest;
    }
}

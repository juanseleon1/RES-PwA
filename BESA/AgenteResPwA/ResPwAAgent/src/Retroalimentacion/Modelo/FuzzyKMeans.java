/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Retroalimentacion.Modelo;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class FuzzyKMeans {
//    private double[][] membershipMatrix;
//    private double epsilon;
//    private static final int K_CLUSTER_NUMBER = 3;
//    private List<T> points;
//
//    /** The list of clusters resulting from the last call to {@link #cluster(Collection)}. */
//    private List<CentroidCluster<T>> clusters;
//    
//    
//    private void initializeMembershipMatrix() {
//        for (int i = 0; i < points.size(); i++) {
//            for (int j = 0; j < K_CLUSTER_NUMBER; j++) {
//                membershipMatrix[i][j] = Math.random();
//            }
//            membershipMatrix[i] = normalizeArray(membershipMatrix[i]);
//        }
//    }
//    
//    private double[] normalizeArray(double[] values){
//        double totalProbability;
//        
//        totalProbability = sumTotalValues(values);
//        
//        for (int i = 0; i < values.length; i++) {
//            values[i] = values[i] / totalProbability;
//        }
//        
//        return values;
//    }
//    
//    private double sumTotalValues(double[] values){
//        double sumProbability = 0d;
//        for (int i = 0; i < values.length; i++) {
//            sumProbability += values[i];
//        }
//        
//        return sumProbability;
//    }
//    
//    public List<T> getDataPoints() {
//        return points;
//    }
//    
//    public List<CentroidCluster<T>> getClusters() {
//        return clusters;
//    }
//    
//    public double getObjectiveFunctionValue() {
//
//        int i = 0;
//        double objFunction = 0.0;
//        for (final T point : points) {
//            int j = 0;
//            for (final CentroidCluster<T> cluster : clusters) {
//                final double dist = distance(point, cluster.getCenter());
//                objFunction += (dist * dist) * FastMath.pow(membershipMatrix[i][j], fuzziness);
//                j++;
//            }
//            i++;
//        }
//        return objFunction;
//    }
}

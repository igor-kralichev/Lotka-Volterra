package JavaApplication1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javaapplication1.Graph1;
import javaapplication1.Graph2;
import javaapplication1.Graph3;
import javaapplication1.Graph4;

public class NewClass {

    public static void matMultMat(double[][] matrix1, double[][] matrix2, double[][] result, int cols1, int rows1, int cols2) {
        for (int i = 0; i < rows1; ++i) {
            for (int j = 0; j < cols2; ++j) {
                result[i][j] = 0.0;
                for (int k = 0; k < cols1; ++k) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
    }

    public static void matMultVec(double[][] matrix1, double[] vector1, double[] result, int cols1, int rows1) {
        for (int i = 0; i < rows1; ++i) {
            result[i] = 0.0;
            for (int k = 0; k < cols1; ++k) {
                result[i] += matrix1[i][k] * vector1[k];
            }
        }
    }

    public static double determinant(double[][] matrix, int size) {
        if (size == 1) {
            return matrix[0][0];
        } else if (size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            double det = 0.0;
            for (int j = 0; j < size; ++j) {
                double[][] temp = new double[size - 1][size - 1];
                for (int k = 1; k < size; ++k) {
                    int tempcol = 0;
                    for (int l = 0; l < size; ++l) {
                        if (l != j) {
                            temp[k - 1][tempcol] = matrix[k][l];
                            ++tempcol;
                        }
                    }
                }
                double sign = (j % 2 == 0) ? 1.0 : -1.0;
                double tempd = determinant(temp, size - 1);
                det += sign * matrix[0][j] * tempd;
            }
            return det;
        }
    }

    public static void inverseMatrix(double[][] matrix, double[][] result, int size) {
        double det = determinant(matrix, size);
        double[][] minor = new double[size][size];

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                double[][] tempMinor = new double[size - 1][size - 1];
                int row = 0;
                for (int k = 0; k < size; ++k) {
                    if (k != i) {
                        int col = 0;
                        for (int l = 0; l < size; ++l) {
                            if (l != j) {
                                tempMinor[row][col] = matrix[k][l];
                                ++col;
                            }
                        }
                        ++row;
                    }
                }
                double sign = ((i + j) % 2 == 0) ? 1.0 : -1.0;
                double minorDet = determinant(tempMinor, size - 1);
                double cofactor = sign * minorDet;
                result[j][i] = cofactor / det;
            }
        }
    }
    
    
    
 public static double f1(double x, double y, double z,double[][] a) {
        return x*(a[0][0] + a[0][1]*x+a[0][2]*y+a[0][3]*z);
    }

    public static double f2(double x, double y, double z,double[][] a) {
        return y*(a[1][0] + a[1][1]*x+a[1][2]*y+a[1][3]*z);
    }

    public static double f3(double x, double y, double z,double[][] a) {
        return z*(a[2][0] + a[2][1]*x+a[2][2]*y+a[2][3]*z);
    }
    
public static void runge(double[][] res, double[][] a, int m) {
        int n = 11; // количество итераций
        double h = 10.0/11.0; // шаг интегрирования
        double x0 = 0.527; // начальное значение x
        double y0 = 0.343; // начальное значение y
        double z0 = 0.131; // начальное значение z
        
        double x=x0;
        double y=y0;
        double z=z0;

    for (int i = 0; i < n; i++) {
            double k1x = h * f1(x, y, z,a);
            double k1y = h * f2(x, y, z,a);
            double k1z = h * f3(x, y, z,a);

            double k2x = h * f1(x + k1x / 2, y + k1y / 2, z + k1z / 2,a);
            double k2y = h * f2(x + k1x / 2, y + k1y / 2, z + k1z / 2,a);
            double k2z = h * f3(x + k1x / 2, y + k1y / 2, z + k1z / 2,a);

            double k3x = h * f1(x + k2x / 2, y + k2y / 2, z + k2z / 2,a);
            double k3y = h * f2(x + k2x / 2, y + k2y / 2, z + k2z / 2,a);
            double k3z = h * f3(x + k2x / 2, y + k2y / 2, z + k2z / 2,a);

            double k4x = h * f1(x + k3x, y + k3y, z + k3z,a);
            double k4y = h * f2(x + k3x, y + k3y, z + k3z,a);
            double k4z = h * f3(x + k3x, y + k3y, z + k3z,a);

            x +=(k1x + 2 * k2x + 2 * k3x + k4x) / 6;
            y +=(k1y + 2 * k2y + 2 * k3y + k4y) / 6;
            z +=(k1z + 2 * k2z + 2 * k3z + k4z) / 6;
            
            res[0][i + 1] = x;
            res[1][i + 1] = y;
            res[2][i + 1] = z;
    }
     // Вывод матрицы res
        System.out.println("Результат по Рунге:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n+1; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
}

    
    public static void findCoefs(double[][] matrix1, double[][] d, double[][] result, int maincol, int n, int m, int metnum) {
        n -= 1;
        int l = m + 1;
        double[][] X = new double[n][l];
        double[][] Xt = new double[l][n];
        if (metnum == 1) {
            for (int i = 0; i < n; i++) {
                X[i][0] = (matrix1[i + 1][maincol] + matrix1[i][maincol]) / 2;
                for (int j = 1; j < l; j++) {
                    X[i][j] = (matrix1[i + 1][maincol] * matrix1[i + 1][j - 1] + matrix1[i][maincol] * matrix1[i][j - 1]) / 2;
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                X[i][0] = 1;
                for (int j = 1; j < l; j++) {
                    X[i][j] = (matrix1[i + 1][j - 1] + matrix1[i][j - 1]) / 2;
                }
            }
        }

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < n; j++) {
                Xt[i][j] = X[j][i];
            }
        }

        double[] dx = new double[n];
        if (metnum == 1) {
            for (int i = 0; i < n; i++) {
                dx[i] = d[i][maincol];
            }
        } else {
            for (int i = 0; i < n; i++) {
                dx[i] = Math.log(matrix1[i + 1][maincol]) - Math.log(matrix1[i][maincol]);
            }
        }

        double[][] XtmultX = new double[l][l];
        double[][] XtmultXinverse = new double[l][l];

        matMultMat(Xt, X, XtmultX, n, l, l);
        inverseMatrix(XtmultX, XtmultXinverse, l);

        double[] Xtd = new double[l];
        matMultVec(Xt, dx, Xtd, n, l);

        double[] ax = new double[l];
        matMultVec(XtmultXinverse, Xtd, ax, l, l);

        for (int i = 0; i < l; i++) {
            result[maincol][i] = ax[i];
        }
        for (int i = 0; i < l; i++) {
            System.out.println("a"+i+":"+ax[i]);     
        }
        System.out.println("");    
    }
    
public static void StaticMeth(double[][] matrix1, double[][] a, double[][] result, int mainrow, int n, int m) {
    for (int j = 0; j < m; j++) {
        result[0][j] = matrix1[0][j];
    }
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < m; j++) {
            result[i + 1][j] = matrix1[i][j] + matrix1[i][j] * (a[j][0] + a[j][1] * matrix1[i][0] + a[j][2] * matrix1[i][1] + a[j][3] * matrix1[i][2]);
        }
    }
}
    
    public static void main(String[] args) {
        int n = 12, m = 3;
        int l = m + 1;

        // Инициализация матрицы A
        double[][] A = new double[n][m];
 
    //Доли компаний
    // Акваника              РОСМ          Городецкие источники
    A[0][0] = 0.527;    A[0][1] = 0.343;    A[0][2] = 0.131;
    A[1][0] = 0.614;    A[1][1] = 0.224;    A[1][2] = 0.162;
    A[2][0] = 0.711;    A[2][1] = 0.155;    A[2][2] = 0.139;
    A[3][0] = 0.778;    A[3][1] = 0.128;    A[3][2] = 0.110;
    A[4][0] = 0.857;    A[4][1] = 0.102;    A[4][2] = 0.061;
    A[5][0] = 0.885;    A[5][1] = 0.080;    A[5][2] = 0.045;                            
    A[6][0] = 0.839;    A[6][1] = 0.112;    A[6][2] = 0.049;
    A[7][0] = 0.890;    A[7][1] = 0.080;    A[7][2] = 0.040;
    A[8][0] = 0.902;    A[8][1] = 0.062;    A[8][2] = 0.036;
    A[9][0] = 0.905;    A[9][1] = 0.067;    A[9][2] = 0.029;
    A[10][0] = 0.911;   A[10][1] = 0.064;   A[10][2] = 0.024;
    A[11][0] = 0.919;   A[11][1] = 0.064;   A[11][2] = 0.017;

        double[][] d = new double[n - 1][m];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                d[i][j] = A[i + 1][j] - A[i][j];
            }
        }
        System.out.println("Интегральный метод");
        System.out.println("Коэффициенты взаимодействия");
        double[][] a = new double[m][l];
        for (int i = 0; i < m; i++) {
            findCoefs(A, d, a, i, n, m, 1);
        }
        //Подсчёт интегральным методом
        double[][] intmet = new double[n][m];
        
        StaticMeth(A, a, intmet, 1, n, m);
        
        System.out.println("Решение статической системы");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(intmet[i][j] +" ");
            }
            System.out.println();
        }
        //Статика для 3 графика
        int[] xvg3 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = intmet[i][0] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        xvg3 [i] = value;
        }
        Graph3.vx3= xvg3;
        
        int[] yvg3 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = intmet[i][1] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        yvg3 [i] = value;
        }
        Graph3.vy3= yvg3;
        
        int[] zvg3 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = intmet[i][2] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        zvg3 [i] = value;
        }
        Graph3.vz3= zvg3;
        
        //Доли для 3 графика
        int[] xfg3 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][0] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        xfg3 [i] = value;
        }
        Graph3.x= xfg3;
        int[] yfg3 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][1] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        yfg3 [i] = value;
        }
        Graph3.y= yfg3;
        int[] zfg3 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][2] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        zfg3 [i] = value;
        }
        Graph3.z= zfg3;
        
        //Отрисовка 1 графика
        Graph3.main(args);
        
        double[][] res = new double[m][n];
        for (int i = 0; i < m; i++) {
            res[i][0] = A[0][i];
        }
        System.out.println("Решение динамической системы");
        runge(res, a, m);
        
        //Рунге для 1 графика
        int[] xvg1 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = res[0][i] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        //System.out.println(value);
        xvg1 [i] = value;
        }
        Graph1.vx1= xvg1;
        
        int[] yvg1 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = res[1][i] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        yvg1 [i] = value;
        }
        Graph1.vy1= yvg1;
        
        int[] zvg1 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = res[2][i] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        zvg1 [i] = value;
        }
        Graph1.vz1= zvg1;
        
        //Доли для 1 графика
        int[] xfg1 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][0] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        xfg1 [i] = value;
        }
        Graph1.x= xfg1;
        int[] yfg1 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][1] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        yfg1 [i] = value;
        }
        Graph1.y= yfg1;
        int[] zfg1 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][2] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        zfg1 [i] = value;
        }
        Graph1.z= zfg1;
        
        //Отрисовка 1 графика
        Graph1.main(args);
         
        System.out.println("Логарифмический метод");
        System.out.println("Коэффициенты взаимодействия");
        for (int i = 0; i < m; i++) {
            findCoefs(A, d, a, i, n, m, 2);
        }
        //Подсчёт логарифмическим методом
        double[][] logmet = new double[n][m];
        StaticMeth(A, a, logmet, 1, n, m);
        
       
        System.out.println("Решение статической системы");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(logmet[i][j] +" ");
            }
            System.out.println();
        }
        
        
        //Статика для 4 графика
        int[] xvg4 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = logmet[i][0] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        xvg4 [i] = value;
        }
        Graph4.vx4= xvg4;
        
        int[] yvg4 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = logmet[i][1] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        yvg4 [i] = value;
        }
        Graph4.vy4= yvg4;
        
        int[] zvg4 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = logmet[i][2] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        zvg4 [i] = value;
        }
        Graph4.vz4= zvg4;
        
        //Доли для 3 графика
        int[] xfg4 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][0] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        xfg4 [i] = value;
        }
        Graph4.x= xfg4;
        int[] yfg4 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][1] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        yfg4 [i] = value;
        }
        Graph4.y= yfg4;
        int[] zfg4 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][2] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        zfg4 [i] = value;
        }
        Graph4.z= zfg4;
        
        //Отрисовка 4 графика
        Graph4.main(args);
        
        System.out.println("Решение динамической системы");
        runge(res, a, m);
        
        //Рунге для 2 графика
        int[] xvg2 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = res[0][i] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        xvg2 [i] = value;
        }
        Graph2.vx2= xvg2;
        int[] yvg2 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = res[1][i] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        yvg2 [i] = value;
        }
        Graph2.vy2= yvg2;
        int[] zvg2 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = res[2][i] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        zvg2 [i] = value;
        }
        Graph2.vz2= zvg2;
        //Доли для второго
        int[] xfg2 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][0] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        xfg2 [i] = value;
        }
        Graph2.x= xfg2;
        int[] yfg2 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][1] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        yfg2 [i] = value;
        }
        Graph2.y= yfg2;
        int[] zfg2 = new int[12];
        for (int i=0;i<12;i++){
        double doubleValue = A[i][2] *1000;
        int value = (int) doubleValue;
        value = (1000-value)/2;
        zfg2 [i] = value;
        }
        Graph2.z= zfg2;
        
        
        //Отрисовка 2 графика
        Graph2.main(args); 
    }
}
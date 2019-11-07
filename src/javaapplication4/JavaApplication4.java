package javaapplication4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class JavaApplication4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[][] data = new double[4][101];
        double[] xm = new double[101];
        double[] xn = new double[11];
        for (int i=0;i<11;i++) {xn[i]=i-5;}
        int alpha;
        System.out.println("Function  4*x-2x^2-0.9");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("|     |  Linear empirical formula |  Quadratic empirical formula  |");
        System.out.println("|Alpha|---------------------------|-------------------------------|");
        System.out.println("|     |      a1     |      a2     |   a1    |     a2   |     a3   |");
        System.out.println("|-----|-------------|-------------|---------|----------|----------|");
        
        
        alpha=1;
        for (int i=0;i<101;i++){
            xm[i]=0.1*i-5;
            data[0][i]=4*xm[i]-2*xm[i]*xm[i]-0.9;
            data[1][i]=data[0][i]+(Math.random()-0.5)*alpha;
        }
        double[] lin_interp=Approximation.LinearApproximation(xm,data[1]);
        double[] kvadr_interp=Approximation.SecondPowerApproximation(xm, data[1]);
        for (int i=0;i<101;i++){
            data[3][i]=lin_interp[1]*xm[i]+lin_interp[0];
            data[2][i]=kvadr_interp[0]*xm[i]*xm[i]+kvadr_interp[1]*xm[i]+kvadr_interp[2];
        }
        Draw myDraw1 = new Draw(xn,data,"Approximation for alpha=1",500,400,"%.1f",10,10);
        System.out.printf("|  1  |%13.5f|%13.5f|%9.5f|%10.5f|%10.5f|\n",lin_interp[1],lin_interp[0],kvadr_interp[0],kvadr_interp[1],kvadr_interp[2]);
        System.out.printf("|     |%13.5s|%13.5s|%8.2f%%|%9.2f%%|%9.2f%%|\n"," "," ",Math.abs((kvadr_interp[0]+2)/(-2)*100),Math.abs((kvadr_interp[1]-4)/4*100),Math.abs((kvadr_interp[2]+0.9)/(-0.9)*100));
        System.out.println("|-----|-------------|-------------|---------|----------|----------|");
        
        alpha=10;
        for (int i=0;i<101;i++)
            data[1][i]=data[0][i]+(Math.random()-0.5)*alpha;
        lin_interp=Approximation.LinearApproximation(xm,data[1]);
        kvadr_interp=Approximation.SecondPowerApproximation(xm, data[1]);
        for (int i=0;i<101;i++){
            data[3][i]=lin_interp[1]*xm[i]+lin_interp[0];
            data[2][i]=kvadr_interp[0]*xm[i]*xm[i]+kvadr_interp[1]*xm[i]+kvadr_interp[2];
        }
        Draw myDraw2 = new Draw(xn,data,"Approximation for alpha=10",500,400,"%.1f",510,10);
        System.out.printf("|  10 |%13.5f|%13.5f|%9.5f|%10.5f|%10.5f|\n",lin_interp[1],lin_interp[0],kvadr_interp[0],kvadr_interp[1],kvadr_interp[2]);
        System.out.printf("|     |%13.5s|%13.5s|%8.2f%%|%9.2f%%|%9.2f%%|\n"," "," ",Math.abs((kvadr_interp[0]+2)/(-2)*100),Math.abs((kvadr_interp[1]-4)/4*100),Math.abs((kvadr_interp[2]+0.9)/(-0.9)*100));
        System.out.println("|-----|-------------|-------------|---------|----------|----------|"); 
        
        alpha=100;
        for (int i=0;i<101;i++)
            data[1][i]=data[0][i]+(Math.random()-0.5)*alpha;
        lin_interp=Approximation.LinearApproximation(xm,data[1]);
        kvadr_interp=Approximation.SecondPowerApproximation(xm, data[1]);
        for (int i=0;i<101;i++){
            data[3][i]=lin_interp[1]*xm[i]+lin_interp[0];
            data[2][i]=kvadr_interp[0]*xm[i]*xm[i]+kvadr_interp[1]*xm[i]+kvadr_interp[2];
        }
        Draw myDraw3 = new Draw(xn,data,"Approximation for alpha=100",500,400,"%.1f",1010,10);
        System.out.printf("| 100 |%13.5f|%13.5f|%9.5f|%10.5f|%10.5f|\n",lin_interp[1],lin_interp[0],kvadr_interp[0],kvadr_interp[1],kvadr_interp[2]);
        System.out.printf("|     |%13.5s|%13.5s|%8.2f%%|%9.2f%%|%9.2f%%|\n"," "," ",Math.abs((kvadr_interp[0]+2)/(-2)*100),Math.abs((kvadr_interp[1]-4)/4*100),Math.abs((kvadr_interp[2]+0.9)/(-0.9)*100));
        System.out.println("-------------------------------------------------------------------");
        
    }
}
    
class Approximation{
    public static double[] LinearApproximation(double[] xm, double[] ym){
        if (xm.length!=ym.length) return new double[1];
        double x=0,y=0,xy=0,x2=0;
        for (int i=0;i<xm.length;i++){
            x+=xm[i];
            y+=ym[i];
            xy+=xm[i]*ym[i];
            x2+=xm[i]*xm[i];
        }
        double[] res = new double[2];
        res[0]=(y*x2-xy*x)/(xm.length*x2-x*x);
        res[1]=(xm.length*xy-x*y)/(xm.length*x2-x*x);
        return res;
    }
    
    public static double[] SecondPowerApproximation(double[] xm, double[] ym){
        if (xm.length!=ym.length) return new double[1];
        double x=0,y=0,xy=0,x2=0,x3=0,x4=0,x2y=0;
        for (int i=0;i<xm.length;i++){
            x+=xm[i];
            y+=ym[i];
            xy+=xm[i]*ym[i];
            x2+=xm[i]*xm[i];
            x3+=Math.pow(xm[i], 3);
            x4+=Math.pow(xm[i], 4);
            x2y+=xm[i]*xm[i]*ym[i];
        }
        double[][] tmp_matr={{x4,x3,x2,x2y},{x3,x2,x,xy},{x2,x,xm.length,y}};  
        double[] res = GaussSolve(tmp_matr);  
        return res; 
    }
    
    public static double[] GaussSolve(double[][] data){
        for (int i=0;i<data.length;i++)
            if (data.length!=data[i].length-1) return new double[1];
        for (int i=0;i<data.length;i++){
            for (int j=i+1;j<data.length;j++){
                double koef=data[i][i]/data[j][i];
                for (int k=i;k<data[i].length;k++)
                    data[j][k]=data[j][k]*koef-data[i][k];
            }
        }
        double[] res = new double[data.length];
        for (int i=data.length-1;i>-1;i--){
            for (int j=data[i].length-1;j>=i;j--)
                data[i][j]/=data[i][i];
            for (int j=i+1;j<data.length;j++)
                data[i][data[i].length-1]-=data[j][data[j].length-1]*data[i][j];
            res[i]=data[i][data[i].length-1];
        }
        return res;
    }
}

class Draw extends javax.swing.JFrame {
 
    private double[] xn;
    private double y_min,y_max;
    private double[][] y;
    private int[][] yInt;
    private final Dimension size; 
    private final Dimension startPointXoY; 
    private double scale;
    String title;
    String form;
    int lx,ly;
    private static final Color[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.MAGENTA,Color.ORANGE,Color.PINK};
 
    public Draw(double[] xn, double[][] y, String title,int width, int height,String form, int lx, int ly) {
        this.xn = xn; 
        this.y = y;
        this.title=title;
        this.form=form;
        this.lx=lx;
        this.ly=ly;
        y_min=y[0][0];
        y_max=y[0][0];
        for (int i=0;i<y.length;i++)
            for (int j=0;j<y[i].length;j++){
                if (y[i][j]>y_max) y_max=y[i][j];
                if (y[i][j]<y_min) y_min=y[i][j];
            }
        size=new Dimension(width,height);
        startPointXoY=new Dimension(30,size.height-30);
        this.scale=((double)y_max-y_min)/(size.height-60);
        yInt = new int[y.length][y[0].length];
        reBuildArreys();
        initInterface();
    }
 
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,size.width,size.height);
        g.setColor(Color.BLACK);
        g.drawLine(startPointXoY.width, startPointXoY.height, startPointXoY.width, 30);
        g.drawLine(startPointXoY.width, startPointXoY.height, size.width-30, startPointXoY.height);
 
        for (int i = 0; i < xn.length; i++){
            g.drawLine(startPointXoY.width + (int)Math.round((double)(size.width-60)/(xn.length-1)*i),startPointXoY.height,startPointXoY.width + (int)Math.round((double)(size.width-60)/(xn.length-1)*i),startPointXoY.height - 5);
            g.drawString(String.format(form, xn[i]),startPointXoY.width - 10 + (int)Math.round((double)(size.width-60)/(xn.length-1)*i),startPointXoY.height + 15);
        }
        for (int i = 0; i < 11; i++)
            g.drawString(String.format(form, (y_min+(y_max-y_min)/10*i)),startPointXoY.width -28,startPointXoY.height - (int)Math.round((double)size.height/11*i));
        for (int i=0;i<y.length;i++){
            g.setColor(colors[i]);
            double x_scale = ((double)size.width-60)/y[i].length;
            for (int j=0;j<y[i].length-1;j++)
                g.drawLine(startPointXoY.width+(int)Math.round(j*x_scale), yInt[i][j], startPointXoY.width+(int)Math.round((j+1)*x_scale), yInt[i][j+1]);
        }
    }
 
    private void initInterface() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(size);
        setResizable(false);
        setTitle(title);
        setLocation(lx,ly);
        setVisible(true);
    }
 
    private void reBuildArreys() {
        for (int i = 0; i < y.length; i++)
        for (int j = 0; j < y[0].length;j++){
            yInt[i][j] = (int)Math.round((y[i][j]-y_min)/scale);
            yInt[i][j] = startPointXoY.height - (yInt[i][j]);
        }
    }
}
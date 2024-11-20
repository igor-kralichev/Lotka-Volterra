package javaapplication1;

import javax.swing.*;
import java.awt.*;
import JavaApplication1.NewClass;
 
class DrawingComponent2 extends JPanel {
int yearsg[] =  Graph2.years;
int xg[] =  Graph2.x;
int yg[] =  Graph2.y;
int zg[] =  Graph2.z;
int xvg2[] =  Graph2.vx2;
int yvg2[] =  Graph2.vy2;
int zvg2[] =  Graph2.vz2;
int ng = Graph2.n;
    
    @Override
    protected void paintComponent(Graphics gh) {   
     int x = 50;
     Graphics2D drp = (Graphics2D)gh;
     Color Yellow = new Color (209,171,0);
     Color Red = new Color(255, 0, 0);
     Color Green = new Color(0, 255, 0);
     drp.drawLine(x, 500, 600 + x, 500);
     drp.drawLine(x, 500, x, 10);
     drp.drawString("Год", 600 + x, 520);
     drp.drawString("X", 580 + x, 41);
     drp.drawString("Y", 580 + x, 468);
     drp.drawString("Z", 580 + x, 492);
     drp.drawString("Доли рынка", 10+x, 10); 
     int year = 2011;
     drp.drawString("2011", x, 520);   
     for (int i = 0; i<11;i++){
     year++;
     x=x+50;
     String yearG =Integer.toString(year);
     drp.drawString(yearG,x, 520);   
    }
     int y=10;
     x=50;
     double share = 1.0;
     drp.drawString("1.0", 80-x , y);  
    for (int i = 0; i<9;i++){
     share = Math.round((share - 0.1)*100.0)/100.0;
     y=y+50;
     String shareG = String.valueOf(share);
     drp.drawString(shareG, 80-x , y);  
    }
    drp.drawString("0", 80-x , y+50);
     drp.setColor(Green);
     drp.drawPolyline(yearsg, xvg2, ng);
     for (int i = 0; i < 12; i++){
        int yearg = yearsg[i];
        int xgg = xg[i];
        drp.drawOval(yearg,xgg,3,3);
     }
     drp.setColor(Yellow);
     drp.drawPolyline(yearsg, yvg2 , ng);
     for (int i = 0; i < 12; i++){
        int yearg = yearsg[i];
        int ygg = yg[i];
        drp.drawOval(yearg,ygg,3,3);
     }
     drp.setColor(Red);
     drp.drawPolyline(yearsg, zvg2 , ng);
     for (int i = 0; i < 12; i++){
        int yearg = yearsg[i];
        int zgg = zg[i];
        drp.drawOval(yearg,zgg,3,3);
     }
    }
}
 
public class Graph2 extends JFrame{
     public static int years[] = {70,120,170,220,270,320,370,420,470,520,570,620};
     public  static int x[];
     public  static int y[];
     public  static int z[];
     public  static int vx2[]; 
     public  static int vy2[]; 
     public  static int vz2[];
     public static int n = 12;

    
 public Graph2 () {  
        super("График динамической системы логарифмического метода");
        JPanel jcp = new JPanel(new BorderLayout());
        setContentPane(jcp);
        jcp.add(new DrawingComponent2 (), BorderLayout.CENTER);     
        jcp.setBackground(Color.gray);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
	public static void main(String[] args)   {            
        new Graph2().setVisible(true);
}
}
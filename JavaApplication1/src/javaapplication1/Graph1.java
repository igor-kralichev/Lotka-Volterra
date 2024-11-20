package javaapplication1;

import javax.swing.*;
import java.awt.*;
import JavaApplication1.NewClass;
 
class DrawingComponent1 extends JPanel {


    @Override
    protected void paintComponent(Graphics gh) {  
    int yearsg[] =  Graph1.years;
    int xg[] =  Graph1.x;
    int yg[] =  Graph1.y;
    int zg[] =  Graph1.z;
    int xvg[] =  Graph1.vx1;
    int yvg[] =  Graph1.vy1;
    int zvg[] =  Graph1.vz1;
    
    int ng = Graph1.n;
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
     drp.drawPolyline(yearsg, xvg, ng);
     
     for (int i = 0; i < 12; i++){
        int yearg = yearsg[i];
        int xgg = xg[i];
        drp.drawOval(yearg,xgg,3,3);
     }
     drp.setColor(Yellow);
     drp.drawPolyline(yearsg, yvg , ng);
     
     for (int i = 0; i < 12; i++){
        int yearg = yearsg[i];
        int ygg = yg[i];
        drp.drawOval(yearg,ygg,3,3);
     }
     drp.setColor(Red);
     drp.drawPolyline(yearsg, zvg , ng);
     
     for (int i = 0; i < 12; i++){
        int yearg = yearsg[i];
        int zgg = zg[i];
        drp.drawOval(yearg,zgg,3,3);
     }
    }
}
 
public  class Graph1 extends JFrame{
     public static int years[] = {70,120,170,220,270,320,370,420,470,520,570,620};
     public  static int x[];
     public  static int y[];
     public  static int z[]; 
     public  static int vx1[]; 
     public  static int vy1[];
     public  static int vz1[];
      
     public static int n = 12;
    
 public Graph1 () {  
        super("График динамической системы интегрального метода");
        JPanel jcp = new JPanel(new BorderLayout());
        setContentPane(jcp);
        jcp.add(new DrawingComponent1 (), BorderLayout.CENTER);     
        jcp.setBackground(Color.gray);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
	public static void main(String[] args)   {            
        new Graph1().setVisible(true);
}
}
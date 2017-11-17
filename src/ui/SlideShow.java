package ui;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class SlideShow{
    JLabel pic;
    Timer tm;
    int x = 0;

    JPanel mainPanel;
    JFrame mainFrame;

    //Images Path In Array
    String[] list = {
                      "add.png",//0
                      "assignSup.jpg",//1
                      "select.png",//2
                      "continue.png",//3
                      "ok.png",//4
                      "bla.png",//5
                      "view.png"//6
                    };
    
    public SlideShow(){
      //  super("Java SlideShow");
        
    	pic = new JLabel();
        pic.setBounds(40, 30, 700, 300);

        mainPanel=new JPanel();
        mainFrame=new JFrame();

        //Call The Function SetImageSize
      //  SetImageSize(6);
       

       //set a timer
        tm = new Timer(500,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SetImageSize(x);
                x += 1;
                if(x >= list.length )
                    x = 0; 
            }
        });
        mainPanel.setLayout(null);
        
        mainPanel.add(pic);
       
        tm.start();
        //setSize(800, 400);
        //mainPanel.getContentPane().setBackground(Color.decode("#bdb67b"));
        //setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
      /* mainFrame.add(mainPanel);
        mainFrame.setSize(400, 400);
        mainFrame.setVisible(true);
        */
    }
    public JPanel getPanel()
    {
    	return mainPanel;
    }
   //create a function to resize the image 
    public void SetImageSize(int i){
        Icon icon = new ImageIcon(list[i]);
        Image img = ((ImageIcon) icon).getImage();
        img = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(img);
        pic.setIcon(newImc);
    }

public static void main(String[] args){ 
      
    new SlideShow();
}
}
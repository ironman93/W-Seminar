// import statements  
import java.awt.*;    
import javax.swing.*;    
public class GroupExample {  
    public static void main(String[] args) {   
   
    	GroupExample example = new GroupExample();

        
         
    }  
    


    JLabel clickMe = new JLabel("Click Here");  
    JButton button = new JButton("This Button"); 
    
    public GroupExample() {

        JFrame frame = new JFrame("GroupLayoutExample"); 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        Container contentPanel = frame.getContentPane(); 

        GroupLayout groupLayout = new GroupLayout(contentPanel); 

        contentPanel.setLayout(groupLayout);  
        groupLayout.setVerticalGroup(  
                groupLayout.createSequentialGroup()  
                           .addComponent(clickMe)  
                           .addComponent(button));  
        groupLayout.setHorizontalGroup(  
                    groupLayout.createParallelGroup()
                                .addComponent(clickMe)  
                                .addGap(10, 20, 100)  
                                .addComponent(button)
        );

        frame.pack();  
        frame.setVisible(true); 
    }
    
}  
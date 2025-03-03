package Lab1;
import javax.swing.JOptionPane;
public class Ex62 {
    public static void main(String[] args){
        String strName= JOptionPane.showInputDialog("What's your name?");
        String ageInput=JOptionPane.showInputDialog("How old are you?");
        int iAge = Integer.parseInt(ageInput);
        String heightInput= JOptionPane.showInputDialog("How tall are you (m)?");
        double dHeight= Double.parseDouble(heightInput); 
        JOptionPane.showMessageDialog(null, 
            "Mrs./Ms. "+strName+" , "+iAge+" years old.\n" +
            "Your height is " + dHeight + " m.");    
        System.exit(0);}
}

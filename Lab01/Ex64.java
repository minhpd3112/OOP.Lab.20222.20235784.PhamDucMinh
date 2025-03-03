package Lab1;
import javax.swing.JOptionPane;
public class Ex64{
    public static void main(String[] args){
        while (true){
            String monthInput = JOptionPane.showInputDialog("Please enter month:");
            String yearInput = JOptionPane.showInputDialog("Please enter year:");
            String month = monthInput.toLowerCase().trim(); 	//chuyen het ve in thuong
            int year=0;
            if (!yearInput.matches("\\d{4,}")){
                JOptionPane.showMessageDialog(null,"Invalid");
                continue;
            }
            try{
                year = Integer.parseInt(yearInput.trim());
                if (year <= 0) throw new NumberFormatException();
            } 
	    catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Invalid");
                continue;
                }
            int days = 0;
            switch (month) {

                case "january": case "jan": case "jan.": case "1":
                case "march": case "mar": case "mar.": case "3":
                case "may": case "5":
                case "july": case "jul": case "jul.": case "7":
                case "august": case "aug": case "aug.": case "8":
                case "october": case "oct": case "oct.": case "10":
                case "december": case "dec": case "dec.": case "12":
                    days = 31;
                    break;

                case "april": case "apr": case "apr.": case "4":
                case "june": case "jun": case "jun.": case "6":
                case "september": case "sep": case "sep.": case "9":
                case "november": case "nov": case "nov.": case "11":
                    days = 30;
                    break;

		// xử lí t2 riêng
                case "february": case "feb": case "feb.": case "2":
                    days = (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) ? 29 : 28;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid");
                    continue;
            }
            JOptionPane.showMessageDialog(null,"The month "+ monthInput+" in year "+ year+ " has "+ days+ " days.");
            break;
        }
        System.exit(0);
    }
}

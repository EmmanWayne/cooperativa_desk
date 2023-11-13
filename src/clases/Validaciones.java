package clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class Validaciones {
	public void entero(JTextField txtField)
	{
		txtField.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        	if (!(
        			(Character.isDigit(c))|| 
                	(c==KeyEvent.VK_PERIOD && txtField.getText().contains("."))||
                 	(c == KeyEvent.VK_BACK_SPACE) ||
                    (c == KeyEvent.VK_DELETE))) {
                    	e.consume();
                    }
         }
       });
	}
	public void decimal(JTextField txtField)
	{
		txtField.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        	if (!(
        			(Character.isDigit(c))|| 
                	(c==KeyEvent.VK_PERIOD && !txtField.getText().contains("."))||
                 	(c == KeyEvent.VK_BACK_SPACE) ||
                    (c == KeyEvent.VK_DELETE))) {
                    	e.consume();
                    }
         }
       });
	}
	
	public void letras(JTextField txtField) {
		txtField.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!(
		        	(Character.isLetter(c))||
		        	(c == KeyEvent.VK_SPACE)||
	                (c == KeyEvent.VK_BACK_SPACE))) {
		        	e.consume();
		        }
	        }
		});
	}
}

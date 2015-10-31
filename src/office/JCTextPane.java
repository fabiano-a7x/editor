/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package office;
 
import java.awt.Color;  
import java.awt.event.KeyEvent;  
import java.awt.event.KeyListener;  
  
import javax.swing.JTextPane;  
import javax.swing.text.AttributeSet;  
import javax.swing.text.SimpleAttributeSet;  
import javax.swing.text.StyleConstants;  
import javax.swing.text.StyleContext;  
  
public class JCTextPane extends JTextPane implements KeyListener{  
  
    private static final long serialVersionUID = 1L;  
    private String[] biblioteca = new String[]{"algoritmo","ate","caracter","caso","de","dec1","enquanto","entao","escolha","escreva","escrevaln","faca","fim","fimcaso","fimenquanto","fimescolha","fimfuncao","fimpara","fimprocedimento","fimse","funcao","inicio","inteiro","leia","literal","para","procedimento","real","repita","se","senao","variaveis"};  
      
    public JCTextPane() {  
        this.addKeyListener(this);  
    }  
  
    public void pintar(int start, int end, boolean cor){  
        Color color = (cor)? Color.RED : Color.black;  
        StyleContext sc = StyleContext.getDefaultStyleContext();     
        if(start != end){  
            AttributeSet aSet = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);     
            this.getStyledDocument().setCharacterAttributes(start, end, aSet, true);  
        }else{  
            AttributeSet aSet = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);     
            this.setCharacterAttributes(aSet, true);          
        }  
    }  
  
    public void keyPressed(KeyEvent e) {  
        //aqui foi feito com a intenção de nao deixar por dois caracteres na hora que pular linha  
        if(e.getKeyChar() == (char)10 || e.getKeyChar() == '\n'){  
            e.consume();  
            int sel = this.getSelectionEnd();  
            this.setText(this.getText().substring(0, this.getSelectionEnd())+'\r'+this.getText().substring(this.getSelectionEnd(), this.getText().length()));  
            this.setCaretPosition(sel+1);  
        }  
    }  
  
    public int getStart(){  
        for (int i = this.getCaretPosition()-1; i >= 0; i--) {  
            if(this.getText().charAt(i) == '\r' || this.getText().charAt(i) == (char)10 || this.getText().charAt(i) == (char)32){  
                return i+1;  
            }  
        }  
        return 0;  
    }  
  
    public int getEnd(){  
        for (int i = this.getCaretPosition(); i < this.getText().length(); i++) {  
            if(this.getText().charAt(i) == '\r' || this.getText().charAt(i) == (char)10 || this.getText().charAt(i) == (char)32){  
                return i;  
            }  
        }  
        return this.getText().length();  
    }  
  
    public boolean verificaPalavra(String palavra){  
        for (int i = 0; i < biblioteca.length; i++) {  
            if(biblioteca[i].equals(palavra)){  
                return true;  
            }  
        }  
        return false;  
    }  
  
    public void keyReleased(KeyEvent e) {  
        int start = getStart();  
        int end = getEnd();  
//      System.out.println(start +" - "+ end +" - "+ verificaPalavra(this.getText().substring(start, end)));  
        pintar(start, end, verificaPalavra(this.getText().substring(start, end)));  
    }  
  
    public void keyTyped(KeyEvent e) {}  
     public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JCTextPane().setVisible(true);
            }
        });
    }
}  
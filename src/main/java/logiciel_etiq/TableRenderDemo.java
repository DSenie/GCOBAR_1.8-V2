package logiciel_etiq;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class TableRenderDemo{
  public static void main(String argv[]) {

    ColorPane pane = new ColorPane();
    for (int n = 1; n <= 400; n += 1) {
      if (isPrime(n)) {
        pane.append(Color.red, String.valueOf(n) + ' ');
      } else if (isPerfectSquare(n)) {
        pane.append(Color.blue, String.valueOf(n) + ' ');
      } else {
        pane.append(Color.black, String.valueOf(n) + ' ');
      }
    }

    JFrame f = new JFrame("ColorPane example");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setContentPane(new JScrollPane(pane));
    f.setSize(600, 400);
    f.setVisible(true);
  }

  public static boolean isPrime(int n) {
    if (n < 2)
      return false;
    double max = Math.sqrt(n);
    for (int j = 2; j <= max; j += 1)
      if (n % j == 0)
        return false; // j is a factor
    return true;
  }

  public static boolean isPerfectSquare(int n) {
    int j = 1;
    while (j * j < n && j * j > 0)
      j += 1;
    return (j * j == n);
  }

  
}

@SuppressWarnings("serial")
class ColorPane extends JTextPane {

  public void appendNaive(Color c, String s) {
    SimpleAttributeSet aset = new SimpleAttributeSet();
    StyleConstants.setForeground(aset, c);

    int len = getText().length();
    setCaretPosition(len); 
    setCharacterAttributes(aset, false);
    replaceSelection(s);
  }

  public void append(Color c, String s) {
    StyleContext sc = StyleContext.getDefaultStyleContext();
    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

    int len = getDocument().getLength();
    setCaretPosition(len); 
    setCharacterAttributes(aset, false);
    replaceSelection(s); 
  }


}
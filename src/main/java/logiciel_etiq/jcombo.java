package logiciel_etiq;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;

public class jcombo extends JComboBox<Object> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
    private boolean layingOut = false;
    private int widestLengh = 0;
    private boolean wide = false;

    public jcombo(Object[] objs) {
        super(objs);
        this.setEditable(true);
        JTextComponent editor = (JTextComponent) this.getEditor().getEditorComponent();
        editor.setDocument(new AutoCompleteComboBox_jcomb(this));
    }
    


    public boolean isWide() {
        return wide;
    }

    // Setting the JComboBox wide
    public void setWide(boolean wide) {
        this.wide = wide;
        widestLengh = getWidestItemWidth();

    }

    public Dimension getSize() {
        Dimension dim = super.getSize();
        if (!layingOut && isWide())
            dim.width = Math.max(widestLengh, dim.width);
        return dim;
    }

    public int getWidestItemWidth() {

        int numOfItems = this.getItemCount();
        Font font = this.getFont();
        FontMetrics metrics = this.getFontMetrics(font);
        int widest = 0;
        for (int i = 0; i < numOfItems; i++) {
            Object item = this.getItemAt(i);
            int lineWidth = metrics.stringWidth(item.toString());
            widest = Math.max(widest, lineWidth);
        }

        return widest + 80;
    }

    public void doLayout() {
        try {
            layingOut = true;
            super.doLayout();
        } finally {
            layingOut = false;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String t) {
        type = t;
    }

    public static void main(String[] args) {
        String title = "Combo Test";
        JFrame frame = new JFrame(title);
    //	String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
        generale.styles("Nimbus");
        SwingUtilities.updateComponentTreeUI(frame);
		
        String[] items = {
                "I need lot of width to be visible , oh am I visible now",
                "I need lot of width to be visible , oh am I visible now" };
        jcombo simpleCombo = new jcombo(items);
        simpleCombo.setPreferredSize(new Dimension(220, 20));
        simpleCombo.setWide(true);
        JLabel label = new JLabel("Wider Drop Down Demo");

        frame.getContentPane().add(simpleCombo, BorderLayout.NORTH);
        frame.getContentPane().add(label, BorderLayout.SOUTH);
        int width = 200;
        int height = 150;
        frame.setSize(width, height);
        frame.setVisible(true);

    }
}
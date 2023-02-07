package logiciel_etiq.View.combobx;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class jcombo extends JComboBox<Object> {

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

    public jcombo() {

    }


    private boolean isWide() {
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

    private int getWidestItemWidth() {

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
       

    }
}
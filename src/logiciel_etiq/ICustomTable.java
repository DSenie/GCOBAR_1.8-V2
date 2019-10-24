package logiciel_etiq;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;


interface ICustomTable {
	
	public Font getFontCellule();
	public Color getBackground1();
	public Color getForeground1();
	public Color getBackground2();
	public Color getForeground2();
	public Border getBorderCellule();
	public Color getBackgroundSelection();
	public Color getForegroundSelection();
	public int getAlignement();
	public boolean isAlternerLignes();

	public void setAlternerLignes(boolean alternerLignes);
	public void setFontCellule(Font font);
	public void setBackground1(Color couleur);
	public void setForeground1(Color couleur);
	public void setBackground2(Color background2);
	public void setForeground2(Color foreground2);
	public void setBorderCellule(Border border);
	public void setBackgroundSelection(Color couleur);
	public void setForegroundSelection(Color couleur);
	public void setAlignement(int alignement);

}


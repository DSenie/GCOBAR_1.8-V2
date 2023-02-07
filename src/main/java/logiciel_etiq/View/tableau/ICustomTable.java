package logiciel_etiq.View.tableau;

import javax.swing.border.Border;
import java.awt.*;


interface ICustomTable {
	
	Font getFontCellule();
	Color getBackground1();
	Color getForeground1();
	Color getBackground2();
	Color getForeground2();
	Border getBorderCellule();
	Color getBackgroundSelection();
	Color getForegroundSelection();
	int getAlignement();
	boolean isAlternerLignes();

	void setAlternerLignes(boolean alternerLignes);
	void setFontCellule(Font font);
	void setBackground1(Color couleur);
	void setForeground1(Color couleur);
	void setBackground2(Color background2);
	void setForeground2(Color foreground2);
	void setBorderCellule(Border border);
	void setBackgroundSelection(Color couleur);
	void setForegroundSelection(Color couleur);
	void setAlignement(int alignement);

}


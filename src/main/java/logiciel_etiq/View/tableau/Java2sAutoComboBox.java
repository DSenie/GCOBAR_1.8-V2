package logiciel_etiq.View.tableau;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.event.ItemEvent;
import java.util.List;

public class Java2sAutoComboBox extends JComboBox {
  private class AutoTextFieldEditor extends BasicComboBoxEditor {

    private Java2sAutoTextField getAutoTextFieldEditor() {
      return (Java2sAutoTextField) editor;
    }

    AutoTextFieldEditor(List list) {
      editor = new Java2sAutoTextField(list, Java2sAutoComboBox.this);
    }
  }

  public Java2sAutoComboBox(List list) {
    isFired = false;
    autoTextFieldEditor = new AutoTextFieldEditor(list);
    setEditable(true);
    setModel(
            new DefaultComboBoxModel(list.toArray()) {

      protected void fireContentsChanged(Object obj, int i, int j) {
        if (!isFired)
          super.fireContentsChanged(obj, i, j);
      }

    });
    setEditor(autoTextFieldEditor);
  }

  public boolean isCaseSensitive() {
    return autoTextFieldEditor.getAutoTextFieldEditor().isCaseSensitive();
  }

  public void setCaseSensitive(boolean flag) {
    autoTextFieldEditor.getAutoTextFieldEditor().setCaseSensitive(flag);
  }

  public boolean isStrict() {
    return autoTextFieldEditor.getAutoTextFieldEditor().isStrict();
  }

  public void setStrict(boolean flag) {
    autoTextFieldEditor.getAutoTextFieldEditor().setStrict(flag);
  }

  public List getDataList() {
    return autoTextFieldEditor.getAutoTextFieldEditor().getDataList();
  }

  public void setDataList(List list) {
    autoTextFieldEditor.getAutoTextFieldEditor().setDataList(list);
    setModel(new DefaultComboBoxModel(list.toArray()));
  }

  void setSelectedValue(Object obj) {
    if (isFired) {
    } else {
      isFired = true;
      setSelectedItem(obj);
      fireItemStateChanged(new ItemEvent(this, 701, selectedItemReminder, 1));
      isFired = false;
    }
  }

  protected void fireActionEvent() {
    if (!isFired)
      super.fireActionEvent();
  }

  private AutoTextFieldEditor autoTextFieldEditor;

  private boolean isFired;

}

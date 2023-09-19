package component;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import static component.MenuType.MAXIMUM;
import static component.MenuType.MINIMUM;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.MenuElement;
import javax.swing.UIManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import static component.MenuType.REGULAR;

public class TextComponentPopUpBuilder {

    private final JTextComponent component;

    private final JPopupMenu menu;

    private final JMenuItem cutItem;
    private final JMenuItem copyItem;
    private final JMenuItem pasteItem;
    private final JMenuItem deleteItem;

    private final JMenuItem selectAllItem;
    private final JCheckBoxMenuItem rightToLeftItem;
    private final JMenuItem clearItem;
    private final JMenuItem findItem;
    private final JMenuItem undoItem;
    private final JMenuItem redoItem;

    private final JMenu toolsSubMenu;
    private final JMenuItem toUpperItem;
    private final JMenuItem toLowerItem;
    private final JMenuItem toTitleItem;

    private MenuType menuType;

    private final UndoManager undo;
    private final Document doc;
    private JRootPane rootPane;
    private String selectedText;
    private int[] padding = {2, 2, 2, 50};

    

    private void init() {
        UIManager.put("CheckBoxMenuItem.doNotCloseOnMouseClick", true);
        FlatSVGIcon icon = new FlatSVGIcon("icons/inSelection.svg");
        selectAllItem.setAction(new AbstractAction("Select All", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (textNotEmpty()) {

                    component.selectAll();
                }
            }
        });

        icon = new FlatSVGIcon("icons/clear.svg");

        clearItem.setAction(new AbstractAction("Clear", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (textNotEmpty()) {
                    component.setText("");
                }
            }

        });

        icon = new FlatSVGIcon("icons/rtl.svg");
        rightToLeftItem.setAction(new AbstractAction("Toggle Alignment", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (rightToLeftItem.getState()) {
                    rightToLeftItem.setIcon(new FlatSVGIcon("icons/rtl.svg"));
                } else {
                    rightToLeftItem.setIcon(new FlatSVGIcon("icons/ltr.svg"));
                }
                component.getActionMap().get("toggle-componentOrientation").actionPerformed(ae);

            }
        });

        icon = new FlatSVGIcon("icons/copy.svg");
        copyItem.setAction(new AbstractAction("Copy", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isValidForAction()) {
                    component.getActionMap().get("copy-to-clipboard").actionPerformed(ae);
                }
            }
        });

        icon = new FlatSVGIcon("icons/cut.svg");
        cutItem.setAction(new AbstractAction("Cut", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isValidForAction()) {

                    component.getActionMap().get("cut-to-clipboard").actionPerformed(ae);
                }

            }
        });
        icon = new FlatSVGIcon("icons/paste.svg");

        pasteItem.setAction(new AbstractAction("Past", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                component.getActionMap().get("paste-from-clipboard").actionPerformed(ae);

            }
        });

        icon = new FlatSVGIcon("icons/delete.svg");

        deleteItem.setAction(new AbstractAction("Delete", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isValidForAction()) {

                    component.setText(component.getText().replace(selectedText, ""));
                }
            }
        });


        icon = new FlatSVGIcon("icons/find.svg");

        findItem.setAction(new AbstractAction("Find", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (textNotEmpty()) {

                    String text = JOptionPane.showInputDialog(null, "Type text you want to search", "Search...", JOptionPane.PLAIN_MESSAGE);
                    if (text != null) {
                        String componentText = component.getText();
                        if (componentText.contains(text)) {
                            int startIndex = componentText.indexOf(text), endIndex = (startIndex + text.length());

                            component.setSelectionStart(startIndex);
                            component.setSelectionEnd(endIndex);
                        } else {
                       
                                JOptionPane.showMessageDialog(null, "The text doesn't found.", "No text found", JOptionPane.ERROR_MESSAGE);
                            
                        }
                    }
                }
            }
        });

        icon = new FlatSVGIcon("icons/undo.svg");
        undoItem.setAction(new AbstractAction("Undo", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                component.getActionMap().get("Undo").actionPerformed(ae);
            }
        });
        icon = new FlatSVGIcon("icons/redo.svg");
        redoItem.setAction(new AbstractAction("Redo", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                component.getActionMap().get("Redo").actionPerformed(ae);

            }
        });

        icon = new FlatSVGIcon("icons/upper.svg");
        toUpperItem.setAction(new AbstractAction("Convert To UPPER CASE", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isValidForAction() && textNotEmpty()) {
                    component.setText(component.getText().replace(selectedText, selectedText.toUpperCase()));

                }

            }
        });
        icon = new FlatSVGIcon("icons/lower.svg");
        toLowerItem.setAction(new AbstractAction("Convert To lower case", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isValidForAction() && textNotEmpty()) {
                    component.setText(component.getText().replace(selectedText, selectedText.toLowerCase()));

                }

            }
        });
        icon = new FlatSVGIcon("icons/title.svg");
        toTitleItem.setAction(new AbstractAction("Convert To Titlel Case", icon) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isValidForAction() && textNotEmpty()) {
                    String[] split = selectedText.split(" ");
                    String titelCase = "";
                    for (String string : split) {
                        titelCase += String.valueOf(string.charAt(0)).toUpperCase() + string.substring(1) + " ";
                    }
                    component.setText(component.getText().replace(selectedText, titelCase.trim()));

                }

            }
        });

        cutItem.setAccelerator(setShortcut(KeyEvent.VK_X));
        copyItem.setAccelerator(setShortcut(KeyEvent.VK_C));
        pasteItem.setAccelerator(setShortcut(KeyEvent.VK_V));
        deleteItem.setAccelerator(setShortcut(KeyEvent.VK_D));

        selectAllItem.setAccelerator(setShortcut(KeyEvent.VK_A));
        rightToLeftItem.setAccelerator(setShortcut(KeyEvent.VK_R));
        clearItem.setAccelerator(setShortcut(KeyEvent.VK_E));
        findItem.setAccelerator(setShortcut(KeyEvent.VK_F));

        undoItem.setAccelerator(setShortcut(KeyEvent.VK_Z));
        redoItem.setAccelerator(setShortcut(KeyEvent.VK_Y));

        toLowerItem.setAccelerator(setShortcut(KeyEvent.VK_L));
        toUpperItem.setAccelerator(setShortcut(KeyEvent.VK_U));
        toTitleItem.setAccelerator(setShortcut(KeyEvent.VK_T));

    }

    private void undo() {
        component.getActionMap().put("Undo", new AbstractAction("Undo") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (undo.canUndo()) {
                        undo.undo();
                    }
                } catch (CannotUndoException e) {
                }
            }
        });

        component.getInputMap().put(setShortcut(KeyEvent.VK_Z), "Undo");
    }

    private void redo() {
        component.getActionMap().put("Redo", new AbstractAction("Redo") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (undo.canRedo()) {
                        undo.redo();
                    }
                } catch (CannotRedoException e) {
                }
            }
        });

        component.getInputMap().put(setShortcut(KeyEvent.VK_Y), "Redo");
    }

    
    private void enableTextComponentUndoListener(UndoManager undo, Document doc) {
        doc.addUndoableEditListener((UndoableEditEvent evt) -> {
            undo.addEdit(evt.getEdit());
        });
    }

    private KeyStroke setShortcut(int key) {

        return KeyStroke.getKeyStroke(key, KeyEvent.CTRL_DOWN_MASK);
    }

    private boolean textNotEmpty() {
        return !component.getText().equals("");
    }

    private boolean isValidForAction() {
        return component.getSelectedText() != null;
    }

    public TextComponentPopUpBuilder(JTextComponent component) {
        this.component = component;
        this.menu = new JPopupMenu();

        this.toolsSubMenu = new JMenu("Tools");
        toolsSubMenu.setIcon(new FlatSVGIcon("icons/build_dark.svg"));
 

        this.undo = new UndoManager();
        this.doc = component.getDocument();
        selectedText = null;

        enableTextComponentUndoListener(undo, doc);
        undo();
        redo();

        cutItem = new JMenuItem();
        copyItem = new JMenuItem();
        pasteItem = new JMenuItem();
        deleteItem = new JMenuItem();

        selectAllItem = new JMenuItem();
        rightToLeftItem = new JCheckBoxMenuItem();
        clearItem = new JMenuItem();
        findItem = new JMenuItem();
        undoItem = new JMenuItem();
        redoItem = new JMenuItem();

        toUpperItem = new JMenuItem();
        toLowerItem = new JMenuItem();
        toTitleItem = new JMenuItem();

        init();
 

        menu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
                selectedText = component.getSelectedText();
                selectAllItem.setEnabled(!component.getText().equals(""));
                clearItem.setEnabled(!component.getText().equals(""));
                findItem.setEnabled(!component.getText().equals(""));

                copyItem.setEnabled(selectedText != null);
                cutItem.setEnabled(selectedText != null);
                deleteItem.setEnabled(selectedText != null);

                toTitleItem.setEnabled(selectedText != null);
                toLowerItem.setEnabled(selectedText != null);
                toUpperItem.setEnabled(selectedText != null);

            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
             }

            @Override
            public void popupMenuCanceled(PopupMenuEvent pme) {
             }
        });

        
        component.setComponentPopupMenu(menu);
        
        
        
        
    }

    public TextComponentPopUpBuilder setPadding(int[] padding) {
        this.padding = padding;
        return this;
    }
 
    public TextComponentPopUpBuilder setMenuType(MenuType menuType) {
        this.menuType = menuType;
        return this;
    }

    public TextComponentPopUpBuilder registerActionsGlobally(JRootPane rootPane) {
        this.rootPane = rootPane;
        return this;
    }

    public void build() { 

        menu.removeAll();
        switch (menuType) {
            case MAXIMUM:
                menu.add(selectAllItem);
                menu.add(new JSeparator());
                menu.add(cutItem);
                menu.add(copyItem);
                menu.add(pasteItem);
                menu.add(deleteItem);
                menu.add(new JSeparator());
                menu.add(clearItem);
                menu.add(new JSeparator());
                menu.add(undoItem);
                menu.add(redoItem);
                menu.add(new JSeparator());

                
                toolsSubMenu.add(findItem);
                toolsSubMenu.add(new JSeparator());

                toolsSubMenu.add(rightToLeftItem);
                toolsSubMenu.add(new JSeparator());
                toolsSubMenu.add(toTitleItem);
                toolsSubMenu.add(toLowerItem);
                toolsSubMenu.add(toUpperItem);
                menu.add(toolsSubMenu);

                
             break;

            case REGULAR:
                menu.add(selectAllItem);
                menu.add(new JSeparator());
                menu.add(cutItem);
                menu.add(copyItem);
                menu.add(pasteItem);
                menu.add(deleteItem);
                menu.add(new JSeparator()); 
                menu.add(undoItem);
                menu.add(redoItem);
                menu.add(new JSeparator());
                menu.add(clearItem);
           break;

            case MINIMUM:
                menu.add(selectAllItem);
                menu.add(new JSeparator());
                menu.add(cutItem);
                menu.add(copyItem);
                menu.add(pasteItem);
                menu.add(deleteItem);
            break;
        }

        if (rootPane != null) {

            MenuElement[] subElements = menu.getSubElements();
            for (MenuElement subElement : subElements) {
                if (subElement instanceof JMenu) {
                    Component[] menuComponents = ((JMenu)subElement).getMenuComponents();
                    for (Component menuToolsComponent : menuComponents) {
                        if (menuToolsComponent instanceof JMenuItem) {

                            resisterActionsGlobally((JMenuItem)menuToolsComponent);
                        }
                    }

                } else if (subElement instanceof JMenuItem) {

                    resisterActionsGlobally((JMenuItem)subElement);
                }

            }

        }

    }

    private void resisterActionsGlobally(JMenuItem i) {
        if (rootPane != null) {
        String actionId = i.getActionCommand();

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(i.getAccelerator(), actionId);
        if (i.getAction() != null) {
            rootPane.getActionMap().put(actionId, i.getAction());
        }
        }
    }

}

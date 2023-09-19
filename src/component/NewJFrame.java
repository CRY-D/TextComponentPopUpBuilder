package component;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

public class NewJFrame extends javax.swing.JFrame {

    public NewJFrame() {
        initComponents();
        initTextComponet(regularText, MenuType.REGULAR);
        initTextComponet(miniText, MenuType.MINIMUM);
        initTextComponet(maxText, MenuType.MAXIMUM);
        
    }

    
    private void initTextComponet(JTextComponent component, MenuType menuType) {
        new TextComponentPopUpBuilder(component)
                .registerActionsGlobally(rootPane)
                
                .setMenuType(menuType).build();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        miniText = new javax.swing.JTextField();
        regularText = new javax.swing.JTextField();
        maxText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        miniText.setText("MINIMUM");
        miniText.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel1.add(miniText);

        regularText.setText("REGULAR");
        regularText.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel1.add(regularText);

        maxText.setText("MAXIMUM");
        maxText.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel1.add(maxText);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        FlatMacDarkLaf.setup();
        UIManager.put("Component.borderWidth",2);
        UIManager.put("Component.focusWidth",0);
        UIManager.put("TextComponent.arc",10);
        


        java.awt.EventQueue.invokeLater(() -> {
            new NewJFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField maxText;
    private javax.swing.JTextField miniText;
    private javax.swing.JTextField regularText;
    // End of variables declaration//GEN-END:variables
}

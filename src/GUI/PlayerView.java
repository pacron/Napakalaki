/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.CultistPlayer;
import Model.Napakalaki;
import Model.Player;
import Model.Treasure;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author paco
 */
public class PlayerView extends javax.swing.JPanel {

    /**
     * Creates new form PlayerView
     */
    public PlayerView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        level = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        buyLevels = new javax.swing.JButton();
        makeVisible = new javax.swing.JButton();
        discardTreasures = new javax.swing.JButton();
        combatLevel = new javax.swing.JLabel();
        CultistLabel = new javax.swing.JLabel();
        bonusLevelMessage = new javax.swing.JLabel();
        bonusLevel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        visibleTreasures = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        hiddenTreasures = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Player"));

        name.setText("Name");

        jLabel1.setText("Nivel:");

        level.setText("level");

        jLabel2.setText("Nivel de combate:");

        buyLevels.setText("Buy levels");
        buyLevels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyLevelsActionPerformed(evt);
            }
        });

        makeVisible.setText("Equip treasure");
        makeVisible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeVisibleActionPerformed(evt);
            }
        });

        discardTreasures.setText("Discard Treasures");
        discardTreasures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardTreasuresActionPerformed(evt);
            }
        });

        combatLevel.setText("combatLevel");

        CultistLabel.setText("JUGADOR SECTARIO");

        bonusLevelMessage.setText("Bonus de nivel por sectarios en juego:");

        bonusLevel.setText("bonusLevel");

        visibleTreasures.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Visible treasures", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        visibleTreasures.setToolTipText("Visible Treasures");
        visibleTreasures.setMaximumSize(new java.awt.Dimension(710, 230));
        visibleTreasures.setMinimumSize(new java.awt.Dimension(710, 230));
        visibleTreasures.setName("Visible Treasures"); // NOI18N
        jScrollPane1.setViewportView(visibleTreasures);
        visibleTreasures.getAccessibleContext().setAccessibleName("Visible Treasures");

        hiddenTreasures.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hidden treasures", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        hiddenTreasures.setToolTipText("Hidden Treasures");
        hiddenTreasures.setMaximumSize(new java.awt.Dimension(710, 230));
        hiddenTreasures.setMinimumSize(new java.awt.Dimension(710, 230));
        jScrollPane2.setViewportView(hiddenTreasures);
        hiddenTreasures.getAccessibleContext().setAccessibleName("Hidden Treasures");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CultistLabel)
                .addGap(187, 187, 187))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buyLevels)
                        .addGap(222, 222, 222)
                        .addComponent(makeVisible)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(discardTreasures))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(level))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(combatLevel)))
                                .addGap(36, 36, 36)
                                .addComponent(bonusLevelMessage)
                                .addGap(51, 51, 51)
                                .addComponent(bonusLevel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name)
                    .addComponent(CultistLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(level)
                    .addComponent(bonusLevelMessage)
                    .addComponent(bonusLevel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combatLevel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(makeVisible)
                    .addComponent(buyLevels)
                    .addComponent(discardTreasures))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void makeVisibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeVisibleActionPerformed
        ArrayList <Treasure> selHidden = this.getSelectedTreasures(this.hiddenTreasures);
        
        for (Treasure t: selHidden){
            this.napakalakiModel.makeTreasureVisible(t);
        }
        
        this.setPlayer(this.napakalakiModel.getCurrentPlayer());
        
        this.repaint();
    }//GEN-LAST:event_makeVisibleActionPerformed

    private void buyLevelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyLevelsActionPerformed
        ArrayList <Treasure> selHidden = this.getSelectedTreasures(this.hiddenTreasures);
        ArrayList <Treasure> selVisible = this.getSelectedTreasures(this.visibleTreasures);
        
        this.playerModel.buyLevels(selVisible, selHidden);
        
        this.fillTreasurePanel(this.hiddenTreasures, this.playerModel.getHiddenTreasures());
        this.fillTreasurePanel(this.visibleTreasures, this.playerModel.getVisibleTreasures());
        
        this.setPlayer(this.playerModel);
        
        this.repaint();
    }//GEN-LAST:event_buyLevelsActionPerformed

    private void discardTreasuresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardTreasuresActionPerformed
        
        ArrayList <Treasure> selHidden = this.getSelectedTreasures(this.hiddenTreasures);
        ArrayList <Treasure> selVisible = this.getSelectedTreasures(this.visibleTreasures);
        
        for (Treasure t: selHidden){
            this.playerModel.discardHiddenTreasure(t);
        }
        
        for (Treasure t: selVisible){
            this.playerModel.discardVisibleTreasure(t);
        }
        
        this.setPlayer(this.napakalakiModel.getCurrentPlayer());
        repaint();
    }//GEN-LAST:event_discardTreasuresActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CultistLabel;
    private javax.swing.JLabel bonusLevel;
    private javax.swing.JLabel bonusLevelMessage;
    private javax.swing.JButton buyLevels;
    private javax.swing.JLabel combatLevel;
    private javax.swing.JButton discardTreasures;
    private javax.swing.JPanel hiddenTreasures;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel level;
    private javax.swing.JButton makeVisible;
    private javax.swing.JLabel name;
    private javax.swing.JPanel visibleTreasures;
    // End of variables declaration//GEN-END:variables
    private Player playerModel;
    private Napakalaki napakalakiModel;
    
    public void setPlayer(Player player){
        this.playerModel = player;
        
        this.name.setText(this.playerModel.getName());
        this.level.setText(Integer.toString(this.playerModel.getLevel()));
        this.combatLevel.setText(Integer.toString(this.playerModel.getCombatLevel()));
        
        fillTreasurePanel(this.visibleTreasures, playerModel.getVisibleTreasures());
        fillTreasurePanel(this.hiddenTreasures, playerModel.getHiddenTreasures());
        
        if(this.playerModel.isCultist()){
            this.CultistLabel.setVisible(true);
            this.bonusLevelMessage.setVisible(true);
            this.bonusLevel.setVisible(true);
            this.bonusLevel.setText(Integer.toString(((CultistPlayer)this.playerModel).getBonusLevel()));
        }
        else{
            this.CultistLabel.setVisible(false);
            this.bonusLevelMessage.setVisible(false);
            this.bonusLevel.setVisible(false);
        }
        repaint();
        revalidate();
    }
    public void setNapakalaki(Napakalaki napakalaki){
        this.napakalakiModel = napakalaki;
    }
    
    public void fillTreasurePanel (JPanel aPanel, ArrayList<Treasure> aList) {
        // Se elimina la información antigua
        aPanel.removeAll();
        // Se recorre la lista de tesoros construyendo y añadiendo sus vistas
        // al panel
        for (Treasure t : aList) {
            TreasureView aTreasureView = new TreasureView();
            aTreasureView.setTreasure (t);
            aTreasureView.setVisible (true);
            aPanel.add(aTreasureView);
        }
        // Se fuerza la actualización visual del panel
        aPanel.repaint();
        aPanel.revalidate();
    }
    
    public ArrayList<Treasure> getSelectedTreasures(JPanel aPanel) {
        TreasureView tv;
        ArrayList<Treasure> output = new ArrayList();
        for (Component c : aPanel.getComponents()) {
            tv = (TreasureView) c;
            if ( tv.isSelected() )
                output.add ( tv.getTreasure() );
        }

        return output;
    }
    
    public void setEnabledMakeVisibleButton(boolean flag){
        this.makeVisible.setEnabled(flag);
    }
    
    public void setEnabledBuyLevelsButton(boolean flag){
        this.buyLevels.setEnabled(flag);
    }
    
    public void setEnabledDiscardTreasuresButton(boolean flag){
        this.discardTreasures.setEnabled(flag);
    }
}
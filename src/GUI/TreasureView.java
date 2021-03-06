/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Treasure;
import java.awt.Color;

/**
 * Vista de los objetos {@link Model.Treasure}. Sirve para mostrar por pantalla 
 * toda la información relativa a dichos tesoros. Muestra información de los bonus
 * de nivel que aporta dicho tesoro, el tipo de tesoro y su valor en monedas de 
 * oro
 * @author Francisco Luque y Antonio Moya
 */
public class TreasureView extends javax.swing.JPanel {

    /**
     * Creates new form TreasureView
     */
    public TreasureView() {
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

        treasureName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        minBonus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        maxBonus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        goldCoins = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        treasureKind = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        treasureName.setText("treasureName");

        jLabel2.setText("Bonus mínimo:");

        minBonus.setText("minBonus");

        jLabel1.setText("Bonus máximo:");

        maxBonus.setText("maxBonus");

        jLabel3.setText("Monedas de oro:");

        goldCoins.setText("goldCoins");

        jLabel4.setText("Tipo de tesoro:");

        treasureKind.setText("treasureKind");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(treasureName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(treasureKind))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(minBonus))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(goldCoins)
                                        .addComponent(maxBonus)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(treasureName, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(minBonus))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(maxBonus))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(goldCoins))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(treasureKind))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que gestiona el comportamiento cuando se selecciona el tesoro. Permite
     * que el tesoro cambie su color de fondo si está seleccionado
     * @param evt Evento de ratón (click sobre el tesoro)
     */
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

        // Se cambia de seleccionado a deseleccionado y viceversa
        this.selected = !this.selected;
        // Se hace opaco o no dependiendo de si está seleccionado (si se selecciona
        // se colorea el fondo del tesoro)
        this.setOpaque(this.selected);
        // Se actualiza la vista
        repaint();
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel goldCoins;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel maxBonus;
    private javax.swing.JLabel minBonus;
    private javax.swing.JLabel treasureKind;
    private javax.swing.JLabel treasureName;
    // End of variables declaration//GEN-END:variables

    private Treasure treasureModel;
    private boolean selected = false;
    
    /**
     * Método que asigna un tesoro del modelo a la vista de tesoro correspondiente
     * y actualiza su representación en pantalla
     * @param treasure Tesoro que se quiere visualizar
     */
    public void setTreasure(Treasure treasure) {

        // Se asigna el tesoro del modelo a una variable
        this.treasureModel = treasure;
        
        // Se cambia el color de fondo y se hace transparente
        this.setBackground(Color.CYAN);
        this.setOpaque(this.selected);
        
        // Se actualiza el valor de las etiquetas del nombre del tesoro, niveles que
        // otorga (min y max bonus), valor en monedas de oro y el tipo de tesoro
        this.treasureName.setText(treasureModel.getName());
        this.minBonus.setText(Integer.toString(treasureModel.getBasicValue()));
        this.maxBonus.setText(Integer.toString(treasureModel.getSpecialValue()));
        this.goldCoins.setText(Integer.toString(treasureModel.getGoldCoins()));
        this.treasureKind.setText(treasureModel.getType().name());

        // Se actualiza la vista por pantalla
        repaint();
    }

    /**
     * Método que devuelve si el tesoro está seleccionado
     * @return true si el tesoro está seleccionado, false si no lo está
     */
    public boolean isSelected() {
        return this.selected;
    }
    
    /**
     * Método que devuelve la instancia de tesoro representada por esta vista
     * @return Instacia de tipo {@link Model.Treasure}
     */
    public Treasure getTreasure() {
        return this.treasureModel;
    }
}

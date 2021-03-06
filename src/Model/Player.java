/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 * Clase que representa a los jugadores del juego. Cada instancia de esta clase 
 * representa a cada uno de los jugadores del juego. Cada uno de estos objetos
 * gestiona la actividad de cada uno de los participantes, de forma que se encarga
 * de mantener la información sobre su nivel, tesoros que tiene tanto visibles como
 * ocultos, y el mal rollo que le queda por cumplir. Además, se encarga de gestionar
 * los combates entre el jugador y los monstruos.
 * @author Francisco Luque y Antonio Moya
 */
public class Player {
    
    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                               Attributes                               //
    //                                                                        //    
    ////////////////////////////////////////////////////////////////////////////

    private boolean dead;
    private String name;
    private int level;
    
    private static final int MAXHIDDENTREASURES = 4;
    
    private ArrayList<Treasure> hiddenTreasures;
    private ArrayList<Treasure> visibleTreasures;
    private BadConsequence pendingBadConsequence;

    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                             Private methods                            //
    //                                                                        //    
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Revive the player
     */
    private void bringToLife(){
        this.dead = false;
    }
    
    /**
     * Increment the player level
     * @param levels Incremented levels
     */
    private void incrementLevels(int levels){
        this.level += levels;
        if (this.level > 10){
            this.level = 10;
        }
    }
    
    /**
     * Decrement the player level
     * @param levels Decremented levels
     */
    private void decrementLevels(int levels){
        this.level -= levels;
        if (this.level < 1){
            this.level = 1;
        }
    }
    
    /**
     * BadConsequence setter
     * @param badConsequence Bad consequence to be set 
     */
    private void setPendingBadConsequence(BadConsequence badConsequence){
        this.pendingBadConsequence = badConsequence;
    }
    
    /**
     * The player dies
     */
    private void die(){
        CardDealer dealer = CardDealer.getInstance();
        for(Treasure treasure: this.visibleTreasures){
            dealer.giveTreasureBack(treasure);
        }
        
        this.visibleTreasures.clear();
        
        for(Treasure treasure: this.hiddenTreasures){
            dealer.giveTreasureBack(treasure);
        }
        
        this.hiddenTreasures.clear();
        this.level = 1;
        this.dead = true;
    }
    
    /**
     * Make the player discard necklace. 
     */
    private void discardNecklaceIfVisible(){
        CardDealer cardDealer = CardDealer.getInstance();
        Treasure necklace = null;
        for (Treasure t: this.visibleTreasures){
            if (t.getType() == TreasureKind.NECKLACE){
                necklace = t;
            }
        }
        if (necklace != null){
            this.visibleTreasures.remove(necklace);
            cardDealer.giveTreasureBack(necklace);
        }
    }
    
    /**
     * Kill the player if he had no treasures. Player cannot stay alive w/out
     * treasures, so he's killed and he will get new treasures when revive
     */
    private void dieIfNoTreasures(){
        if(this.hiddenTreasures.isEmpty() && this.visibleTreasures.isEmpty()){
            this.die();
        }
    }
    
    /**
     * Test if player can buy levels or not
     * @param levels Number of levels the player wants to buy
     * @return True if it's possible to buy them, false if not
     */
    private boolean canIBuyLevels(int levels){
        return (this.level + levels < 10);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                             Public methods                             //
    //                                                                        //    
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Computa el valor en monedas de oro de un array de tesoros que se pasa como
     * argumento. Este valor se utiliza después para ver el número de niveles que
     * puede comprar el jugador. Puede comprar un nivel por cada 1000 monedas de oro
     * @param treasures Tesoros que quiere vender
     * @return Valor en monedas de oro del array de tesoros
     */
    protected float computeGoldCoinsValue(ArrayList<Treasure> treasures){
        float totalLevels = 0;
        for (Treasure t: treasures){
            totalLevels += t.getGoldCoins();
        }
        return totalLevels/(float)1000;
    }
    
    /**
     * Método que aplica el buen rollo al jugador
     * @param prize Buen rollo a aplicar
     */
    public void applyPrize(Prize prize){
        int nLevels = prize.getLevels();
        this.incrementLevels(nLevels);
        int nPrize = prize.getTreasures();
        CardDealer dealer = CardDealer.getInstance();
        
        for(int i=0; i<nPrize; i++){
            Treasure treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
        
    }
    
    /**
     * Método en que se ejecuta el combate del jugador con el monstruo
     * generando la correspondiente salida en funcion a lo ocurrido en el
     * combate
     * @param monster Monstruo contra el que lucha
     * @return El resultado del combate
     */
    public CombatResult combat(Monster monster){
        CombatResult combatResult;
        int myLevel = this.getCombatLevel();
        int levelMonster = this.getOponentLevel(monster);
        if(myLevel > levelMonster){
           Prize prize = monster.getPrize();
           this.applyPrize(prize);
           if(this.level < 10)
               combatResult = CombatResult.WIN;
           else{
               combatResult = CombatResult.WINANDWINGAME;
           }
        }
        else{
            Dice dice = Dice.getInstance();
            int escape = dice.nextNumber("El monstruo te va a vencer!","Si sacas un 5 o un 6 podrás escapar de él");
            
            if(escape < 5){
                BadConsequence bad = monster.getBadConsequence();
                boolean amIDead = bad.kills();
                
                if(amIDead == true){
                    this.die();
                    combatResult = CombatResult.LOSEANDDIE;
                }
                else{
                    this.applyBadConsequence(bad);
                    if(this.shouldConvert()){
                        combatResult = CombatResult.LOSEANDCONVERT;
                    }
                    else{
                        combatResult = CombatResult.LOSE;
                    }
                }
            }
            
            else{
                combatResult = CombatResult.LOSEANDESCAPE;
            }
        }
        this.discardNecklaceIfVisible();
        CardDealer dealer = CardDealer.getInstance();
        dealer.giveMonsterBack(monster);
        return combatResult;
    }
    
    /**
     * Método que devuelve el mal rollo pendiente que tiene el jugador
     * @return Dicho mal rollo
     */
    public BadConsequence getPendingBadConsequence(){
        return this.pendingBadConsequence;
    }
    
    /**
     * Método en que se aplica el mal rollo al jugador
     * @param badConsequence Mal rollo que se aplica
     */
    public void applyBadConsequence(BadConsequence badConsequence){
        int nLevels = badConsequence.getLevels();
        this.decrementLevels(nLevels);
        BadConsequence pendingBad = 
                badConsequence.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures);
        
        this.setPendingBadConsequence(pendingBad);
        
    }
    
    /**
     * Método que indica si un tesoro puede pasar de oculto a visible
     * @param treasure Tesoro que se quiere pasar a visible
     * @return Boolean que indica si es posible o no.
     */
    public boolean makeTreasureVisible(Treasure treasure){
        boolean canI = this.canMakeTreasureVisible(treasure);
        if(canI){
            this.visibleTreasures.add(treasure);
            this.hiddenTreasures.remove(treasure);
        }
        return canI;
    }
    
    /**
     * Comprueba si el jugador reúne las condiciones necesarias para convertir en
     * visible el tesoro que se pasa como argumento.
     * @param treasure Tesoro que se quiere convertir en visible
     * @return True si se puede convertir en visible, false en caso contrario
     */
    public boolean canMakeTreasureVisible(Treasure treasure){
        TreasureKind treasureType = treasure.getType();
        boolean allowed = true;
        
        if(treasureType == TreasureKind.BOTHHANDS){
            for(Treasure t: this.visibleTreasures){
                if(t.getType() == TreasureKind.BOTHHANDS || t.getType() == TreasureKind.ONEHAND){
                    allowed = false;
                }
            }
        } else if(treasureType == TreasureKind.ONEHAND && allowed){
            int count = 0;
            for(Treasure t: this.visibleTreasures){
                if(t.getType() == TreasureKind.ONEHAND){
                    count += 1;
                }
                
                if(t.getType() == TreasureKind.BOTHHANDS || (t.getType() == TreasureKind.ONEHAND && count > 1)){
                    allowed = false;
                }
            }
            
        } else if (allowed){
            for (Treasure t: this.visibleTreasures){
                if(t.getType() == treasureType){
                    allowed = false;
                }
            }
        }
        return allowed;
    }
    
    /**
     * Método en que el jugador se descarta de un tesoro visible y lo
     * envía a la baraja de cartas.
     * @param treasure Tesoro visible del que te quieres descartar
     */
    public void discardVisibleTreasure(Treasure treasure){
        this.visibleTreasures.remove(treasure);
        if((this.pendingBadConsequence != null) && (!this.pendingBadConsequence.isEmpty())){
            this.pendingBadConsequence.substractVisibleTreasure(treasure);
        }
        CardDealer dealer = CardDealer.getInstance();
        dealer.giveTreasureBack(treasure);
        this.dieIfNoTreasures();
        
    }
    
     /**
     * Método en que el jugador se descarta de un tesoro oculto y lo
     * envía a la baraja de cartas.
     * @param treasure Tesoro oculto del que te quieres descartar
     */
    public void discardHiddenTreasure(Treasure treasure){
        this.hiddenTreasures.remove(treasure);
        if((this.pendingBadConsequence != null) && (!this.pendingBadConsequence.isEmpty())){
            this.pendingBadConsequence.substractHiddenTreasure(treasure);
        }
        CardDealer dealer = CardDealer.getInstance();
        dealer.giveTreasureBack(treasure);
        this.dieIfNoTreasures();
    }
    
     /**
     * Método que ajusta la compra de niveles y devuelve si es posible o no
     * @param visibleTreasures Tesoros visibles que se quieren poner en venta para comprar niveles
     * @param hiddenTreasures Tesoros ocultos que se quieren poner en venta para comprar un nivel
     * @return Boolean indicando si es posible comprar niveles o no
     */
    public boolean buyLevels(ArrayList<Treasure> visibleTreasures, ArrayList<Treasure> hiddenTreasures){
        float levels = this.computeGoldCoinsValue(visibleTreasures);
        levels += this.computeGoldCoinsValue(hiddenTreasures);
        boolean canI = this.canIBuyLevels((int) levels);
        if(canI){
            this.incrementLevels((int) levels);
            for(Treasure treasure: visibleTreasures){
                this.discardVisibleTreasure(treasure);
            }
            for(Treasure treasure: hiddenTreasures){
                this.discardHiddenTreasure(treasure);
            }
        }
        
        return canI;
    }
    
    /**
     * Getter para el nivel de combat del jugador. Este nivel se computa como el
     * nivel real del jugador más los niveles que le otorguen los bonus de los 
     * tesoros visibles que lleve.
     * @return Nivel total de combate del jugador
     */
    public int getCombatLevel(){
        int combatLevel = this.level;
        boolean hasNecklace = false;
        
        for (Treasure t: this.visibleTreasures){
            if (t.getType() == TreasureKind.NECKLACE){
                hasNecklace = true;
            }
        }
        
        if(hasNecklace){
            for (Treasure t: this.visibleTreasures){
                combatLevel += t.getMaxBonus();
            }
        } else {
            for (Treasure t: this.visibleTreasures){
                combatLevel += t.getMinBonus();
            }
        }
        
        return combatLevel;
    }
    
    /**
     * Comprueba si el estado del jugador es consistente como para que el jugador
     * termine su turno.
     * @return True si el jugador está en un estado válido, false en caso contrario
     */
    public boolean validState(){
        return (this.pendingBadConsequence == null || this.pendingBadConsequence.isEmpty()) &&
                this.hiddenTreasures.size() <= MAXHIDDENTREASURES;
    }
    
     /**
     * Método en que se inician los tesoros del jugador, bien porque es su primera mano,
     * o bien porque ha muerto y comienza de nuevo.
     */
    public void initTreasures(){
        this.bringToLife();
        Dice dice = Dice.getInstance();
        int number = dice.nextNumber("Tira el dado para saber con cuántos tesoros\n comienzas", 
                "Con un 1 recibes un tesoro, con un 6\n recibes 3, y 2 con cualquier otro");
        CardDealer dealer = CardDealer.getInstance();
        if(number == 1){
            Treasure treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
        else if(number < 6){
            for(int i=0; i<2; i++){
                Treasure treasure = dealer.nextTreasure();
                this.hiddenTreasures.add(treasure);
            }
        }
        else{
            for(int i=0; i<3; i++){
                Treasure treasure = dealer.nextTreasure();
                this.hiddenTreasures.add(treasure);
            }
        }
        
        
    }
    
    /**
     * Método que comprueba si el jugador está muerto.
     * @return True si lo está, false en caso contrario
     */
    public boolean isDead(){
        return this.dead;
    }
    
    /**
     * Comprueba si el jugador tiene algún tesoro visible.
     * @return True si lo tiene, false en caso contrario
     */
    public boolean hasVisibleTreasures(){
        return !this.visibleTreasures.isEmpty();
    }
    
    /**
     * Constructor del jugador. Inicializa al jugador a un estado válido, con el
     * que inicia la partida.
     * @param name Nombre del jugador en la partida
     */
    public Player(String name){
        this.name = name;
        this.level = 1;
        this.dead = true;
        this.hiddenTreasures = new ArrayList();
        this.visibleTreasures = new ArrayList();
    }
    
    /**
     * Constructor de copia del jugador
     * @param p jugador que se quiere copiar
     */
    public Player(Player p){
        this.dead = p.dead;
        this.hiddenTreasures = p.hiddenTreasures;
        this.level = p.level;
        this.name = p.name;
        this.pendingBadConsequence = p.pendingBadConsequence;
        this.visibleTreasures = p.visibleTreasures;
    }
    
    /**
     * Getter para el array de tesoros visibles. 
     * @return Array de tesoros visibles
     */
    public ArrayList<Treasure> getVisibleTreasures(){
        return this.visibleTreasures;
    }
    
    /**
     * Getter para el array de tesoros ocultos.
     * @return Array de tesoros ocultos
     */
    public ArrayList<Treasure> getHiddenTreasures(){
        return this.hiddenTreasures;
    }
    
     /**
     * Getter del nombre del jugador
     * @return El nombre del jugador
     */
    
    public String getName(){
        return this.name;
    }
    
    /**
     * Getter para el nivel del jugador
     * @return Entero que representa el nivel
     */
    public int getLevel(){
        return this.level;
    }
    
    /**
     * Método que devuelve el nombre y nivel del jugador
     * @return Nombre y nivel del jugador
     */
    
    public String toString(){
        String printable = "Nombre: " + this.name + "; Nivel " + Integer.toString(level) + 
                "; CombatLevel: " + Integer.toString(this.getCombatLevel());
        
        if(this.visibleTreasures.isEmpty() == false){
            printable += "\nTesoros visibles:\n\t\t ";
            for(Treasure t: this.visibleTreasures){
                printable += t.toString();
            }
        }
        
        if(this.hiddenTreasures.isEmpty() == false){
            printable += "\nTesoros ocultos:\n\t\t ";
            for(Treasure t: this.hiddenTreasures){
                printable += t.toString();
            }
        }
        
        if(this.pendingBadConsequence == null || this.pendingBadConsequence.isEmpty()){
            printable += "\n No tienes mal rollo pendiente ";
        }
        else{
            printable += "\n Mal rollo: " + this.pendingBadConsequence.toString();
        }
        
        return printable;
                
    }
    
    /**
     * Método que comprueba si el jugador debe pasar o no a ser sectario. Para ello,
     * se lanza un dado y se comprueba si se saca un 6
     * @return true si sale un 6, false en otro caso
     */
    protected boolean shouldConvert(){
        boolean should = false;
        Dice dice = Dice.getInstance();
        
        if(dice.nextNumber("Te llaman desde la secta", "Si sacas un 6 te convertirás en sectario") == 6){
            should = true;
            
        }
        
        return should;
    }
    
    /**
     * Método que devuelve el nivel del monstruo oponente cuando se pelea contra
     * este jugador. Devuelve el nivel básico del monstruo, ya que este tipo de 
     * jugador no es sectario, que es el tipo de jugadores contra los que varían
     * los monstruos de nivel
     * @param m Monstruo del que se quiere conocer el nivel de combate
     * @return Nivel de dicho monstruo
     */
    public int getOponentLevel(Monster m){
        return m.getLevel();
    }
    
    /**
     * Método que asigna al jugador una lista de tesoros visibles (utilizado en examen)
     * @param treasures Lista de tesoros que se asigna
     */
    public void setVisibleTreasures(ArrayList<Treasure> treasures){
        this.visibleTreasures = treasures;
    }
    
    /**
     * Método que asigna al jugador una lista de tesoros ocultos (utilizado en examen)
     * @param treasures Lista de tesoros que se asigna
     */
    public void setHiddenTreasures(ArrayList<Treasure> treasures){
        this.hiddenTreasures = treasures;
    }
    
    /**
     * Método que comprueba si este jugador es sectario
     * @return false, este jugador no es sectario.
     */
    public boolean isCultist(){
        return false;
    }
}

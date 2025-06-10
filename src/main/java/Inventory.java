public class Inventory {
    private Potion[] potions;
    private int[] potionCount;
    private int[] potionModNum;
    public Inventory(){
        String[] potionNames = new String[] {"Small Healing Potion", "Medium Healing Potion", "Large Healing Potion", "Mega Healing Potion", "Speed Potion"};
        String[] potionEffects = new String[] {"Restores 10 Hp", "Restores 20 Hp", "Restores 30 Hp", "Restores 40 Hp", "Increases speed by 20 for the rest of combat"};
        this.potions = new Potion[potionNames.length];
        this.potionModNum = new int[] {10, 20, 30, 40, 20};
        this.potionCount = new int[potionNames.length];
        for(int i = 0; i < potionNames.length; i ++){
            Potion p = new Potion(potionNames[i], potionEffects[i]);
            this.potions[i] = p;
        }
    }
        
        public void addPotion(int index, int count){
            for(int i = 0; i < count; i++){
                potionCount[index]++;
            }
        }

        public void potionList(){
            
        }
        
    }


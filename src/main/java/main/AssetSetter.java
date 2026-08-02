package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.Obj_Door;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        
    }
    
    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }
    
    public void setMonster(){
        gp.mon[0] = new MON_GreenSlime(gp);
        gp.mon[0].worldX = gp.tileSize * 23;
        gp.mon[0].worldY = gp.tileSize * 36;
        
        gp.mon[1] = new MON_GreenSlime(gp);
        gp.mon[1].worldX = gp.tileSize * 23;
        gp.mon[1].worldY = gp.tileSize * 37;
        
    }
}

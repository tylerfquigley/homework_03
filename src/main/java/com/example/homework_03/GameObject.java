package com.example.homework_03;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.nio.file.InvalidPathException;

public abstract class GameObject {
   private boolean debug =true;
   private float x=0;
   private float y=0;
   private float hsp=0;
   private float vsp=0;
   private int bBoxW=0;
    private int bBoxH=0;

     GraphicsContext gc;
   private Image sprite;
   // graphics rendering called from animation timer
    void draw(GraphicsContext graphicsContext){
        gc= graphicsContext;
        if (sprite!=null){gc.drawImage(sprite, (int) (x-bBoxW/2),(int) (y-bBoxH/2));}
        gc.setStroke(Color.AQUA);
        if (debug){gc.strokeRect(x-bBoxW/2, y-bBoxH/2, bBoxW, bBoxH);}
    }
    // backend logic that gets called each frame
    abstract void toDo();

   public void setX(float x){
       this.x=x;
   }
   public void setY(float y){
        this.y=y;
    }
    public void setHsp(float hsp){
       this.hsp=hsp;
    }
    public void setVsp(float vsp){
       this.vsp=vsp;
    }
    public void setbBoxW(int bBoxW){
        this.bBoxW=bBoxW;
    }
    public void setbBoxH(int bBoxH){
        this.bBoxH=bBoxH;
    }

    public boolean loadSprite(String path){
       try {
           sprite = new Image(path);
           setbBoxH((int)sprite.getWidth());
           setbBoxW((int)sprite.getHeight());
           return true;
       }
       catch (Exception e){
           System.out.print("Failed to load image "+ path);
           return false;
       }
    }
    public float getX(){
       return x;
    }
    public float getY(){
       return y;
    }
    public float getHsp(){
       return hsp;
    }
    public float getVsp(){
       return vsp;
    }
    public int getbBoxW(){
       return bBoxW;
    }
    public int getbBoxH(){
       return bBoxH;
    }

}

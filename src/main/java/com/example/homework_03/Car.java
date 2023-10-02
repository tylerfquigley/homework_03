package com.example.homework_03;

public class Car extends Player {

    // 0-right 1- down right 2-
    @Override
    public void toDo() {
        super.toDo();

        boolean u = (Controller.isUp() && !Controller.isDown());
        boolean d = (!Controller.isUp() && Controller.isDown());
        boolean l = (Controller.isLeft() && !Controller.isRight());
        boolean r = (!Controller.isLeft() && Controller.isRight());
        if (u&&!r&&!l){
          loadSprite("carU.png");
        }
        if (d&&!r&&!l){
            loadSprite("carD.png");
        }
        if (l&&!u&&!d){
            loadSprite("carL.png");
        }
        if (r&&!u&&!d){
            loadSprite("car.png");
        }
        if (d&&l){
            loadSprite("carDL.png");
        }
        if (d&&r){
            loadSprite("carDR.png");
        }
        if (u&&l){
            loadSprite("carUL.png");
        }
        if (u&&r){
            loadSprite("carUR.png");
        }

    }
    @Override
  protected void reset(){
        super.reset();
        loadSprite("car.png");
    }
    @Override
    protected boolean checkWin(){
        int tmp=0;
        try {
           tmp= pixelReader.getArgb((int) getX(), (int) getY());
        }
        catch (Exception e){
            //do nothing pixel data invalid and can be ignored
        }
            if (tmp==-10092340){
                return true;
            }else {
        return false;}
    }
}
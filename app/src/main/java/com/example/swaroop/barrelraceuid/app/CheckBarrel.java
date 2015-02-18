package com.example.swaroop.barrelraceuid.app;

/**
 * Created by swaroop on 11/30/2014.
 */
public class CheckBarrel {

    //Coordnates of center of barrel being checked and rhe radius of the circle desired
    private int mCenterXBarrel1;
    private int mCenterYBarrel1;
    private int mCenterXBarrel2;
    private int mCenterYBarrel2;
    private int mCenterXBarrel3;
    private int mCenterYBarrel3;

    //Barrel 1
    int mC1B1X;
    int mC1B1Y;
    int mC2B1X;
    int mC2B1Y;
    int mC3B1X;
    int mC3B1Y;
    int mC4B1X;
    int mC4B1Y;

    //Barrel 2
    int mC1B2X;
    int mC1B2Y;
    int mC2B2X;
    int mC2B2Y;
    int mC3B2X;
    int mC3B2Y;
    int mC4B2X;
    int mC4B2Y;

    //Barrel 3
    int mC1B3X;
    int mC1B3Y;
    int mC2B3X;
    int mC2B3Y;
    int mC3B3X;
    int mC3B3Y;
    int mC4B3X;
    int mC4B3Y;

   //Check variables of Barrel1 circles
    boolean mbC1B1Covered = false;
    boolean mbC2B1Covered = false;
    boolean mbC3B1Covered = false;
    boolean mbC4B1Covered = false;

    //Check variables of Barrel2 circles
    boolean mbC1B2Covered = false;
    boolean mbC2B2Covered = false;
    boolean mbC3B2Covered = false;
    boolean mbC4B2Covered = false;

    //Check variables of Barrel3 circles
    boolean mbC1B3Covered = false;
    boolean mbC2B3Covered = false;
    boolean mbC3B3Covered = false;
    boolean mbC4B3Covered = false;

    //comstructor
    CheckBarrel(){
        //Check variables of Barrel1 circles
        mbC1B1Covered = false;
        mbC2B1Covered = false;
        mbC3B1Covered = false;
        mbC4B1Covered = false;

        //Check variables of Barrel2 circles
        mbC1B2Covered = false;
        mbC2B2Covered = false;
        mbC3B2Covered = false;
        mbC4B2Covered = false;

        //Check variables of Barrel3 circles
        mbC1B3Covered = false;
        mbC2B3Covered = false;
        mbC3B3Covered = false;
        mbC4B3Covered = false;
    }

    //Set the center of barrel 1
    public void setBarrel1Center(int p_icX,int p_icY){
        mCenterXBarrel1 = p_icX;
        mCenterYBarrel1 = p_icY;
    }

    //Set the center of barrel 2
    public void setBarrel2Center(int p_icX,int p_icY){
        mCenterXBarrel2 = p_icX;
        mCenterYBarrel2 = p_icY;
    }

    //Set the center of barrel 3
    public void setBarrel3Center(int p_icX,int p_icY){
        mCenterXBarrel3 = p_icX;
        mCenterYBarrel3 = p_icY;
    }

    public void GenerateCircleCenters(){

        //Barrel 1
        mC1B1X = mCenterXBarrel1 + 25;
        mC1B1Y = mCenterYBarrel1 - 50;
        mC2B1X = mCenterXBarrel1 + 100;
        mC2B1Y = mCenterYBarrel1 + 50;
        mC3B1X = mCenterXBarrel1 + 25;
        mC3B1Y = mCenterYBarrel1 + 50;
        mC4B1X = mCenterXBarrel1 - 50;
        mC4B1Y = mCenterYBarrel1 + 50;

        //Barrel 2
        mC1B2X = mCenterXBarrel2 + 25;
        mC1B2Y = mCenterYBarrel2 - 50;
        mC2B2X = mCenterXBarrel2 + 100;
        mC2B2Y = mCenterYBarrel2 + 50;
        mC3B2X = mCenterXBarrel2 + 25;
        mC3B2Y = mCenterYBarrel2 + 50;
        mC4B2X = mCenterXBarrel2 - 50;
        mC4B2Y = mCenterYBarrel2 + 50;

        //Barrel 3
        mC1B3X = mCenterXBarrel3 + 20;
        mC1B3Y = mCenterYBarrel3 - 45;
        mC2B3X = mCenterXBarrel3 + 90;
        mC2B3Y = mCenterYBarrel3 + 30;
        mC3B3X = mCenterXBarrel3 + 20;
        mC3B3Y = mCenterYBarrel3 + 45;
        mC4B3X = mCenterXBarrel3 - 50;
        mC4B3Y = mCenterYBarrel3 + 30;
    }


    //check barrel  1 covered
    public boolean checkBarrel1Covered(int p_iX,int p_iY){
        //Set the values
        boolean bBarrelCircled = false;

        //Check Coordinates for Q1
        checkCoordinateC1B1(p_iX,p_iY);
        checkCoordinateC2B1(p_iX,p_iY);
        checkCoordinateC3B1(p_iX,p_iY);
        checkCoordinateC4B1(p_iX,p_iY);

        //if all the quadrants covered
        System.out.println("C1B1 :: " + mbC1B1Covered);
        System.out.println("C2B1 :: " + mbC2B1Covered);
        System.out.println("C3B1 :: " + mbC3B1Covered);
        System.out.println("C4B1 :: " + mbC4B1Covered);
        if(mbC1B1Covered && mbC2B1Covered && mbC3B1Covered && mbC4B1Covered){
            bBarrelCircled = true;
        }
        return bBarrelCircled;
    }

    //check barrel  2 covered
    public boolean checkBarrel2Covered(int p_iX,int p_iY){
        //Set the values
        boolean bBarrelCircled = false;

        //Check Coordinates for Q1
        checkCoordinateC1B2(p_iX,p_iY);
        checkCoordinateC2B2(p_iX,p_iY);
        checkCoordinateC3B2(p_iX,p_iY);
        checkCoordinateC4B2(p_iX,p_iY);

        //if all the quadrants covered
        System.out.println("C1B2 :: " + mbC1B2Covered);
        System.out.println("C2B2 :: " + mbC2B2Covered);
        System.out.println("C3B2 :: " + mbC3B2Covered);
        System.out.println("C4B2 :: " + mbC4B2Covered);
        if(mbC1B2Covered && mbC2B2Covered && mbC3B2Covered && mbC4B2Covered){
            bBarrelCircled = true;
        }
        return bBarrelCircled;
    }

    //check barrel  3 covered
    public boolean checkBarrel3Covered(int p_iX,int p_iY){
        //Set the values
        boolean bBarrelCircled = false;

        //Check Coordinates for Q1
        checkCoordinateC1B3(p_iX,p_iY);
        checkCoordinateC2B3(p_iX,p_iY);
        checkCoordinateC3B3(p_iX,p_iY);
        checkCoordinateC4B3(p_iX,p_iY);

        //if all the quadrants covered
        System.out.println("C1B3 :: " + mbC1B3Covered);
        System.out.println("C2B3 :: " + mbC2B3Covered);
        System.out.println("C3B3 :: " + mbC3B3Covered);
        System.out.println("C4B3 :: " + mbC4B3Covered);
        if(mbC1B3Covered && mbC2B3Covered && mbC3B3Covered && mbC4B3Covered){
            bBarrelCircled = true;
        }
        return bBarrelCircled;
    }

    //---------------------------------Barrel 1-----------------------------------------------------
    //Check for Circle 1 Barrel 1
    void checkCoordinateC1B1(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC1B1X)*(p_iX - mC1B1X))
                + ((p_iY - mC1B1Y)*(p_iY - mC1B1Y))
                <= 3200) {
            mbC1B1Covered = true;
        }
    }

    //Check for Circle 2 Barrel 1
    void checkCoordinateC2B1(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC2B1X)*(p_iX - mC2B1X))
                + ((p_iY - mC2B1Y)*(p_iY - mC2B1Y))
                <= 3200) {
            mbC2B1Covered = true;
        }
    }

    //Check for Circle 3 Barrel 1
    void checkCoordinateC3B1(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC3B1X)*(p_iX - mC3B1X))
                + ((p_iY - mC3B1Y)*(p_iY - mC3B1Y))
                <= 3200) {
            mbC3B1Covered = true;
        }
    }

    //Check for Circle 4 Barrel 1
    void checkCoordinateC4B1(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC4B1X)*(p_iX - mC4B1X))
                + ((p_iY - mC4B1Y)*(p_iY - mC4B1Y))
                <= 3200) {
            mbC4B1Covered = true;
        }
    }


    //---------------------------------Barrel 2-----------------------------------------------------
    //Check for Circle 1 Barrel 2
    void checkCoordinateC1B2(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC1B2X)*(p_iX - mC1B2X))
                + ((p_iY - mC1B2Y)*(p_iY - mC1B2Y))
                <= 3200) {
            mbC1B2Covered = true;
        }
    }

    //Check for Circle 2 Barrel 2
    void checkCoordinateC2B2(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC2B2X)*(p_iX - mC2B2X))
                + ((p_iY - mC2B2Y)*(p_iY - mC2B2Y))
                <= 3200) {
            mbC2B2Covered = true;
        }
    }

    //Check for Circle 3 Barrel 2
    void checkCoordinateC3B2(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC3B2X)*(p_iX - mC3B2X))
                + ((p_iY - mC3B2Y)*(p_iY - mC3B2Y))
                <= 3200) {
            mbC3B2Covered = true;
        }
    }

    //Check for Circle 4 Barrel 2
    void checkCoordinateC4B2(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC4B2X)*(p_iX - mC4B2X))
                + ((p_iY - mC4B2Y)*(p_iY - mC4B2Y))
                <= 3200) {
            mbC4B2Covered = true;
        }
    }

    //------------------------------Barrel 3--------------------------------------------------------
    //Check for Circle 1 Barrel 3
    void checkCoordinateC1B3(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC1B3X)*(p_iX - mC1B3X))
                + ((p_iY - mC1B3Y)*(p_iY - mC1B3Y))
                <= 2400) {
            mbC1B3Covered = true;
        }
    }

    //Check for Circle 2 Barrel 3
    void checkCoordinateC2B3(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC2B3X)*(p_iX - mC2B3X))
                + ((p_iY - mC2B3Y)*(p_iY - mC2B3Y))
                <= 3200) {
            mbC2B3Covered = true;
        }
    }

    //Check for Circle 3 Barrel 3
    void checkCoordinateC3B3(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC3B3X)*(p_iX - mC3B3X))
                + ((p_iY - mC3B3Y)*(p_iY - mC3B3Y))
                <= 3200) {
            mbC3B3Covered = true;
        }
    }

    //Check for Circle 4 Barrel 3
    void checkCoordinateC4B3(int p_iX,int p_iY){
        //Check if reached home
        if (((p_iX - mC4B3X)*(p_iX - mC4B3X))
                + ((p_iY - mC4B3Y)*(p_iY - mC4B3Y))
                <= 3200) {
            mbC4B3Covered = true;
        }
    }

}

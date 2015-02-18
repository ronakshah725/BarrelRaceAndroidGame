package com.example.swaroop.barrelraceuid.app;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;




class Coordinates{
    int XCoordinate;
    int YCoordinate;
}
public class Game extends ActionBarActivity implements SurfaceHolder.Callback,SensorEventListener {

    private SurfaceView surface;
    private SurfaceHolder holder;

    public static int x;
    public static int y;


    static Bitmap image;
    static Bitmap bmHorse;
    static Bitmap bmBarrel1;
    static Bitmap bmBarrel2;
    static Bitmap bmFence1;
    static Bitmap bmText;
    static Bitmap bmStart;
    static Bitmap bmPause;
    static Bitmap bmGameover;
    static Bitmap bmLike;

    static Bitmap temp;
    static Bitmap tempHorse;
    static Bitmap tempBarrel1;
    static Bitmap tempBarrel2;
    static Bitmap tempFence1;
    static Bitmap tempText;
    static Bitmap tempStart;
    static Bitmap tempPause;
    static Bitmap tempGameover;
    static Bitmap tempLike;

    int margenMinX;
    int margenMinY;
    int margenMaxX;
    int margenMaxY;

    static boolean barrel1Completed = false;
    static boolean barrel2Completed =false;
    static boolean barrel3Completed = false;



    static List<Coordinates> lstCoordinates;
    static CheckBarrel objCheckBarrel;
    private SensorManager sensorManager = null;
    Coordinates homeCoordinates;

    long mStartTime;
    public static String timer="0:00:00";
    public static String finalTimer="0:00:00";
    public static long lFinalTimeinMillis = 00;
    private volatile boolean running = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        surface = (SurfaceView) findViewById(R.id.gamescene);
        holder = surface.getHolder();
        surface.getHolder().addCallback(this);



        // Get a reference to a SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);



    }

    private void draw() {
        // thread safety - the SurfaceView could go away while we are drawing

        Canvas c = null;
        try {
            // NOTE: in the LunarLander they don't have any synchronization
            // here,
            // so I guess this is OK. It will return null if the holder is not
            // ready
            c = holder.lockCanvas();

            // this needs to synchronize on something
            if (c != null) {
                doDraw(c);
            }
        } finally {
            if (c != null) {
                holder.unlockCanvasAndPost(c);
            }
        }
    }

    private float getBitmapScalingFactor(Bitmap bm, String strName) {
        try{
            AssetManager assetManager = getAssets();
            InputStream istr = assetManager.open(strName + ".png");
            Bitmap bitmap = BitmapFactory.decodeStream(istr);

            float screenWidth = getResources().getDisplayMetrics().widthPixels;
            int ih=bitmap.getHeight();
            int iw=bitmap.getWidth();
            float scalefactor = screenWidth/iw;
            return scalefactor;
        }
        catch(IOException e) {

        }
        return 1;
    }

    // Coordinates of three barrels
    private int barrels[][];
    private int fence[][];
    public void doDraw(Canvas canvas) {
        System.out.println("On Draw");
        image = Bitmap.createScaledBitmap(temp,canvas.getWidth(),canvas.getHeight(),true);
        margenMaxX = mWidth - 100;
        margenMinX = 10;
        margenMaxY = ((int)((3*mHeight)/4))+60;
        margenMinY = ((int)((mHeight)/3))-20;
        bmHorse = Bitmap.createScaledBitmap(tempHorse,tempHorse.getWidth(),tempHorse.getHeight(),true);

        int ScaleBarrel1 = (int)getBitmapScalingFactor(tempBarrel1,"ic_barrel2");
        int ScaleBarrel2 = (int)getBitmapScalingFactor(tempBarrel2,"ic_barrel3");
        int ScaleFence = (int)getBitmapScalingFactor(tempFence1,"fence2");
        int ScaleGameover = (int)getBitmapScalingFactor(tempGameover,"gameover");
        int ScaleLike = (int)getBitmapScalingFactor(tempLike,"ic_like");

        bmBarrel1 = Bitmap.createScaledBitmap(tempBarrel1,tempBarrel1.getWidth()*ScaleBarrel1,
                                                tempBarrel1.getHeight()*ScaleBarrel1,true);
        bmBarrel2 = Bitmap.createScaledBitmap(tempBarrel2,tempBarrel2.getWidth()*ScaleBarrel2,
                                              tempBarrel2.getHeight()*ScaleBarrel2,true);
        bmFence1 = Bitmap.createScaledBitmap(tempFence1,tempFence1.getWidth()*ScaleFence,
                                             tempFence1.getHeight()*ScaleFence,true);

        bmGameover = Bitmap.createScaledBitmap(tempGameover,tempGameover.getWidth()*ScaleGameover,
                                                tempGameover.getHeight()*ScaleGameover,true);

        bmLike = Bitmap.createScaledBitmap(tempLike,tempLike.getWidth()*ScaleGameover,
                tempLike.getHeight()*ScaleGameover,true);



        canvas.drawBitmap(image,0,0,null);
        canvas.drawBitmap(bmBarrel2,((int)(mWidth/3)) - 100 ,((int)((2*mHeight)/3))-100,null);
        canvas.drawBitmap(bmBarrel2, ((int)(mWidth/3)) + 200 ,((int)((2*mHeight)/3))-100, null);
        canvas.drawBitmap(bmBarrel1,((int)(mWidth/2))-50 ,((int)((mHeight)/3))+120,null);



        // Coordinates of three barrels
        barrels[0][0] = ((int)(mWidth/3)) - 100;
        barrels[0][1] = ((int)((2*mHeight)/3))-100;
        barrels[1][0] = ((int)(mWidth/3)) + 200;
        barrels[1][1] = ((int)((2*mHeight)/3))-100;
        barrels[2][0] = ((int)(mWidth/2))-50;
        barrels[2][1] = ((int)((mHeight)/3))+120;

        Bitmap bmNearFence = Game.scaleDown(bmFence1,200,true);
        canvas.drawBitmap(bmNearFence,5 ,((int)((3*mHeight)/4))+30,null);
        canvas.drawBitmap(bmNearFence,((int)(mWidth/4))+ 200 ,((int)((3*mHeight)/4))+30,null);
        canvas.drawBitmap(bmNearFence,100 ,((int)((3*mHeight)/4))+30,null);
        canvas.drawBitmap(bmNearFence,((int)(mWidth/4))+ 350 ,((int)((3*mHeight)/4))+30,null);

        bmFence1 = Game.scaleDown(bmFence1,150,true);

        canvas.drawBitmap(bmFence1,((int)(mWidth/4))- 50 ,((int)((mHeight)/3))-20, null);
        canvas.drawBitmap(bmFence1,((int)(mWidth/3))+20 ,((int)((mHeight)/3))-20,null);
        canvas.drawBitmap(bmFence1,((int)(mWidth/4))+ 50 ,((int)((mHeight)/3))-20, null);
        canvas.drawBitmap(bmFence1,((int)(mWidth/3))+ 170 ,((int)((mHeight)/3))-20,null);
        if(!MyActivity.bGameFarm){
            canvas.drawBitmap(bmFence1, 0, ((int)((mHeight)/3))-20, null);
            canvas.drawBitmap(bmFence1,((int)(mWidth/3))+ 320,((int)((mHeight)/3))-20,null);
        }

        // Coordinates of fence
        fence[0][0] = 5;
        fence[0][1] = ((int)((3*mHeight)/4))+30;
        fence[1][0] = ((int)(mWidth/4))+ 200;
        fence[1][1] = ((int)((3*mHeight)/4))+30;
        fence[2][0] = 100;
        fence[2][1] = ((int)((3*mHeight)/4))+30;
        fence[3][0] = ((int)(mWidth/4))+ 350;
        fence[3][1] = ((int)((3*mHeight)/4))+30;

        fence[4][0] = ((int)(mWidth/4))- 50;
        fence[4][1] = ((int)((mHeight)/3))-20;
        fence[5][0] = ((int)(mWidth/3))+20;
        fence[5][1] = ((int)((mHeight)/3))-20;
        fence[6][0] = ((int)(mWidth/4))+50;
        fence[6][1] = ((int)((mHeight)/3))-20;
        fence[7][0] = ((int)(mWidth/3))+ 170;
        fence[7][1] = ((int)((mHeight)/3))-20;

        Matrix matrix = new Matrix();
        //matrix.setTranslate(10,10);
        matrix.setRotate(-70, 220, 30);
        //canvas.drawBitmap(bmFence1, matrix, null);
        Paint timerPaint = new Paint();
        timerPaint.setColor(Color.RED);
        timerPaint.setTextAlign(Paint.Align.LEFT);
        timerPaint.setTextScaleX((float) 2.2);
        timerPaint.setTextSize(50);
        timerPaint.setStyle(Paint.Style.FILL);
        timerPaint.setStrokeWidth(6);
        timerPaint.setAntiAlias(true);
        canvas.drawText(timer, 150, 190, timerPaint);
        if(bGameStatus) {
            canvas.drawBitmap(bmHorse, Game.x, Game.y, null);
            Coordinates objCoordinates = new Coordinates();
            objCoordinates.XCoordinate = (int) x;
            objCoordinates.YCoordinate = (int) y;
            lstCoordinates.add(objCoordinates);


            int iRadius = 2500;
            //Check for Barrel Hit
            for (int iPos = 0; iPos < 3; iPos++) {
                if(iPos== 2){
                    iRadius = 1600;
                }
                if ((x - barrels[iPos][0]) * (x - barrels[iPos][0])
                        + (y - barrels[iPos][1]) * (y - barrels[iPos][1]) <= iRadius) {
                    touchBarrel = true;
                    break;
                }

            }

            //Check for Fence Hit
            //This loop will check if moving ball has hit any of the barrel
            for (int i = 0; i < 8; i++) {

                if ((x - fence[i][0]) * (x - fence[i][0])
                        + (y - fence[i][1]) * (y - fence[i][1]) <= 4300) {

                    hitfence = true;
                }

            }

            if(touchBarrel){
                System.out.println("Game Over");
                canvas.drawBitmap(bmGameover, ((int)(mWidth/3))-40, ((int)((mHeight)/3))+100, null);
            }

            //check for barrel covered
            if(!barrel1Completed) {
                barrel1Completed = objCheckBarrel.checkBarrel1Covered(x,y);
            }

            if(!barrel2Completed) {
                barrel2Completed = objCheckBarrel.checkBarrel2Covered(x,y);
            }

            if(!barrel3Completed) {
                barrel3Completed = objCheckBarrel.checkBarrel3Covered(x,y);
            }


            if(barrel1Completed){
                canvas.drawBitmap(bmLike,((int)(mWidth/3)) - 90 ,((int)((2*mHeight)/3))-130,null);
            }

            if(barrel2Completed){
                canvas.drawBitmap(bmLike, ((int)(mWidth/3)) + 220 ,((int)((2*mHeight)/3))-130, null);
            }

            if(barrel3Completed){
                canvas.drawBitmap(bmLike,((int)(mWidth/2))-50 ,((int)((mHeight)/3))+90,null);
            }
            if(barrel1Completed && barrel2Completed && barrel3Completed){
                //Check if reached home
                if (((x - Xinitial) * (x - Xinitial))
                        + ((y - Yinitial) * (y - Yinitial)) <= 900) {
                    bReachedHome = true;
                }
            }
            if(bReachedHome){
                //stop the timer
                finalTimer = timer;
                lFinalTimeinMillis = objStopWatch.stop();

                //unregister the sensor
                UnregisterSensor();
                bGameCompleted = true;
            }
        }
        }

    static int mWidth;
    static int mHeight;

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
        //objHorseGallpoing.setSize(width, height);
        mWidth = width;
        mHeight = height;
    }

    private GameThread objGameThread;
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.v("Game","Surface Created");
        running = true;
        objGameThread = new GameThread();
        objGameThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        try {
            objGameThread.safeStop();
        } finally {
            objGameThread = null;
        }

    }

    void RegisterSensor(){
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    void UnregisterSensor(){
        sensorManager.unregisterListener(this);
    }

    static boolean bGameStatus = false;
    static boolean bGamePaused = false;
    static boolean bGameStopped = false;
    static ImageButton StartButton;
    static ImageButton RestartButton;
    static ImageButton PauseButton;
    static Stopwatch objStopWatch;
    private boolean gameOver = false;
    private boolean bGameCompleted = false;
    private boolean touchBarrel = false;
    private boolean hitfence = false;
    private boolean bReachedHome = false;
    private int hitfencecount = 0;
    static int Xinitial;
    static int Yinitial;
    private class GameThread extends Thread implements View.OnClickListener {

        public void safeStop() {
            objStopWatch.stop();
            mStartTime = 0;
            timer = "0:00:00";
            bGamePaused = false;
            bGameStatus = false;
            barrel1Completed = false;
            barrel2Completed =false;
            barrel3Completed = false;
            running = false;
        }

        long millis = 00;
        int seconds = 00;
        int minutes = 00;

        long pausedmillis = 00;

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.startgame:{

                    if(touchBarrel){
                        touchBarrel = false;
                        running = true;
                    }

                    if(!bGameStatus){

                        //mStartTime=SystemClock.uptimeMillis();
                        objStopWatch = new Stopwatch();
                        objStopWatch.start();
                        mStartTime = objStopWatch.startTime;
                        System.out.println("Start Time :: " + mStartTime );
                        RegisterSensor();
                        bGameStatus = true;
                    }

                    if(bGamePaused){
                        objStopWatch.resume();
                        bGamePaused = false;
                        RegisterSensor();
                    }
                }
                break;
                case R.id.restartgame:{
                    objStopWatch.stop();
                    bGamePaused = false;
                    bGameStatus = false;
                    timer = "0:00:00";
                    mStartTime = 0;
                    millis = 00;
                    seconds = 00;
                    minutes = 00;
                    bGamePaused = false;
                    bGameStatus = false;
                    barrel1Completed = false;
                    barrel2Completed =false;
                    barrel3Completed = false;
                    hitfence = false;
                    Intent intent = Game.this.getIntent();
                    finish();
                    startActivity(intent);
                }
                break;
                case R.id.pausegame:{
                    if(bGameStatus) {
                        bGamePaused = true;
                        objStopWatch.pause();

                        UnregisterSensor();
                        pausedmillis = millis;
                    }
                }
                break;
            }

        }

        public void run() {

            StartButton = (ImageButton) findViewById(R.id.startgame);
            // startBtn.setBackgroundDrawable(R.drawable.ic_start_button);
            StartButton.setOnClickListener(this);

            RestartButton = (ImageButton) findViewById(R.id.restartgame);
            // startBtn.setBackgroundDrawable(R.drawable.ic_start_button);
            RestartButton.setOnClickListener(this);

            PauseButton = (ImageButton) findViewById(R.id.pausegame);
            // startBtn.setBackgroundDrawable(R.drawable.ic_start_button);
            PauseButton.setOnClickListener(this);

            //Set the Barrel coordinates
            objCheckBarrel = new CheckBarrel();
            objCheckBarrel.setBarrel1Center(((int)(mWidth/3)) - 100,
                                               ((int)((2*mHeight)/3))-100);
            objCheckBarrel.setBarrel2Center(((int)(mWidth/3)) + 200,
                                              ((int)((2*mHeight)/3))-100);
            objCheckBarrel.setBarrel3Center(((int)(mWidth/2))-50,
                                                ((int)((mHeight)/3))+120);

            //generate the circle center
            objCheckBarrel.GenerateCircleCenters();


            lstCoordinates = new ArrayList<Coordinates>();
            barrels = new int[3][3];
            fence = new int[8][8];

            if(MyActivity.bGameFarm) {
                temp = BitmapFactory.decodeResource(getResources(), R.drawable.background1);
            }
            else
            {
                temp = BitmapFactory.decodeResource(getResources(), R.drawable.background2);
            }
            tempHorse = BitmapFactory.decodeResource(getResources(),R.drawable.horse1);
            tempBarrel1 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_barrel2);
            tempBarrel2 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_barrel3);
            tempFence1 = BitmapFactory.decodeResource(getResources(),R.drawable.fence2);


            tempGameover = BitmapFactory.decodeResource(getResources(),R.drawable.gameover);
            tempLike = BitmapFactory.decodeResource(getResources(),R.drawable.ic_like);

            x = ((int)((mWidth)/2))-60;
            y = ((int)((3*mHeight)/4))+60;
            Xinitial = ((int)((mWidth)/2))-60;
            Yinitial = ((int)((3*mHeight)/4))+60;

            homeCoordinates = new Coordinates();
            homeCoordinates.XCoordinate = (int)x;
            homeCoordinates.YCoordinate = (int)y;



            //if (rotation == Configuration.ORIENTATION_LANDSCAPE){

            //}
           // else{



           // }

            while(running){
                if(!bGamePaused) {
                    if (mStartTime > 0) {
                        System.out.println("Start Time in Loop :: " + mStartTime );
                        if(hitfence){
                            objStopWatch.setPenaltyCount();
                            hitfence = false;
                        }
                        millis = objStopWatch.getElapsedTimeMili();
                        System.out.println("Game Time Elapsed :: " + millis);
                        seconds = (int) (millis / 1000);
                        minutes = seconds / 60;
                        seconds = seconds % 60;
                        millis = millis - minutes * 60 * 1000 - seconds * 1000;
                        pausedmillis = 00;
                    }
                    // If game is not started then timer will be 0:00:00
                    timer = minutes + ":" + seconds + ":" + millis;

                    if (timer.length() > 8)
                        timer = timer.substring(0, 8);

                    draw();

                    if(touchBarrel){
                        running=false;
                        objStopWatch.stop();
                        gameOver=true;
                        draw();

                        bGameStatus = false;
                        mStartTime = 0;
                        millis = 00;
                        seconds = 00;
                        minutes = 00;
                        timer = "0:00:00";
                        bGamePaused = false;
                        bGameStatus = false;
                        barrel1Completed = false;
                        barrel2Completed =false;
                        barrel3Completed = false;
                    }

                    if(bGameCompleted) {
                            timer = "0:00:00";
                            mStartTime = 0;
                            millis = 00;
                            seconds = 00;
                            minutes = 00;
                            bGamePaused = false;
                            bGameStatus = false;
                            barrel1Completed = false;
                            barrel2Completed =false;
                            barrel3Completed = false;
                            hitfence = false;
                        Intent intentAddScore = new Intent(Game.this, AddScore.class);
                        startActivity(intentAddScore);
                        running = false;
                    }
                }
            }
        }

    }
    public void onSensorChanged(SensorEvent sensorEvent) {

        //Try synchronize the events
        synchronized(this){
            //For each sensor
            switch (sensorEvent.sensor.getType()) {
                /*case Sensor.TYPE_MAGNETIC_FIELD: //Magnetic sensor to know when the screen is landscape or portrait
                    //Save values to calculate the orientation
                    mMagneticValues = sensorEvent.values.clone();
                    break;*/
                case Sensor.TYPE_ACCELEROMETER://Accelerometer to move the ball
                    if (Configuration.ORIENTATION_LANDSCAPE == this.getResources().getConfiguration().orientation){//Landscape
                        //Positive values to move on x
                        if (sensorEvent.values[1]>0){
                            //In margenMax I save the margin of the screen this value depends of the screen where we run the application. With this the ball not disapears of the screen
                            if (x<=margenMaxX){
                                //We plus in x to move the ball
                                x = x + (int) Math.pow(sensorEvent.values[1], 2);
                            }
                        }
                        else{
                            //Move the ball to the other side
                            if (x>=margenMinX){
                                x = x - (int) Math.pow(sensorEvent.values[1], 2);
                            }
                        }
                        //Same in y
                        if (sensorEvent.values[0]>0){
                            if (y<=margenMaxY){
                                y = y + (int) Math.pow(sensorEvent.values[0], 2);
                            }
                        }
                        else{
                            if (y>=margenMinY){
                                y = y - (int) Math.pow(sensorEvent.values[0], 2);
                            }
                        }
                    }
                    else{//Portrait
                        //Eje X
                        if (sensorEvent.values[0]<0){
                            if (x<=margenMaxX){
                                x = x + (int) Math.pow(sensorEvent.values[0], 2);
                            }
                        }
                        else{
                            if (x>=margenMinX){
                                x = x - (int) Math.pow(sensorEvent.values[0], 2);
                            }
                        }
                        //Eje Y
                        if (sensorEvent.values[1]>0){
                            if (y<=margenMaxY){
                                y = y + (int) Math.pow(sensorEvent.values[1], 2);
                            }
                        }
                        else{
                            if (y>=margenMinY){
                                y = y - (int) Math.pow(sensorEvent.values[1], 2);
                            }
                        }

                    }
                    //Save the values to calculate the orientation
                    //mAccelerometerValues = sensorEvent.values.clone();
                    break;
                default:
                    break;
            }
            //Screen Orientation
            /*if (mMagneticValues != null && mAccelerometerValues != null) {
                float[] R = new float[16];
                SensorManager.getRotationMatrix(R, null, mAccelerometerValues, mMagneticValues);
                float[] orientation = new float[3];
                SensorManager.getOrientation(R, orientation);
                //if x have positives values the screen orientation is landscape in other case is portrait
                if (orientation[0]>0){//LandScape
                    //Here I change the margins of the screen for the ball not disapear
                    bOrientacion=true;
                    margenMaxX=1200;
                    margenMinX=0;
                    margenMaxY=500;
                    margenMinY=0;
                }
                else{//Portrait
                    bOrientacion=false;
                    margenMaxX=600;
                    margenMinX=0;
                    margenMaxY=1000;
                    margenMinY=0;
                }

            }*/
        }

    }

    // I've chosen to not implement this method
    public void onAccuracyChanged(Sensor arg0, int arg1)
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // Register this class as a listener for the accelerometer sensor
        mStartTime = 0;
        bGamePaused = false;
        bGameStatus = false;
        barrel1Completed = false;
        barrel2Completed =false;
        barrel3Completed = false;


    }

    @Override
    protected void onPause()
    {

        // Unregister the listener
        UnregisterSensor();
        objStopWatch.stop();
        mStartTime = 0;
        timer = "0:00:00";
        bGamePaused = false;
        bGameStatus = false;
        barrel1Completed = false;
        barrel2Completed =false;
        barrel3Completed = false;
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();



    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }


    //oncreate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.highscore, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.highscoreaction: {
                if(MyActivity.m_objDataHandler.getListSize() == 0){
                    Toast.makeText(getApplicationContext(), "No Scores Available!!!", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Intent intentHighScore = new Intent(this, HighScores.class);
                startActivity(intentHighScore);
            }
            break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

}

 class Stopwatch {
    public long startTime = 0;
    public long penaltyTime = 0;
    private boolean running = false;
    private long currentTime = 0;
    private int penaltyCount = 0;

    void setPenaltyCount(){
        this.penaltyCount = this.penaltyCount + 1;
        this.penaltyTime = 5000*penaltyCount;
    }


    public void start() {
        System.out.println("Stop Watch GetTime :: " + System.currentTimeMillis());
        this.startTime = System.currentTimeMillis();
        this.running = true;
        System.out.println("Stop Watch Start :: " + this.startTime);

    }


    public long stop() {
        this.running = false;
        long lastTime = this.elapsed;
        elapsed = 0;
        startTime = 0;
        penaltyTime = 0;
        currentTime = 0;
        penaltyCount = 0;
        return lastTime;

    }

    public void pause() {
        this.running = false;
        System.out.println("Stop Watch Get Pause:: " + System.currentTimeMillis());
        currentTime = System.currentTimeMillis() - startTime;
        System.out.println("Stop Watch Current Time :: " + currentTime);


    }
    public void resume() {
        this.running = true;
        System.out.println("Stop Watch Get Resume:: " + System.currentTimeMillis());
        this.startTime = System.currentTimeMillis() - currentTime;
    }

    long elapsed = 0;
    //elaspsed time in milliseconds
    public long getElapsedTimeMili() {

        if (this.running) {
            this.elapsed = System.currentTimeMillis() - this.startTime + this.penaltyTime;
        }
        System.out.println("Stop Watch Get Elapsed:: " + System.currentTimeMillis());
        return this.elapsed;
    }


    //elaspsed time in seconds
    public long getElapsedTimeSecs() {
        long elapsed = 0;
        if (this.running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000) % 60;
        }
        return elapsed;
    }

    //elaspsed time in seconds
    public long getCurrentTime() {

        if(this.running){
            return System.currentTimeMillis();}

        return 0;
    }
}
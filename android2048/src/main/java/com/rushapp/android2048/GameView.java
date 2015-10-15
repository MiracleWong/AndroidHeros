package com.rushapp.android2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miraclewong on 15/10/10.
 */
public class GameView extends GridLayout {
    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

    private void initGameView(){
        setColumnCount(4);
        setBackgroundColor(0xffbdada0);
        setOnTouchListener(new OnTouchListener() {
            private float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;

                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                swipeLeft();
                            } else if (offsetX > 5) {
                                swipeRight();
                            }
                        } else {
                            if (offsetY < -5) {
                                swipeUp();
                            } else if (offsetY > 5) {
                                swipeDown();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    // 当布局发生改变的时候，我们可以知道当前的布局的宽高，知道之后才可以去jisuan
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w, h) - 10)/4;
        
        addCards(cardWidth, cardWidth);
        startGame();
    }

    public void startGame(){
        MainActivity.getMainActivity().clearScore();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardsMap[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();
    }

    private void addCards(int cardWidth, int cardHeight){
        Card card;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                card = new Card(getContext());
                card.setNum(0);
                addView(card,cardWidth, cardHeight);
                // 将新生成的card添加到数组中
                cardsMap[x][y] = card;
            }
        }
    }

    private void addRandomNum(){
        // 开始的时候进行清空
        emptyPoints.clear();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardsMap[x][y].getNum() <= 0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }
        // 1个for循环走完要取出一个点
        Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        // 2：4的概率是9：1
        cardsMap[p.x][p.y].setNum(Math.random() > 0.1?2:4);
    }

    private void swipeLeft(){
        System.out.println("left");

        boolean merge = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                // 从当前的位置向右遍历
                for (int x1 = x+1; x1 < 4; x1++) {
                    if (cardsMap[x1][y].getNum() > 0){
                        if (cardsMap[x][y].getNum() <= 0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x--;
                            merge =true;
                        }else if (cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge){
            addRandomNum();
            checkCompleted();
        }
    }
    private void swipeRight(){
        boolean merge = false;
        System.out.println("right");
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                // 从当前的位置向右遍历
                for (int x1 = x-1; x1 >= 0; x1--) {
                    if (cardsMap[x1][y].getNum() > 0){
                        if (cardsMap[x][y].getNum() <= 0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x++;
                            merge = true;
                        }else if (cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge){
            addRandomNum();
            checkCompleted();
        }
    }
    private void swipeUp(){
        boolean merge = false;
        System.out.println("up");
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                // 从当前的位置向右遍历
                for (int y1 = y+1; y1 < 4; y1++) {
                    if (cardsMap[x][y1].getNum() > 0){
                        if (cardsMap[x][y].getNum() <= 0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            y--;
                            merge = true;
                        }else if (cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge){
            addRandomNum();
            checkCompleted();
        }
    }
    private void swipeDown(){
        boolean merge = false;
        System.out.println("down");
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                // 从当前的位置向下遍历
                for (int y1 = y-1; y1 >= 0; y1--) {
                    if (cardsMap[x][y1].getNum() > 0){
                        if (cardsMap[x][y].getNum() <= 0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            y++;
                            merge = true;
                        }else if (cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge){
            addRandomNum();
            checkCompleted();
        }
    }


    public void checkCompleted(){
        boolean complete = true;
        ALL:
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardsMap[x][y].getNum() == 0 ||
                        (x>0 && cardsMap[x][y].equals(cardsMap[x-1][y])) ||     //向左判断
                        (x<3 && cardsMap[x][y].equals(cardsMap[x+1][y])) ||
                        (y>0 && cardsMap[x][y].equals(cardsMap[x][y-1])) ||
                        (y<3 && cardsMap[x][y].equals(cardsMap[x][y+1]))
                        ){
                    complete = false;
                    break ALL;
                }
            }
        }
        if (complete){
            new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束").setPositiveButton("重来", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startGame();
                }
            }).show();
        }
    }

    private Card[][] cardsMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<>();
}

package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B, arial_50B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {

        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text) {

        messageOn = true;
        message = text;
    }
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playState) {
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
                int x = getXforCenteredText(message);
                int y = gp.screenHeight/4;
                g2.drawString(message, x, y);
                messageCounter++;
            }
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
}

public void drawPauseScreen() {

    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
    String text = "PAUSED";
    int x = getXforCenteredText(text);
    int y = gp.screenHeight/4;

    g2.drawString(text, x, y);
}

public int getXforCenteredText(String text) {

    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = gp.screenWidth/2 - length/2;
    return x;
}
}
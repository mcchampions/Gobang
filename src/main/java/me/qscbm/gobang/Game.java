package me.qscbm.gobang;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends JFrame implements MouseListener {
    public static int qx = 20, qy = 40, qw = 490, qh = 490, bw = 150, bh = 50, bx = 570, by = 150;
    public static int x;
    public static int y;

    public static int qc = 1;
    //0为没有棋子，1为黑，2为白
    //int[y][x]
    public static int[][] data = new int[15][15];

    //同上
    public static int playerPawn = 1;

    public static File jpg = new File("jpg.jpg");

    public Game() {
        this.setTitle("QS五子棋");
        this.setSize(800,550);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((width - 800) / 2, (height - 600) / 2);
        this.addMouseListener(this);
        this.setVisible(true);
    }

    public static String info = "";

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(800, 550, BufferedImage.TYPE_INT_ARGB);
        Graphics g2 = bi.createGraphics();

        BufferedImage image = null;
        try {
            image = ImageIO.read(jpg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(image, 10, 10, this);
        g2.setColor(Color.BLACK);//设置画笔颜色
        g2.setFont(new Font("华文行楷", 10, 50));
        g2.drawString("简单五子棋", 525, 100);
        g2.setColor(Color.getHSBColor(30, (float) 0.10, (float) 0.90));
        g2.fillRect(qx, qy, qw, qh);
        g2.setColor(Color.WHITE);
        g2.fillRect(bx, by, bw, bh);
        g2.setFont(new Font("华文行楷", 10, 30));
        g2.setColor(Color.black);
        g2.drawString("开始", 615, 185);
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(bx, by + 60, bw, bh);
        g2.setFont(new Font("华文行楷", 10, 30));
        g2.setColor(Color.WHITE);
        g2.drawString("悔棋", 615, 245);
        g2.setColor(Color.GRAY);
        g2.fillRect(bx, by + 120, bw, bh);
        g2.setFont(new Font("华文行楷", 10, 30));
        g2.setColor(Color.WHITE);
        g2.drawString("认输", 615, 305);
        g2.setColor(Color.CYAN);
        g2.fillRect(550, 350, 200, 150);
        g2.setColor(Color.black);
        g2.setFont(new Font("黑体", 10, 20));
        g2.drawString("游戏信息", 610, 380);
        g2.drawString("作者：qscbm187531", 560, 410);
        g2.drawString("联系方式:", 560, 445);
        g2.drawString("github mcchampions", 560, 460);
        g2.drawString(info, 560, 490);
        g2.setColor(Color.BLACK);
        for (int x = 0; x <= qw; x += 35) {
            g2.drawLine(qx, x + qy, qw + qx, x + qy);
            g2.drawLine(x + qx, qy, x + qx, qh + qy);
        }
        for (int i = 3; i <= 11; i += 4) {
            for (int y = 3; y <= 11; y += 4) {
                g2.fillOval(35 * i + qx - 3, 35 * y + qy - 3, 6, 6);
            }
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (Game.data[i][j] == 1) {
                    int sx = j * 35 + qx;
                    int sy = i * 35 + qy;
                    g2.setColor(Color.BLACK);
                    g2.fillOval(sx - 13, sy - 13, 26, 26);
                }
                if (Game.data[i][j] == 2) {
                    int sx = j * 35 + qx;
                    int sy = i * 35 + qy;
                    g2.setColor(Color.WHITE);
                    g2.fillOval(sx - 13, sy - 13, 26, 26);
                    g2.drawOval(sx - 13, sy - 13, 26, 26);
                }
            }
        }
        g.drawImage(bi, 0, 0, this);
    }

    public void Initialize() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                data[i][j] = 0;
            }
        }
        qc = 1;
        info = "轮到黑子";
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    
    public static boolean isPlayed = false;

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        if (isPlayed) {
            if (x > qx && x < qx + qw && y > qy && y < qy + qh) {
                if ((x - qx) % 35 > 17) {
                    x = (x - qx) / 35 + 1;
                } else {
                    x = (x - qx) / 35;
                }
                if ((y - qy) % 35 > 17) {
                    y = (y - qy) / 35 + 1;
                } else {
                    y = (y - qy) / 35;
                }
                if (data[y][x] == 0) {
                    data[y][x] = qc;
                    qn = 0;
                } else {
                    qn = 1;
                }
                if (qn == 0) {
                    if (qc == 1) {
                        qc = 2;
                        info = "轮到白子";
                        Pos pos = Util.determineBestPos(false);
                        data[pos.getY()][pos.getX()] = 2;
                        int wl = Util.isEnd();

                        if (wl != 3) {
                            this.repaint();
                            JOptionPane.showMessageDialog(this, "游戏结束，" + (wl == 0 ? "黑方赢了" : (wl == 1 ? "白方赢了" : "平局")));
                            isPlayed = false;
                        }
                    }
                    qc = 1;
                    info = "轮到黑子";
                }

                this.repaint();

                int wl = Util.isEnd();

                if (wl != 3) {
                    JOptionPane.showMessageDialog(this, "游戏结束，" + (wl == 0 ? "黑方赢了" : (wl == 1 ? "白方赢了" : "平局")));
                    isPlayed = false;
                }
            }
        }

        if (e.getX() > bx && e.getX() < bx + bw && e.getY() > by && e.getY() < by + bh) {
            if (!isPlayed) {
                isPlayed = true;
                JOptionPane.showMessageDialog(this, "游戏开始");
                Initialize();
                this.repaint();

            } else {
                JOptionPane.showMessageDialog(this, "重新开始");
                Initialize();
                this.repaint();

            }
        }
        if (e.getX() > bx && e.getX() < bx + bw && e.getY() > by + 60 && e.getY() < by + 60 + bh) {
            if (isPlayed) {
                int z = 0;
                for (int i = 0; i < 15; i++) {
                    for (int j = 0; j < 15; j++) {
                        if (data[i][j] != 0) {
                            z++;
                        }
                    }
                }
                if (z != 0) {
                    JOptionPane.showMessageDialog(this, "下棋亦如人生，你走的每一步都没有回头路。");
                } else {
                    JOptionPane.showMessageDialog(this, "棋盘上已无棋子");
                }

            } else {
                JOptionPane.showMessageDialog(this, "请先开始游戏");
            }
        }

        if (e.getX() > bx && e.getX() < bx + bw && e.getY() > by + 120 && e.getY() < by + 120 + bh) {
            if (isPlayed) {
                if (qc == 1) {
                    JOptionPane.showMessageDialog(this, "黑方认输，白方获胜");
                    isPlayed = false;
                } else if (qc == 2) {
                    JOptionPane.showMessageDialog(this, "白方认输，黑方获胜");
                    isPlayed = false;
                }
            } else {
                JOptionPane.showMessageDialog(this, "请先开始游戏");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    int qn = 0;
}

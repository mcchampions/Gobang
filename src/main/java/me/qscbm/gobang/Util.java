package me.qscbm.gobang;

import java.util.*;

public class Util {
    //0黑，1白，2平局，3无结束
    public static int isEnd(boolean isPlayer) {
        boolean flag = false;
        int pawn = isPlayer ? Game.playerPawn : (Game.playerPawn == 1 ? 2 : 1);
        int[][] temp = Game.data;
        for (int i = 0;i<15;i++) {
            if (flag) {
                break;
            }
            for (int j = 0; j < 15; j++) {
                if (temp[i][j] == pawn) {
                    Pos pos = new Pos(j, i, 0);
                    int upRow = 4;
                    int downRow = 4;
                    int leftRow = 4;
                    int rightRow = 4;
                    if (i - 4 < 0) {
                        upRow = i;
                    } else if (15 - i < 5) {
                        downRow = 14 - i;
                    }
                    if (j - 4 < 0) {
                        leftRow = j;
                    } else if (15 - j < 5) {
                        rightRow = 14 - j;
                    }

                    StringBuilder sb = new StringBuilder("1");
                    if (upRow != 0) {
                        for (int k = 0; k < upRow; k++) {
                            sb.append(temp[i - k - 1][j] == pawn ? 1 : 0);
                        }
                        if (returnPos(sb.toString(), pos, 1).getValue() == 100) {
                            flag = true;
                            break;
                        }
                    }

                    if (downRow != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < downRow; k++) {
                            sb.append(temp[i + k + 1][j] == pawn ? 1 : 0);
                        }
                        if (returnPos(sb.toString(), pos, 2).getValue() == 100) {
                            flag = true;
                            break;
                        }
                    }

                    if (leftRow != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < leftRow; k++) {
                            sb.append(temp[i][j - k - 1] == pawn ? 1 : 0);
                        }
                        if (returnPos(sb.toString(), pos, 3).getValue() == 100) {
                            flag = true;
                            break;
                        }
                    }

                    if (rightRow != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < rightRow; k++) {
                            sb.append(temp[i][j + k + 1] == pawn ? 1 : 0);
                        }
                        if (returnPos(sb.toString(), pos, 4).getValue() == 100) {
                            flag = true;
                            break;
                        }
                    }


                    sb = new StringBuilder("1");
                    int tempInt = Math.min(leftRow, upRow);
                    if (tempInt != 0) {
                        for (int k = 0; k < tempInt; k++) {
                            sb.append(temp[i - k - 1][j - k - 1] == pawn ? 1 : 0);
                        }
                        if (returnPos1(sb.toString(), pos, 1).getValue() == 100) {
                            flag = true;
                            break;
                        }
                    }

                    tempInt = Math.min(rightRow, downRow);
                    if (tempInt != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < tempInt; k++) {
                            sb.append(temp[i + k + 1][j + k + 1] == pawn ? 1 : 0);
                        }
                        if (returnPos1(sb.toString(), pos, 2).getValue() == 100) {
                            flag = true;
                            break;
                        }
                    }

                    tempInt = Math.min(leftRow, downRow);
                    if (tempInt != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < tempInt; k++) {
                            sb.append(temp[i + k + 1][j - k - 1] == pawn ? 1 : 0);
                        }
                        if (returnPos1(sb.toString(), pos, 3).getValue() == 100) {
                            flag = true;
                            break;
                        }
                    }

                    tempInt = Math.min(rightRow, upRow);
                    if (tempInt != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < tempInt; k++) {
                            sb.append(temp[i - k - 1][j + k + 1] == pawn ? 1 : 0);
                        }
                        if (returnPos1(sb.toString(), pos, 4).getValue() == 100) {
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
        boolean isTie = false;
        if (!flag) {
            isTie = true;
            for (int j = 0;j<15;j++) {
                if (!isTie) {
                    break;
                }
                for (int k = 0;k<15;k++) {
                    if (Game.data[j][k] == 0) {
                        isTie = false;
                        break;
                    }
                }
            }
        }
        if (isTie) {
            flag = true;
        }
        if (flag) {
            if (isTie) {
                return 2;
            } else {
                return pawn - 1;
            }
        } else {
            return 3;
        }
    }
    /**
     * 判断最佳下棋位置
     * @param isPlayer 是否玩家
     * @return 坐标
     */
    public static Pos determineBestPos(boolean isPlayer) {
        int pawn = isPlayer ? Game.playerPawn : (Game.playerPawn == 1 ? 2 : 1);
        int[][] temp = Game.data;
        List<Pos> posList = new ArrayList<>();
        for (int i = 0;i<15;i++) {
            for (int j = 0;j<15;j++) {
                if (temp[i][j] == pawn) {
                    Pos pos = new Pos(j,i,0);
                    int upRow = 4;
                    int downRow = 4;
                    int leftRow = 4;
                    int rightRow = 4;
                    if (i - 4 < 0) {
                        upRow = i;
                    } else if (15-i < 5){
                        downRow = 14-i;
                    }
                    if (j - 4 < 0) {
                        leftRow = j;
                    } else if (15 - j < 5){
                        rightRow = 14 - j;
                    }

                    StringBuilder sb = new StringBuilder("1");
                    if (upRow != 0) {
                        for (int k = 0; k < upRow; k++) {
                            sb.append(temp[i - k - 1][j] == pawn ? 1 : 0);
                        }
                        posList.add(returnPos(sb.toString(), pos, 1));
                    }

                    if (downRow != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < downRow; k++) {
                            sb.append(temp[i + k + 1][j] == pawn ? 1 : 0);
                        }
                        posList.add(returnPos(sb.toString(), pos, 2));
                    }

                    if (leftRow != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < leftRow; k++) {
                            sb.append(temp[i][j - k - 1] == pawn ? 1 : 0);
                        }
                        posList.add(returnPos(sb.toString(), pos, 3));
                    }

                    if (rightRow != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < rightRow; k++) {
                            sb.append(temp[i][j + k + 1] == pawn ? 1 : 0);
                        }
                        posList.add(returnPos(sb.toString(), pos, 4));
                    }



                    sb = new StringBuilder("1");
                    int tempInt = Math.min(leftRow, upRow);
                    if (tempInt != 0) {
                        for (int k = 0; k < tempInt; k++) {
                            sb.append(temp[i - k - 1][j - k - 1] == pawn ? 1 : 0);
                        }
                        posList.add(returnPos1(sb.toString(), pos, 1));
                    }

                    tempInt = Math.min(rightRow, downRow);
                    if (tempInt != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < tempInt; k++) {
                            sb.append(temp[i + k + 1][j + k + 1] == pawn ? 1 : 0);
                        }
                        posList.add(returnPos1(sb.toString(), pos, 2));
                    }

                    tempInt = Math.min(leftRow, downRow);
                    if (tempInt != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < tempInt; k++) {
                            sb.append(temp[i + k + 1][j - k - 1] == pawn ? 1 : 0);
                        }
                        posList.add(returnPos1(sb.toString(), pos, 3));
                    }

                    tempInt = Math.min(rightRow, upRow);
                    if (tempInt != 0) {
                        sb = new StringBuilder("1");
                        for (int k = 0; k < tempInt; k++) {
                            sb.append(temp[i - k - 1][j + k + 1] == pawn ? 1 : 0);
                        }
                        posList.add(returnPos1(sb.toString(), pos, 4));
                    }
                }
            }
        }
        final Pos[] p = new Pos[]{new Pos(new Random().nextInt(15), new Random().nextInt(15), 0)};
        Map<String,Double> map = new HashMap<>();
        List<Pos> pL = posList.stream().toList();
        pL.forEach(pos -> {
            String str = pos.getX() + "," + pos.getY();
            double d = pos.getValue();
            if (map.containsKey(str)) {
                d = Math.max(map.get(str),pos.getValue()) + 0.05;
                posList.remove(pos);
                posList.add(new Pos(pos.getX(),pos.getY(),d));
            }
            map.put(str, d);
        });
        posList.forEach(pos -> {
            if (pos.getValue() > p[0].getValue()) {
                if (temp[pos.getY()][pos.getX()] == 0 ) {
                    p[0] = pos;
                }
            }
        });
        while(temp[p[0].getY()][p[0].getX()] != 0) {
            p[0] = new Pos(new Random().nextInt(15), new Random().nextInt(15), 0);
        }
        Pos position = p[0];
        if (!isPlayer) {
            Pos tempP = determineBestPos(true);
            if (tempP.getValue() > position.getValue()) {
                position = tempP;
            }
        }
        return position;
    }

    //1上2下3左4右
    public static Pos returnPos(String s,Pos pos,int i) {
        switch (s) {
            case "10":
                if (i == 1) {
                    return new Pos(pos.getX(),pos.getY()-1,0.6);
                } else if (i == 2) {
                    return new Pos(pos.getX(),pos.getY()+1,0.6);
                } else if (i == 3) {
                    return new Pos(pos.getX()-1,pos.getY(),0.6);
                } else {
                    return new Pos(pos.getX()+1,pos.getY(),0.6);
                }
            case "100":
                if (i == 1) {
                    return new Pos(pos.getX(),pos.getY()-1,0.7);
                } else if (i == 2) {
                    return new Pos(pos.getX(),pos.getY()+1,0.7);
                } else if (i == 3) {
                    return new Pos(pos.getX()-1,pos.getY(),0.7);
                } else {
                    return new Pos(pos.getX()+1,pos.getY(),0.7);
                }
            case "1000":
                if (i == 1) {
                    return new Pos(pos.getX(),pos.getY()-1,0.8);
                } else if (i == 2) {
                    return new Pos(pos.getX(),pos.getY()+1,0.8);
                } else if (i == 3) {
                    return new Pos(pos.getX()-1,pos.getY(),0.8);
                } else {
                    return new Pos(pos.getX()+1,pos.getY(),0.8);
                }
            case "10000":
                if (i == 1) {
                    return new Pos(pos.getX(),pos.getY()-1,1);
                } else if (i == 2) {
                    return new Pos(pos.getX(),pos.getY()+1,1);
                } else if (i == 3) {
                    return new Pos(pos.getX()-1,pos.getY(),1);
                } else {
                    return new Pos(pos.getX()+1,pos.getY(),1);
                }
            case "110":
                if (i == 1) {
                    return new Pos(pos.getX(),pos.getY()-2,0.8);
                } else if (i == 2) {
                    return new Pos(pos.getX(),pos.getY()+2,0.8);
                } else if (i == 3) {
                    return new Pos(pos.getX()-2,pos.getY(),0.8);
                } else {
                    return new Pos(pos.getX()+2,pos.getY(),0.8);
                }
            case "1100":
                if (i == 1) {
                    return new Pos(pos.getX(),pos.getY()-2,0.9);
                } else if (i == 2) {
                    return new Pos(pos.getX(),pos.getY()+2,0.9);
                } else if (i == 3) {
                    return new Pos(pos.getX()-2,pos.getY(),0.9);
                } else {
                    return new Pos(pos.getX()+2,pos.getY(),0.9);
                }
            case "11000":
                if (i == 1) {
                    return new Pos(pos.getX(),pos.getY()-2,2);
                } else if (i == 2) {
                    return new Pos(pos.getX(),pos.getY()+2,2);
                } else if (i == 3) {
                    return new Pos(pos.getX()-2,pos.getY(),2);
                } else {
                    return new Pos(pos.getX()+2,pos.getY(),2);
                }
            case "101":
                if (i == 1) {
                    return new Pos(pos.getX(), pos.getY() - 1, 0.75);
                } else if (i == 2) {
                    return new Pos(pos.getX(), pos.getY() + 1, 0.75);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 1, pos.getY(), 0.75);
                } else {
                    return new Pos(pos.getX() + 1, pos.getY(), 0.75);
                }
            case "1010":
                Random random = new Random();
                if (random.nextInt(2) == 1) {
                    if (i == 1) {
                        return new Pos(pos.getX(), pos.getY() - 3, 0.9);
                    } else if (i == 2) {
                        return new Pos(pos.getX(), pos.getY() + 3, 0.9);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 3, pos.getY(), 0.9);
                    } else {
                        return new Pos(pos.getX() + 3, pos.getY(), 0.9);
                    }
                } else {
                    if (i == 1) {
                        return new Pos(pos.getX(), pos.getY() - 1, 0.9);
                    } else if (i == 2) {
                        return new Pos(pos.getX(), pos.getY() + 1, 0.9);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 1, pos.getY(), 0.9);
                    } else {
                        return new Pos(pos.getX() + 1, pos.getY(), 0.9);
                    }
                }
            case "10100":
                random = new Random();
                if (random.nextInt(2) == 1) {
                    if (i == 1) {
                        return new Pos(pos.getX(), pos.getY() - 3, 2);
                    } else if (i == 2) {
                        return new Pos(pos.getX(), pos.getY() + 3, 2);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 3, pos.getY(), 2);
                    } else {
                        return new Pos(pos.getX() + 3, pos.getY(), 2);
                    }
                } else {
                    if (i == 1) {
                        return new Pos(pos.getX(), pos.getY() - 1, 2);
                    } else if (i == 2) {
                        return new Pos(pos.getX(), pos.getY() + 1, 2);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 1, pos.getY(), 2);
                    } else {
                        return new Pos(pos.getX() + 1, pos.getY(), 2);
                    }
                }
            case "1001":
                random = new Random();
                if (random.nextInt(2) == 1) {
                    if (i == 1) {
                        return new Pos(pos.getX(), pos.getY() - 2, 0.8);
                    } else if (i == 2) {
                        return new Pos(pos.getX(), pos.getY() + 2, 0.8);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 2, pos.getY(), 0.8);
                    } else {
                        return new Pos(pos.getX() +2, pos.getY(), 0.8);
                    }
                } else {
                    if (i == 1) {
                        return new Pos(pos.getX(), pos.getY() - 1, 0.8);
                    } else if (i == 2) {
                        return new Pos(pos.getX(), pos.getY() + 1, 0.8);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 1, pos.getY(), 0.8);
                    } else {
                        return new Pos(pos.getX() + 1, pos.getY(), 0.8);
                    }
                }
            case "10010":
                random = new Random();
                if (random.nextInt(2) == 1) {
                    if (i == 1) {
                        return new Pos(pos.getX(), pos.getY() - 2, 2);
                    } else if (i == 2) {
                        return new Pos(pos.getX(), pos.getY() + 2, 2);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 2, pos.getY(), 2);
                    } else {
                        return new Pos(pos.getX() +2, pos.getY(), 2);
                    }
                } else {
                    if (i == 1) {
                        return new Pos(pos.getX(), pos.getY() - 1, 2);
                    } else if (i == 2) {
                        return new Pos(pos.getX(), pos.getY() + 1, 2);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 1, pos.getY(), 2);
                    } else {
                        return new Pos(pos.getX() + 1, pos.getY(), 2);
                    }
                }
            case "10101":
                random = new Random();
                if (random.nextInt(2) == 1) {
                    if (i == 1) {
                        return new Pos(pos.getX(), pos.getY() - 3, 3);
                    } else if (i == 2) {
                        return new Pos(pos.getX(), pos.getY() + 3, 3);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 3, pos.getY(), 3);
                    } else {
                        return new Pos(pos.getX() + 3, pos.getY(), 3);
                    }
                } else {
                    if (i == 1) {
                        return new Pos(pos.getX(), pos.getY() - 1, 3);
                    } else if (i == 2) {
                        return new Pos(pos.getX(), pos.getY() + 1, 3);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 1, pos.getY(), 3);
                    } else {
                        return new Pos(pos.getX() + 1, pos.getY(), 3);
                    }
                }
            case "1110":
                if (i == 1) {
                    return new Pos(pos.getX(), pos.getY() - 3, 0.9);
                } else if (i == 2) {
                    return new Pos(pos.getX(), pos.getY() + 3, 0.9);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 3, pos.getY(), 0.9);
                } else {
                    return new Pos(pos.getX() + 3, pos.getY(), 0.9);
                }
            case "11100":
                if (i == 1) {
                    return new Pos(pos.getX(), pos.getY() - 3, 4);
                } else if (i == 2) {
                    return new Pos(pos.getX(), pos.getY() + 3, 4);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 3, pos.getY(), 4);
                } else {
                    return new Pos(pos.getX() + 3, pos.getY(), 4);
                }
            case "1101":
                if (i == 1) {
                    return new Pos(pos.getX(), pos.getY() - 2, 0.8);
                } else if (i == 2) {
                    return new Pos(pos.getX(), pos.getY() + 2, 0.8);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 2, pos.getY(), 0.8);
                } else {
                    return new Pos(pos.getX() + 2, pos.getY(), 0.8);
                }
            case "11010":
                if (i == 1) {
                    return new Pos(pos.getX(), pos.getY() - 2, 4);
                } else if (i == 2) {
                    return new Pos(pos.getX(), pos.getY() + 2, 4);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 2, pos.getY(), 4);
                } else {
                    return new Pos(pos.getX() + 2, pos.getY(), 4);
                }
            case "1011":
                if (i == 1) {
                    return new Pos(pos.getX(), pos.getY() - 1, 0.8);
                } else if (i == 2) {
                    return new Pos(pos.getX(), pos.getY() + 1, 0.8);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 1, pos.getY(), 0.8);
                } else {
                    return new Pos(pos.getX() + 1, pos.getY(), 0.8);
                }
            case "10110":
                if (i == 1) {
                    return new Pos(pos.getX(), pos.getY() - 1, 4);
                } else if (i == 2) {
                    return new Pos(pos.getX(), pos.getY() + 1, 4);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 1, pos.getY(), 4);
                } else {
                    return new Pos(pos.getX() + 1, pos.getY(), 4);
                }
            case "11110":
                if (i == 1) {
                    return new Pos(pos.getX(), pos.getY() - 4, 8);
                } else if (i == 2) {
                    return new Pos(pos.getX(), pos.getY() + 4, 8);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 4, pos.getY(), 8);
                } else {
                    return new Pos(pos.getX() + 4, pos.getY(), 8);
                }
            case "11101":
                if (i == 1) {
                    return new Pos(pos.getX(), pos.getY() - 3, 8);
                } else if (i == 2) {
                    return new Pos(pos.getX(), pos.getY() + 3, 8);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 3, pos.getY(), 8);
                } else {
                    return new Pos(pos.getX() + 3, pos.getY(), 8);
                }
            case "11011":
                if (i == 1) {
                    return new Pos(pos.getX(), pos.getY() - 2, 8);
                } else if (i == 2) {
                    return new Pos(pos.getX(), pos.getY() + 2, 8);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 2, pos.getY(), 8);
                } else {
                    return new Pos(pos.getX() + 2, pos.getY(), 8);
                }
            case "10111":
                if (i == 1) {
                    return new Pos(pos.getX(), pos.getY() - 1, 8);
                } else if (i == 2) {
                    return new Pos(pos.getX(), pos.getY() + 1, 8);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 1, pos.getY(), 8);
                } else {
                    return new Pos(pos.getX() + 1, pos.getY(), 8);
                }
            case "11111":
                return new Pos(0,0,100);
            default:
                return new Pos(new Random().nextInt(15),new Random().nextInt(15),1);
        }
    }

    //1左上2右下3左下4右上
    public static Pos returnPos1(String s,Pos pos,int i) {
        switch (s) {
            case "10":
                if (i == 1) {
                    return new Pos(pos.getX()-1,pos.getY()-1,0.1);
                } else if (i == 2) {
                    return new Pos(pos.getX()+1,pos.getY()+1,0.1);
                } else if (i == 3) {
                    return new Pos(pos.getX()-1,pos.getY()+1,0.1);
                } else {
                    return new Pos(pos.getX()+1,pos.getY()-1,0.1);
                }
            case "100":
                if (i == 1) {
                    return new Pos(pos.getX()-1,pos.getY()-1,0.1);
                } else if (i == 2) {
                    return new Pos(pos.getX()+1,pos.getY()+1,0.1);
                } else if (i == 3) {
                    return new Pos(pos.getX()-1,pos.getY()+1,0.1);
                } else {
                    return new Pos(pos.getX()+1,pos.getY()-1,0.1);
                }
            case "1000":
                if (i == 1) {
                    return new Pos(pos.getX()-1,pos.getY()-1,0.6);
                } else if (i == 2) {
                    return new Pos(pos.getX()+1,pos.getY()+1,0.6);
                } else if (i == 3) {
                    return new Pos(pos.getX()-1,pos.getY()+1,0.6);
                } else {
                    return new Pos(pos.getX()+1,pos.getY()-1,0.6);
                }
            case "10000":
                if (i == 1) {
                    return new Pos(pos.getX()-1,pos.getY()-1,1);
                } else if (i == 2) {
                    return new Pos(pos.getX()+1,pos.getY()+1,1);
                } else if (i == 3) {
                    return new Pos(pos.getX()-1,pos.getY()+1,1);
                } else {
                    return new Pos(pos.getX()+1,pos.getY()-1,1);
                }
            case "110":
                if (i == 1) {
                    return new Pos(pos.getX() - 2,pos.getY() - 2, 0.2);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 2,pos.getY() + 2, 0.2);
                } else if (i == 3) {
                    return new Pos(pos.getX()-2,pos.getY(),0.2);
                } else {
                    return new Pos(pos.getX()+2,pos.getY(),0.2);
                }
            case "1100":
                if (i == 1) {
                    return new Pos(pos.getX() - 2,pos.getY() - 2, 0.3);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 2,pos.getY() + 2, 0.3);
                } else if (i == 3) {
                    return new Pos(pos.getX()-2,pos.getY(),0.3);
                } else {
                    return new Pos(pos.getX()+2,pos.getY(),0.3);
                }
            case "11000":
                if (i == 1) {
                    return new Pos(pos.getX() - 2,pos.getY() - 2, 2);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 2,pos.getY() + 2, 2);
                } else if (i == 3) {
                    return new Pos(pos.getX()-2,pos.getY(),2);
                } else {
                    return new Pos(pos.getX()+2,pos.getY(),2);
                }
            case "101":
                if (i == 1) {
                    return new Pos(pos.getX() - 1, pos.getY() - 1, 0.8);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 1, pos.getY() + 1, 0.8);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 1, pos.getY() + 1, 0.8);
                } else {
                    return new Pos(pos.getX() + 1, pos.getY()-1, 0.8);
                }
            case "1010":
                Random random = new Random();
                if (random.nextInt(2) == 1) {
                    if (i == 1) {
                        return new Pos(pos.getX() - 3, pos.getY() - 3, 0.5);
                    } else if (i == 2) {
                        return new Pos(pos.getX() + 3, pos.getY() + 3, 0.5);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 3, pos.getY()+3, 0.5);
                    } else {
                        return new Pos(pos.getX() + 3, pos.getY()-3, 0.5);
                    }
                } else {
                    if (i == 1) {
                        return new Pos(pos.getX() - 1, pos.getY() - 1, 0.5);
                    } else if (i == 2) {
                        return new Pos(pos.getX() + 1, pos.getY() + 1, 0.5);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 1, pos.getY()+1, 0.5);
                    } else {
                        return new Pos(pos.getX() + 1, pos.getY()-1, 0.5);
                    }
                }
            case "10100":
                random = new Random();
                if (random.nextInt(2) == 1) {
                    if (i == 1) {
                        return new Pos(pos.getX() - 3, pos.getY() - 3, 2);
                    } else if (i == 2) {
                        return new Pos(pos.getX() + 3, pos.getY() + 3, 2);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 3, pos.getY()+3, 2);
                    } else {
                        return new Pos(pos.getX() + 3, pos.getY()-3, 2);
                    }
                } else {
                    if (i == 1) {
                        return new Pos(pos.getX() - 1, pos.getY() - 1, 2);
                    } else if (i == 2) {
                        return new Pos(pos.getX() + 1, pos.getY() + 1, 2);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 1, pos.getY()+1, 2);
                    } else {
                        return new Pos(pos.getX() + 1, pos.getY()-1, 2);
                    }
                }
            case "1001":
                random = new Random();
                if (random.nextInt(2) == 1) {
                    if (i == 1) {
                        return new Pos(pos.getX() - 2, pos.getY() - 2, 0.2);
                    } else if (i == 2) {
                        return new Pos(pos.getX() + 2, pos.getY() + 2, 0.2);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 2, pos.getY()+2, 0.2);
                    } else {
                        return new Pos(pos.getX() +2, pos.getY()-2, 0.2);
                    }
                } else {
                    if (i == 1) {
                        return new Pos(pos.getX() - 1, pos.getY() - 1, 0.2);
                    } else if (i == 2) {
                        return new Pos(pos.getX() + 1, pos.getY() + 1, 0.2);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 1, pos.getY()+1, 0.2);
                    } else {
                        return new Pos(pos.getX() + 1, pos.getY()-1, 0.2);
                    }
                }
            case "10010":
                random = new Random();
                if (random.nextInt(2) == 1) {
                    if (i == 1) {
                        return new Pos(pos.getX() - 2, pos.getY() - 2, 2);
                    } else if (i == 2) {
                        return new Pos(pos.getX() + 2, pos.getY() + 2, 2);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 2, pos.getY()+2, 2);
                    } else {
                        return new Pos(pos.getX() +2, pos.getY()-2, 2);
                    }
                } else {
                    if (i == 1) {
                        return new Pos(pos.getX() - 1, pos.getY() - 1, 2);
                    } else if (i == 2) {
                        return new Pos(pos.getX() + 1, pos.getY() + 1, 2);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 1, pos.getY()+1, 2);
                    } else {
                        return new Pos(pos.getX() + 1, pos.getY()-1, 2);
                    }
                }
            case "10101":
                random = new Random();
                if (random.nextInt(2) == 1) {
                    if (i == 1) {
                        return new Pos(pos.getX() - 3, pos.getY() - 3, 3);
                    } else if (i == 2) {
                        return new Pos(pos.getX() + 3, pos.getY() + 3, 3);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 3, pos.getY()+3, 3);
                    } else {
                        return new Pos(pos.getX() + 3, pos.getY()-3, 3);
                    }
                } else {
                    if (i == 1) {
                        return new Pos(pos.getX() - 1, pos.getY() - 1, 3);
                    } else if (i == 2) {
                        return new Pos(pos.getX() + 1, pos.getY() + 1, 3);
                    } else if (i == 3) {
                        return new Pos(pos.getX() - 1, pos.getY()+1, 3);
                    } else {
                        return new Pos(pos.getX() + 1, pos.getY()-1, 3);
                    }
                }
            case "1110":
                if (i == 1) {
                    return new Pos(pos.getX() - 3, pos.getY() - 3, 0.4);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 3, pos.getY() + 3, 0.4);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 3, pos.getY()+3, 0.4);
                } else {
                    return new Pos(pos.getX() + 3, pos.getY()-3, 0.4);
                }
            case "11100":
                if (i == 1) {
                    return new Pos(pos.getX() - 3, pos.getY() - 3, 4);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 3, pos.getY() + 3, 4);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 3, pos.getY()+3, 4);
                } else {
                    return new Pos(pos.getX() + 3, pos.getY()-3, 4);
                }
            case "1101":
                if (i == 1) {
                    return new Pos(pos.getX() - 2, pos.getY() - 2, 0.4);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 2, pos.getY() + 2, 0.4);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 2, pos.getY()+2, 0.4);
                } else {
                    return new Pos(pos.getX() + 2, pos.getY()-2, 0.4);
                }
            case "11010":
                if (i == 1) {
                    return new Pos(pos.getX() - 2, pos.getY() - 2, 4);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 2, pos.getY() + 2, 4);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 2, pos.getY()+2, 4);
                } else {
                    return new Pos(pos.getX() + 2, pos.getY()-2, 4);
                }
            case "1011":
                if (i == 1) {
                    return new Pos(pos.getX() - 1, pos.getY() - 1, 0.4);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 1, pos.getY() + 1, 0.4);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 1, pos.getY()+1, 0.4);
                } else {
                    return new Pos(pos.getX() + 1, pos.getY()-1, 0.4);
                }
            case "10110":
                if (i == 1) {
                    return new Pos(pos.getX() - 1, pos.getY() - 1, 4);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 1, pos.getY() + 1, 4);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 1, pos.getY()+1, 4);
                } else {
                    return new Pos(pos.getX() + 1, pos.getY()-1, 4);
                }
            case "11110":
                if (i == 1) {
                    return new Pos(pos.getX() - 4, pos.getY() - 4, 8);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 4, pos.getY() + 4, 8);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 4, pos.getY()+4, 8);
                } else {
                    return new Pos(pos.getX() + 4, pos.getY()-4, 8);
                }
            case "11101":
                if (i == 1) {
                    return new Pos(pos.getX() - 3, pos.getY() - 3, 8);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 3, pos.getY() + 3, 8);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 3, pos.getY()+3, 8);
                } else {
                    return new Pos(pos.getX() + 3, pos.getY()-3, 8);
                }
            case "11011":
                if (i == 1) {
                    return new Pos(pos.getX() - 2, pos.getY() - 2, 8);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 2, pos.getY() + 2, 8);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 2, pos.getY()+2, 8);
                } else {
                    return new Pos(pos.getX() + 2, pos.getY()-2, 8);
                }
            case "10111":
                if (i == 1) {
                    return new Pos(pos.getX() - 1, pos.getY() - 1, 8);
                } else if (i == 2) {
                    return new Pos(pos.getX() + 1, pos.getY() + 1, 8);
                } else if (i == 3) {
                    return new Pos(pos.getX() - 1, pos.getY()+1, 8);
                } else {
                    return new Pos(pos.getX() + 1, pos.getY()-1, 8);
                }
            case "11111":
                return new Pos(0,0,100);
            default:
                return new Pos(new Random().nextInt(15),new Random().nextInt(15),1);
        }
    }
}

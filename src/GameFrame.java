import control.GameProcess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame {
    JFrame gameFrame = new JFrame("生命游戏");
    JPanel operatePanel;
    JPanel viewPanel;
    GameProcess gameControl;
    boolean isRunning = false;
    int multipyCycle = 2;
    int multipyCount = 0;
    int row = 10;
    int col = 10;
    int[][] cells = new int[10][10];
    int sideLength = 20;
    int adjustLength = 50;
    JTextField multipyCountField;
    JButton changeButton;
    JButton reStartButton;
    JButton startButton;

    public static void main(String[] args) {
        new GameFrame();
    }

    public GameFrame() {
        this.gameFrame.setResizable(false);
        Dimension screnSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1200;
        int height = 800;
        this.gameFrame.setBounds((screnSize.width - width) / 2, (screnSize.height - height) / 2, width, height);
        this.gameFrame.setLayout((LayoutManager)null);
        this.gameControl = new GameProcess(this.row, this.col);
        this.cells = this.gameControl.getAllCellStatus();
        this.operatePanel = this.getOperatePanel();
        this.viewPanel = this.getViewPanel();
        this.gameFrame.add(this.viewPanel);
        this.gameFrame.add(this.operatePanel);
        this.gameFrame.setVisible(true);
        this.gameFrame.addMouseListener(new GameFrame.ClickMonitor());
    }

    private JPanel getOperatePanel() {
        JPanel operatePanel = new JPanel();
        operatePanel.setBounds(800, 0, 360, 800);
        operatePanel.setLayout(new GridLayout(12, 1, 10, 10));
        JPanel mapWidthPanel = new JPanel();
        JLabel mapWidthLabel = new JLabel("地图行数:");
        final JTextField mapWidthFiled = new JTextField(20);
        mapWidthFiled.setText(String.valueOf(this.col));
        mapWidthPanel.add(mapWidthLabel);
        mapWidthPanel.add(mapWidthFiled);
        JPanel mapLengthPanel = new JPanel();
        JLabel mapLengthLabel = new JLabel("地图列数:");
        final JTextField mapLengthField = new JTextField(20);
        mapLengthField.setText(String.valueOf(this.row));
        mapLengthPanel.add(mapLengthLabel);
        mapLengthPanel.add(mapLengthField);
        JPanel multipyCountPanel = new JPanel();
        JLabel multipyCountLabel = new JLabel("繁殖次数:");
        this.multipyCountField = new JTextField(20);
        this.multipyCountField.setText(String.valueOf(this.multipyCount));
        this.multipyCountField.setEditable(false);
        multipyCountPanel.add(multipyCountLabel);
        multipyCountPanel.add(this.multipyCountField);
        JPanel multipyCycPanel = new JPanel();
        JLabel multipyCycLabel = new JLabel("繁殖周期（s）:");
        final JTextField multipyCycField = new JTextField(20);
        multipyCycField.setText(String.valueOf(this.multipyCycle));
        multipyCycPanel.add(multipyCycLabel);
        multipyCycPanel.add(multipyCycField);
        this.changeButton = new JButton("修改设置");
        this.changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameFrame.this.clearPaint(GameFrame.this.gameFrame.getGraphics());
                GameFrame.this.row = Integer.parseInt(mapWidthFiled.getText());
                GameFrame.this.col = Integer.parseInt(mapLengthField.getText());
                GameFrame.this.multipyCycle = Integer.parseInt(multipyCycField.getText());
                GameFrame.this.multipyCount = 0;
                GameFrame.this.multipyCountField.setText(String.valueOf(GameFrame.this.multipyCount));
                GameFrame.this.gameControl = new GameProcess(GameFrame.this.row, GameFrame.this.col);
                GameFrame.this.paintGirdLines(GameFrame.this.gameFrame.getGraphics());
            }
        });
        this.reStartButton = new JButton("生成地图");
        this.reStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameFrame.this.gameControl.setDefaultWorld();
                GameFrame.this.cells = GameFrame.this.gameControl.getAllCellStatus();
                GameFrame.this.paintGirdLines(GameFrame.this.gameFrame.getGraphics());
                GameFrame.this.paintCells(GameFrame.this.gameFrame.getGraphics());
            }
        });
        this.startButton = new JButton("开始繁殖ֳ");
        this.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                (GameFrame.this.new MultipyThread()).start();
            }
        });
        JButton stopButton = new JButton("停止繁殖ֳ");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameFrame.this.isRunning = false;
                GameFrame.this.changeButton.setEnabled(true);
                GameFrame.this.reStartButton.setEnabled(true);
                GameFrame.this.startButton.setEnabled(true);
            }
        });
        JLabel spaceLabel = new JLabel("");
        operatePanel.add(spaceLabel);
        operatePanel.add(spaceLabel);
        operatePanel.add(mapWidthPanel);
        operatePanel.add(mapLengthPanel);
        operatePanel.add(multipyCountPanel);
        operatePanel.add(multipyCycPanel);
        operatePanel.add(this.changeButton);
        operatePanel.add(this.reStartButton);
        operatePanel.add(this.startButton);
        operatePanel.add(stopButton);
        return operatePanel;
    }

    private JPanel getViewPanel() {
        JPanel viewPanel = new JPanel();
        viewPanel.setBounds(0, 0, 800, 800);
        return viewPanel;
    }

    public void clearPaint(Graphics g) {
        g.setColor(new Color(238, 238, 238));

        for(int i = 0; i <= this.row; ++i) {
            for(int j = 0; j <= this.col; ++j) {
                g.fillRect(j * this.sideLength + this.adjustLength, i * this.sideLength + this.adjustLength, this.sideLength, this.sideLength);
            }
        }

    }

    public void paintGirdLines(Graphics g) {  //绘制网格线
        g.setColor(new Color(0, 0, 0));

        int i;
        for(i = 0; i <= this.row; ++i) {
            g.drawLine(this.adjustLength, i * this.sideLength + this.adjustLength, this.col * this.sideLength + this.adjustLength, i * this.sideLength + this.adjustLength);
        }

        for(i = 0; i <= this.col; ++i) {
            g.drawLine(i * this.sideLength + this.adjustLength, this.adjustLength, i * this.sideLength + this.adjustLength, this.row * this.sideLength + this.adjustLength);
        }

    }

    public void paintCells(Graphics g) {
        g.setColor(new Color(0, 0, 0));

        for(int i = 0; i < this.row; ++i) {
            for(int j = 0; j < this.col; ++j) {
                if (1 == this.cells[i][j]) {
                    g.fillRect(j * this.sideLength + this.adjustLength, i * this.sideLength + this.adjustLength, this.sideLength, this.sideLength);
                }
            }
        }

    }

    public void createCell(Graphics g, int row, int col) {
        g.setColor(new Color(0, 0, 0));
        GameFrame.this.cells[row][col] = 1;
        g.fillRect(col * this.sideLength + this.adjustLength, row * this.sideLength + this.adjustLength, this.sideLength, this.sideLength);
    }

    public void killCell(Graphics g, int row, int col) {
        g.setColor(new Color(238, 238, 238));
        GameFrame.this.cells[row][col] = 0;
        g.fillRect(col * this.sideLength + this.adjustLength, row * this.sideLength + this.adjustLength, this.sideLength, this.sideLength);
        /*this.clearPaint(g);*/
    }

    class ClickMonitor implements MouseListener {
        ClickMonitor() {
        }

        public void mouseClicked(MouseEvent e) {
            int point_x = e.getX();
            int point_y = e.getY();
            if (point_x >= GameFrame.this.adjustLength && point_x <= GameFrame.this.col * GameFrame.this.sideLength + GameFrame.this.adjustLength && point_y >= GameFrame.this.adjustLength && point_y <= GameFrame.this.row * GameFrame.this.sideLength + GameFrame.this.adjustLength) {
                int col = (point_x - GameFrame.this.adjustLength) / GameFrame.this.sideLength;
                int row = (point_y - GameFrame.this.adjustLength) / GameFrame.this.sideLength;
                boolean beforeStatus = GameFrame.this.gameControl.changeCellStatus(row, col);
                if (beforeStatus) {
                    GameFrame.this.killCell(GameFrame.this.gameFrame.getGraphics(), row, col);
                } else {
                    GameFrame.this.createCell(GameFrame.this.gameFrame.getGraphics(), row, col);
                }

            }
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    class MultipyThread extends Thread {
        MultipyThread() {
        }

        public void run() {
            GameFrame.this.isRunning = true;
            GameFrame.this.changeButton.setEnabled(false);
            GameFrame.this.reStartButton.setEnabled(false);
            GameFrame.this.startButton.setEnabled(false);

            while(GameFrame.this.isRunning) {
                GameFrame.this.gameControl.cellMultiply();
                GameFrame.this.cells = GameFrame.this.gameControl.getAllCellStatus();
                GameFrame.this.clearPaint(GameFrame.this.gameFrame.getGraphics());
                GameFrame.this.paintGirdLines(GameFrame.this.gameFrame.getGraphics());
                GameFrame.this.paintCells(GameFrame.this.gameFrame.getGraphics());
                ++GameFrame.this.multipyCount;
                GameFrame.this.multipyCountField.setText(String.valueOf(GameFrame.this.multipyCount));

                try {
                    Thread.sleep((long)(GameFrame.this.multipyCycle * 1000));
                } catch (InterruptedException var2) {
                    var2.printStackTrace();
                }
            }

        }
    }
}

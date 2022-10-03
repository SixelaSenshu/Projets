import javax.swing.*;
import java.awt.*;

public class InterfaceMemory {

    private JFrame window;
    private Dimension dimWindow;
    private JMenuBar nav;
    private ModeleMemory memory = new ModeleMemory(4,4);
    private JButton[][] board;

    public InterfaceMemory(){
        this.window = new JFrame("Memory");
        this.board = new JButton[this.memory.getRows()][this.memory.getColumns()];
        this.setDisplay();
    }

    public void setWindow(){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dimWindow = window.getToolkit().getScreenSize();
        window.setLocationRelativeTo(null);
		window.setLocation((dimWindow.width - window.getWidth()) / 2,(dimWindow.height - window.getHeight()) / 2);
        window.setSize(this.dimWindow.width,this.dimWindow.height);
        window.setMinimumSize(new Dimension(450,450));
        window.setMaximumSize(new Dimension(1000,1000));
        window.setPreferredSize(new Dimension(800,800));
        window.setVisible(true); 
    }

    public void setJMenuItem(){
        this.nav = new JMenuBar();
        JMenu option = new JMenu("Option");
        JMenu help = new JMenu("Help");
        JMenuItem reinit = new JMenuItem("Reinit");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem assitance = new JMenuItem("Assistance");
        option.add(reinit);
        option.add(exit);
        help.add(assitance);
        this.nav.add(option);
        this.nav.add(help);
        this.window.setJMenuBar(this.nav);
    }

    public void setBoard(){
        //JPanel containerPanel = new JPanel();
        Container cont = window.getContentPane();
        cont.setLayout(new GridLayout(this.memory.getRows(),this.memory.getColumns()));
        for(int x = 0; x < this.memory.getRows(); x++){
            for(int y = 0; y < this.memory.getColumns(); y++){
                this.board[x][y] = new JButton(new ImageIcon(this.memory.getBoard(x,y).getUrl()));
                cont.add(this.board[x][y]);
                this.board[x][y].setSize(25, 25);
            }
        }
       // containerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
       // containerPanel.setLayout(new BorderLayout());
       // this.window.getContentPane().add(containerPanel, BorderLayout.CENTER);
        this.window.pack();
    }


    public void setDisplay(){
        this.setWindow();
        this.setBoard();
        this.setJMenuItem();
    }

    public static void main(String[] args){
        InterfaceMemory main = new InterfaceMemory();
    }
}

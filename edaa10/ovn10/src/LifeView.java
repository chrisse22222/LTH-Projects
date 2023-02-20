import java.awt.*;
import java.awt.event.*;

public class LifeView extends Frame {	
	private static final long serialVersionUID = 1L;
	private LifeBoard board;
	private int lastCommand;
	private int lastRow,lastCol;
	private SWEventBuffer eventQueue;
	private Button nextButton,quitButton;
	private Label generationLabel;
	private Square[][] squares;
	private int rows,cols;

	/** Skapar ett LifeView-fšnster fšr spelplanen board */
	public LifeView(LifeBoard board) {
		super("Life");
		this.board = board;
		rows = board.getRows();
		cols = board.getCols();
		eventQueue = new SWEventBuffer();

		Panel grid = new Panel();
		grid.setLayout(new GridLayout(rows,cols));
		SquareHandler sqhand = new SquareHandler();
		squares = new Square[rows][cols];
		for (int i = 0; i<rows; i++)
			for (int k = 0; k<cols; k++) {
				squares[i][k]= new Square(i,k);
				grid.add(squares[i][k]);
				squares[i][k].addMouseListener(sqhand);
			}
		add("Center",grid);

		Panel buttonPanel = new Panel();
		GridBagLayout gridbag = new GridBagLayout();
		buttonPanel.setLayout(gridbag);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		ButtonHandler bhand = new ButtonHandler();

		nextButton = new Button("Next");
		nextButton.addActionListener(bhand);
		buttonPanel.add(nextButton);
		gridbag.setConstraints(nextButton,gbc);

		quitButton = new Button("Quit");
		quitButton.addActionListener(bhand);
		buttonPanel.add(quitButton);
		gridbag.setConstraints(quitButton,gbc);

		generationLabel = new Label("Generation: " + board.getGeneration() + "  ");
		buttonPanel.add(generationLabel);
		gridbag.setConstraints(generationLabel,gbc);

		add("East",buttonPanel);

		add("North", new Label(""));
		add("West", new Label(""));
		add("South", new Label(""));

		addWindowListener(new WindowEventHandler());

		setBackground(Color.white);
		pack();
		setVisible(true);
	}

	/** Ritar upp de fixa delarna av spelplanen (rutnŠtet,
      generationsrŠknaren och knapparna) */
	public void drawBoard() {
		repaint();
	}

	/** Ritar om de delar av ritfšnstret som Šndrats sedan fšregŒende uppritning */
	public void update() {
		repaint();
		for (int i = 0; i<rows; i++)
			for (int k = 0; k<cols; k++)
				if (squares[i][k].isChanged())
					squares[i][k].repaint();
	}

	/** VŠntar tills anvŠndaren klickar med musen. Ger:
      1: Klick i ruta på spelplanen. Index fšr rutan kan hŠmtas med getRow
			och getCol
      2: Klick i Next-rutan
      3: Klick i Quit-rutan */
	public int getCommand() {
		AWTEvent lastEvent = eventQueue.getNextEvent();
		if (lastEvent.getSource() instanceof Square) {
			lastRow = ((Square) lastEvent.getSource()).x;
			lastCol = ((Square) lastEvent.getSource()).y;
			lastCommand = 1;
		}
		else if (lastEvent.getSource()==nextButton)
			lastCommand = 2;
		else 
			lastCommand = 3;
		return lastCommand;
	}

	/** Tar reda på radnummer fšr den klickade rutan efter kommando nr 1 */
	public int getRow() {
		return lastRow;
	}

	/** Tar reda på kolonnummer fšr den klickade rutan efter kommando nr 1 */
	public int getCol() {
		return lastCol;
	}

	/*--------------------------------------------------*/

	public void paint(Graphics g) {
		generationLabel.setText("Generation: " + board.getGeneration() + "  ");
	}

	class Square extends Canvas {
		private static final long serialVersionUID = 1L;
		int x,y;
		private boolean filled;

		public Square(int ix, int iy) {
			x = ix;
			y = iy;
		}

		public Dimension getPreferredSize() {
			return new Dimension(15,15);
		}

		public boolean isChanged() {
			return filled != board.get(x,y);
		}

		public void paint(Graphics g) {
			Dimension d = getSize();
			g.drawLine(0,0,0,d.height-1);
			g.drawLine(0,d.height-1,d.width-1,d.height-1);
			g.drawLine(d.width-1,d.height-1,d.width-1,0);
			g.drawLine(d.width-1,0,0,0);
			filled = board.get(x,y);
			if (filled)
				g.fillOval(2,2,d.width-4,d.height-4);
		}
	}

	class SquareHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			eventQueue.postEvent(e);
		}
	}

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			eventQueue.postEvent(e);
		}
	}

	class WindowEventHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
}

class SWEventBuffer {
	private AWTEvent bufferedEvent;

	public synchronized void postEvent(AWTEvent e) {
		while (bufferedEvent!=null) {
			try {
				wait();
			} catch (InterruptedException ex) { }
		}
		bufferedEvent = e;
		notifyAll();
	}

	public synchronized AWTEvent getNextEvent() {
		AWTEvent e;
		while (bufferedEvent==null) {
			try {
				wait();
			} catch (InterruptedException ex) { }
		}
		e = bufferedEvent;
		bufferedEvent = null;
		notifyAll();
		return e;
	}
}

package view;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * The Class ViewPanel.
 *
 * @author Jean-Aymeric Diet
 */
class ViewPanel extends JPanel implements Observer {

	/** The view frame. */
	private ViewFrame					viewFrame;
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -998294702363713521L;

	/**
	 * Instantiates a new view panel.
	 *
	 * @param viewFrame
	 *          the view frame
	 */
	public ViewPanel(final ViewFrame viewFrame) {
		this.setViewFrame(viewFrame);
		viewFrame.getModel().getObservable().addObserver(this);
	}

	/**
	 * Gets the view frame.
	 *
	 * @return the view frame
	 */
	private ViewFrame getViewFrame() {
		return this.viewFrame;
	}

	/**
	 * Sets the view frame.
	 *
	 * @param viewFrame
	 *          the new view frame
	 */
	private void setViewFrame(final ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable arg0, final Object arg1) {
		this.repaint();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(final Graphics graphics) {
		Graphics2D g2 = (Graphics2D)graphics;
		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		//graphics.drawString(this.getViewFrame().getModel().getMap().getSchema(), 10, 20);

		// for each line
		int posY = 0;
		for(int i = 0;i<viewFrame.getModel().getMap().getNline();i++){
			String lineToWrite = viewFrame.getModel().getMap().getSchema(i);
			int posX = 0;
			for(char c : lineToWrite.toCharArray()){
				switch (c){
					// Wall
					case 'X':
						graphics.drawImage(viewFrame.getModel().getMap().getSprites(0),posX,posY, this);
						break;
					// Dirt
					case 'D':
						graphics.drawImage(viewFrame.getModel().getMap().getSprites(1),posX,posY, this);
						break;
					// Empty
					case 'V' :
						graphics.drawImage(viewFrame.getModel().getMap().getSprites(2),posX,posY, this);
						break;
					// Rock
					case 'C':
						graphics.drawImage(viewFrame.getModel().getMap().getSprites(3),posX,posY, this);
						break;
				}
				posX+=16;
			}
			posY+=16;
		}
	}

}

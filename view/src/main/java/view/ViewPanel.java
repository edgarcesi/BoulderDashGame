package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import entity.Block;
import entity.BlockType;
import entity.Map;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

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

	private final Font font;

	/**
	 * Instantiates a new view panel.
	 *
	 * @param viewFrame
	 *          the view frame
	 */
	public ViewPanel(final ViewFrame viewFrame) {
		this.setViewFrame(viewFrame);
		viewFrame.getModel().getObservable().addObserver(this);
		font = new Font("Monaco",Font.BOLD, 14);
		Thread loop = new Thread(() -> {
			while (true){
				this.repaint();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		loop.setDaemon(true);
		loop.start();
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
		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());

		// Draw blocks
		Map map = viewFrame.getModel().getMap();
		Block[][] block = map.getBlocks();
        //for each blocks in the map
        for(int y = 0; y<map.getHeight(); y++){
				for(int x = 0;x<map.getLenght();x++){
					graphics.drawImage(block[y][x].getSprites(), block[y][x].getPosX(), block[y][x].getPosY(), this);
				}
			}

        // Draw player
        graphics.drawImage(viewFrame.getModel().getPlayer().getSprites(), viewFrame.getModel().getPlayer().getPosX(),viewFrame.getModel().getPlayer().getPosY(), this);

		graphics.setFont(font);
		// Write score
        graphics.setColor(Color.blue);
        graphics.drawString("Score : " + viewFrame.getModel().getPlayer().getScore(), (viewFrame.getWidth()/2),viewFrame.getHeight()-45);
		// Write time
        graphics.setColor(Color.red);
        graphics.drawString("Temps : " + viewFrame.getModel().getTime(),(viewFrame.getWidth()/2)-100,viewFrame.getHeight()-45);
	}

}

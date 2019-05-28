package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import entity.Block;
import entity.BlockType;
import entity.Map;
import entity.Player;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

/**
 * The Class ViewPanel.
 *
 * @author Jean-Aymeric Diet
 */
class ViewPanel extends JPanel implements Observer {
	int k = 0;
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
		Thread loop = new Thread(() -> {
			while (true){
				this.repaint();
				try {
					Thread.sleep(200l);
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
		Block[][] blocks = map.getBlocks();
        //for each blocks in the map
		int nb = 0;

        for(int y = 0; y<map.getHeight(); y++){
				for(int x = 0;x<map.getLenght();x++){
					switch (blocks[y][x].getType()){
						case WALL:
							graphics.drawImage(viewFrame.getModel().getMap().getSprites(0),blocks[y][x].getPosX(),blocks[y][x].getPosY(), this);
							break;
						case DIRT:
							graphics.drawImage(viewFrame.getModel().getMap().getSprites(1),blocks[y][x].getPosX(),blocks[y][x].getPosY(), this);
							break;
						case EMPTY:
							graphics.drawImage(viewFrame.getModel().getMap().getSprites(2),blocks[y][x].getPosX(),blocks[y][x].getPosY(), this);
							break;
						case ROCK:

								if (blocks[y+1][x].getType().equals(BlockType.EMPTY) && blocks[y+1][x].getType().equals(BlockType.EMPTY) && (viewFrame.getModel().getPlayer().getPosX()/16 != x || viewFrame.getModel().getPlayer().getPosY()/16 != y+1)){
									graphics.drawImage(viewFrame.getModel().getMap().getSprites(2),blocks[y][x].getPosX(),blocks[y][x].getPosY(), this);
									viewFrame.getModel().getMap().TransformToDirt(x, y);
									viewFrame.getModel().getMap().TransformToRock(x, y+1);
								}else if (blocks[y+1][x-1].getType().equals(BlockType.EMPTY) && blocks[y][x-1].getType().equals(BlockType.EMPTY) && (viewFrame.getModel().getPlayer().getPosX()/16 != x || viewFrame.getModel().getPlayer().getPosY()/16 != y+1) && (viewFrame.getModel().getPlayer().getPosX()/16 != x-1 || viewFrame.getModel().getPlayer().getPosY()/16 != y+1) && (viewFrame.getModel().getPlayer().getPosX()/16 != x-1 || viewFrame.getModel().getPlayer().getPosY()/16 != y)){
									graphics.drawImage(viewFrame.getModel().getMap().getSprites(2),blocks[y][x].getPosX(),blocks[y][x].getPosY(), this);
									viewFrame.getModel().getMap().TransformToDirt(x, y);
									viewFrame.getModel().getMap().TransformToRock(x-1, y+1);
								}else if (blocks[y+1][x+1].getType().equals(BlockType.EMPTY) && blocks[y][x+1].getType().equals(BlockType.EMPTY) && (viewFrame.getModel().getPlayer().getPosX()/16 != x || viewFrame.getModel().getPlayer().getPosY()/16 != y+1) && (viewFrame.getModel().getPlayer().getPosX()/16 != x+1 || viewFrame.getModel().getPlayer().getPosY()/16 != y+1) && (viewFrame.getModel().getPlayer().getPosX()/16 != x+1 || viewFrame.getModel().getPlayer().getPosY()/16 != y)){
									graphics.drawImage(viewFrame.getModel().getMap().getSprites(2),blocks[y][x].getPosX(),blocks[y][x].getPosY(), this);
									viewFrame.getModel().getMap().TransformToDirt(x, y);
									viewFrame.getModel().getMap().TransformToRock(x+1, y+1);
								}

								graphics.drawImage(viewFrame.getModel().getMap().getSprites(3),blocks[y][x].getPosX(),blocks[y][x].getPosY(), this);




							break;
						case DIAMOND:
							graphics.drawImage(viewFrame.getModel().getMap().getSprites(4),blocks[y][x].getPosX(),blocks[y][x].getPosY(), this);
							break;
						case END:
							graphics.drawImage(viewFrame.getModel().getMap().getSprites(5),blocks[y][x].getPosX(),blocks[y][x].getPosY(), this);
							break;
						case STAR:
							graphics.drawImage(viewFrame.getModel().getMap().getSprites(6),blocks[y][x].getPosX(),blocks[y][x].getPosY(), this);
							break;
					}
				}
			}


        // Draw player
        graphics.drawImage(viewFrame.getModel().getPlayer().getSprites(), viewFrame.getModel().getPlayer().getPosX(),viewFrame.getModel().getPlayer().getPosY(), this);

		// Draw score

        Font font1=new Font("",Font.BOLD, 14);
        graphics.setFont(font1);
        graphics.setColor(Color.blue);
        graphics.drawString("Score : " + viewFrame.getModel().getPlayer().getScore(), (viewFrame.getWidth()/2),viewFrame.getHeight()-45);
        //delay 50 millisecondes

        Font font2=new Font("",Font.BOLD, 14);
        graphics.setFont(font2);
        graphics.setColor(Color.red);
        graphics.drawString("Temps : " + viewFrame.getModel().getTime(),(viewFrame.getWidth()/2)-100,viewFrame.getHeight()-45);
        k++;
        if (k % 10 == 0){
            repaint();
        }

	}

}

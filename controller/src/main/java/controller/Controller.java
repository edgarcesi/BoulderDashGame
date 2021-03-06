package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;
import entity.*;

import java.awt.*;
import java.awt.geom.Dimension2D;

/**
 * The Class Controller.
 */
public final class Controller implements IController {

	/** The view. */
	private IView		view;

	/** The model. */
	private IModel	model;

	private boolean msgSet = false;

	/**
	 * Instantiates a new controller.
	 *
	 * @param view
	 *          the view
	 * @param model
	 *          the model
	 */
	public Controller(final IView view, final IModel model) {
		this.setView(view);
		this.setModel(model);

		Thread gameEventThread = new Thread(() -> {
			gameEvent();
		});
		gameEventThread.start();
	}

	/**
     * Control.
     */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#control()
	 */
	public void control() {
		this.view.printMessage("Appuyer sur les flèches HAUT, BAS, GAUCHE, DROITE, pour déplacer le personnage.");
	}

	/**
     * Sets the view.
     *
     * @param pview
     *            the new view
     */
	private void setView(final IView pview) {
		this.view = pview;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *          the new model
	 */
	private void setModel(final IModel model) {
		this.model = model;
	}

	/**
     * Order perform.
     *
     * @param controllerOrder
     *            the controller order
     */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#orderPerform(contract.ControllerOrder)
	 */
	public void orderPerform(final ControllerOrder controllerOrder) {

		if(!model.isDead()) {
			// Player move
			Dimension2D movement;
			switch (controllerOrder) {
				case UP:
					// Get next move
					movement = new Dimension(model.IndexPos(model.getPlayer().getPosX()), model.IndexPos(model.getPlayer().getPosY() - 1));
					// If next block is not solid
					if (!model.getMap().isSolid((int) movement.getWidth(), (int) movement.getHeight())) {
						// move player
						model.getPlayer().moveUp();
						// Process block action
						preMove(model.getMap(), movement);
					}
					break;
				case DOWN:
					// Get next move
					movement = new Dimension(model.IndexPos(model.getPlayer().getPosX()), model.IndexPos(model.getPlayer().getPosY()) + 1);
					if (!model.getMap().isSolid((int) movement.getWidth(), (int) movement.getHeight())) {
						// move player
						model.getPlayer().moveDown();
						// Process block action
						preMove(model.getMap(), movement);
					}
					break;
				case LEFT:
					// Get next move
					movement = new Dimension(model.IndexPos(model.getPlayer().getPosX()) - 1, model.IndexPos(model.getPlayer().getPosY()));
					// If next block is not solid
					if (!model.getMap().isSolid((int) movement.getWidth(), (int) movement.getHeight())) {
						// move player
						model.getPlayer().moveLeft();
						// Process block action
						preMove(model.getMap(), movement);
					}
					break;
				case RIGHT:
					// Get next move
					movement = new Dimension(model.IndexPos(model.getPlayer().getPosX()) + 1, model.IndexPos(model.getPlayer().getPosY()));
					// If next block is not solid
					if (!model.getMap().isSolid((int) movement.getWidth(), (int) movement.getHeight())) {
						// move player
						model.getPlayer().moveRight();
						// Process block action
						preMove(model.getMap(), movement);
					}
					break;
				case NOTHING:
					model.getPlayer().setFrame(PlayerSprite.IDLE);
					break;
			}
		}
	}

	public void preMove(Map map, Dimension2D movement){
	    Block block = map.getBlocks((int)movement.getWidth(), (int)movement.getHeight());
	    switch (block.getType()){
            case DIRT:
                block.setType(BlockType.EMPTY);
                break;
            case DIAMOND:
                block.setType(BlockType.EMPTY);
				model.pickDiamond();
                break;
            case END:
                model.setWin(true);
                break;
        }
    }

    public void gameEvent(){
			while (!msgSet) {
				// Win event
				if(model.getWin() && !msgSet) {
					view.playWinMusic();
					view.printMessage("You have win ! "+model.getPlayer().getScore()+" diamonds collected.");
					msgSet = true;
					System.exit(0);
				}
				// Game over event
				if(model.isDead() && !msgSet){
					view.playGameoverMusic();
					view.printMessage("Gameover ! Be careful to rocks...");
					msgSet = true;
					System.exit(0);
				}
				// Time over event
				if(model.getTime()<=0 && !msgSet){
					view.playGameoverMusic();
					view.printMessage("Gameover ! Be faster.");
					msgSet = true;
					System.exit(0);
				}

				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}
}

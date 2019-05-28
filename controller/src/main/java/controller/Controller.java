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

		control();
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
	    // Rock gravity
	    model.getMap().gravity(model.getPlayer());

		// Player move
        Dimension2D movement;
        switch (controllerOrder) {
			case UP:
			    movement = new Dimension(model.IndexPos(model.getPlayer().getPosX()), model.IndexPos(model.getPlayer().getPosY()-1));
                if(!model.getMap().isSolid((int)movement.getWidth(), (int)movement.getHeight())) {
                    preMove(model.getPlayer(), model.getMap(), movement);
                    model.getPlayer().moveUp();
                }
				break;
			case DOWN:
			    movement = new Dimension(model.IndexPos(model.getPlayer().getPosX()),model.IndexPos(model.getPlayer().getPosY())+1);
                if(!model.getMap().isSolid((int)movement.getWidth(), (int)movement.getHeight())) {
                    preMove(model.getPlayer(), model.getMap(), movement);
                    model.getPlayer().moveDown();
                }
				break;
			case LEFT:
                movement = new Dimension(model.IndexPos(model.getPlayer().getPosX())-1,model.IndexPos(model.getPlayer().getPosY()));
                if(!model.getMap().isSolid((int)movement.getWidth(), (int)movement.getHeight())) {
                    preMove(model.getPlayer(), model.getMap(), movement);
                    model.getPlayer().moveLeft();
                }
				break;
			case RIGHT:
                movement = new Dimension(model.IndexPos(model.getPlayer().getPosX())+1,model.IndexPos(model.getPlayer().getPosY()));
                if(!model.getMap().isSolid((int)movement.getWidth(), (int)movement.getHeight())) {
                    preMove(model.getPlayer(), model.getMap(), movement);
                    model.getPlayer().moveRight();
                }
				break;
			case NOTHING:
				model.getPlayer().setFrame(PlayerSprite.IDLE);
				break;
		}

        // Spawn end block
		if(model.getPlayer().getScore()/500>=model.getMap().getDiamond()){
			model.getMap().getBlocks(model.getMap().getEndY(),model.getMap().getEndX()).setType(BlockType.END);
		}
		// Timer
		if(model.getTime()<=0 && !model.isDead()){
			view.printMessage(" PERDU !!!!!vous avez dépassé le temps ");
			model.setDead(true);
			System.exit(0);
		}
	}

	public void preMove(Player player, Map map, Dimension2D movement){
	    Block block = map.getBlocks((int)movement.getWidth(), (int)movement.getHeight());
	    switch (block.getType()){
            case DIRT:
                block.setType(BlockType.EMPTY);
                break;
            case DIAMOND:
                int score = player.getScore();
                block.setType(BlockType.EMPTY);
                player.setScore(score+=500);
                break;
            case END:
                view.printMessage("Félicitation vous avez gagné ! Votre score : "+model.getPlayer().getScore());
                model.setWin(true);
                System.exit(0);
                break;
        }
    }
}

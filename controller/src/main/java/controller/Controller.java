package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;
import entity.BlockType;
import entity.PlayerSprite;

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
		int nextX, nextY;
		BlockType nextBlockType = BlockType.WALL;
		int nowX = nextX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "NOW");
		int nowY = nextY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "NOW");
		BlockType nowBlockType = model.getMap().getBlockType(model.IndexPos(nowX), model.IndexPos(nowY));

		switch (controllerOrder) {
			case UP:
				model.getPlayer().setFrame(PlayerSprite.UP);
				nextX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "UP");
				nextY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "UP");
				nextBlockType = model.getMap().getBlockType(nextX/16, nextY/16);
				break;
			case DOWN:
				model.getPlayer().setFrame(PlayerSprite.DOWN);
				nextX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "DOWN");
				nextY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "DOWN");
				nextBlockType = model.getMap().getBlockType(nextX/16, nextY/16);
				break;
			case LEFT:
				model.getPlayer().setFrame(PlayerSprite.LEFT);
				nextX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "LEFT");
				nextY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "LEFT");
				nextBlockType = model.getMap().getBlockType(nextX/16, nextY/16);
				break;
			case RIGHT:
				model.getPlayer().setFrame(PlayerSprite.RIGHT);
				nextX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "RIGHT");
				nextY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "RIGHT");
				nextBlockType = model.getMap().getBlockType(nextX/16, nextY/16);
				break;
			case NOTHING:
				model.getPlayer().setFrame(PlayerSprite.IDLE);
				break;
		}

		if (nextBlockType != BlockType.WALL && nextBlockType != BlockType.ROCK){ // on teste si le player peut bouger
			int topX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "UP"); // upper X
			int topY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "UP"); // upper Y
			BlockType prevTopType =  model.getMap().getBlockType(model.IndexPos(topX), model.IndexPos(topY));
			BlockType prevTopLeftType =  model.getMap().getBlockType(model.IndexPos(topX)-1, model.IndexPos(topY));
			BlockType prevTopRightType =  model.getMap().getBlockType(model.IndexPos(topX)+1, model.IndexPos(topY));

			//on teste si le player va descendre sous un rocher, si oui il meurt
			if ((prevTopType.equals(BlockType.ROCK)) && controllerOrder.equals(ControllerOrder.DOWN)){ // test du cailloux juste au dessus de lui
				model.getPlayer().setPosX(nextX);
				model.getPlayer().setPosY(nextY);

				model.PlayerDeathAnnimation(nextX, nextY);
				model.getMap().TransformToDirt(model.IndexPos(topX), model.IndexPos(topY));
				view.printMessage("Vous avez per du ... Votre score est de  : "+model.getPlayer().getScore());
				System.exit(0);
			}
			if (prevTopLeftType.equals(BlockType.ROCK) && controllerOrder.equals(ControllerOrder.DOWN)){// Test cailloux haut gauche
				model.getPlayer().setPosX(nextX);
				model.getPlayer().setPosY(nextY);
				model.PlayerDeathAnnimation(nextX, nextY);
				model.getMap().TransformToDirt(model.IndexPos(topX)-1, model.IndexPos(topY));
				view.printMessage("Vous avez perdu ... Votre score est de  : "+model.getPlayer().getScore());
				System.exit(0);
			}
			if (prevTopRightType.equals(BlockType.ROCK) && controllerOrder.equals(ControllerOrder.DOWN)){ // Test cailloux haut droit
				model.getPlayer().setPosY(nextY);
				model.getPlayer().setPosX(nextX);
				model.PlayerDeathAnnimation(nextX, nextY);
				model.getMap().TransformToDirt(model.IndexPos(topX)+1, model.IndexPos(topY));
				view.printMessage("Vous avez perdu ... Votre score : "+model.getPlayer().getScore());
				System.exit(0);
			}

			//changement de position du player
			model.getPlayer().setPosX(nextX);
			model.getPlayer().setPosY(nextY);
			nowBlockType = nextBlockType;
			//creuse quand le joueur passe sur de la terre
			if (nowBlockType == BlockType.DIRT){
				model.getMap().TransformToDirt(model.IndexPos(nextX), model.IndexPos(nextY));
			}
			// Score diamant
			if (nowBlockType.equals(BlockType.DIAMOND)){
				model.getMap().TransformToDirt(nextX/16, nextY/16);
				model.getPlayer().IncrementScore(500);
				System.out.println(model.getPlayer().getScore());
			}
		}

		// Lorsque le joueur atteint le score cible le bloc de fin apparait.
		if(model.getPlayer().getScore()/500>=model.getMap().getDiamond()){
			model.getMap().getBlocks(model.getMap().getEndY(),model.getMap().getEndX()).setType(BlockType.END);
			if( (model.IndexPos(model.getPlayer().getPosX()) == model.getMap().getEndX() ) &&
					(model.IndexPos(model.getPlayer().getPosY()) == model.getMap().getEndY() ) &&
					!model.getWin() ) {
				view.printMessage("Félicitation vous avez gagné ! Votre score : "+model.getPlayer().getScore());
				model.setWin(true);
				System.exit(0);
			}
		}
		if(model.getTime()<=0 && !model.isDead()){
			view.printMessage(" PERDU !!!!!vous avez dépassé le temps ");
			model.setDead(true);
			System.exit(0);
		}
	}

}

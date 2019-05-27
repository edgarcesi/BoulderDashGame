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

		if (nextBlockType != BlockType.WALL && nextBlockType != BlockType.ROCK){


			// gravité des Rochers
			int run = 0;
			int prevTopX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "UP");
			int prevTopY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "UP") - 16;
			int prevBotX = nowX;
			int prevBotY = nowY - 16;
			int i = 0;
			BlockType prevTopType, prevBotType;
			while (run == 0){
				/*
				System.out.println(prevTopX);
				System.out.println(prevTopY);*/
				System.out.println(prevBotX);
				System.out.println(prevBotY);
				System.out.println("PLAYER:");
				System.out.println(nextY);
				System.out.println(nextX);
				System.out.println("BLOCK");
				//System.out.println(model.getMap().getBlocks().getPosY());

				//prevTopX -= i * 16;
				prevTopY += 16;
				//prevBotX -= i * 16;
				prevBotY += 16;

				prevTopType =  model.getMap().getBlockType(model.IndexPos(prevTopX), model.IndexPos(prevTopY));
				prevBotType =  model.getMap().getBlockType(model.IndexPos(prevBotX), model.IndexPos(prevBotY));
				//System.out.println(prevTopType);
				//System.out.println(prevBotType);
				//System.out.println(i);

				if (prevBotType.equals(BlockType.EMPTY) && prevTopType.equals(BlockType.ROCK)){
					model.getMap().TransformToDirt(model.IndexPos(prevTopX), model.IndexPos(prevTopY));
					model.getMap().TransformToRock(model.IndexPos(prevBotX), model.IndexPos(prevBotY-12));
					model.getMap().TransformToRock(model.IndexPos(prevBotX), model.IndexPos(prevBotY-8));
					model.getMap().TransformToRock(model.IndexPos(prevBotX), model.IndexPos(prevBotY-4));
					model.getMap().TransformToDirt(model.IndexPos(prevTopX), model.IndexPos(prevTopY));
					model.getMap().TransformToRock(model.IndexPos(prevBotX), model.IndexPos(prevBotY));
					if (prevBotX == nextX && prevBotY == nextY){
						model.getPlayer().setPosX(nextX);
						model.getPlayer().setPosY(nextY);

						model.PlayerDeathAnnimation(prevTopX, prevBotY);
						model.getMap().TransformToDirt(model.IndexPos(prevTopX), model.IndexPos(prevBotY));
						view.printMessage("Vous avez perdu ... Votre score est de  : "+model.getPlayer().getScore());
						System.exit(0);
					}
					System.out.println("done");

				}else{
					run = 1;
					System.out.println("err");
				}
				System.out.println("---------");
				i += 1;
			}

			//changement de position du player
			model.getPlayer().setPosX(nextX);
			model.getPlayer().setPosY(nextY);
			nowBlockType = nextBlockType;
			//creuse quand dle joueur passe sur de la terre
			if (nowBlockType == BlockType.DIRT){
				model.getMap().TransformToDirt(model.IndexPos(nextX), model.IndexPos(nextY));
			}
			// Score diamant
			if (nowBlockType.equals(BlockType.DIAMOND)){
				model.getMap().TransformToDirt(nextX/16, nextY/16);
				model.getPlayer().IncrementScore(500);
				System.out.println(model.getPlayer().getScore());
			}


			// Lorsque le joueur atteint le score cible le bloc de fin apparait.
			if(model.getPlayer().getScore()/500>=model.getMap().getDiamond()){
				model.getMap().getBlocks(model.getMap().getEndY(),model.getMap().getEndX()).setType(BlockType.END);

				System.out.println(model.IndexPos(model.getPlayer().getPosX()) +","+model.getMap().getEndX());

				if( (model.IndexPos(model.getPlayer().getPosX()) == model.getMap().getEndX() ) &&
					(model.IndexPos(model.getPlayer().getPosY()) == model.getMap().getEndY() ) &&
					 !model.getWin() ) {
					view.printMessage("Félicitation vous avez gagné ! Votre score : "+model.getPlayer().getScore());
					model.setWin(true);
					System.exit(0);
				}
			}

		}
	}

}

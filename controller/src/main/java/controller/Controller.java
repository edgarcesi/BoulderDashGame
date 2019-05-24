package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;
import entity.BlockType;

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
		BlockType nowBlockType = model.getMap().getBlockType(nowX/16, nowY/16);

		switch (controllerOrder) {
			case UP:
				nextX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "UP");
				nextY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "UP");
				nextBlockType = model.getMap().getBlockType(nextX/16, nextY/16);
				break;
			case DOWN:
				nextX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "DOWN");
				nextY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "DOWN");
				nextBlockType = model.getMap().getBlockType(nextX/16, nextY/16);
				break;
			case LEFT:
				nextX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "LEFT");
				nextY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "LEFT");
				nextBlockType = model.getMap().getBlockType(nextX/16, nextY/16);
				break;
			case RIGHT:
				nextX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "RIGHT");
				nextY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "RIGHT");
				nextBlockType = model.getMap().getBlockType(nextX/16, nextY/16);
				break;
			case NOTHING:
				break;
		}

		if (nextBlockType != BlockType.WALL && nextBlockType != BlockType.ROCK){
			if (nowBlockType == BlockType.DIRT){
				model.getMap().TransformToDirt(nowX/16, nowY/16);
			}


			// gravité
			int run = 0;
			int prevTopX = model.getMap().CoordoneeXNextBlock(model.getPlayer(), "UP");
			int prevTopY = model.getMap().CoordoneeYNextBlock(model.getPlayer(), "UP") - 16;
			int prevBotX = nowX;
			int prevBotY = nowY - 16;
			int i = 0;
			BlockType prevTopType, prevBotType;
			while (run == 0){
				System.out.println(prevTopX);
				System.out.println(prevTopY);
				System.out.println(prevBotX);
				System.out.println(prevBotY);
				//prevTopX -= i * 16;
				prevTopY += 16;
				//prevBotX -= i * 16;
				prevBotY += 16;

				prevTopType =  model.getMap().getBlockType(prevTopX/16, prevTopY/16);
				prevBotType =  model.getMap().getBlockType(prevBotX/16, prevBotY/16);
				System.out.println(prevTopType);
				System.out.println(prevBotType);
				System.out.println(i);

				if (prevBotType.equals(BlockType.EMPTY) && prevTopType.equals(BlockType.ROCK)){
					model.getMap().TransformToDirt(prevTopX/16, prevTopY/16);
					model.getMap().TransformToRock(prevBotX/16, prevBotY/16);
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
			// Score dimamant
			if (nowBlockType.equals(BlockType.DIAMOND)){
				model.getMap().TransformToDirt(nextX/16, nextY/16);
				model.getPlayer().IncrementScore(500);
				System.out.println(model.getPlayer().getScore());
			}
		}
	}

}

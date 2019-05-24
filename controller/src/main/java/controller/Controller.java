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
		switch (controllerOrder) {
			case UP:
				//System.out.println(model.getMap().getBlocks(model.IndexPos(model.getPlayer().getPosX() - model.RealPos(1)),model.IndexPos(model.getPlayer().getPosY() - model.RealPos(1))));
				System.out.println(model.IndexPos(model.getPlayer().getPosX() - model.RealPos(1)));
				System.out.println(model.getPlayer().getPosX() - model.RealPos(1)
				);
				//graphics.drawImage(viewFrame.getModel().getMap().getSprites(0),blocks[y][x].getPosX(),blocks[y][x].getPosY(), this);
				//System.out.println(model.getMap().getBlocks(5,5).getType());
				break;
			case DOWN:
				break;
			case LEFT:
				break;
			case RIGHT:
				break;
			case NOTHING:
				break;
		}
	}

}

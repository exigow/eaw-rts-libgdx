package eaw.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import eaw.game.mechanics.Group;
import eaw.game.mechanics.Place;
import eaw.game.mechanics.Unit;
import eaw.game.mechanics.orders.MoveOrder;
import eaw.game.mechanics.orders.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _GameController {
  public class Steppers {
    public final Stepper
      stepperSelect = new Stepper(),
      stepperAction = new Stepper(),
      stepperUp = new Stepper(),
      stepperDown = new Stepper(),
      stepperLeft = new Stepper(),
      stepperRight = new Stepper(),
      stepperIn = new Stepper(),
      stepperOut = new Stepper();
  }

  public final Steppers steppers = new Steppers();
  public final Rectangle
    selection = new Rectangle(),
    selectionFixed = new Rectangle();
  public final List<Group>
    selectedList = new ArrayList<Group>(),
    highlightedList = new ArrayList<Group>();

  public void control(Camera camera) {
    final float
      multiple_plane = 16.0f,
      multiple_zoom = .075f;

    steppers.stepperUp.update(Gdx.input.isKeyPressed(Input.Keys.W));
    steppers.stepperDown.update(Gdx.input.isKeyPressed(Input.Keys.S));
    steppers.stepperLeft.update(Gdx.input.isKeyPressed(Input.Keys.A));
    steppers.stepperRight.update(Gdx.input.isKeyPressed(Input.Keys.D));
    steppers.stepperIn.update(Gdx.input.isKeyPressed(Input.Keys.E));
    steppers.stepperOut.update(Gdx.input.isKeyPressed(Input.Keys.Q));
/*
    if (steppers.stepperUp.stepIsEqual(Stepper.Step.HOLD)) {
      camera.addMoveScaled(0.0f, multiple_plane, 0.0f);
    } else {
      if (steppers.stepperDown.stepIsEqual(Stepper.Step.HOLD)) {
        camera.addMoveScaled(0.0f, -multiple_plane, 0.0f);
      }
    }
    if (steppers.stepperLeft.stepIsEqual(Stepper.Step.HOLD)) {
      camera.addMoveScaled(-multiple_plane, 0.0f, 0.0f);
    } else {
      if (steppers.stepperRight.stepIsEqual(Stepper.Step.HOLD)) {
        camera.addMoveScaled(multiple_plane, 0.0f, 0.0f);
      }
    }
    if (steppers.stepperIn.stepIsEqual(Stepper.Step.HOLD)) {
      camera.addMoveScaled(0.0f, 0.0f, -multiple_zoom);
    } else {
      if (steppers.stepperOut.stepIsEqual(Stepper.Step.HOLD)) {
        camera.addMoveScaled(0.0f, 0.0f, multiple_zoom);
      }
    }
*/
    // Only one stepper can be active.
    if (steppers.stepperAction.stepIsEqual(Stepper.Step.WAIT)) {
      steppers.stepperSelect.update(Gdx.input.isButtonPressed(Input.Buttons.LEFT));
    }
    if (steppers.stepperSelect.stepIsEqual(Stepper.Step.WAIT)) {
      steppers.stepperAction.update(Gdx.input.isButtonPressed(Input.Buttons.RIGHT));
    }
  }

  public void selecting(Camera camera, List<Group> overallList) {
    switch (steppers.stepperSelect.getStep()) {
      case BEGIN: {
        selection.x = camera.getMouseWorldPosition().x;
        selection.y = camera.getMouseWorldPosition().y;
        break;
      }
      case HOLD: {
        selection.width = camera.getMouseWorldPosition().x - selection.x;
        selection.height = camera.getMouseWorldPosition().y - selection.y;

        // Fix rectangle.
        selectionFixed.set(selection);
        if (selection.width < 0) {
          selectionFixed.width = -selection.width;
          selectionFixed.x -= selectionFixed.width;
        }
        if (selection.height < 0) {
          selectionFixed.height = -selection.height;
          selectionFixed.y -= selectionFixed.height;
        }

        highlightedList.clear();

        for (Group group: overallList) {
          boolean add = false;
          for (Unit unit: group.units) {
            if (selectionFixed.contains(unit.place.position)) {
              add = true;
            }
          }
          if (add) {
            highlightedList.add(group);
          }
        }
        break;
      }
      case END: {
        if (!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
          selectedList.clear();
        }
        selectedList.addAll(highlightedList);
        Collections.sort(selectedList);
        for (int i = 0; i < selectedList.size() - 1; i++) {
          if (selectedList.get(i).getId() == selectedList.get(i + 1).getId()) {
            selectedList.remove(i);
            i--;
          }
        }
        highlightedList.clear();
        break;
      }
    }
  }

  public void action(Camera camera, List<Group> overallList) {
    switch (steppers.stepperAction.getStep()) {
      case BEGIN: {
        break;
      }
      case HOLD: {
        break;
      }
      case END: {
        if (!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
          for (Group g: selectedList) {
            g.orders.clear();
          }
        }
        for (Group g: selectedList) {
          Place orderSpot = new Place().setPosition(camera.getMouseWorldPosition());
          Order order = new MoveOrder(orderSpot);
          g.orders.push(order);
        }
        break;
      }
    }
  }
}

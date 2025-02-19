package model.charactersModel;

import model.FinalPanelModel;
import model.MyPolygon;
import model.charactersModel.blackOrb.Orb;
import model.collision.Collidable;
import model.collision.CollisionState;
import model.collision.Impactable;
import model.movement.Direction;
import model.movement.Movable;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static controller.UserInterfaceController.findBulletView;
import static controller.constants.Constants.BULLET_RADIUS;
import static controller.constants.Constants.BULLET_VELOCITY;
import static controller.UserInterfaceController.creatBulletView;
import static controller.Utils.*;

public class BulletModel extends GeoShapeModel implements Movable, Collidable, Impactable {
    public static CopyOnWriteArrayList<BulletModel> bulletModels = new CopyOnWriteArrayList<>();

    public BulletModel(Point2D anchor, Direction direction) {
        super();
        this.radius = BULLET_RADIUS;
        this.anchor = anchor;
        this.direction = direction;
        // is needed?
        bulletModels.add(this);


        movables.add(this);
        collidables.add(this);
        creatBulletView(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean isCircular() {
        return true;
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void setDirection(Direction direction) {}

    @Override
    public void bulletImpact(BulletModel bulletModel, Point2D collisionPoint) {
        this.eliminate();
    }



    public void bulletImpact(BulletModel bulletModel, Point2D collisionPoint, Collidable collidable) {
        ((Movable) collidable).bulletImpact(bulletModel, collisionPoint);
        for (Movable movable : movables){
            if (movable != this && movable != bulletModel && movable!= collidable){
                ((Impactable)movable).impact(new CollisionState(collisionPoint));
            }
        }
        this.eliminate();
    }


    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void move(Direction direction) {
        Point2D movement = multiplyVector(direction.getNormalizedDirectionVector(), BULLET_VELOCITY);
        this.anchor = addVectors(anchor, movement);
    }

    @Override
    public void move() {
        move(direction);
    }

    @Override
    public void friction() {}

    @Override
    public Point2D getAnchor() {
        return this.anchor;
    }

    @Override
    public void setMyPolygon(MyPolygon myPolygon) {

    }


    @Override
    public Point2D[] getVertices() {
        return null;
    }

    @Override
    public ArrayList<Line2D> getEdges() {
        return null;
    }

    @Override
    public boolean isImpactInProgress() {
        return false;
    }

    @Override
    public void setImpactInProgress(boolean impactInProgress) {

    }

    @Override
    public void impact(CollisionState collisionState) {}

    @Override
    public void impact(Point2D normalVector, Point2D collisionPoint, Collidable polygon) {}


    @Override
    public double getImpactCoefficient(Point2D collisionRelativeVector) {
        return 0;
    }

    @Override
    public void banish() {}

    @Override
    public void eliminate(){
        // is needed?
        bulletModels.remove(this);

        collidables.remove(this);
        movables.remove(this);
        findBulletView((this).getId()).eliminate();
    }

    @Override
    public void onCollision(Collidable other, Point2D intersection) {
        if (other instanceof FinalPanelModel) eliminate();
        if (other instanceof Orb) eliminate();
    }

    @Override
    public void onCollision(Collidable other) {

    }
}

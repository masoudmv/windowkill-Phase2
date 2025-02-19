package model.charactersModel.smiley;

import controller.Utils;
import model.FinalPanelModel;
import model.MyPolygon;
import model.charactersModel.ArchmireModel;
import model.charactersModel.GeoShapeModel;
//import model.collision.Coll;
import model.collision.Collidable;
import model.movement.Direction;
import org.example.GraphicalObject;
import util.ThreadPoolManager;
import view.MainFrame;
import view.charactersView.GeoShapeView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import static controller.Utils.multiplyVector;
import static controller.constants.SmileyConstants.*;
import static model.imagetools.ToolBox.getBufferedImage;

public class Fist extends GeoShapeModel implements Collidable {

    static BufferedImage image;
    public static ArrayList<Fist> fists = new ArrayList<>();
    private FinalPanelModel finalPanelModel;
    private Point2D bottomVertex;
    private double elapsedTime = 0; // Elapsed time
    private double ySpeed = 0; // Initial y-speed
    private boolean movingDown = true; // Direction of motion
    private double currentHeight = 0; // Current height of the fist

    private boolean quakeInProgress = false;
    protected static MyPolygon pol;


    public Fist(Point2D anchor) {
        super(anchor, image, pol);
        fists.add(this);
        angle = -90;
        rotate(180);

        Point2D loc = new Point2D.Double(anchor.getX()-90, anchor.getY()-90);
        Dimension size = new Dimension(200, 200);
        finalPanelModel = new FinalPanelModel(loc, size);

        collidables.add(this);
        
//        quake();
    }


    void move(Direction direction) {
        if (direction == null) return;
        Point2D movement = multiplyVector(direction.getNormalizedDirectionVector(), direction.getMagnitude());
        movePolygon(movement);
        finalPanelModel.moveLocation(movement);
    }

    public void move() {
        if (!quakeInProgress) return;
        setBottomVertex();
        direction = new Direction(new Point2D.Double(0, getSpeed()));
        move(direction);

    }

    private void setBottomVertex(){
        double yMin = Double.MIN_VALUE;
        Point2D res = null;
        for (int i = 0; i < myPolygon.npoints; i++) {
            if (yMin < myPolygon.ypoints[i]){
                yMin = myPolygon.ypoints[i];
                res = new Point2D.Double(myPolygon.xpoints[i], myPolygon.ypoints[i]);
            }
        }
        bottomVertex = res;
    }


    public double getSpeed() {
        elapsedTime += dt;

        if (movingDown) {
            ySpeed += a * dt; // Accelerate downwards
            currentHeight += ySpeed * dt;

            // Check for collision
            if (screenHeight < bottomVertex.getY()) {
                movingDown = false; // Change direction
                ySpeed = -ySpeed * restitution; // Apply restitution
                elapsedTime = 0; // Reset elapsed time
            }
        } else {
            ySpeed += a * dt; // Accelerate upwards (slowing down)
            currentHeight += ySpeed * dt;


            // Check if the fist has bounced up to the desired height
            if (bottomVertex.getY() <= screenHeight * (1 - bounceHeightFactor)) {
                movingDown = true; // Change direction
                elapsedTime = 0; // Reset elapsed time
                ySpeed = 0; // Reset speed to start falling again
                quakeInProgress = false;
            }
        }

        return ySpeed;
    }



    public void rotate(double angle){
        setMyPolygon(Utils.rotateMyPolygon(myPolygon, angle, getAnchor()));
    }


    public static BufferedImage loadImage() {
        Image img = new ImageIcon("./src/fist.png").getImage();
        Fist.image = getBufferedImage(img);
        GraphicalObject bowser = new GraphicalObject(image);
        pol = bowser.getMyBoundingPolygon();
        return Fist.image;
    }

    private void quake(){
        quakeInProgress = true;
//        setBottomVertex();
//        direction = new Direction(new Point2D.Double(0, 2));


    }

    @Override
    public void setMyPolygon(MyPolygon myPolygon) {
        super.myPolygon = myPolygon;
    }

    @Override
    public void eliminate() {

    }

    @Override
    public boolean isCircular() {
        return false;
    }

//    @Override
//    public Point2D[] getVertices() { //todo
//        Point2D[] vertices = new Point2D[myPolygon.npoints];
//        for (int i = 0; i < myPolygon.npoints; i++) {
//            double x = myPolygon.xpoints[i];
//            double y = myPolygon.ypoints[i];
//            vertices[i] = new Point2D.Double(x, y);
//        }
//        return vertices;
//    }

//    @Override
//    public ArrayList<Line2D> getEdges() {
//        return null;
//    }

    @Override
    public void onCollision(Collidable other, Point2D intersection) {

    }

    @Override
    public void onCollision(Collidable other) {

    }
}

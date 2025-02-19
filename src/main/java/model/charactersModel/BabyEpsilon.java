package model.charactersModel;

import model.MyPolygon;
import model.movement.Direction;
import java.awt.geom.Point2D;
import static controller.UserInterfaceController.createBabyEpsilonView;
import static controller.Utils.addVectors;
import static controller.Utils.multiplyVector;
import static controller.constants.EntityConstants.BABY_EPSILON_RADIUS;

public class BabyEpsilon extends GeoShapeModel {

    BabyEpsilon(Point2D anchor) {
        super();
        this.anchor = anchor;
        this.radius = BABY_EPSILON_RADIUS.getValue();
        createBabyEpsilonView(id);
    }

    public void move(Direction direction) {
        Point2D movement = multiplyVector(direction.getNormalizedDirectionVector(), direction.getMagnitude());
        this.anchor = addVectors(anchor, movement);
    }



    @Override
    public void setMyPolygon(MyPolygon myPolygon) {

    }

    public static void createBabies() {
        Point2D epsilonAnchor = EpsilonModel.getINSTANCE().getAnchor();
        double sideLength = 50; // replace with the actual side length if known
        BabyEpsilon[] babies = new BabyEpsilon[3];
        Point2D[] vertices = calculateEquilateralTriangleVertices(epsilonAnchor, sideLength);

        for (int i = 0; i < 3; i++) {
            babies[i] = new BabyEpsilon(vertices[i]);
        }

        EpsilonModel.getINSTANCE().setBabies(babies);
    }

    private static Point2D[] calculateEquilateralTriangleVertices(Point2D center, double sideLength) {
        Point2D[] vertices = new Point2D[3];
        double height = (Math.sqrt(3) / 3) * sideLength;

        // Top vertex
        vertices[0] = new Point2D.Double(center.getX(), center.getY() - height);

        // Bottom-left vertex
        vertices[1] = new Point2D.Double(center.getX() - sideLength / 2, center.getY() + height / 2);

        // Bottom-right vertex
        vertices[2] = new Point2D.Double(center.getX() + sideLength / 2, center.getY() + height / 2);

        return vertices;
    }
}

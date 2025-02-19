package view.charactersView;

import model.MyPolygon;
import model.TimedLocation;
import model.charactersModel.GeoShapeModel;
import view.FinalPanelView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static controller.UserInterfaceController.*;
import static controller.Utils.relativeLocation;
import static model.imagetools.ToolBox.getBufferedImage;
import static model.imagetools.ToolBox.rotateImage;

public class GeoShapeView {
    String id;
//    Point2D currentLocation;
    BufferedImage image;
    double imageWidth;
    double imageHeight;
//    protected MyPolygon myPolygon;
    public static CopyOnWriteArrayList<GeoShapeView> geoShapeViews = new CopyOnWriteArrayList<>();
    protected double angle;
    protected int zOrder = 2;

    protected ConcurrentHashMap<String, Point2D> locations = new ConcurrentHashMap<>();
    protected ConcurrentHashMap<String, MyPolygon> myPolygons = new ConcurrentHashMap<>();


    public GeoShapeView(String id, Image image, Point2D anchor, MyPolygon myPolygon) {
        this.id = id;
        this.image = getBufferedImage(image);
        this.imageWidth = this.image.getWidth();
        this.imageHeight = this.image.getHeight();
        geoShapeViews.add(this);

        initiator(anchor, myPolygon);
    }


    public GeoShapeView(String id, Image image) {
        this.id = id;
        this.image = getBufferedImage(image);
        this.imageWidth = this.image.getWidth();
        this.imageHeight = this.image.getHeight();
        geoShapeViews.add(this);

//        initiator();
    }

    public GeoShapeView(String id) {
        this.id = id;
        geoShapeViews.add(this);

//        initiator();
    }

    public void setCurrentLocation(String panelID, Point2D currentLocation) {
//        this.currentLocation =
//                new Point2D.Double(currentLocation.getX() - imageWidth/2, currentLocation.getY() - imageHeight/2);

        this.locations.put(panelID, currentLocation);
    }

//    public Point2D getCurrentLocation() {
//        return currentLocation;
//    }

    public String getId() {
        return id;
    }

    public void setMyPolygon(String panelID, MyPolygon myPolygon) {
        this.myPolygons.put(panelID, myPolygon);
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void eliminate(){
        geoShapeViews.remove(this);
    }
    public void setLocationHistory(LinkedList<TimedLocation> timedLocations){

    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.imageWidth = this.image.getWidth();
        this.imageHeight = this.image.getHeight();
    }

    private void initiator(Point2D anchor, MyPolygon myPolygon){
//        this.setCurrentLocation(calculateViewLocationPolygonalEnemy(component, this.getId()));
//        this.setMyPolygon(calculateEntityView(component, this.getId()));
//        this.setAngle(calculateGeoShapeViewAngle(this.getId()));
//        this.setLocationHistory(calculateLocationHistory(component, this.getId())); //archmire

        for (FinalPanelView finalPanelView : FinalPanelView.finalPanelViews){ // finalPanelModel???
            String panelID = finalPanelView.getId();

            Point2D currentLocation = calculateViewLocationPolygonalEnemy(finalPanelView, this.getId());
            Point corner = new Point(finalPanelView.getX(), finalPanelView.getY());
//            assert geoShapeModel != null;
            Point2D p =  relativeLocation(anchor, corner);
            this.setCurrentLocation(panelID, p);

            double[] xpoints = new double[myPolygon.npoints];
            double[] ypoints = new double[myPolygon.npoints];

            for (int i = 0; i < myPolygon.npoints; i++) {
                xpoints[i] = myPolygon.xpoints[i] - finalPanelView.getX();
                ypoints[i] = myPolygon.ypoints[i] - finalPanelView.getY();
            }


            MyPolygon myPol = new MyPolygon(xpoints, ypoints, myPolygon.npoints);
            this.setMyPolygon(panelID, myPol);
        }
    }




    protected void drawPolygon(){

    }

    public int getZOrder() {
        return zOrder;
    }

    public void setZOrder(int zOrder) {this.zOrder = zOrder;}

    public void draw(Graphics g, String panelID){
        Graphics2D g2d = (Graphics2D) g;

//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        if (locations.get(panelID) == null) return;
        int x = (int) locations.get(panelID).getX();
        int y = (int) locations.get(panelID).getY();


        g2d.drawImage(rotateImage(image, angle), (int) (x - imageWidth/2), (int) (y - imageWidth/2), null);

//        int[] xpoints = new int[myPolygon.npoints];
//        int[] ypoints = new int[myPolygon.npoints];
//
//        for (int i = 0; i < myPolygon.npoints; i++) {
//            xpoints[i] = (int) myPolygon.xpoints[i];
//            ypoints[i] = (int) myPolygon.ypoints[i];
//        }

//        double radius = imageHeight/2;
//        g2d.drawOval((int) getCurrentLocation().getX(), (int) getCurrentLocation().getY(), (int) (2*radius), (int) (2*radius));
//        g2d.drawOval((int) getCurrentLocation().getX(), (int) getCurrentLocation().getY(), (int) (2), (int) (2));
//        g2d.setColor(Color.red);
//
//        g2d.drawPolygon(xpoints, ypoints, myPolygon.npoints);
    }

}

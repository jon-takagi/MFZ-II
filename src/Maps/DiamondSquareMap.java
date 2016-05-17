package Maps;

import java.util.Random;

/**
 * Created by 40095 on 5/4/16.
 */
public class DiamondSquareMap extends HeightMap {
    private Cell[][] cells = getCells();

    public DiamondSquareMap(int l, int w) {
        super(l, w);
        //an initial seed value for the corners of the data
        final double SEED = 122;
        //seed the data
        cells[0][0].setHeight(80);
        cells[0][length - 1].setHeight(200);
        cells[length - 1][0].setHeight(0);
        cells[length - 1][length - 1].setHeight(0);

        double h = 122;
        //the range (-h -> +h) for the average offset
        Random r = new Random();
        //for the new value in range of h
        //side length is distance of a single square side
        //or distance of diagonal in diamond
        for (int sideLength = length - 1;
            //side length must be >= 2 so we always have
            //a new value (if its 1 we overwrite existing values
            //on the last iteration)
             sideLength >= 2;
            //each iteration we are looking at smaller squares
            //diamonds, and we decrease the variation of the offset
             sideLength /= 2, h /= 2.0) {
            //half the length of the side of a square
            //or distance from diamond center to one corner
            //(just to make calcs below a little clearer)
            int halfSide = sideLength / 2;

            //generate the new square values
            for (int x = 0; x < length - 1; x += sideLength) {
                for (int y = 0; y < length - 1; y += sideLength) {
                    //x, y is upper left corner of square
                    //calculate average of existing corners

                    double average = cells[x][y].getHeight() + cells[x + sideLength][y].getHeight() + cells[x][y +
                            sideLength].getHeight() + cells[x + sideLength][y + sideLength].getHeight();
                    average /= 4.0;

                    //center is average plus random offset
                    //We calculate random value in range of 2h
                    //and then subtract h so the end value is
                    //in the range (-h, +h)

                    cells[x + halfSide][y + halfSide].setHeight(average + (r.nextDouble() * 2 * h) - h);
                }
            }

            //generate the diamond values
            //since the diamonds are staggered we only move x
            //by half side
            //NOTE: if the data shouldn't wrap then x < length
            //to generate the far edge values
            for (int x = 0; x < length - 1; x += halfSide) {
                //and y is x offset by half a side, but moved by
                //the full side length
                //NOTE: if the data shouldn't wrap then y < length
                //to generate the far edge values
                for (int y = (x + halfSide) % sideLength; y < length - 1; y += sideLength) {
                    //x, y is center of diamond
                    //note we must use mod  and add length for subtraction
                    //so that we can wrap around the array to find the corners
                    double average =
                            cells[(x - halfSide + length) % length][y].getHeight() + //left of center
                                    cells[(x + halfSide) % length][y].getHeight() + //right of center
                                    cells[x][(y + halfSide) % length].getHeight() + //below center
                                    cells[x][(y - halfSide + length) % length].getHeight(); //above center
                    average /= 4.0;

                    //new value = average plus random offset
                    //We calculate random value in range of 2h
                    //and then subtract h so the end value is
                    //in the range (-h, +h)
                    average = average + (r.nextDouble() * 2 * h) - h;
                    //update value for center of diamond
                    cells[x][y].setHeight(average);

                    //wrap values on the edges, remove
                    //this and adjust loop condition above
                    //for non-wrapping values.
                    if (x == 0) {
                        cells[length - 1][y].setHeight(average);
                    }
                    if (y == 0) {
                        cells[x][length - 1].setHeight(average);
                    }
                }
            }
        }

    }

}

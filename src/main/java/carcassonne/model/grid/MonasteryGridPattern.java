/**
 * 
 */
package carcassonne.model.grid;

import java.util.List;

import carcassonne.model.terrain.TerrainType;
import carcassonne.model.tile.TileType;

/**
 * This class represents a specific kind of grid pattern, the grid patterns for the terrain type
 * MONASTERY.
 * @author Timur Saglam
 */
public class MonasteryGridPattern extends GridPattern {

    /**
     * Simple constructor that creates the pattern.
     * @param startingTile is the starting tile of the pattern, containing a monastery.
     * @param grid is the grid the pattern is created from.
     */
    public MonasteryGridPattern(GridSpot spot, Grid grid) {
        super(TerrainType.MONASTERY);
        TileType tileType = spot.getTile().getType();
        if (tileType != TileType.Monastery && tileType != TileType.MonasteryRoad) {
            throw new IllegalArgumentException("Can't create monastery pattern from non monastery tile");
        }
        buildPattern(spot, grid);
    }

    private void buildPattern(GridSpot monasterySpot, Grid grid) {
        List<GridSpot> neighbors = grid.getNeighbors(monasterySpot);
        add(monasterySpot); // add monastery
        monasterySpot.getTile().setTag(GridDirection.MIDDLE, this);
        for (GridSpot neighbor : neighbors) {
            spotList.add(neighbor);
        }
        if (neighbors.size() == GridDirection.neighbors().length) {
            complete = true;
        }
    }
}

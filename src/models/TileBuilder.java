package models;

import ui.Config;
import java.util.ArrayList;

public class TileBuilder {
    Tile[] tiles;
    ArrayList<Integer> spawnTilesIdx = new ArrayList<Integer>();

    public TileBuilder() {
        this.tiles = new Tile[Config.MAX_SCREEN_COL*Config.MAX_SCREEN_ROW];
    }

    public Tile[] getTiles() {
        return this.tiles;
    }

    public void buildMap() {
        basicMap();
    }

    public int[] getSpawn() {
        int xSum = 0;
        int ySum = 0;
        int[] tilePos = new int[2];
        int tileCount = spawnTilesIdx.size();

        for (int i = 0; i < tileCount; i++) {
            tilePos = tiles[spawnTilesIdx.get(i)].getPos();
            xSum += tilePos[0];
            ySum += tilePos[1];
        }

        return new int[] {xSum/tileCount-Config.TILE_SIZE/4, ySum/tileCount-Config.TILE_SIZE/4};
    }

    private void randomMap() {}

    private void basicMap() {
        int row=0, col=0, t;

        for (int i=0; i<tiles.length; i++) {
            t = 3;

            if (col >= Config.MAX_SCREEN_COL) {
                col = 0;
                row++;
            }

            if ( i < 2 ) {
                t = 1;
            }
            else if ( i==2) {
                t = 2;
            }
            else if (row == 0) {
                t = 3;
            }
            else if (row == 1 && col == Config.MAX_SCREEN_COL-1) {
                t = 3;
            }
            else if (row == 1 && col == 0 ){
                t = 3;
            }
            else if (i < 20 ) {
                t = 0;
            }
            else if (i == tiles.length -1 ) {
                t = 2;
            }
            tiles[i] = new Tile(col, row, t);
            if (t == 1) {
                spawnTilesIdx.add(i);
            }

            col++;
        }
    }
}

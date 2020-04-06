import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grid
{
    private final int sizeX, sizeY;
    private int posX, posY;
    private Dir dir;
    private final int[][] grid;

    private Grid(int sizeX, int sizeY,
                 int posX, int posY, Dir dir,
                 int[][] grid) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posX = mod(posX, sizeX);
        this.posY = mod(posY, sizeY);
        this.dir = dir;
        this.grid = grid;
    }


    public int[][] getGrid() {
        return grid;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = mod(posX, sizeX);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = mod(posY, sizeY);
    }

    public int getDir() {
        return dir.toInt();
    }

    public void setDir(int dir) {
        this.dir = Dir.fromInt(dir);
    }

    public int getValue(int x, int y) {
        return grid[x][y];
    }

    public void setValue(int x, int y, int val) {
        grid[x][y] = val;
    }

    public static Grid create(int[][] grid, int posX, int posY, int dir) {
        int sizeX = grid.length;
        int sizeY = sizeX == 0 ? 0 : grid[0].length;
        int[][] gridCopy = new int[sizeX][sizeY];
        for (int x = 0; x < sizeX; x ++) {
            gridCopy[x] = Arrays.copyOf(grid[x], sizeY);
        }
        return new Grid(sizeX, sizeY,
                        mod(posX, sizeX), mod(posY, sizeY), Dir.fromInt(dir),
                        gridCopy);
    }

    public static Grid parseGrid(String exeName, Reader reader) {
        Scanner scanner = new Scanner(reader);
        try {
            int sizeX = scanner.nextInt();
            int sizeY = scanner.nextInt();
            int posX = scanner.nextInt();
            int posY = scanner.nextInt();
            int dir = scanner.nextInt();
            int[][] grid = new int[sizeX][sizeY];
            for (int j = sizeY - 1; j >= 0; j --) {
                for (int i = 0; i < sizeX; i ++) {
                    grid[i][j] = scanner.nextInt();
                }
            }
            return create(grid, posX, posY, dir);
        } catch (InputMismatchException e) {
            System.err.println("java " + exeName + ": input mismatch in the input grid;\n"
                               + "something, in this grid, is not an integer.");
            System.exit(2);
            return null;
        } catch (NoSuchElementException e) {
            System.err.println("java " + exeName + ": missing element in the input grid;\n"
                               + "either you gave wrong dimensions or you forgot some values.");
            System.exit(2);
            return null;
        }
    }

    /* Mathematically correct modulo. */
    private static int mod(int a, int b) {
        int rval = a % b;
        if (rval < 0) {
            return rval + b;
        } else {
            return rval;
        }
    }

    public void move(int moveX, int moveY, int rot) {
        setPosX(posX + moveX);
        setPosY(posY + moveY);
        dir = dir.rotate(rot);
    }

    public void avancer(int dist) {
        dir.move(this, dist);
    }

    public void tourner(int rot) {
        move(0, 0, rot);
    }

    public void ecrire(int val) {
        setValue(getPosX(), getPosY(), val);
    }

    public int lire() {
        return getValue(getPosX(), getPosY());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(sizeX);
        builder.append(" ").append(sizeY);
        builder.append(" ").append(posX);
        builder.append(" ").append(posY);
        builder.append(" ").append(dir.toInt());
        builder.append("\n");
        int[] lengths = colLengths();
        for (int y = sizeY - 1; y >= 0; y --) {
            for (int x = 0; x < sizeX; x ++) {
                builder.append(String.format("%" + lengths[x] + "d",
                                             grid[x][y]));
                if (x < sizeX - 1)
                    builder.append(" ");
            }
            if (y > 0)
                builder.append("\n");
        }
        return builder.toString();
    }

    private int colLength(int x) {
        int length = 0;
        for (int val : grid[x]) {
            int valLength = String.valueOf(val).length();
            if (valLength > length)
                length = valLength;
        }
        return length;
    }

    private int[] colLengths() {
        int[] lengths = new int[sizeX];
        for (int x = 0; x < sizeX; x ++)
            lengths[x] = colLength(x);
        return lengths;
    }

    private enum Dir {
        E(0,  1,  0),
        N(1,  0,  1),
        W(2, -1,  0),
        S(3,  0, -1);

        private final int intValue, moveX, moveY;
        private static final Map<Integer, Dir> intToDir =
            Stream.of(values()).collect(Collectors.toMap(Dir::toInt, e -> e));

        Dir(int intValue, int moveX, int moveY) {
            this.intValue = intValue;
            this.moveX = moveX;
            this.moveY = moveY;
        }

        int toInt() {
            return intValue;
        }

        static Dir fromInt(int dir) {
            return intToDir.get(mod(dir, 4));
        }

        Dir rotate(int rot) {
            return fromInt(toInt() + rot);
        }

        void move(Grid grid, int dist) {
            grid.move(moveX * dist, moveY * dist, 0);
        }
    }
}

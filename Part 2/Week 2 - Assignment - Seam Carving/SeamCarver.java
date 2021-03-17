import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private static final double BORDER_PIXEL_ENERGY = 1000d;

    private Picture picture;

    private double[] energy;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }

        this.picture = new Picture(picture);
        this.energy = new double[width() * height()];
    }

    // current picture
    public Picture picture() {
        return new Picture(picture);
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {

        if (x < 0 || y < 0 || x > width() - 1 || y > height() - 1)
            throw new IllegalArgumentException();

        if (isOnBorder(x, y))
            return BORDER_PIXEL_ENERGY;

        int left = picture.getRGB(x - 1, y);
        int right = picture.getRGB(x + 1, y);
        int top = picture.getRGB(x, y - 1);
        int bottom = picture.getRGB(x, y + 1);

        double rx = getRed(right) - getRed(left);
        double gx = getGreen(right) - getGreen(left);
        double bx = getBlue(right) - getBlue(left);

        double ry = getRed(bottom) - getRed(top);
        double gy = getGreen(bottom) - getGreen(top);
        double by = getBlue(bottom) - getBlue(top);

        double dx = rx * rx + gx * gx + bx * bx;
        double dy = ry * ry + gy * gy + by * by;

        return Math.sqrt(dx + dy);
    }

    private int getRed(int rgb) {
        return (rgb >> 16) & 0xff;
    }

    private int getGreen(int rgb) {
        return (rgb >> 8) & 0xff;
    }

    private int getBlue(int rgb) {
        return rgb & 0xff;
    }

    private boolean isOnBorder(int x, int y) {
        return x == 0 || x == width() - 1 || y == 0 || y == height() - 1;
    }

    private boolean isInBound(int x, int y) {
        return x >= 0 && x < width() && y >= 0 && y < height();
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                energy[pixelIndex(x, y)] = energy(x, y);
            }
        }
        double[] distTo = new double[width() * height()];
        int[] edgeTo = new int[width() * height()];

        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                if (x == 0)
                    distTo[pixelIndex(x, y)] = 0;
                else
                    distTo[pixelIndex(x, y)] = Double.POSITIVE_INFINITY;
                edgeTo[pixelIndex(x, y)] = -1;
            }
        }

        for (int x = 0; x < width() - 1; x++) {
            for (int y = 0; y < height(); y++) {
                if (isInBound(x + 1, y - 1))
                    relax(pixelIndex(x, y), pixelIndex(x + 1, y - 1), distTo, edgeTo, energy);

                relax(pixelIndex(x, y), pixelIndex(x + 1, y), distTo, edgeTo, energy);

                if (isInBound(x + 1, y + 1))
                    relax(pixelIndex(x, y), pixelIndex(x + 1, y + 1), distTo, edgeTo, energy);
            }
        }

        int minLastPixel = 0;
        double minDist = Double.POSITIVE_INFINITY;
        for (int y = 0; y < height(); y++) {
            if (distTo[pixelIndex(width() - 1, y)] < minDist) {
                int i = pixelIndex(width() - 1, y);
                minDist = distTo[i];
                minLastPixel = i;
            }
        }

        // Build seam
        int[] seam = new int[width()];
        for (int i = width() - 1; i >= 0; i--) {
            seam[i] = minLastPixel / width();
            minLastPixel = edgeTo[minLastPixel];
        }

        return seam;
    }

    private void relax(int from, int to, double[] distTo, int[] edgeTo, double[] energy) {
        if (distTo[from] + energy[to] < distTo[to]) {
            distTo[to] = distTo[from] + energy[to];
            edgeTo[to] = from;
        }
    }

    private int pixelIndex(int width, int x, int y) {
        return width * y + x;
    }

    private int pixelIndex(int x, int y) {
        return width() * y + x;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        transposePicture();
        var seam = findHorizontalSeam();
        transposePicture();

        return seam;
    }

    private void transposePicture() {
        Picture transposed = new Picture(height(), width());
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                transposed.setRGB(y, x, picture.getRGB(x, y));
            }
        }
        this.picture = transposed;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || height() <= 1 || seam.length != width()) {
            throw new IllegalArgumentException();
        }

        vaildateSeam(seam);

        Picture newPic = new Picture(width(), height() - 1);
        double[] newEnergy = new double[width() * (height() - 1)];

        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < seam[x]; y++) {
                newPic.set(x, y, picture.get(x, y));
                newEnergy[pixelIndex(x, y)] = energy[pixelIndex(x, y)];
            }

            for (int y = seam[x] + 1; y < height(); y++) {
                newPic.set(x, y - 1, picture.get(x, y));
                newEnergy[pixelIndex(x, y - 1)] = energy[pixelIndex(x, y)];
            }
        }

        this.picture = newPic;
        this.energy = newEnergy;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || width() <= 1 || seam.length != height()) {
            throw new IllegalArgumentException();
        }

        vaildateSeam(seam);

        Picture newPic = new Picture(width() - 1, height());
        double[] newEnergy = new double[(width() - 1) * height()];

        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < seam[y]; x++) {
                newPic.set(x, y, picture.get(x, y));
                newEnergy[pixelIndex(newPic.width(), x, y)] = energy[pixelIndex(x, y)];
            }

            for (int x = seam[y] + 1; x < width(); x++) {
                newPic.set(x - 1, y, picture.get(x, y));
                newEnergy[pixelIndex(newPic.width(), x - 1, y)] = energy[pixelIndex(x, y)];
            }
        }

        this.picture = newPic;
        this.energy = newEnergy;
    }

    private void vaildateSeam(int[] seam) {
        for (int i = 1; i < seam.length; i++) {
            if (Math.abs(seam[i] - seam[i-1]) > 1) {
                throw new IllegalArgumentException();
            }
        }
    }

    //  unit testing (optional)
    public static void main(String[] args) {

    }

}
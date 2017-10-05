
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Arrays;


class Brain {

    public static Properties load(String path) throws IOException {
        Properties props = new Properties();
        FileInputStream inStream = new FileInputStream(path);
        BufferedInputStream buffer = new BufferedInputStream(inStream);
        props.load(buffer);
        inStream.close();
        return props;
    }
    public static double[][] convertCoefs(double[][] output, String[] data) {
        for (int i = 0, x = 0, xl = output.length; x < xl; x++) {
            for (int y = 0, yl = output[x].length; y < yl; y++) {
                output[x][y] = Double.parseDouble(data[i++]);
            }
        }
        return output;
    }
    
    public static double[] convertInters(double[] output, String[] data) {
        for (int i = 0, x = 0, xl = output.length; x < xl; x++) {
            output[x] = Double.parseDouble(data[i++]);
        }
        return output;
    }

    public static int predict(float[] atts) {
        if (atts.length != 20) { return -1; }
        /*double[][] coefs1 = {{-21.87204756787634, 57.163772937644573, 82.146391348369946, 1.4753461690238781, 19.972502690365829, 18.753208277713373, 13.994902668386356, 5.0294780188690709, -25.713053256663116, -10.860795984024751, 65.483981685680234, -26.51360773159492, -18.176944486789317, 15.846622497895865, -17.064307598381674, 46.518013268256801, 18.733352335796187, -0.10135690024775802, 3.6182919531952886, 4.3778827691345814}, {11.533146619680549, -16.213935766102551, -53.139675877431536, -12.657401870197727, -9.5168759039387911, -13.152163655770808, -7.3583958046967695, 26.596068490958896, 18.23764699227014, -14.068384993456716, -79.819182315775436, -4.783546745468243, 29.367433871246657, -27.017891237938311, -21.64590871072815, -14.548480298947037, -5.3396399053872621, -29.112334123551555, -29.412952669526373, -27.986600143185534}, {14.965531588872205, -23.740139486513872, -32.096616244196277, 52.914473910077774, -45.823794031803267, 8.5002224655664165, 31.957851168337324, -38.333080848109063, 16.19439157869483, 17.379666381912855, 20.051931019173846, -3.9316383090497746, -20.374762269898412, -27.584856743746101, -9.9176650404870959, -37.113585212086711, -48.097417384450182, 29.358295788050224, 19.666571382637375, 4.8604974874702753}};
        double[] inters1 = {-65.284066947411134, -51.445944422963947, -101.41817191124214};*/
        try {
            Properties model = Brain.load(System.getProperty("user.dir") + "\\model.properties");
            // model.properties: "inters=0.0, 0.0, 10.0, 12. ... "
            double[][] coefs = Brain.convertCoefs(new double[3][20], model.getProperty("coefs").split(","));
            double[] inters = Brain.convertInters(new double[3], model.getProperty("inters").split(","));
            int class_idx = -1;
            double class_val = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < 3; i++) {
                double prob = 0.;
                for (int j = 0; j < 20; j++) {
                    prob += coefs[i][j] * atts[j];
                }
                if (prob + inters[i] > class_val) {
                    class_val = prob + inters[i];
                    class_idx = i;
                }
            }
            return class_idx;
        } catch (IOException ioe) {
            // ignore for now...
            System.out.println("Exception occurred in handling properties");
            return -1;
        }
    }

    public static void main(String[] args) {
        if (args.length == 20) {
            float[] atts = new float[args.length];
            for (int i = 0, l = args.length; i < l; i++) {
                atts[i] = Float.parseFloat(args[i]);
            }
            System.out.println(Brain.predict(atts));
        }
    }
}
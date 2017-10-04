class Brain {

    public static int predict(float[] atts) {
        if (atts.length != 20) { return -1; }
    
        double[] coefs = {-0.1865702489959101, 0.2112252820566414, 0.38260950493581214, -0.4274928562631512, 6.7106772121213272, 0.56711827014810678, 0.085173341265718058, 0.13257477728791242, -0.85733572675827785, -1.9451763050653084, 2.4124050621268678, -0.65817689006096658, -0.2381912285710234, -0.21501356699897781, -0.43260938134153276, 0.27645794280635083, -0.090277928831050527, -0.4286850955226244, -0.31802828263062916, -0.2302633413856445};
        double inters = 0.46606201970963601;
    
        double prob = 0.;
        for (int i = 0; i < 20; i++) {
            prob += coefs[i] * atts[i];
        }
        if (prob + inters > 0) {
            return 1;
        }
        return 0;
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
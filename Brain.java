class Brain {

    public static int predict(float[] atts) {
        if (atts.length != 20) { return -1; }
    
        double[] coefs = {-12.150176641452498, 7.6732610265113976, 7.0488594848816826, 16.706494345515008, -6.3645872169866342, -1.9066262848866724, 11.342850406566219, -6.7821129917137029, 2.347084418077531, -2.9790549723013715, -8.0826617276515069, -9.2788995129085379, 22.283118199191069, 26.534273717043867, 29.197029558663772, 115.25901614356006, -0.54331603911795323, -12.197124770093559, 8.5084600237258421, 26.256478470221161};
        double inters = -23.287546510599199;
    
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
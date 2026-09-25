public class argumentos {
    static void main(String[] args) {




        if (Integer.parseInt(args[0]) >  Integer.parseInt(args[1]) && Integer.parseInt(args[0]) >  Integer.parseInt(args[2])){
            System.out.println(args[0]);
        } else if (Integer.parseInt(args[1]) >  Integer.parseInt(args[0]) && Integer.parseInt(args[1]) >  Integer.parseInt(args[2])){
            System.out.println(args[1]);
        }else {
            System.out.println(args[2]);

        }
    }

}

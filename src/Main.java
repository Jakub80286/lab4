import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        stworzpkt();
    }
    static File plikwczytanie = new File("punktyKubek");
    static File plikzapis = new File("gotowyKubek.txt");
    static Scanner wczytanie;

    static {
        try {
            wczytanie = new Scanner(plikwczytanie);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static BufferedWriter zapis;

    static {
        try {
            zapis = new BufferedWriter(new FileWriter(plikzapis));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Punkty[][] inputPoints = new Punkty[4][4];


    static int dwumian (int a, int b)
    {
        if (b == 0 || b == a)
            return 1;
        else
            return dwumian(a-1, b-1) + dwumian(a-1, b);
    }

    static Double berstein(int m, int i, double v){
        return dwumian(m,i) * Math.pow(v,i) * Math.pow(1-v,m-i);
    }

    public static void stworzpkt() throws IOException {
        double x,y,z;
        x=0.0;y=0.0;z=0.0;
        int iloscplaszczyzn = wczytanie.nextInt();
        zapis.write("x, y, z"+System.lineSeparator());
        for(int a=0;a<iloscplaszczyzn;a++){

            for(int b=0;b<4;b++){
                for(int c=0;c<4;c++){
                    double pktx = Double.parseDouble(wczytanie.next());
                    double pkty = Double.parseDouble(wczytanie.next());
                    double pktz = Double.parseDouble(wczytanie.next());
                    inputPoints[b][c] = new Punkty(pktx,pkty,pktz);
                }
            }
            for(double v=0.0;v<=1.0;v+=0.01) {
                for(double w=0.0;w<=1.0;w+=0.01){
                    for (int i=0;i<4;i++){
                        for(int j=0; j<4 ;j++){
                            x += inputPoints[i][j].x * berstein(3,i,w) * berstein(3,j,v);
                            y += inputPoints[i][j].y * berstein(3,i,w) * berstein(3,j,v);
                            z += inputPoints[i][j].z * berstein(3,i,w) * berstein(3,j,v);
                        }
                    }
                    zapis.write(x+","+y+","+z+System.lineSeparator());
                    x=0.0;y=0.0;z=0.0;
                }
            }
        }
        zapis.close();


    }



}
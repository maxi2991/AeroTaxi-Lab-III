import Clases.Gold;

public class Main {

    public static void main(String[] args) {


        Gold gold = new Gold();

        gold.setWifi();
        System.out.println(gold);
        System.out.println();


        /*File archivo = new File("mi_archivo.txt");

        if(archivo.exists()) {
            System.out.println("existe");
            if(archivo.isFile()) {
                System.out.println("es un archivo");
                System.out.println(archivo.getName());
                System.out.println(archivo.getAbsolutePath());
                if(archivo.isDirectory()) {
                    System.out.println("es un directorio");
                }
            }
        }*/

        //escribir un archivo
        /*try {
            BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter(new File("mi_archivo.txt")));

            fileWriter.write("hola mundo!");
            fileWriter.write("\n");
            fileWriter.write("Franco se la come");
            fileWriter.close();



        } catch (Exception e) {
            System.out.println("se produjo un error error");
        }*/


        //leer un archivo
        /*if(!(new File("mi_archivo.txt")).exists()) {
            System.out.println("error");
        } else {
            try{
                BufferedReader fileReader = new BufferedReader(
                        new FileReader(new File("mi_archivo.txt")));
                String linea = null;
                while((linea = fileReader.readLine()) != null) {
                    System.out.println(linea);
                }

                fileReader.close();
            } catch (IOException e) {
                System.out.println("se produjo un error: " + e.getMessage());
            }
        }*/

    }
}

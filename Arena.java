import java.io.*;
import java.util.*;


public class Arena {

    public static final int NOME=0, HP=1, TIPO=2, RESISTENZA=3, DEBOLEZZA =4,NUMEROATTACCO =5;

    static ArrayList<Pokemon>mieiPokemon = new ArrayList<Pokemon>(); //Contiene tutti i pokèmon
    static ArrayList<Pokemon>scelte = new ArrayList<Pokemon>(); //Contiene scelte dell'utente
    static ArrayList<Pokemon>pokemonNemici = new ArrayList<Pokemon>(); //Contiene i pokèmon nemici

    static Scanner scan = new Scanner(System.in);

    //Metodo per controllare se l'input dell'utente è valido quando si fanno scelte
    public static int validitaUtente(int range1, int range2, int inputUtente)
    {

        if(inputUtente > range2)
        {
            return 0;      //L'input utente non è nel range
        }
        if(inputUtente < range1)
        {
            return 0;       //L'input utente non è nel range
        }
        else
        {
             return inputUtente; //Scelta valida
        }
    }
    //Metodo per decidere chi inizia prima (50% di possibilità)
    public static int inizioPartita(){

        Random numero = new Random();
        int inizio = numero.nextInt(2);
        return inizio;
    }
}

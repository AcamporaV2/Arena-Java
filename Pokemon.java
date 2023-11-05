import java.util.*;

public class Pokemon{

    private boolean successoAttacco = true, usaEnegia = true, pokemonInibito = false;
    public boolean pokemonStordito = false;
    public int hpIniziali, hp, energia;
    public String nome, tipo, resistenza, debolezza;

    ArrayList<Attacco>attacchi; //Prende l'arraylist Attachi
    
    //Costruttore pokemon
    public Pokemon (String nome, int hp, String tipo, String resistenza, String debolezza, ArrayList<Attacco>attacchi)
    {
        this.hpIniziali=hp;  //Punti vita all'inizio della battaglia, non vengono cambiati durante lo scontro
        this.hp=hpIniziali;  //Punti vita attuali del pokemon, vengono cambiati durante lo scontro
        this.nome = nome;
        this.tipo = tipo;
        this.resistenza = resistenza;
        this.debolezza = debolezza;
        this.attacchi = attacchi;
        this.energia = 100;
    }

    //Metodo per l'attacco del nostro pokèmon. Controlla se ha un attacco speciale e richiama il calcolo attacco se è un attacco normale e calcolo attacco speciale se è uno special
    public void attaccoPokemon(Pokemon corrente, int atkInfo, Pokemon nemico)
    {
        Attacco pokeAtk = corrente.attacchi.get(atkInfo);

        if(pokeAtk.special.equals(""))
        {
            System.out.printf("%s used %s \n",corrente.nome,pokeAtk.nome);
        }

        if(successoAttacco == true)
        {
            calcoloAttacco(corrente,atkInfo,nemico); //calcolo danno
        }
        else
        {
            attaccoSpeciale(corrente, atkInfo, nemico);
        }
    }

    //Metodo per l'attacco del nemico. Controlla se esso ha energia per attaccare o no
    public int attaccoNemico(Pokemon nemico){
        
        boolean mossa = false; //flag per la mossa
        ArrayList<Integer>mosseUsabili = new ArrayList<Integer>(); //Array list per le mosse usabili
        Random number = new Random(); //Numero random

        for(int i = 0; i<nemico.attacchi.size(); i++)
        {
            if(pokemonHaEnergia(nemico,i)== true)
            {
                mosseUsabili.add(i);
                mossa = true;
            }
        }
        if(mosseUsabili.size()== 0)
        {
            return -1;
        }
        int selezioneMossa = number.nextInt(mosseUsabili.size());
        return selezioneMossa;
    }

    public void attaccoSpeciale(Pokemon corrente, int atkInfo, Pokemon nemico)
    {
       Attacco pokeAtk = corrente.attacchi.get(atkInfo);
        
       //Ricarica energia
        if(pokeAtk.special.equals("ricarica"))
        {
            System.out.printf("%s ha usato %s\n",corrente.nome,pokeAtk.nome);
            ricarica(corrente);
        }
        
        else if(pokeAtk.special.equals("stordire")){
            System.out.printf("%s ha usato %s \n",corrente.nome,pokeAtk.nome);
            stordire(corrente, nemico); //Attacco stordire, 50% di farlo
        }

}
    public void calcoloAttacco(Pokemon corrente, int atkInfo, Pokemon nemico)
    {
        Attacco attaccoPokemon = corrente.attacchi.get(atkInfo);
        int danno= attaccoPokemon.danno;

        if(nemico.hp <= 0)
        {
            nemico.hp = 0; //fa sì che gli hp non vadano negativi
        }

        if(corrente.hp <=0)
        {
            corrente.hp = 0; //fa sì che gli hp non vadano negativi
        }

        if(successoAttacco == true)
        {
            if(corrente.tipo.equals(nemico.resistenza))
            {
                System.out.println("Non è molto efficace\n");
                danno = danno/2; //danno dimezzato se non è molto efficace
            }
            else if(corrente.tipo.equals(nemico.debolezza))
            {
                System.out.println("E' Superefficace!\n");
                danno = danno *2; //danno doppio se è superefficace
            }
            nemico.hp -= danno;
            if(usaEnegia == true){
                setEnergia(corrente,attaccoPokemon.energia);
            }

        }

        //Mostra le stats dei pokemon dopo l'attacco
        System.out.println("\t\t\t Statistiche correnti Pokemon: \n");
        System.out.println("---------------------------------------------------");
        System.out.printf("\t%-11s : HP: %-3d Energia: %-4d\n",nemico.nome,nemico.hp,nemico.energia);
        System.out.printf("\t%-11s : HP: %-3d Energia: %-4d\n",corrente.nome,corrente.hp,corrente.energia);
        System.out.println("---------------------------------------------------");
    }
    public void setEnergia(Pokemon corrente, int valore){

        corrente.energia -= valore;
        if(corrente.energia <= 0)
        {
            corrente.energia = 0;
        }

    }
    //Metodo che controlla se il pokemon (nemico o proprio) ha abbastanza energia e si può permettere l'attacco 
    public boolean pokemonHaEnergia(Pokemon corrente, int atkInfo)
    {
        boolean pokemonHaEnergia= false;
        Attacco attaccoPokemon = corrente.attacchi.get(atkInfo);

        if(corrente.energia >= attaccoPokemon.energia)
        {
            pokemonHaEnergia = true;
        }

        return pokemonHaEnergia;
    }

    public static boolean pokemonCaduto(Pokemon pkmn )
    {
        boolean caduto = false;
        if(pkmn.hp <= 0)
        {
            caduto = true;
        }
        return caduto;
    }


    public void ricarica(Pokemon pkmn)
    {
        pkmn.energia += 20; //aggiunge 20 energia al pokemon che lo usa
        if(pkmn.energia > 100)
        {
            pkmn.energia = 100;
        }
        System.out.printf("%s ha recuperato 20 punti energia\n",pkmn.nome);
    } 

    //Metodo che riguarda la mossa speciale stordire che ha il 50% di possibilità di stordire il nemico
    public void stordire(Pokemon mioPokemon, Pokemon nemico){

        boolean pass = false;
        Random numero = new Random();
        int risultato = numero.nextInt(2);
        if(risultato == 0 )
        {
            pass = true;
        }

        if(pass == true)
        {
            nemico.pokemonStordito = true;
            System.out.printf("%s è stato stordito e passa il turno\n", nemico.nome);
        }
        else {
            System.out.printf("%s ha fallito la mossa\n",mioPokemon.nome);
        }
    }
}

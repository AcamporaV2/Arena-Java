public class Attacco {
    String nome, special;
    int energia, danno;

    //Costruttore classe attacco
    public Attacco(String nome, int energia, int danno, String special){
        this.nome = nome;
        this.energia = energia;
        this.danno = danno;
        this.special = special;
    }

    //Rappresentazione formattata delle statistiche
    public String format(){
        return String.format("\t< Mossa Pokemon: %-12s > \nDanno: %-4d  \nEnegia: %-4s  \nSpecial: %-4s \n",nome, danno, energia, special);
    }
}

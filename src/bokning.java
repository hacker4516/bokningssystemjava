import java.time.LocalDate;
import java.time.Period;
//Dessa två importerar klass från java.time, där LocalDate kollar med datumet nu och Period kollar på tiden mellan datum så det kan bestämma antal år.
import java.util.Scanner;
//Så det kan ta input av användare.

public class bokning {


    static Scanner fortnite = new Scanner(System.in);
    //Scanner i java, fortnite för min men kan vara "scanner" också. Jag är van med static Scanner, då det kan användas i hela koden.
    //Men i denna kod krävs inte static Scanner, bara Scanner fortnite... räcker i main metoden.
    static int biljettKvar = 21;
    static String biljettNamnF[][] = new String[21][1];
    static String biljettNamnE[][] = new String[21][1];
    static double biljettPris[][] = new double[21][1];
    static double[] biljettPriserna = {149.90, 299.90, 199.90};
    //Alla dessa är variabler.
    //biljettKvar räknar ut hur många biljetter det är kvar på destinationen.
    //biljettNamnF och E är det som kollar i systemet om passegeraren är redan bokat/lägger till passegerare i systemet.
    //biljettPris är det som räknar ut priset av passegeraren osv, mycket matte.
    //biljettPriserna sätter värde på de 3 biljett typerna beroende på ålder, och skapar fält för det.

    public static void main(String[] args) {
        //Default metoden

        for (int i = 1; i < 4; ) {
            //Ger dig möjligheten till 3 försök med login for loopen, och då avslutas om du får fel på tredje försöket.

            String namn, kod;
            System.out.print("Skriv in användarnamn: ");
            namn = fortnite.nextLine();
            System.out.print("Skriv in kod: ");
            kod = fortnite.nextLine();
            //Detta är user interfacen, där vi anger användernamn och kod, som kan vara bokstäver eller nummer osv.
            //Detta ska vara användernamn och kod för bussmenyn, där om man anger fel av dessa två får man inte logga in.
            if (namn.equals("LOL") && kod.equals("LOLZ")) {
                //Printar ut menyn om användernamn och kod är korrekta.

                for (int x = 1; x == 1; ) {
                    //Att använda sig av variabel som x gör så att du kan fortsätta med koden tills den ska avslutas, där x=0 och då avslutas det, medan x=1 är bara fortsättning.
                    //Funkar så, där valet fortsätter med flera frågor osv man svarar på om x=0, om det är fel input får man testa igen genom att göra x=1.
                    System.out.println("*******************************");
                    System.out.println("** Buss biljetter alternativ **");
                    System.out.println("** [1] Boka biljett          **");
                    System.out.println("** [2] Avboka biljett        **");
                    System.out.println("** [3] Vinster               **");
                    System.out.println("** [4] Avsluta menyn         **");
                    System.out.println("*******************************");

                    System.out.print("Ange val: ");
                    String val = fortnite.nextLine();
                    //Interaktionen med menyn är här, där man väljer vad man vill göra.

                    if (val.equals("1")) {
                        //Om man vill reservera EN biljett.

                        if(biljettKvar==0){
                            System.out.println("Finns inga fler biljetter kvar.");
                        }
                        //Detta är en if villkor där om antal biljetter kvar är 0, kan man inte boka fler.
                        else {
                        //Om antal biljetter är > 0.

                        System.out.print("Skriv in förnamn: ");
                        String förNamn = fortnite.nextLine();
                        System.out.print("Skriv in efternamn: ");
                        String efterNamn = fortnite.nextLine();

                            if (personFinns(förNamn, efterNamn)) {
                                System.out.println("Passegeraren existerar, går tillbaka till login.");
                                //Detta är en metod på att kolla om passegeraren existerar.
                                //Kollar på för och efternamn i systemet, om det matchar då tar den dig tillbaka.
                            }
                            else {
                                try {
                                    //En try-catch metod på personnumret.
                                    System.out.print("Skriv in personnummer i formaten YYYY-MM-DD: ");
                                    String personnummer = fortnite.nextLine();
                                    LocalDate persNum = LocalDate.parse(personnummer);
                                    System.out.println("Du är " + fåÅlder(persNum) + " år gammal.");
                                    läggTillPassegerare(förNamn, efterNamn, persNum);
                                    //Om personnumret skrivs i stilen YYYY-MM-DD, med streck mellan, då läggs till passegerare.
                                } catch (Exception e) {
                                    System.out.println("Fel format, försök igen senare.");
                                    //Om det finns ingen streck eller om det inte är nummer, då blir det ogiltigt och tar dig tillbaka.
                                }
                            }
                        }
                    }
                    else if (val.equals("2")) {
                        //Om man vill avbryta EN biljett.
                        System.out.print("Skriv in förnamn: ");
                        String förNamn = fortnite.nextLine();
                        System.out.print("Skriv in efternamn: ");
                        String efterNamn = fortnite.nextLine();

                        if (personFinns(förNamn, efterNamn)) {
                            taBortPassegerare(förNamn, efterNamn);
                            biljettKvar++;
                            //Öka biljett antal med 1 då man har tar bort en reserverad biljett.
                            System.out.println("Biljetten blev avbryten, en till biljett lades till i antal biljetter.");
                            //Använder samma metod från innan som tar in passegerare som finns, och om det stämmer med systemet så tas bort passegeraren.
                        }
                        else {
                            System.out.println("Passegeraren finns inte med, vänligen försök senare!");
                            //Om det förnamn och/eller efternamn matchar inte.
                        }
                    }
                    else if (val.equals("3")) {
                        //Om man vill se vinster på köpta biljetter.
                        int biljettBarn = (int) räknaBiljetter(biljettPriserna[0]);
                        int biljettVuxen = (int) räknaBiljetter(biljettPriserna[1]);
                        int biljettPensionär = (int) räknaBiljetter(biljettPriserna[2]);
                        //Tre variabler för att beräkna antal biljetter per ålder kategori.
                        //Använder sig av räknaBiljetter metod för att se hur mycket av varje kategori det finns.

                        double vinst = räknaUtVinster(biljettBarn, biljettVuxen, biljettPensionär);
                        System.out.println("Dina vinster: " + vinst + " kr");
                        System.out.println("Antal biljetter för åldrarna: ");
                        System.out.println("Barn: " + biljettBarn);
                        System.out.println("Vuxen: " + biljettVuxen);
                        System.out.println("Pensionär: " + biljettPensionär);
                    }
                    else if (val.equals("4")) {
                        //Om man vill avsluta programmet.
                        System.out.println("\nAvslutad menyn!\n");
                        break;
                        //En break då man går tillbaka till login delen, så att passegeraren behålls även när man slutar, tills man avslutar manuellt.
                    }
                    else {
                        System.out.println("Fel input, försök igen!");
                        //Om man inte skriver en av de 4 valen.
                    }
                }
            } else {
                System.out.println("\nFel användernamn eller kod, försök igen, du har gjort " + i + " försök, du har max 3 försök!");
                i++;
                //For loopen som användes ovan. 3 försök avslutar koden då loopen avslutas då.
            }
        }
    }
    public static int fåÅlder(LocalDate persNum) {
        //En metod för att beräkna ålden via personnumret du matar in.
        LocalDate datumIdag = LocalDate.now();
        //Kalkylerar tiden mellan datumen och returnerar år.
        if ((persNum != null) && (datumIdag != null)) {
            return Period.between(persNum, datumIdag).getYears();
            //Kontrollerar om både personnumret och datum idag är giltiga.
            //Om de är det beräknas åldern genom att ta perioden mellan personnumret och datumet idag och returnera antalet år i perioden. Om något av datumen är ogiltig, returneras 0.
            }
            else{
                return 0;
            }
    }

    public static boolean personFinns(String förNamn, String efterNamn) {
        for (int i = 0; i < biljettNamnF.length; i++) {
            if (biljettNamnF[i][0] != null && biljettNamnE[i][0] != null) {
                if (biljettNamnF[i][0].equalsIgnoreCase(förNamn) && biljettNamnE[i][0].equalsIgnoreCase(efterNamn)) {
                    return true;
                }
            }
        }
        return false;
    }
    //Denna metod är det som tar emot inputs som är förnamn och efternamn.
    //Om personen finns i systemet då returneras true och aa det funkar ba.
    //Om personen inte finns, då blir de false.
    //Används för o kolla om det finns passegerare eller för att ta bort existerande passegerare.

    public static void läggTillPassegerare(String förNamn, String efterNamn, LocalDate persNum) {
        for (int i = 0; i < biljettNamnF.length; i++) {
            if (biljettNamnF[i][0] == null) {
                biljettNamnF[i][0] = förNamn;
                biljettNamnE[i][0] = efterNamn;
                biljettPris[i][0] = räknaUtPrisBiljett(persNum);
                biljettKvar--;
                System.out.println("Biljetten blev bokad!");
                break;
            }
        }
    }
    //Denna metod lägger till passegerare med inputs.
    //Letar efter en ledig plats i arrayen biljettNamnF och lägger till en passagerare biljettnamnF, biljettNamnE och biljettPris.
    //Antalet tillgängliga biljetter minskas med biljetKvar--; och sedan printas ut biljetten blev bokad.
    //Om ingen ledig plats hittas, läggs ingen passagerare till i systemet.

    public static void taBortPassegerare(String firstName, String lastName) {
        for (int i = 0; i < biljettNamnF.length; i++) {
            if (biljettNamnF[i][0] != null && biljettNamnF[i][0].equals(firstName)
                    && biljettNamnE[i][0].equals(lastName)) {
                biljettNamnF[i][0] = null;
                biljettNamnE[i][0] = null;
                biljettPris[i][0] = 0.0;
                break;
            }
        }
    }
    //Metoden taBortPassegerare letar efter en passagerare i biljettNamnF och biljettNamnE.
    //Om en passegerare med samma namn hittas då blir det till null, och biljett priset för passegeraren blir till 0,0 kr.
    //Om det är så att det blir null, då går passegeraren bort från systemet.

    public static double räknaBiljetter(double biljettPriser) {
        int räkna = 0;
        for (int i = 0; i < biljettNamnF.length; i++) {
            if (biljettNamnF[i][0] != null) {
                if (biljettPris[i][0] == biljettPriser) {
                    räkna++;
                }
            }
        }
        return räkna;
    }
    //Metoden räknar biljetter med specifika pris.
    //biljettPriser är specifika biljett priset av ålder kategori.
    //Det kollar om biljettNamnF finns i systemet, om inte blir det null, om det finns då ökas räknaren med 1.
    //Exempel på användningen finns ovan, det är då förenklat o se hur det funkar.

    public static double räknaUtPrisBiljett(LocalDate persNum) {
        int ålder = fåÅlder(persNum);
        if (ålder <= 17) {
            return biljettPriserna[0];
        } else if (ålder >= 69) {
            return biljettPriserna[2];
        } else {
            return biljettPriserna[1];
        }
    }
    //Denna metod gör det möjligt att kunna få ålder grupper med biljetterna.
    //Genom att lägga int age = fåÅlder(persNum) kan vi få ett nummer som "age" då blir.
    //Där du är ett visst ålder, biljetten tillhör en av biljettPriserna grupper i fältet, och sedan returnerar det numret.


    public static double räknaUtVinster(int biljettBarn, int biljettVuxen, int biljettPensionär) {
        return (biljettBarn * biljettPriserna[0]) + (biljettVuxen * biljettPriserna[1]) + (biljettPensionär * biljettPriserna[2]);
    }
}
//Detta är bara en metod för att beräkna vinsten av biljetterna, genom att summera det multiplicerade antal biljetter med biljett priserna.
//T.ex. 4 barn biljetter * 149.90, 3 vuxen biljetter * 299.90 osv.

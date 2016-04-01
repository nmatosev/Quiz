package com.example.b312967.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by b312967 on 23.12.2015..
 */
public class DataHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 32;
    // Database Namea
    private static final String DATABASE_NAME = "triviaQuiz";
    // tasks table name
    private static final String TABLE_POVIJEST = "povijest";
    private static final String TABLE_SPORT = "sport";
    private static final String TABLE_ZEMLJOPIS = "zemljopis";
    private static final String TABLE_ZNANOST = "znanost";
    private static final String TABLE_FILM = "film";
    private static final String TABLE_GLAZBA = "glazba";
    private static final String TABLE_IT = "it";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTA= "opta"; //option a
    private static final String KEY_OPTB= "optb"; //option b
    private static final String KEY_OPTC= "optc"; //option c
    private static final String KEY_OPTD= "optd";

    private static final String CREATE_TABLE_SPORT = "CREATE TABLE IF NOT EXISTS " + TABLE_SPORT + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
            + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
            +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD+" TEXT)";

    private static final String CREATE_TABLE_POVIJEST = "CREATE TABLE IF NOT EXISTS " + TABLE_POVIJEST + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
            + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
            +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD+" TEXT)";
    //KREIRAJ NOVU TABLICU
    private static final String CREATE_TABLE_ZEMLJOPIS= "CREATE TABLE IF NOT EXISTS " + TABLE_ZEMLJOPIS + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
            + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
            +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD+" TEXT)";

    private static final String CREATE_TABLE_ZNANOST= "CREATE TABLE IF NOT EXISTS " + TABLE_ZNANOST + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
            + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
            +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD+" TEXT)";

    private static final String CREATE_TABLE_FILM= "CREATE TABLE IF NOT EXISTS " + TABLE_FILM + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
            + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
            +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD+" TEXT)";

    private static final String CREATE_TABLE_GLAZBA= "CREATE TABLE IF NOT EXISTS " + TABLE_GLAZBA + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
            + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
            +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD+" TEXT)";

    private static final String CREATE_TABLE_IT= "CREATE TABLE IF NOT EXISTS " + TABLE_IT + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
            + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
            +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD+" TEXT)";

    private SQLiteDatabase dbase;

    public DataHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        ////////////////////STVARANJE DB I EXECUTE SQL IZRAZA ZA STVARANJE NOVIH TABLICA//////////
        db.execSQL(CREATE_TABLE_POVIJEST);
        db.execSQL(CREATE_TABLE_SPORT);
        db.execSQL(CREATE_TABLE_ZEMLJOPIS);
        db.execSQL(CREATE_TABLE_ZNANOST);
        db.execSQL(CREATE_TABLE_FILM);
        db.execSQL(CREATE_TABLE_GLAZBA);
        db.execSQL(CREATE_TABLE_IT);
        Log.d("oncreate sqlite", "");


        ///////////////ONCREATE =====> ADDQUESTIONS_KATEGORIJA(STVARANJE OBJEKTA) ==========> ADDQUESTIONKATEGORIJA (UNOS U BAZU)
        addQuestionsPovijest();
        addQuestionsSport();
        addQuestionsZemljopis();
        addQuestionsZnanost();
        addQuestionsFilm();
        addQuestionsGlazba();
        addQuestionsIT();

//db.close();
    }

    //GETERI SETERI, stvaraj nove objekte koji se spremaju u tablice
    private void addQuestionsPovijest()
    {

        List<String> questionlist = new ArrayList<String>(); // lista pitanja i odgovora
        //List<Povijest> povijestList;  // lista objekata povijest
        Log.w("test", "testbeforeparse");
        questionlist = ParseFile("res/raw/povijest.txt");   // ime text filea
        String[] separated;  // array u kojem ce prvi clan bit pitanje, sljedeca 4 odgovori

        for (int i=0; i<questionlist.size(); i++)
        {
            separated = questionlist.get(i).split(",");
            Log.w("q", separated[0] + separated[1] + separated[2] + separated[3] + separated[4] + separated[5]);
            Povijest q = new Povijest(separated[0], separated[1], separated[2], separated[3], separated[4], separated[5]);
            this.addQuestionPovijest(q);
        }

        //////////////////KREIRAJ NOVE OBJEKTE
        Povijest q1=new Povijest("Kad je počeo prvi svjetski rat?","1914.","1873.","1899.","1945.","1914.");
        ////////////////////////POZOVI FJU ADDQUESTION I KAO ARGUMENT JOJ POŠALJI NOVOSTVORENI OBJEKT
        this.addQuestionPovijest(q1);

        Povijest q2=new Povijest("Koje godine je otkrivena Amerika?", "1491.", "1492.","1493.", "1495.", "1492.");
        this.addQuestionPovijest(q2);

        Povijest q3=new Povijest("Kad su održane zimske olimpijske igre u Sarajevu?","1980.", "1984.","1986.","1988.","1984.");
        this.addQuestionPovijest(q3);

        Povijest q4=new Povijest("Kad je počeo drugi svjetski rat?",    "1929.", "1939.", "1942.","1947","1939.");
        this.addQuestionPovijest(q4);

        Povijest q5=new Povijest("Koje godine je osnovan NK Hajduk?","1910.","1911.","1912","1913.","1911.");
        this.addQuestionPovijest(q5);

        Povijest q6=new Povijest("U kojem gradu se zbio atentat koji je bio uzrok prvog svjetskog rata?","U Beogradu","U Parizu","U Sarajevu","U Zagrebu","U Sarajevu");
        this.addQuestionPovijest(q6);

        Povijest q7=new Povijest("Koje godine je započelo rušenje berlinskog zida?", "1987.", "1989.", "1993.", "1994.","1989.");
        this.addQuestionPovijest(q7);
        Povijest q8=new Povijest("Koje godine su žene u SAD dobile pravo glasa?","1913.","1920.","1922.","1927.","1920.");
        this.addQuestionPovijest(q8);
        Povijest q9=new Povijest("Tko je bio engleski kralj za vrijeme drugog svjetskog rata?","George V.","George VI.","George VII.","George IV.","George VI.");
        this.addQuestionPovijest(q9);
        Povijest q10=new Povijest("Bomba se zvala Little Boy; kako se zvao zrakoplov iz kojeg je bačena? ","Purple Haze","Enola Gay","Little Jim","Foxy Lady","Enola Gay");
        this.addQuestionPovijest(q10);
        Povijest q11=new Povijest("Čije su posljednje riječi: \"Noli turbare circulos meos\"? ","Arhimedove","Senekine","Platonove","Sokratove","Arhimedove");
        this.addQuestionPovijest(q11);
        Povijest q12=new Povijest("Tko je bio Hitlerov ministar propagande? ","Hermann Göring","Alfred Rosenberg","Heinrich Himmler","Joseph Goebbels","Joseph Goebbels");
        this.addQuestionPovijest(q12);
        Povijest q13=new Povijest("Koja je bolest bila znana i kao francuska bolest? ","Šarlah","Sifilis","Mononukleoza","Tuberkoloza","Sifilis");
        this.addQuestionPovijest(q13);
        Povijest q14=new Povijest("Al Capone je završio na jedanaestogodišnjoj robiji u Alcatrazu zbog: ","Svodništva","Otmice","Ubojstva","Utaje poreza","Utaje poreza");
        this.addQuestionPovijest(q14);
        Povijest q15=new Povijest("Koji se grad skriva iza keltskog naziva Vindobona?","Pariz","London","Beč","Varaždin","Beč");
        this.addQuestionPovijest(q15);
        Povijest q16=new Povijest("U kojem je nacističkom logoru umrla Anna Franck? ","Neuengamme","Mauthausen","Bergen-Belsen","Ravensbrück","Bergen-Belsen");
        this.addQuestionPovijest(q16);
        Povijest q17=new Povijest("Koji je general vodio savezničke trupe na \"Dan D\"? ","Dwight Eisenhower","Fitzroy Maclean","George Patton","Douglas MacArthur","Dwight Eisenhower");
        this.addQuestionPovijest(q17);
        Povijest q18=new Povijest("Koje godine je Hitler postao kancelar Njemačke? ","1931.","1932.","1933.","1934.","1933.");
        this.addQuestionPovijest(q18);
        Povijest q19=new Povijest("Čiji teritorij nije u potpunosti okupiralo Rimsko carstvo? ","Njemačke","Španjolske","Grčke","Francuske","Njemačke");
        this.addQuestionPovijest(q19);



    }

    public List<String> ParseFile(String filename) // filename triba bit tipa "raw/ime.text" u res/raw folderu
    {
        Log.w("asd", "test");
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
        Log.w("asd", "test2");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        List<String> list = new ArrayList<String>(); // lista stringova
        String str; // string u kojem je pitanje (linija text filea)
        //String[] separated;  // array u kojem ce prvi clan bit pitanje, sljedeca 4 odgovori

        try {
            while ((str = reader.readLine()) != null)
            {
                Log.w("asd", str);
                list.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*for (int i=0; i<list.size(); i++)
        {
            separated = list.get(i).split("|");
        }*/

        return list;
    }




    private void addQuestionsSport()
    {
        Sport q1=new Sport("Tko je osvojio svjetsko nogometno prvenstvo 2010?","Španjolska","Francuska","Brazil","Engleska","Španjolska");
        this.addQuestionSport(q1);

        Sport q2=new Sport("Gdje je održano prvo svjetsko nogometno prvenstvo?", "U Urugvaju", "U Portugalu", "U Francuskoj","U Rusiji", "U Urugvaju");
        this.addQuestionSport(q2);

        Sport q3=new Sport("Gdje su održane ljetne olimpijske igre 2012. godine?","U Londonu", "U Parizu","U Berlinu","U Moskvi","U Londonu");
        this.addQuestionSport(q3);

        Sport q4=new Sport("Koliko naslova prvaka NBA lige je osvojio Michael Jordan?",    "2","3", "5", "6","6");
        this.addQuestionSport(q4);
        Sport q5=new Sport("Koliko se teniskih Grand Slam turnira igra svake godine?","3","4","5","7","4");
        this.addQuestionSport(q5);
        Sport q6=new Sport("Na kojoj visini stoji košarkaški obruč","300 cm","305 cm","309 cm","310 cm","305 cm");
        this.addQuestionSport(q6);
        Sport q7=new Sport("Ime stadiona na kojem Real Madrid igra domaće utakmice je:","Santiago Bernabéu","Giuseppe Meazza","Maracana","Gaston Petit","Santiago Bernabéu");
        this.addQuestionSport(q7);
        Sport q8=new Sport("Koje nacionalnosti je tenisač Rafael Nadal?","Čileanac","Argentinac","Meksikanac","Španjolac","Španjolac");
        this.addQuestionSport(q8);
        Sport q9=new Sport("Koje godine je poginuo Ayrton Senna?","1993.","1994.","1995.","1994.","1994.");
        this.addQuestionSport(q9);
        Sport q10=new Sport("Goran Ivanišević triput je poražen u finalu Wimbledona. Dvaput ga je porazio Pete Sampras i jedanput...? ","Jim Courier","Stefan Edberg","Andre Agassi","Boris Becker","Andre Agassi");
        this.addQuestionSport(q10);
        Sport q11=new Sport("Tko je osvojio svjetsko nogometno prvenstvo 2002. godine? ","Brazil","Francuska","Njemačka","Engleska","Brazil");
        this.addQuestionSport(q11);
        Sport q12=new Sport("U kojem gradu se nalazi stadion Millenium?","Cardiff","Dublin","Birmingham","Glasgow","Cardiff");
        this.addQuestionSport(q12);
        Sport q13=new Sport("Koji tim je izbacio Englesku u četvrtfinalu Svjetskog prvenstva 2002. godine?","Danska","Švedska","Francuska","Brazil","Brazil");
        this.addQuestionSport(q13);
        Sport q14=new Sport("Koji tim igra na Allianz areni?","Hertha","Nurnberg","Bayern","Werder","Bayern");
        this.addQuestionSport(q14);

    }



    private void addQuestionsZemljopis()
    {
        Zemljopis q1=new Zemljopis("Koji je glavni grad Brazila?","Rio de Janeiro","Brazilia","Sao Paulo","Recife","Brazilia");
        this.addQuestionZemljopis(q1);

        Zemljopis q2=new Zemljopis("Glavni grad Portugala je:", "Lisabon", "Coimbra","Porto", "Braga", "Lisabon");
        this.addQuestionZemljopis(q2);

        Zemljopis q3=new Zemljopis("Glavni grad Ukrajine je:","Kijev","Volgograd", "Riga","Helsinki","Kijev");
        this.addQuestionZemljopis(q3);

        Zemljopis q4=new Zemljopis("Površinom najveća zemlja Europe je:",    "Francuska","Njemačka", "Ukrajina", "Španjolska","Ukrajina");
        this.addQuestionZemljopis(q4);
        Zemljopis q5=new Zemljopis("Koja od ovih država leži na ekvatoru?","Čile","Meksiko","Argentina","Brazil","Brazil");
        this.addQuestionZemljopis(q5);
        Zemljopis q6=new Zemljopis("Na granici kojih država se nalazi Mt. Everest?","Nepala i Indije","Nepala i Kine","Kine i Mongolije","Indije i Pakistana","Nepala i Kine");
        this.addQuestionZemljopis(q6);
        Zemljopis q7=new Zemljopis("Najveći otok u Mediteranskom moru je:","Korzika","Cipar","Sardinija","Sicilija","Sicilija");
        this.addQuestionZemljopis(q7);
        Zemljopis q8=new Zemljopis("Lagos je glavni grad:","Nigerije","Tunisa","Maroka","Čada","Nigerije");
        this.addQuestionZemljopis(q8);
        Zemljopis q9=new Zemljopis("Glavni grad Bjelorusije je:","Riga","Donjeck","Kazan","Minsk","Minsk");
        this.addQuestionZemljopis(q9);

    }

    private void addQuestionsZnanost()
    {
        List<String> questionlist = new ArrayList<String>(); // lista pitanja i odgovora
        questionlist = ParseFile("res/raw/znanost.txt");   // ime text filea
        String[] separated;  // array u kojem ce prvi clan bit pitanje, sljedeca 4 odgovori
        for (int i=0; i<questionlist.size(); i++)
        {
            separated = questionlist.get(i).split(",");
            //Log.w("q", separated[0] + separated[1] + separated[2] + separated[3] + separated[4] + separated[5]);
            Znanost q = new Znanost(separated[0], separated[1], separated[2], separated[3], separated[4], separated[5]);
            this.addQuestionZnanost(q);
        }

        Znanost q1=new Znanost("Koliko kromosoma ima ljudska stanica?","16","46","52","98","46");
        this.addQuestionZnanost(q1);

        Znanost q2=new Znanost("Koje dvije boje miješanjem daju ljubičastu?", "Crvena i plava", "Žuta i zelena", "Žuta i plava", "Zelena i žuta","Crvena i plava" );
        this.addQuestionZnanost(q2);

        Znanost q3=new Znanost("Koliko kuteva ima heksagon","3", "4","5","6","6");
        this.addQuestionZnanost(q3);

        Znanost q4=new Znanost("Kako se zove najduža stranica pravokutnog " +
                "trokuta?",    "Kateta", "Hipotenuza", "Tangenta","Normala","Hipotenuza");
        this.addQuestionZnanost(q4);
        Znanost q5=new Znanost("Kvadratni korijen iz 2 iznosi:","1.4343","1.4521","1.4565","1.4142","1.4142");
        this.addQuestionZnanost(q5);
        Znanost q6=new Znanost("Prvu bateriju izumio je:","Thomas Edison","Nikola Tesla","Alessandro Volta","Benjamin Franklin","Alessandro Volta");
        this.addQuestionZnanost(q6);
        Znanost q7=new Znanost("Zbroj stupnjeva u trokutu iznosi: ","150°","180°","210°","240°","180°");
        this.addQuestionZnanost(q7);

    }

    private void addQuestionsFilm()
    {
        Film q1=new Film("Koja glumica igra glavnu žensku ulogu u filmu \"Titanic\"?","Cameron Diaz","Julia Roberts","Natalie Portman","Kate Winslet","Kate Winslet");
        this.addQuestionFilm(q1);

        Film q2=new Film("Glavnu mušku ulogu u filmu \"The Shawshank Redemption igra\": ", "Tim Robbins", "Mel Gibson", "Brad Pitt", "Bob Gunton","Tim Robbins" );
        this.addQuestionFilm(q2);

        Film q3=new Film("Glavnu mušku ulogu u filmu Schindlerova lista igra:","Harrison Ford","Roger Moore", "Liam Neeson","Morgan Freeman","Liam Neeson");
        this.addQuestionFilm(q3);

        Film q4=new Film("Koji od ovih filmova nije djelo Quentina Tarantina",    "Kill Bill", "Pulp Fiction", "Seven","Django","Seven");
        this.addQuestionFilm(q4);
        Film q5=new Film("Koliko Oscara je osvojio filmski hit iz 1994. - \"Forrest Gump\"?","4","5","6","7","6");
        this.addQuestionFilm(q5);
        Film q6=new Film("Kako se zove fikcijski grad u kojem živi Superman?","Old York","Gotham City","Metropolis","Megalopolis","Metropolis");
        this.addQuestionFilm(q6);
        Film q7=new Film("U kojem ratu se odigrava radnja filma \"Saving Private Ryan?\"","Drugi svjetski rat","Korejski rat","Vijetnamski rat","Prvi svjetski rat","Drugi svjetski rat");
        this.addQuestionFilm(q7);
        Film q8=new Film("Ko je igrao James Bonda u filmu \"Diamonds are forever\"?","Sean Conery","Roger Moore","George Lazenby","Timothy Dalton","Sean Conery");
        this.addQuestionFilm(q8);
        Film q9=new Film("U kojoj zemlji je rođen Arnold Schwarzenegger?","U SAD-u","U Njemačkoj","U Austriji","U Nizozemskoj","U Austriji");
        this.addQuestionFilm(q9);
        Film q10=new Film("Sa koliko godina je u prometnoj nesreći poginio James Dean?","22","24","26","28","24");
        this.addQuestionFilm(q10);
        Film q11=new Film("Koji automobil vozi 007 u filmu Tomorrow Never Dies?","BMW","Aston Martin","Jaguar","Mercedes","BMW");
        this.addQuestionFilm(q11);

    }

    private void addQuestionsGlazba()
    {
        List<String> questionlist = new ArrayList<String>(); // lista pitanja i odgovora
        questionlist = ParseFile("res/raw/glazba.txt");   // ime text filea
        String[] separated;  // array u kojem ce prvi clan bit pitanje, sljedeca 4 odgovori
        for (int i=0; i<questionlist.size(); i++)
        {
            separated = questionlist.get(i).split(",");
            //Log.w("q", separated[0] + separated[1] + separated[2] + separated[3] + separated[4] + separated[5]);
            Glazba q = new Glazba(separated[0], separated[1], separated[2], separated[3], separated[4], separated[5]);
            this.addQuestionGlazba(q);
        }

        Glazba q1=new Glazba("Autor hitova \"Billie Jean\" i \"Thriller\" je:","Elvis Presley","Frank Sinatra","Jimi Hendrix","Michael Jackson","Michael Jackson");
        this.addQuestionGlazba(q1);
        Glazba q2=new Glazba("Tko je napisao \"Malu noćnu muziku\"?", "Mozart", "Schubert", "Handel", "Haydn","Mozart");
        this.addQuestionGlazba(q2);
        Glazba q3=new Glazba("Poznata operna kuća La Scala nalazi se u:","U Firenci", "U Milanu", "U Rimu","U Genovi","U Milanu");
        this.addQuestionGlazba(q3);
        Glazba q4=new Glazba("Koju svoju simfoniju je Beethoven prvotno posvetio Napoleonu?","Drugu", "Treću", "Petu","Šestu","Treću");
        this.addQuestionGlazba(q4);
        Glazba q5=new Glazba("Koju je poznatu skladbu Bob Dylan napisao za vestern Sama Packinpaha \"Pat Garret i Billy the Kid\"? ","Knockin' on Heaven's Door", "Like a Rolling Stone", "Blowin' in the Wind","Subterranean Hoimesick Blues","Knockin' on Heaven's Door");
        this.addQuestionGlazba(q5);

    }

    private void addQuestionsIT()
    {
        IT q0=new IT("Tko se smatra ocem umjetne inteligencije?","Alan Turing","George Boole","Julije Ožegović","John McCharthy","John McCharthy");
        this.addQuestionIT(q0);
        IT q1=new IT("Što označava kratica TCP?","Transmission control program","Transmission control protocol","Total control protocol","Total control program","Transmission control protocol");
        this.addQuestionIT(q1);
        IT q2=new IT("Jedan bajt sadrži:","4 bita","8 bita","12 bita","16 bita","8 bita");
        this.addQuestionIT(q2);
        IT q3=new IT("Izbaci uljeza:","Microsoft","Windows","IBM","Apple","Windows");
        this.addQuestionIT(q3);
        IT q4=new IT("Izbaci uljeza:","Java","C","C++","Asembler","Asembler");
        this.addQuestionIT(q4);
        IT q5=new IT("Što je od ovoga najmanje?","Kilobajt","Megabajt","Terabajt","Gigabajt","Kilobajt");
        this.addQuestionIT(q5);
        IT q6=new IT("Tko je izumio i patentirao telefon?","Thomas Edison","George Boole","Nikola Tesla","Graham Bell","Graham Bell");
        this.addQuestionIT(q6);
        IT q7=new IT("Koje je godine osnovana tvrtka Microsoft?","1965","1973","1975","1978","1975");
        this.addQuestionIT(q7);
        IT q8=new IT("Koliko se bita koristi u IPv6 adresi?","32","64","128","256","128");
        this.addQuestionIT(q8);
        IT q9=new IT("Koji od pojmova nije operativni sustav?","DOS","Mac","C","Linux","C");
        this.addQuestionIT(q9);
        IT q10=new IT("Koliko slojeva sadrži OSI model?","9","3","6","7","7");
        this.addQuestionIT(q10);
        IT q11=new IT("Koja je tvrtka razvila operativni sustav iOS?","Apple","IBM","Microsoft","Google","Apple");
        this.addQuestionIT(q11);
        IT q12=new IT("Podatke kojeg tipa označava ekstenzija .jpg?","Slike","Sistemske podatke","Filmove","Tekst","Slike");
        this.addQuestionIT(q12);
        IT q13=new IT("Kako se zvao prvi kompjuterski virus","Rabbit","Creeper Virus","Elk Cloner","SCA Virus","Creeper Virus");
        this.addQuestionIT(q13);
        IT q14=new IT("Koji se programski jezik najčešće koristi za umjetnu inteligenciju?","Java","C","Prolog","Ruby","Prolog");
        this.addQuestionIT(q14);

    }


    //POZIVA SE KAD SE PROMIJENI VERZIJA DB, ZATIM SE OPET POZIVA ONCREATE I RADI PONOVNO PUNJENJE TABLICA
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POVIJEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPORT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZEMLJOPIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZNANOST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GLAZBA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IT);
// Create tables again
        onCreate(db);
    }

    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////DODAVANJE NOVIH PITANJA U BAZU PODATAKA/////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////


    ///////////////////////DODAJ PITANJE U POVIJEST TABLICU////////////////////////
    public void addQuestionPovijest(Povijest quest) {//STAVLJAJ U TABLICU NOVE RETKE
        /////////////////////PRIMI NOVOSTVORENI OBJEKT IZ ADDQUESTIONS
 //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());
// Inserting Row
        dbase.insert(TABLE_POVIJEST, null, values);
        //db.insert(TABLE_POVIJEST,null, values);
        Log.d("dodano novo", "");
        //db.close();


    }

    public void addQuestionSport(Sport quest) {//STAVLJAJ U TABLICU NOVE RETKE
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());
// Inserting Row
        dbase.insert(TABLE_SPORT, null, values);

    }


    ////////////////////////DODAJ PITANJE U ZEMLJOPIS TABLICU
    public void addQuestionZemljopis(Zemljopis quest) {//STAVLJAJ U TABLICU NOVE RETKE
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());
// Inserting Row
        dbase.insert(TABLE_ZEMLJOPIS, null, values);


    }

    ////////////////////////DODAJ PITANJE U ZEMLJOPIS TABLICU
    public void addQuestionZnanost(Znanost quest) {//STAVLJAJ U TABLICU NOVE RETKE
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());
// Inserting Row
        dbase.insert(TABLE_ZNANOST, null, values);


    }

    public void addQuestionFilm(Film quest) {//STAVLJAJ U TABLICU NOVE RETKE
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());
// Inserting Row
        dbase.insert(TABLE_FILM, null, values);


    }

    public void addQuestionGlazba(Glazba quest) {//STAVLJAJ U TABLICU NOVE RETKE
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());
// Inserting Row
        dbase.insert(TABLE_GLAZBA, null, values);


    }

    public void addQuestionIT(IT quest) {//STAVLJAJ U TABLICU NOVE RETKE
        // dbase = this.getWritableDatabase();
        Log.d("program usao u addq", " IT");
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());
// Inserting Row
        dbase.insert(TABLE_IT, null, values);
      //  dbase.close();


    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////SELECT UPITI/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    ////////////////////SELECT ALL QUERY IZ TABLICE POVIJEST
    public List<Povijest> getAllQuestionsPovijest() {
        List<Povijest> quesList = new ArrayList<Povijest>();

        String selectQuery = "SELECT  * FROM " + TABLE_POVIJEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
/////////////STVORI LISTU, PROŠETAJ SA WHILE KROZ TABLICU,NAPUNI LISTU OBJEKTIMA IZ TABLICE,VRATI LISTU
        if (cursor.moveToFirst()) {
            do {
                Povijest quest = new Povijest();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        return quesList;
    }
    /////////////////////////////////////////SELECT ALL QUERY SPORT
    public List<Sport> getAllQuestionsSport() {
        List<Sport> quesList = new ArrayList<Sport>();

        String selectQuery = "SELECT  * FROM " + TABLE_SPORT;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Sport quest = new Sport();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }

        return quesList;
    }


    /////////////////////////////////////////SELECT ALL QUERY ZEMLJOPIS
    public List<Zemljopis> getAllQuestionsZemljopis() {
        List<Zemljopis> quesList = new ArrayList<Zemljopis>();

        String selectQuery = "SELECT  * FROM " + TABLE_ZEMLJOPIS;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Zemljopis quest = new Zemljopis();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return quest list
        return quesList;
    }

    public List<Znanost> getAllQuestionsZnanost() {
        List<Znanost> quesList = new ArrayList<Znanost>();

        String selectQuery = "SELECT  * FROM " + TABLE_ZNANOST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Znanost quest = new Znanost();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return quest list
        return quesList;
    }

    public List<Film> getAllQuestionsFilm() {
        List<Film> quesList = new ArrayList<Film>();

        String selectQuery = "SELECT  * FROM " + TABLE_FILM;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Film quest = new Film();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return quest list
        return quesList;
    }

    public List<Glazba> getAllQuestionsGlazba() {
        List<Glazba> quesList = new ArrayList<Glazba>();

        String selectQuery = "SELECT  * FROM " + TABLE_GLAZBA;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Glazba quest = new Glazba();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return quest list
        return quesList;
    }

    public List<IT> getAllQuestionsIT() {
        List<IT> quesList = new ArrayList<IT>();

        String selectQuery = "SELECT  * FROM " + TABLE_IT;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                IT quest = new IT();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return quest list
        return quesList;
    }



    public  void deleteQuestion(Povijest quest)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POVIJEST,KEY_ID + " = ?",
                new String[] { String.valueOf(quest.getID())});
        db.close();
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_POVIJEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}

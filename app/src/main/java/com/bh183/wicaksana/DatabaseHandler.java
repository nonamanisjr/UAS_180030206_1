package com.bh183.wicaksana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 3;
    private final static String DATABASE_NAME = "db_music";
    private final static String TABLE_LAGU = "t_lagu";
    private final static String KEY_ID_LAGU = "ID_Lagu";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_ARTIST = "Artist";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_ALBUM = "Album";
    private final static String KEY_LIRIK_LAGU = "Lirik_Lagu";
    private final static String KEY_LINK = "Link";
    private Context context;

    public DatabaseHandler(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_LAGU = "CREATE TABLE " + TABLE_LAGU
                + "(" + KEY_ID_LAGU + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_JUDUL + " TEXT, " + KEY_ARTIST + " TEXT, "
                + KEY_GAMBAR + " TEXT, " + KEY_ALBUM + " TEXT, "
                + KEY_LIRIK_LAGU + " TEXT, " + KEY_LINK + " TEXT);";

        db.execSQL(CREATE_TABLE_LAGU);
        inisialisasiLaguAwal(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_LAGU;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahLagu(Lagu dataLagu) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataLagu.getJudul());
        cv.put(KEY_ARTIST, dataLagu.getArtist());
        cv.put(KEY_GAMBAR, dataLagu.getGambar());
        cv.put(KEY_ALBUM, dataLagu.getAlbum());
        cv.put(KEY_LIRIK_LAGU, dataLagu.getLirikLagu());
        cv.put(KEY_LINK, dataLagu.getLink());

        db.insert(TABLE_LAGU, null, cv);
        db.close();
    }

    public void tambahLagu(Lagu dataLagu, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataLagu.getJudul());
        cv.put(KEY_ARTIST, dataLagu.getArtist());
        cv.put(KEY_GAMBAR, dataLagu.getGambar());
        cv.put(KEY_ALBUM, dataLagu.getAlbum());
        cv.put(KEY_LIRIK_LAGU, dataLagu.getLirikLagu());
        cv.put(KEY_LINK, dataLagu.getLink());

        db.insert(TABLE_LAGU, null, cv);
    }

    public void editLagu(Lagu dataLagu) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataLagu.getJudul());
        cv.put(KEY_ARTIST, dataLagu.getArtist());
        cv.put(KEY_GAMBAR, dataLagu.getGambar());
        cv.put(KEY_ALBUM, dataLagu.getAlbum());
        cv.put(KEY_LIRIK_LAGU, dataLagu.getLirikLagu());
        cv.put(KEY_LINK, dataLagu.getLink());

        db.update(TABLE_LAGU, cv, KEY_ID_LAGU + "=?", new String[]{String.valueOf(dataLagu.getIdLagu())});

        db.close();
    }

    public void hapusLagu(int idLagu) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_LAGU, KEY_ID_LAGU + "=?", new String[]{String.valueOf(idLagu)});
        db.close();
    }

    public ArrayList<Lagu> getAllLagu() {
        ArrayList<Lagu> dataLagu = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_LAGU;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if (csr.moveToFirst()) {
            do {
                Lagu tempLagu = new Lagu(
                        csr.getInt(0),
                        csr.getString(1),
                        csr.getString(2),
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6)
                );

                dataLagu.add(tempLagu);
            } while (csr.moveToNext());
        }

        return dataLagu;
    }

    private String storeImageFile(int id) {
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiLaguAwal(SQLiteDatabase db) {
        int idLagu = 0;

        //Data Lagu ke 1
        Lagu lagu1 = new Lagu(
                idLagu,
                "Lovely",
                "Billie Eilish and Khalid",
                storeImageFile(R.drawable.lagu1),
                "13 Reasons Why: Season 2",
                "Thought I found a way\n" +
                        "Thought I found a way out (found)\n" +
                        "But you never go away (never go away)\n" +
                        "So I guess I gotta stay now\n" +
                        "\n" +
                        "Oh, I hope someday I'll make it out of here\n" +
                        "Even if it takes all night or a hundred years\n" +
                        "Need a place to hide, but I can't find one near\n" +
                        "Wanna feel alive, outside I can't fight my fear\n" +
                        "\n" +
                        "Isn't it lovely? All alone\n" +
                        "Heart made of glass, my mind of stone\n" +
                        "Tear me to pieces, skin to bone\n" +
                        "Hello, welcome home\n" +
                        "\n" +
                        "Walking out of time\n" +
                        "Looking for a better place (looking for a better place)\n" +
                        "Something's on my mind\n" +
                        "Always in my head space\n" +
                        "\n" +
                        "But I know someday I'll make it out of here\n" +
                        "Even if it takes all night or a hundred years\n" +
                        "Need a place to hide, but I can't find one near\n" +
                        "Wanna feel alive outside I can't fight my fear\n" +
                        "\n" +
                        "Isn't it lovely? All alone\n" +
                        "Heart made of glass, my mind of stone\n" +
                        "Tear me to pieces, skin to bone\n" +
                        "Hello. Welcome home\n" +
                        "\n" +
                        "Whoa, yeah\n" +
                        "Yeah, ah\n" +
                        "Whoa, whoa\n" +
                        "Hello, welcome home",
                "https://en.wikipedia.org/wiki/Lovely_(Billie_Eilish_and_Khalid_song)"
        );

        tambahLagu(lagu1, db);
        idLagu++;

        // Data Lagu ke 2
        Lagu lagu2 = new Lagu(
                idLagu,
                "Memories",
                "Maroon 5",
                storeImageFile(R.drawable.lagu2),
                "Memories",
                "Here's to the ones that we got\n" +
                        "Cheers to the wish you were here, but you're not\n" +
                        "'Cause the drinks bring back all the memories\n" +
                        "Of everything we've been through\n" +
                        "Toast to the ones here today\n" +
                        "Toast to the ones that we lost on the way\n" +
                        "'Cause the drinks bring back all the memories\n" +
                        "And the memories bring back, memories bring back you\n" +
                        "\n" +
                        "There's a time that I remember, when I did not know no pain\n" +
                        "When I believed in forever, and everything would stay the same\n" +
                        "Now my heart feel like December when somebody say your name\n" +
                        "'Cause I can't reach out to call you, but I know I will one day, yeah\n" +
                        "\n" +
                        "Everybody hurts sometimes\n" +
                        "Everybody hurts someday, ayy ayy\n" +
                        "But everything gon' be alright\n" +
                        "Go and raise a glass and say, ayy\n" +
                        "\n" +
                        "Here's to the ones that we got\n" +
                        "Cheers to the wish you were…",
                "https://en.wikipedia.org/wiki/Memories_(Maroon_5_song)"
        );

        tambahLagu(lagu2, db);
        idLagu++;

        // Data Lagu ke 3
        Lagu lagu3 = new Lagu(
                idLagu,
                "Intentions",
                "Justin Bieber ft. Quavo",
                storeImageFile(R.drawable.lagu3),
                "Changes",
                "Picture perfect, you don't need no filter\n" +
                        "Gorgeous, make 'em drop dead, you a killer\n" +
                        "Shower you with all my attention\n" +
                        "Yeah, these are my only intentions\n" +
                        "Stay in the kitchen, cooking up, got your own bread\n" +
                        "Heart full of equity, you're an asset\n" +
                        "Make sure that you don't need no mentions\n" +
                        "Yeah, these are my only intentions\n" +
                        "\n" +
                        "Shout out to your mom and dad for making you\n" +
                        "Standing ovation, they did a great job raising you\n" +
                        "When I create, you're my muse\n" +
                        "That kind of smile that makes the news\n" +
                        "\n" +
                        "Can't nobody throw shade on your name in these streets\n" +
                        "Triple threat, you a boss, you a bae, you a beast\n" +
                        "You make it easy to choose\n" +
                        "You got a mean touch I can't refuse (No, I can't refuse it)\n" +
                        "\n" +
                        "Picture perfect, you don't need no filter\n" +
                        "Gorgeous, make 'em drop dead, you a killer\n" +
                        "Shower you with all my attention\n" +
                        "Yeah, these are my only intentions\n" +
                        "Stay in the kitchen, cooking up, got your own bread\n" +
                        "Heart full of equity, you're an asset\n" +
                        "Make sure that you don't need no mentions\n" +
                        "Yeah, these are my only intentions\n" +
                        "\n" +
                        "Already pass, you don't need no approval\n" +
                        "Good everywhere, don't worry 'bout no refusal\n" +
                        "Second to none, you got the upper hand now\n" +
                        "Don't need a sponsor, nope, you're the brand now\n" +
                        "\n" +
                        "You're my rock, my Colorado\n" +
                        "Get that ring, just like Toronto\n" +
                        "Love you now, a little more tomorrow\n" +
                        "This how I feel\n" +
                        "Act like you know that you are\n" +
                        "\n" +
                        "Picture perfect, you don't need no filter\n" +
                        "Gorgeous, make 'em drop dead, you a killer\n" +
                        "Shower you with all my attention\n" +
                        "Yeah, these are my only intentions\n" +
                        "Stay in the kitchen, cooking up, got your own bread (Whip it)\n" +
                        "Heart full of equity, you're an asset (Asset)\n" +
                        "Make sure that you don't need no mentions (Yeah, yeah)\n" +
                        "Yeah, these are my only intentions (Quavo)\n" +
                        "\n" +
                        "No cap, no pretending\n" +
                        "You don't need mentions (No cap)\n" +
                        "Got 'em sayin' goals, they don't wanna be independent ('Pendent)\n" +
                        "Tell them to mind your business\n" +
                        "We in our feelings\n" +
                        "It's fifty-fifty percentage (Fifty)\n" +
                        "Attention, we need commitment\n" +
                        "We gotta both admit it (Both)\n" +
                        "It's funny we both listen (Both)\n" +
                        "It's a blessing 'cause we both get it (Both)\n" +
                        "You the best thing and I don't need a witness (Best thing)\n" +
                        "I'ma find me a ring and pray it's perfect fitted (Perfect)\n" +
                        "\n" +
                        "Picture perfect, you don't need no filter (No filter)\n" +
                        "Gorgeous, make 'em drop dead, you a killer (Oh)\n" +
                        "Shower you with all my attention (I will)\n" +
                        "Yeah, these are my only intentions (Yeah)\n" +
                        "\n" +
                        "Stay in the kitchen, cooking up, got your own bread (You do)\n" +
                        "Heart full of equity, you're an asset (Uh huh)\n" +
                        "Make sure that you don't need no mentions (No mentions)\n" +
                        "Yeah, these are my only intentions (Only intentions)\n" +
                        "(That's all I plan to do, ooh)",
                "https://en.wikipedia.org/wiki/Intentions_(Justin_Bieber_song)"
        );

        tambahLagu(lagu3, db);
        idLagu++;

        // Data Lagu ke 4
        Lagu lagu4 = new Lagu(
                idLagu,
                "Cinta Luar Biasa",
                "Andmesh Kamaleng",
                storeImageFile(R.drawable.lagu4),
                "Cinta Luar Biasa",
                "Waktu pertama kali\n" +
                        "Kulihat dirimu hadir\n" +
                        "Rasa hati ini inginkan dirimu\n" +
                        "Hati tenang mendengar\n" +
                        "\n" +
                        "Suara indah menyapa geloranya hati ini tak ku sangka\n" +
                        "Rasa initaktertahan\n" +
                        "Hati ini selalu untukmu\n" +
                        "Terimalah lagu ini dari orang biasa\n" +
                        "Tapi cintaku padamu luar biasa\n" +
                        "\n" +
                        "Aku tak punya bunga\n" +
                        "Aku tak punya harta\n" +
                        "Yang kupunya hanyalah hati yang setia tulus padamu\n" +
                        "Du... Du... Du... Hari hari berganti\n" +
                        "\n" +
                        "Kini cintapun hadir\n" +
                        "Melihatmu, memandangmu bagai bidadari\n" +
                        "Lentik indah matamu\n" +
                        "Manis senyum bibirmu\n" +
                        "Hitam panjang rambutmu anggung terikat\n" +
                        "\n" +
                        "Rasa ini tak tertahan\n" +
                        "Hati ini slalu untukmu\n" +
                        "Terimalah lagu ini\n" +
                        "Dari orang biasa\n" +
                        "Tapi cintaku padamu luar biasa\n" +
                        "\n" +
                        "Aku tak punya bunga\n" +
                        "Aku tak punya harta\n" +
                        "Yang kupunya hanyalah hati yang setia tulus padamuu. Ooooooo\n" +
                        "Terimalah lagu ini. Hmm...\n" +
                        "\n" +
                        "Dari orang biasa\n" +
                        "Terimalah lagu ini\n" +
                        "Dari orang biasa\n" +
                        "Tapi cinta ku padamu luar biasa\n" +
                        "\n" +
                        "Aku tak punya bunga\n" +
                        "Aku tak punya harta\n" +
                        "Yang kupunya hanyalah hati yang setia\n" +
                        "Yang kupunya hanyalah hati yang setia\n" +
                        "Terimalah cintaku yang luar biasa tulus padamu",
                "https://id.wikipedia.org/wiki/Cinta_Luar_Biasa_(lagu)"
        );

        tambahLagu(lagu4, db);
        idLagu++;

        // Data Lagu ke 5
        Lagu lagu5 = new Lagu(
                idLagu,
                "Menghapus Jejakmu",
                "Peterpan",
                storeImageFile(R.drawable.lagu5),
                "Hari Yang Cerah",
                "Terus melangkah melupakanmu\n" +
                        "Belah hati perhatikan sikapmu\n" +
                        "Jalan pikiranmu buatku ragu\n" +
                        "Tak mungkin ini tetap bertahan\n" +
                        "\n" +
                        "Perlahan mimpi terasa mengganggu\n" +
                        "Kucoba untuk terus menjauh\n" +
                        "Perlahan hatiku terbelenggu\n" +
                        "Kucoba untuk lanjutkan itu\n" +
                        "\n" +
                        "Engkau bukanlah segalaku\n" +
                        "Bukan tempat tuk hentikan langkahku\n" +
                        "Usai sudah semua berlalu\n" +
                        "Biar hujan menghapus jejakmu\n" +
                        "\n" +
                        "Terus melangkah melupakanmu\n" +
                        "Belah hati perhatikan sikapmu\n" +
                        "Jalan pikiranmu buatku ragu\n" +
                        "Tak mungkin ini tetap bertahan\n" +
                        "\n" +
                        "Perlahan mimpi terasa mengganggu\n" +
                        "Kucoba untuk terus menjauh\n" +
                        "Perlahan hatiku terbelenggu\n" +
                        "Kucoba untuk lanjutkan itu\n" +
                        "\n" +
                        "Engkau bukanlah segalaku\n" +
                        "Bukan tempat tuk hentikan langkahku\n" +
                        "Usai sudah semua berlalu\n" +
                        "Biar hujan menghapus jejakmu\n" +
                        "\n" +
                        "Lepaskan segalanya\n" +
                        "Lepaskan segalanya\n" +
                        "\n" +
                        "Engkau bukanlah segalaku\n" +
                        "Bukan tempat tuk hentikan langkahku\n" +
                        "Usai sudah semua berlalu\n" +
                        "Biar hujan menghapus jejakmu\n" +
                        "\n" +
                        "Nanananana\n" +
                        "Nanananana\n" +
                        "Nanananana\n" +
                        "Nanananana\n" +
                        "Nanananana\n" +
                        "Nanananana",
                "https://en.wikipedia.org/wiki/Hari_Yang_Cerah"
        );

        tambahLagu(lagu5, db);
        idLagu++;

        // Data Lagu ke 6
        Lagu lagu6 = new Lagu(
                idLagu,
                "Despacito",
                "Luis Fonsi ft. Daddy Yankee",
                storeImageFile(R.drawable.lagu6),
                "Vida",
                "Ay\n" +
                        "Fonsi, DY\n" +
                        "Oh, oh no, oh no (ey)\n" +
                        "Hey, yeah, diri-diri-diriridi, Daddy, go!\n" +
                        "\n" +
                        "Sí, sabes que ya llevo un rato mirándote\n" +
                        "Tengo que bailar contigo hoy (DY)\n" +
                        "Vi que tu mirada ya estaba llamándome\n" +
                        "Muéstrame el camino, que yo voy (oh)\n" +
                        "\n" +
                        "Tú, tú eres el imán y yo soy el metal\n" +
                        "Me voy acercando y voy armando el plan\n" +
                        "Sólo con pensarlo se acelera el pulso (oh, yeah)\n" +
                        "\n" +
                        "Ya, ya me estás gustando más de lo normal\n" +
                        "Todos mis sentidos van pidiendo más\n" +
                        "Esto hay que tomarlo sin ningún apuro\n" +
                        "\n" +
                        "Despacito\n" +
                        "Quiero respirar tu cuello despacito\n" +
                        "Deja que te diga cosas al oído\n" +
                        "Para que te acuerdes si no estás conmigo\n" +
                        "\n" +
                        "Despacito\n" +
                        "Quiero desnudarte a besos despacito\n" +
                        "Firmar las paredes de tu laberinto\n" +
                        "Y hacer de tu cuerpo todo un manuscrito (sube, sube, sube)\n" +
                        "Sube, sube (oh)\n" +
                        "\n" +
                        "Quiero ver bailar tu pelo, quiero ser tu ritmo\n" +
                        "Que le enseñes a mi boca tus lugares favoritos\n" +
                        "(Favoritos, favoritos, baby)\n" +
                        "Déjame sobrepasar tus zonas de peligro\n" +
                        "Hasta provocar tus gritos y que olvides tu apellido\n" +
                        "\n" +
                        "Si te pido un beso, ven, dámelo\n" +
                        "Yo sé que estás pensándolo\n" +
                        "Llevo tiempo intentándolo\n" +
                        "Mami, esto es dando y dándolo\n" +
                        "\n" +
                        "Sabes que tu corazón conmigo te hace \"bam-bam\"\n" +
                        "Sabes que esa beba está buscando de mi \"bam-bam\"\n" +
                        "\n" +
                        "Ven, prueba de mi boca para ver cómo te sabe\n" +
                        "Quiero, quiero, quiero ver cuánto amor a ti te cabe\n" +
                        "Yo no tengo prisa, yo me quiero dar el viaje\n" +
                        "Empezamos lento, después salvaje\n" +
                        "\n" +
                        "Pasito a pasito, suave, suavecito\n" +
                        "Nos vamos pegando poquito a poquito\n" +
                        "Cuando tú me besas con esa destreza\n" +
                        "Veo que eres malicia con delicadeza\n" +
                        "\n" +
                        "Pasito a pasito, suave, suavecito\n" +
                        "Nos vamos pegando poquito a poquito\n" +
                        "Y es que esa belleza es un rompecabezas\n" +
                        "Pero pa' montarlo, aquí tengo la pieza, oye\n" +
                        "\n" +
                        "Despacito\n" +
                        "Quiero respirar tu cuello despacito\n" +
                        "Deja que te diga cosas al oído\n" +
                        "Para que te acuerdes si no estás conmigo\n" +
                        "\n" +
                        "Despacito\n" +
                        "Quiero desnudarte a besos despacito\n" +
                        "Firmar las paredes de tu laberinto\n" +
                        "Y hacer de tu cuerpo todo un manuscrito (sube, sube, sube)\n" +
                        "Sube, sube (oh)\n" +
                        "\n" +
                        "Quiero ver bailar tu pelo, quiero ser tu ritmo\n" +
                        "Que le enseñes a mi boca tus lugares favoritos\n" +
                        "(Favoritos, favoritos, baby)\n" +
                        "Déjame sobrepasar tus zonas de peligro\n" +
                        "Hasta provocar tus gritos y que olvides tu apellido\n" +
                        "\n" +
                        "Despacito\n" +
                        "Vamo' a hacerlo en una playa en Puerto Rico\n" +
                        "Hasta que las olas griten: \"¡Ay, Bendito!\"\n" +
                        "Para que mi sello se quede contigo (báilalo)\n" +
                        "\n" +
                        "Pasito a pasito, suave, suavecito\n" +
                        "Nos vamos pegando poquito a poquito\n" +
                        "Que le enseñes a mi boca tus lugares favoritos\n" +
                        "(Favoritos, favoritos, baby)\n" +
                        "\n" +
                        "Pasito a pasito, suave, suavecito\n" +
                        "Nos vamos pegando poquito a poquito\n" +
                        "Hasta provocar tus gritos y que olvides tu apellido\n" +
                        "Despacito",
                "https://id.wikipedia.org/wiki/Despacito"
        );

        tambahLagu(lagu6, db);
    }
}
